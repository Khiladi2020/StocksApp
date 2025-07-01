package com.kapil.stocks.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.kapil.stocks.adapters.StockAdapter
import com.kapil.stocks.constants.Constants
import com.kapil.stocks.databinding.ActivityStockListBinding
import com.kapil.stocks.viewmodel.STOCKS_LIST_TYPE
import com.kapil.stocks.viewmodel.StockListViewModel
import com.kapil.stocks.viewmodel.StockViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StockListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStockListBinding
    private lateinit var viewModel: StockListViewModel
    private lateinit var listType: STOCKS_LIST_TYPE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStockListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Apply insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.parent) { view, insets ->
            val statusBarInsets = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            view.setPadding(0, statusBarInsets.top, 0, 0)
            insets
        }

        listType = STOCKS_LIST_TYPE.valueOf(
            intent.getStringExtra(Constants.LIST_TYPE) ?: STOCKS_LIST_TYPE.GAINERS.toString()
        )
        Log.d(
            Constants.TAG,
            "list type recevied in stock list activity ${listType} ${intent.getStringExtra(Constants.LIST_TYPE)}"
        )

        viewModel = ViewModelProvider(this)[StockListViewModel::class.java]


        setupAppBar()
        setupRecyclerView()
        initData()
        initListeners()
    }

    private fun setupAppBar() {
        if(listType == STOCKS_LIST_TYPE.WATCHLIST){
            binding.topAppBar.title = "WatchList 1"
            return
        }
        binding.topAppBar.title =
            if (listType == STOCKS_LIST_TYPE.GAINERS) "Top Gainers" else "Top Losers"
    }

    private fun initData() {
        if (listType == STOCKS_LIST_TYPE.LOSERS) {
            viewModel.fetchStocksList(STOCKS_LIST_TYPE.LOSERS)
        } else if (listType == STOCKS_LIST_TYPE.GAINERS) {
            viewModel.fetchStocksList(STOCKS_LIST_TYPE.GAINERS)
        } else if (listType == STOCKS_LIST_TYPE.WATCHLIST) {
            viewModel.fetchWatchList(this)
        }
    }

    private fun initListeners() {
        binding.topAppBar.setNavigationOnClickListener {
            finish()
        }
        lifecycleScope.launch {
            viewModel.stockDetails.collect { data ->
                (binding.recyclerView.adapter as StockAdapter).updateData(data);
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = StockAdapter({ item ->
            navigateToDetailActivity(item.name)
        })
    }

    private fun navigateToDetailActivity(stockName: String) {
        val intent = Intent(this, StockDetailsActivity::class.java)
        intent.putExtra(Constants.COMPANY_NAME, stockName)
        startActivity(intent)
    }
}
