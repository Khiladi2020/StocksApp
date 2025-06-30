package com.kapil.stocks.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.kapil.stocks.adapters.StockAdapter
import com.kapil.stocks.constants.Constants
import com.kapil.stocks.databinding.ActivityStockListBinding
import com.kapil.stocks.viewmodel.ALL_STOCKS_TYPE
import com.kapil.stocks.viewmodel.StockListViewModel
import com.kapil.stocks.viewmodel.StockViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StockListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStockListBinding
    private lateinit var viewModel: StockListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStockListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[StockListViewModel::class.java]

        val isGainers = intent.getBooleanExtra("isGainers", true)
        supportActionBar?.title = if (isGainers) "Top Gainers" else "Top Losers"

        setupRecyclerView()
        initData()
        initListeners()
    }

    private fun initData() {
        viewModel.fetchStocksList(ALL_STOCKS_TYPE.GAINERS)
    }

    private fun initListeners() {
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

    private fun navigateToDetailActivity(stockName: String){
        val intent = Intent(this, StockDetailsActivity::class.java)
        intent.putExtra(Constants.COMPANY_NAME, stockName)
        startActivity(intent)
    }
}
