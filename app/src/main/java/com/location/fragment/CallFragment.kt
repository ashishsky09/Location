package com.location.fragment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.location.R
import kotlinx.android.synthetic.main.fragment_call.*

class CallFragment : Fragment() {

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_call, container, false)

        return view
    }

    @Override
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            textView1.setText(
                Html.fromHtml(
                    "<h3>India Trade Promotion Organisation(ITPO)</h3>" +
                            "<h5>Pragati Bhawan,Pragati Maidan</h5>" +
                            "<h5>New Delhi - 110001</h5>"+
                            "<h6>Tel: +91-11-23371822,23371319</h6>"+
                            "<h6>E-mail: <a href=\"mailto:tkpant@itpo.gov.in?Subject=Your%20Query\" target=\"_top\">tkpant@itpo.gov.in</a>, <a href=\"mailto:iilf@itpo.gov.in?Subject=Your%20Query\" target=\"_top\">iilf@itpo.gov.in</a></h6>"+
                            "<h6><a href=\"rppawar@itpo.gov.in?Subject=Your%20Query\" target=\"_top\">rppawar@itpo.gov.in</a></h6></center>",

                    Html.FROM_HTML_MODE_COMPACT
                )
            )

            textView2.setText(
                Html.fromHtml(
                    "<b>Project Team</b></br><ul><pre><li style='color:#000000'>&nbsp;Sh. Jayanta Das, Genral Manager</li><li style='color:#000000'>&nbsp;Sh. Tarun Kant Pant, Manager </li><li style='color:#000000'>&nbsp;Sh. KP Muraleedharan, Manager </li><li style='color:#000000'>&nbsp;Sh. Rakesh Kumar Pawar, Dy. Manager </li></pre></ul>",
                    Html.FROM_HTML_MODE_COMPACT
                )
            )
        } else {

            textView1.setText(Html.fromHtml("<h3>India Trade Promotion Organisation(ITPO)</h3>" +
                    "<h5>Pragati Bhawan,Pragati Maidan</h5>" +
                    "<h5>New Delhi - 110001</h5>"+
                    "<h6>Tel: +91-11-23371822,23371319</h6>"+
                    "<h6>E-mail: <a href=\"mailto:tkpant@itpo.gov.in?Subject=Your%20Query\" target=\"_top\">tkpant@itpo.gov.in</a>, <a href=\"mailto:iilf@itpo.gov.in?Subject=Your%20Query\" target=\"_top\">iilf@itpo.gov.in</a></h6>"+
                    "<h6><a href=\"rppawar@itpo.gov.in?Subject=Your%20Query\" target=\"_top\">rppawar@itpo.gov.in</a></h6></center>"
            ))
            textView2.setText(Html.fromHtml("<b>Project Team</b><br/><ul><pre><li style='color:#000000'>&nbsp;Sh. Jayanta Das, Genral Manager</li><li style='color:#000000'>&nbsp;Sh. Tarun Kant Pant, Manager </li><li style='color:#000000'>&nbsp;Sh. KP Muraleedharan, Manager </li><li style='color:#000000'>&nbsp;Sh. Rakesh Kumar Pawar, Dy. Manager </li></pre></ul>"))
        }

        call.setOnClickListener(View.OnClickListener {
                checkPermission()

        })


        email.setOnClickListener(View.OnClickListener {

            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:") // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, "desmond.lua@luasoftware.com")
            intent.putExtra(Intent.EXTRA_SUBJECT,"Feedback")
            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(intent)
            }
        })


    }

    fun checkPermission() {
        if (ContextCompat.checkSelfPermission(requireActivity(),
                Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
                    Manifest.permission.CALL_PHONE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(requireActivity(),
                    arrayOf(Manifest.permission.CALL_PHONE),
                    42)
            }
        } else {
            // Permission has already been granted
            callPhone()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 42) {
            // If request is cancelled, the result arrays are empty.
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // permission was granted, yay!
                callPhone()
            } else {
                // permission denied, boo! Disable the
                // functionality
            }
            return
        }
    }

    fun callPhone(){
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "1122334455"))
        startActivity(intent)
    }
}
