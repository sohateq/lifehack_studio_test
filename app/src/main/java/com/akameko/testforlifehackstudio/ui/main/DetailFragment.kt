package com.akameko.testforlifehackstudio.ui.main

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.akameko.testforlifehackstudio.R
import com.akameko.testforlifehackstudio.repository.CompanyDetailItem
import java.io.IOException

class DetailFragment : Fragment() {
    private var sharedViewModel: SharedViewModel? = null
    private var companyDetailItem: CompanyDetailItem? = null
    var companyName: TextView? = null
    var companyPhone: TextView? = null
    var companyWww: TextView? = null
    var companyMap: TextView? = null
    var companyDescription: TextView? = null
    var companyPhoto: ImageView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_detail, container, false)
        companyName = root.findViewById(R.id.text_view_details_name)
        companyPhone = root.findViewById(R.id.text_view_details_phone)
        companyWww = root.findViewById(R.id.text_view_details_www)
        companyMap = root.findViewById(R.id.text_view_details_map)
        companyDescription = root.findViewById(R.id.text_view_details_description)
        companyPhoto = root.findViewById(R.id.image_view_details)
        return root
    }

    override fun onStart() {
        super.onStart()
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        sharedViewModel!!.details.observe(viewLifecycleOwner, Observer { details: List<CompanyDetailItem> ->
            for (c in details) {
                Log.d("", c.toString())
            }
            companyDetailItem = details[0]
            initViews()
        })
    }

    private fun initViews() {
        companyName!!.text = companyDetailItem!!.name
        if (companyDetailItem!!.phone != "") {
            companyPhone!!.text = companyDetailItem!!.phone
            companyPhone!!.visibility = View.VISIBLE
        }
        if (companyDetailItem!!.www != "") {
            companyWww!!.text = companyDetailItem!!.www
            companyWww!!.visibility = View.VISIBLE
        }
        if (companyDetailItem!!.lat != "0" && companyDetailItem!!.lon != "0") {
            companyMap!!.text = companyDetailItem!!.lat + ", " + companyDetailItem!!.lon
            companyMap!!.visibility = View.VISIBLE
        }
        if (companyDetailItem!!.description != "") {
            companyDescription!!.text = companyDetailItem!!.description
            companyDescription!!.visibility = View.VISIBLE
        }
        val am = resources.assets
        try {
            val `is` = am.open(companyDetailItem!!.img)
            companyPhoto!!.setImageDrawable(Drawable.createFromStream(`is`, 0.toString()))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}