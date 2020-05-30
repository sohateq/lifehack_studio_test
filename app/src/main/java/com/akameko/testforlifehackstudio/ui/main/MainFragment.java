package com.akameko.testforlifehackstudio.ui.main;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.akameko.testforlifehackstudio.MainActivity;
import com.akameko.testforlifehackstudio.R;
import com.akameko.testforlifehackstudio.repository.CompanyListItem;

import java.util.List;

public class MainFragment extends Fragment {

    private SharedViewModel sharedViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel.class);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.getCompanies().observe(getViewLifecycleOwner(), companies -> {

            for (CompanyListItem c : companies) {
                Log.d("", c.toString());
            }
            initRecycler(companies);
        });


    }

    private void initRecycler(List<CompanyListItem> companyListItem) {

        RecyclerView recyclerView = getActivity().findViewById(R.id.main_recycler_view);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        final MainAdapter mainAdapter = new MainAdapter(companyListItem);
        mainAdapter.setOnItemClickListener((view, position) -> {
            Toast.makeText(getContext(), String.format("Позиция - %d", position), Toast.LENGTH_SHORT).show();
            sharedViewModel.select(position + 1);
            ((MainActivity)getActivity()).showDetailsFragment();
        });

        recyclerView.setAdapter(mainAdapter);

    }

}
