package com.noemi.android.popup.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.noemi.android.popup.api.model.Artwork
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_pixabay.view.*

class PixabayVH(private val view: View, private val pixabay: Pixabay) :
    RecyclerView.ViewHolder(view) {

    fun bind(artWork: Artwork) {
        Picasso.get().load(artWork.url).into(view.ivArtWork)
        itemView.setOnLongClickListener {
            pixabay.invoke(artWork)
            true
        }
    }
}