package com.noemi.android.popup.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.noemi.android.popup.R
import com.noemi.android.popup.api.model.Artwork

typealias Pixabay = (artwork: Artwork) -> Unit

class PixabayAdapter(private val pixabay: Pixabay) :
    ListAdapter<Artwork, PixabayVH>(PixabayDifUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixabayVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pixabay, parent, false)
        return PixabayVH(view, pixabay)
    }

    override fun onBindViewHolder(holder: PixabayVH, position: Int) {
        holder.bind(getItem(position))
    }
}