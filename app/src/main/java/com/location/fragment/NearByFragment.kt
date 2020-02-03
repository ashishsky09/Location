package com.location.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.location.R
import com.location.adapter.GridAdapter
import kotlinx.android.synthetic.main.fragment_nearby.*

class NearByFragment : Fragment() {

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

        val view: View = inflater.inflate(R.layout.fragment_nearby, container, false)

        return view
    }

    @Override
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        //Find View By Id For GridView


        /*Create and ArrayList of Integer Type To Store Images From drawable.Here we add Images to ArrayList.
        We have Images of Android Icons of Different versions.
        */

        val arrayListImage = ArrayList<Int>()

        arrayListImage.add(R.drawable.ic_aboutus)
        arrayListImage.add(R.drawable.ic_aboutus)
        arrayListImage.add(R.drawable.ic_aboutus)
        arrayListImage.add(R.drawable.ic_aboutus)
        arrayListImage.add(R.drawable.ic_aboutus)

        // Here We take and Array of Android OS names in Same Sequence as we take Images.

        val name = arrayOf(
            "ATMs",
            "TOILETs",
            "INFORMATION DESKs",
            "AMBULANCE SERVICEs",
            "FOOD & RESTAURANTs"
        )


        //We Have Created Custom Adapter Class in that we pass Context,ArrayList of Image and Array Of name

        val customAdapter = GridAdapter(requireActivity(), arrayListImage, name)

        //Set Adapter to ArrayList

        gridView.adapter = customAdapter


        gridView.setOnItemClickListener { adapterView, parent, position, l ->

            val intent = Intent(Intent.ACTION_VIEW)
            if (name[position].equals("ATMs")) {
                intent.data = Uri.parse("http://maps.google.co.in/maps?q=ATMs")
            } else if (name[position].equals("TOILETs")) {
                intent.data = Uri.parse("http://maps.google.co.in/maps?q=TOILETs")
            } else if (name[position].equals("INFORMATION DESKs")) {
                intent.data = Uri.parse("http://maps.google.co.in/maps?q=INFORMATION DESKs")

            } else if (name[position].equals("AMBULANCE SERVICEs")) {
                intent.data = Uri.parse("http://maps.google.co.in/maps?q=AMBULANCE SERVICEs")
            } else {
                intent.data = Uri.parse("http://maps.google.co.in/maps?q=FOOD & RESTAURANTs")
            }
            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(intent)
            }
        }

    }
}