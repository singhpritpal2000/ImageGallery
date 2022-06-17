package com.example.imagegallery.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imagegallery.R
import com.example.imagegallery.model.network.FlickrPhoto
import com.example.imagegallery.util.getProgressDrawable
import com.example.imagegallery.util.loadImage
import kotlinx.android.synthetic.main.layout_grid_item.view.*

class SearchRecyclerViewAdapter(var listFlickrPhoto: ArrayList<FlickrPhoto>) :
    RecyclerView.Adapter<SearchRecyclerViewAdapter.ViewHolder>() {

    fun updatePhoto(newPhoto: List<FlickrPhoto>) {
        listFlickrPhoto.clear()
        listFlickrPhoto.addAll(newPhoto)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.layout_grid_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listFlickrPhoto[position])
    }

    override fun getItemCount() = listFlickrPhoto.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imageView = view.image

        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(flickrPhoto: FlickrPhoto) {
            imageView.loadImage(flickrPhoto.url, progressDrawable)
        }
    }
}