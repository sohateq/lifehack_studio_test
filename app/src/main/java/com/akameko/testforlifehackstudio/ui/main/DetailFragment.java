package com.akameko.testforlifehackstudio.ui.main;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akameko.testforlifehackstudio.R;
import com.akameko.testforlifehackstudio.repository.CompanyDetailItem;
import com.akameko.testforlifehackstudio.repository.CompanyListItem;

import java.io.IOException;
import java.io.InputStream;


public class DetailFragment extends Fragment {
    private SharedViewModel sharedViewModel;
    private CompanyDetailItem companyDetailItem;

    TextView companyName;
    TextView companyPhone;
    TextView companyWww ;
    TextView companyMap ;
    TextView companyDescription ;
    ImageView companyPhoto;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_detail, container, false);
        companyName = root.findViewById(R.id.text_view_details_name);
        companyPhone = root.findViewById(R.id.text_view_details_phone);
        companyWww = root.findViewById(R.id.text_view_details_www);
        companyMap = root.findViewById(R.id.text_view_details_map);
        companyDescription = root.findViewById(R.id.text_view_details_description);
        companyPhoto = root.findViewById(R.id.image_view_details);
       return root;
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
            initViews();
        });

    }

    private void initViews(){
        companyName.setText(companyDetailItem.getName());
        if (!companyDetailItem.getPhone().equals("")){
            companyPhone.setText(companyDetailItem.getPhone());
            companyPhone.setVisibility(View.VISIBLE);
        }
        if (!companyDetailItem.getWww().equals("")){
            companyWww.setText(companyDetailItem.getWww());
            companyWww.setVisibility(View.VISIBLE);
        }
        if (!companyDetailItem.getLat().equals("0") && !companyDetailItem.getLon().equals("0")){
            companyMap.setText(companyDetailItem.getLat() + ", "+ companyDetailItem.getLon());
            companyMap.setVisibility(View.VISIBLE);
        }
        if (!companyDetailItem.getDescription().equals("")){
            companyDescription.setText(companyDetailItem.getDescription());
            companyDescription.setVisibility(View.VISIBLE);
        }






        AssetManager am = getResources().getAssets();
        try {
            InputStream is = am.open(companyDetailItem.getImg());
            companyPhoto.setImageDrawable( Drawable.createFromStream(is, String.valueOf(0)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
