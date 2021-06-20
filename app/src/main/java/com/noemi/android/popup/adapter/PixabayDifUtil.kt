package com.noemi.android.popup.adapter

import androidx.recyclerview.widget.DiffUtil
import com.noemi.android.popup.api.model.Artwork

class PixabayDifUtil : DiffUtil.ItemCallback<Artwork>() {

    override fun areItemsTheSame(oldItem: Artwork, newItem: Artwork): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Artwork, newItem: Artwork): Boolean {
        return oldItem == newItem
    }
}