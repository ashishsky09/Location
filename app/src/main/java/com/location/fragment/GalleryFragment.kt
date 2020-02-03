package com.location.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.location.R
import com.location.adapter.ImageGalleryAdapter
import kotlinx.android.synthetic.main.gallery_fragment.*


class GalleryFragment: Fragment() {

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @Override
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.gallery_fragment, container, false)

        return view
    }


    @Override
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val layoutManager = GridLayoutManager(requireActivity(), 2)

        gallery_recycler_view.setHasFixedSize(true)
        gallery_recycler_view.layoutManager = layoutManager
        val imageGalleryAdapter = ImageGalleryAdapter(requireActivity(),MemoriesPhoto.getPhotos())
        gallery_recycler_view.adapter = imageGalleryAdapter
    }
}