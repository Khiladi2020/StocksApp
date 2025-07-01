package com.kapil.stocks.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kapil.stocks.R
import com.kapil.stocks.adapters.StockAdapter
import com.kapil.stocks.adapters.WatchlistAdapter
import com.kapil.stocks.constants.Constants
import com.kapil.stocks.databinding.ActivityStockListBinding
import com.kapil.stocks.databinding.ActivityWatchlistBinding
import com.kapil.stocks.services.SharedPreferences
import com.kapil.stocks.viewmodel.STOCKS_LIST_TYPE

class WatchlistActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWatchlistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWatchlistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Apply insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.parent) { view, insets ->
            val statusBarInsets = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            view.setPadding(0, statusBarInsets.top, 0, 0)
            insets
        }

        setupRecyclerView()
        initData()
        initListeners()
    }

    private fun initListeners() {
        binding.topAppBar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun initData() {
        val watchListData = SharedPreferences.readWatchlistData(this)
        Log.d(Constants.TAG, "watchlist data ${watchListData}")
        (binding.recyclerView.adapter as WatchlistAdapter).updateData(watchListData ?: emptyList());
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = WatchlistAdapter({
            navigateToListActivity()
        })
    }

    private fun navigateToListActivity() {
        val intent = Intent(this, StockListActivity::class.java)
        intent.putExtra(Constants.LIST_TYPE, STOCKS_LIST_TYPE.WATCHLIST.toString())
        startActivity(intent)
    }
}
