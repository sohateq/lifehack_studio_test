package com.akameko.testforlifehackstudio.ui.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akameko.testforlifehackstudio.R;
import com.akameko.testforlifehackstudio.repository.CompanyDetailItem;
import com.akameko.testforlifehackstudio.repository.CompanyListItem;


public class DetailFragment extends Fragment {
    private SharedViewModel sharedViewModel;
    private CompanyDetailItem companyDetailItem;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.getDetails().observe(getViewLifecycleOwner(), details -> {

            for (CompanyDetailItem c : details) {
                Log.d("", c.toString());
            }
            companyDetailItem = details.get(0);
        });

    }


}
