package com.kapil.stocks.ui.activities

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.kapil.stocks.databinding.ActivityStockDetailBinding
import com.kapil.stocks.databinding.ItemStatBinding
import com.kapil.stocks.viewmodel.StockViewModel
import kotlinx.coroutines.launch

class StockDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: StockViewModel
    private lateinit var _binding: ActivityStockDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityStockDetailBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        viewModel = ViewModelProvider(this)[StockViewModel::class.java]

        setupListeners()
    }

    private fun setupListeners() {
        lifecycleScope.launch {
            viewModel.stockDetails.collect { data ->
                if (data != null) {
                    // Header Info
                    _binding.tvCompanyName.text = data.Name
                    _binding.tvSymbolExchange.text = "${data.Symbol}, ${data.Exchange}"
                    _binding.tvPrice.text = "$${data.PERatio}"

                    // Description
                    _binding.tvDescription.text = data.Description

                    // Tags
                    _binding.tvSector.text = "Sector: ${data.Sector}"
                    _binding.tvIndustry.text = "Industry: ${data.Industry}"

                    // Chart
                    setupChart()

                    // Grid Stats
                    //setStat("52-Week Low", "$${data._52WeekLow}")
                    setStat("Current Price", "$${data.PERatio}")
                    //setStat("52-Week High", "$${data._52WeekHigh}")
                    setStat("Market Cap", "$${data.MarketCapitalization}")
                    setStat("P/E Ratio", data.PERatio)
                    setStat("Dividend Yield", "${data.DividendYield}%")
                    setStat("Profit Margin", "${data.ProfitMargin}%")
                    setStat("EPS", "$${data.EPS}")
                }
            }
        }
    }

    private fun setStat(label: String, value: String) {
        val statBinding = ItemStatBinding.inflate(LayoutInflater.from(this))
        statBinding.tvStatLabel.text = label
        statBinding.tvStatValue.text = value
        _binding.gridStats.addView(statBinding.root)
    }

    private fun setupChart() {
        val entries = ArrayList<Entry>().apply {
            add(Entry(0f, 150f))
            add(Entry(1f, 160f))
            add(Entry(2f, 155f))
            add(Entry(3f, 165f))
            add(Entry(4f, 170f))
            add(Entry(5f, 175f))
        }

        val dataSet = LineDataSet(entries, "Price Trend").apply {
            color = Color.BLUE
            valueTextColor = Color.BLACK
            circleRadius = 4f
            lineWidth = 2f
            setDrawFilled(true)
            fillColor = Color.CYAN
        }

        val lineData = LineData(dataSet)
        _binding.lineChart.data = lineData
        _binding.lineChart.setTouchEnabled(true)
        _binding.lineChart.setPinchZoom(true)
        _binding.lineChart.description = Description().apply { text = "" }
        _binding.lineChart.invalidate()
    }
}



//package com.kapil.stocks.ui.activities
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.lifecycleScope
//import com.kapil.stocks.databinding.ActivityStockDetailBinding
//import com.kapil.stocks.viewmodel.StockViewModel
//import kotlinx.coroutines.launch
//
//class StockDetailsActivity : AppCompatActivity() {
//
//    //    private lateinit var stockNameTextView: TextView
////    private lateinit var stockPriceTextView: TextView
//    private lateinit var viewModel: StockViewModel
//    private lateinit var _binding: ActivityStockDetailBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        _binding =  ActivityStockDetailBinding.inflate(layoutInflater)
//        setContentView(_binding.root)
//
//        viewModel = ViewModelProvider(this)[StockViewModel::class.java]
//
//        setupListeners()
//
//        // get activity arguments
//        // symbol = read data
//        lifecycleScope.launch {
////            viemodel.fetchStockDetails(stockName)
//        }
//
////        stockNameTextView = findViewById(R.id.textStockName)
////        stockPriceTextView = findViewById(R.id.textStockPrice)
//
//        // Safely receive stock data
////        @Suppress("DEPRECATION")
////        val stock: Stock? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
////            intent.getSerializableExtra("stock", Stock::class.java)
////        } else {
////            intent.getSerializableExtra("stock") as? Stock
////        }
////
////        if (stock != null) {
////            stockNameTextView.text = stock.name
////            stockPriceTextView.text = stock.price
////        } else {
////            Toast.makeText(this, "Stock data not found!", Toast.LENGTH_SHORT).show()
////            finish()
////        }
//    }
//
//    private fun setupListeners(){
//        lifecycleScope.launch {
//            viewModel.stockDetails.collect { data ->
//                if(data !== null){
//                    _binding.textStockName.text = data.Symbol
//                    _binding.textStockPrice.text = data.PERatio
//                }
//
//            }
//        }
//    }
//}