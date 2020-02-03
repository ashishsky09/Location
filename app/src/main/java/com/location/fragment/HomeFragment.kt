package com.location.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import com.location.R
import com.location.adapter.GridAdapter
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
//Find View By Id For GridView

        val gridView = view.findViewById(R.id.gridView) as GridView

        /*Create and ArrayList of Integer Type To Store Images From drawable.Here we add Images to ArrayList.
        We have Images of Android Icons of Different versions.
        */

        val arrayListImage = ArrayList<Int>()

        arrayListImage.add(R.drawable.ic_aboutus)
        arrayListImage.add(R.drawable.ic_aboutus)
        arrayListImage.add(R.drawable.ic_aboutus)
        arrayListImage.add(R.drawable.ic_aboutus)
        arrayListImage.add(R.drawable.ic_aboutus)
        arrayListImage.add(R.drawable.ic_aboutus)
        arrayListImage.add(R.drawable.ic_aboutus)
        arrayListImage.add(R.drawable.ic_aboutus)
        arrayListImage.add(R.drawable.ic_aboutus)


        // Here We take and Array of Android OS names in Same Sequence as we take Images.

        val name = arrayOf(
            "Organiser",
            "About IIFF",
            "About CIFI",
            "Exhibitor List",
            "Layouts",
            "Registration",
            "Nearby",
            "Feedback",
            "Gallery"
        )


        //We Have Created Custom Adapter Class in that we pass Context,ArrayList of Image and Array Of name

        val customAdapter = GridAdapter(requireActivity(), arrayListImage, name)

        //Set Adapter to ArrayList

        gridView.adapter = customAdapter

        //On Click for GridView Item

        gridView.setOnItemClickListener { adapterView, parent, position, l ->

            val transaction = fragmentManager!!.beginTransaction()

            if (name[position].equals("Feedback")) {
                var fragment = FeedbackFragment()
                transaction.replace(
                    R.id.fragment_container,
                    fragment
                ) // fragment container id in first parameter is the  container(Main layout id) of Activity
                transaction.addToBackStack(null)  // this will manage backstack
                transaction.commit()
            }else if(name[position].equals("Nearby"))
            {
                var fragment = NearByFragment()
                transaction.replace(
                    R.id.fragment_container,
                    fragment
                ) // fragment container id in first parameter is the  container(Main layout id) of Activity
                transaction.addToBackStack(null)  // this will manage backstack
                transaction.commit()
            }

            else if(name[position].equals("Gallery"))
            {
                var fragment = GalleryFragment()
                transaction.replace(
                    R.id.fragment_container,
                    fragment
                ) // fragment container id in first parameter is the  container(Main layout id) of Activity
                transaction.addToBackStack(null)  // this will manage backstack
                transaction.commit()
            }
        }

        return view
    }


}
