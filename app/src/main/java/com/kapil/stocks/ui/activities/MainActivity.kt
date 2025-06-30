package com.kapil.stocks.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.kapil.stocks.R
import com.kapil.stocks.adapters.StockAdapter
import com.kapil.stocks.constants.Constants
//import com.kapil.stocks.data.fallback.StockData
import com.kapil.stocks.databinding.ActivityMainBinding
import com.kapil.stocks.viewmodel.STOCKS_LIST_TYPE

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerViews()
        setupBottomNav()
        setupViewAllClickListeners()
    }

    private fun setupRecyclerViews() {
//        val gainers = StockData.getTopGainers().take(4)
//        val losers = StockData.getTopLosers().take(4)
//
//        binding.recyclerGainers.layoutManager = GridLayoutManager(this, 2)
//        binding.recyclerGainers.adapter = StockAdapter() { stock ->
//            openStockDetails(stock.name)  // name == symbol
//        }
//
//        binding.recyclerLosers.layoutManager = GridLayoutManager(this, 2)
//        binding.recyclerLosers.adapter = StockAdapter() { stock ->
//            openStockDetails(stock.name)
//        }
    }

    private fun openStockDetails(symbol: String) {
        val intent = Intent(this, StockDetailsActivity::class.java)
        intent.putExtra("stock_symbol", symbol)
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
