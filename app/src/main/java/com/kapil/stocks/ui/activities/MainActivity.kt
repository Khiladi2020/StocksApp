package com.kapil.stocks.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.kapil.stocks.R
import com.kapil.stocks.adapters.StockAdapter
import com.kapil.stocks.constants.Constants
//import com.kapil.stocks.data.fallback.StockData
import com.kapil.stocks.databinding.ActivityMainBinding
import com.kapil.stocks.viewmodel.STOCKS_LIST_TYPE
import com.kapil.stocks.viewmodel.StockListViewModel
import com.kapil.stocks.viewmodel.StockViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: StockListViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[StockListViewModel::class.java]


        // Apply insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.parent) { view, insets ->
            val statusBarInsets = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            view.setPadding(0, statusBarInsets.top, 0, 0)
            insets
        }

        setupRecyclerViews()
        initData()
        setupBottomNav()
        setupViewAllClickListeners()
    }

    private fun initData() {
        lifecycleScope.launch {
            viewModel.fetchTopGainersLosers(STOCKS_LIST_TYPE.GAINERS).collect { data ->
                (binding.recyclerGainers.adapter as StockAdapter).updateData(data.take(4));
            }
        }
        lifecycleScope.launch {
            viewModel.fetchTopGainersLosers(STOCKS_LIST_TYPE.LOSERS).collect { data ->
                (binding.recyclerLosers.adapter as StockAdapter).updateData(data.take(4));
            }
        }
    }

    private fun setupRecyclerViews() {

        binding.recyclerGainers.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerGainers.adapter = StockAdapter() { stock ->
            navigateToDetailActivity(stock.name)  // name == symbol
        }

        binding.recyclerLosers.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerLosers.adapter = StockAdapter() { stock ->
            navigateToDetailActivity(stock.name)
        }
    }

    private fun navigateToDetailActivity(stockName: String) {
        val intent = Intent(this, StockDetailsActivity::class.java)
        intent.putExtra(Constants.COMPANY_NAME, stockName)
        startActivity(intent)
    }

    private fun setupBottomNav() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> true
                R.id.nav_watchlist -> {
                    startActivity(Intent(this, WatchlistActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

//    private fun setupViewAllClickListeners() {
//        binding.viewAllGainers.setOnClickListener {
//            startActivity(Intent(this, ViewAllGainersActivity::class.java))
//        }
//
//        binding.viewAllLosers.setOnClickListener {
//            startActivity(Intent(this, ViewAllLosersActivity::class.java))
//        }
//    }
private fun setupViewAllClickListeners() {
    binding.viewAllGainers.setOnClickListener {
        val intent = Intent(this, StockListActivity::class.java)
        intent.putExtra(Constants.LIST_TYPE, STOCKS_LIST_TYPE.GAINERS.toString())
        startActivity(intent)
    }

    binding.viewAllLosers.setOnClickListener {
        val intent = Intent(this, StockListActivity::class.java)
        intent.putExtra(Constants.LIST_TYPE, STOCKS_LIST_TYPE.LOSERS.toString())
        startActivity(intent)
    }
}



}
