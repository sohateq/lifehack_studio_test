package com.akameko.testforlifehackstudio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.akameko.testforlifehackstudio.repository.CompanyListItem;
import com.akameko.testforlifehackstudio.repository.Repository;
import com.akameko.testforlifehackstudio.ui.main.MainFragment;
import com.akameko.testforlifehackstudio.ui.main.SharedViewModel;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();


        }


//        SharedViewModel model = new ViewModelProvider(this).get(SharedViewModel.class);
//        model.getCompanies().observe(this, companies -> {
//
//            for (CompanyListItem c : companies) {
//                Log.d("", c.toString());}
//        });

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
