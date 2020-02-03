package com.location

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.location.fragment.MemoriesPhoto
import com.squareup.picasso.Picasso

class DetailPhotoActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SUNSET_PHOTO = "PhotoActivity.EXTRA_SUNSET_PHOTO"
    }

    private lateinit var imageView: ImageView
    private lateinit var sunsetPhoto: MemoriesPhoto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_photo)

        sunsetPhoto = intent.getParcelableExtra(EXTRA_SUNSET_PHOTO)
        imageView = findViewById(R.id.image)
    }

    override fun onStart() {
        super.onStart()

        Picasso.get()
            .load(sunsetPhoto.url)
            .placeholder(android.R.drawable.ic_menu_myplaces)
            .error(android.R.drawable.stat_notify_error)
            .fit()
            .into(imageView)
    }
}
