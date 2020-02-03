package com.location

import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.location.fragment.CallFragment
import com.location.fragment.HomeFragment
import com.location.fragment.LocateFragment
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*
import kotlin.concurrent.schedule

class HomeActivity : FragmentActivity() {
    private lateinit var locationRequest: LocationRequest

    companion object {

        private const val REQUEST_CHECK_SETTINGS = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState == null) {
            val fragment = HomeFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment, fragment.javaClass.getSimpleName())
                .commit()
        }
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    val fragment = HomeFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragment_container,
                            fragment,
                            fragment.javaClass.getSimpleName()
                        )
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_call -> {
                    val fragment = CallFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragment_container,
                            fragment,
                            fragment.javaClass.getSimpleName()
                        )
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_loc -> {

                    createLocationRequest()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private fun createLocationRequest() {
        // 1
        locationRequest = LocationRequest()
        // 2
        locationRequest.interval = 10000
        // 3
        locationRequest.fastestInterval = 5000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)

        // 4
        val client = LocationServices.getSettingsClient(this)
        val task = client.checkLocationSettings(builder.build())

        // 5
        task.addOnSuccessListener {

            val fragment = LocateFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment, fragment.javaClass.getSimpleName())
                .commit()


        }
        task.addOnFailureListener { e ->
            // 6
            if (e is ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    e.startResolutionForResult(
                        this,
                        REQUEST_CHECK_SETTINGS
                    )
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }
    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            if (resultCode == Activity.RESULT_OK) {


                Timer("SettingUp", false).schedule(2000) {
                    val fragment = LocateFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragment_container,
                            fragment,
                            fragment.javaClass.getSimpleName()
                        )
                        .commit()

                }

            }
        }
    }
}
