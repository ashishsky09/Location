package com.location.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.location.DetailPhotoActivity
import com.location.R
import com.location.fragment.MemoriesPhoto
import com.squareup.picasso.Picasso

class ImageGalleryAdapter(val context: Context, val galleryPhotos: Array<MemoriesPhoto>)
    : RecyclerView.Adapter<ImageGalleryAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageGalleryAdapter.MyViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val photoView = inflater.inflate(R.layout.item_image, parent, false)
        return MyViewHolder(photoView)
    }

    override fun onBindViewHolder(holder: ImageGalleryAdapter.MyViewHolder, position: Int) {
        val galleryPhoto = galleryPhotos[position]
        val imageView  = holder.photoImageView

        Picasso.get()
            .load(galleryPhoto.url)
            .placeholder(R.drawable.ic_home)
            .error(R.drawable.ic_directions_bus_white_24dp)
            .fit()
            .into(imageView)
    }

    override fun getItemCount(): Int {
        return galleryPhotos.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var photoImageView: ImageView= itemView.findViewById(R.id.iv_photo)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val galleryPhoto = galleryPhotos[position]
                val intent = Intent(context, DetailPhotoActivity::class.java).apply {
                    putExtra(DetailPhotoActivity.EXTRA_SUNSET_PHOTO, galleryPhoto)
                }
                context.startActivity(intent)

            }
        }
    }
}