package com.kapil.stocks.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kapil.stocks.R
import com.kapil.stocks.data.model.Stock
import com.kapil.stocks.data.model.WatchList

class WatchlistAdapter(private val onClick: () -> Unit) :
    RecyclerView.Adapter<WatchlistAdapter.StockViewHolder>() {

    private var _data: List<WatchList> = emptyList()

    inner class StockViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.watchlistName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_watchlist, parent, false)
        return StockViewHolder(view)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val watchlist = _data[position]
        holder.name.text = watchlist.name
        holder.itemView.setOnClickListener { onClick() }
    }

    fun updateData(list: List<WatchList>){
        _data = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = _data.size
}
