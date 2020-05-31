package com.akameko.testforlifehackstudio.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akameko.testforlifehackstudio.MainActivity
import com.akameko.testforlifehackstudio.R
import com.akameko.testforlifehackstudio.repository.CompanyListItem

class MainFragment : Fragment() {
    private var sharedViewModel: SharedViewModel? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel.class);
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        sharedViewModel!!.companies.observe(viewLifecycleOwner, Observer { companies: List<CompanyListItem> ->
            for (c in companies) {
                Log.d("", c.toString())
            }
            initRecycler(companies)
        })
    }

    private fun initRecycler(companyListItem: List<CompanyListItem>) {
        val recyclerView: RecyclerView = activity!!.findViewById(R.id.main_recycler_view)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        val mainAdapter = MainAdapter(companyListItem)
        mainAdapter.setOnItemClickListener { _: View?, position: Int ->
            //Toast.makeText(getContext(), String.format("Позиция - %d", position), Toast.LENGTH_SHORT).show();
            sharedViewModel!!.select(position + 1)
            (activity as MainActivity?)!!.showDetailsFragment()
        }
        recyclerView.adapter = mainAdapter
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}