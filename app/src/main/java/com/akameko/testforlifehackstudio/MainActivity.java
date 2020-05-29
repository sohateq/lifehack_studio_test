package com.akameko.testforlifehackstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.akameko.testforlifehackstudio.repository.CompanyListItem;
import com.akameko.testforlifehackstudio.repository.Repository;
import com.akameko.testforlifehackstudio.ui.main.mainfragment.MainFragment;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    Repository repository;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
            repository = new Repository();

        }

        ArrayList<CompanyListItem> companiesList = new ArrayList<>();

//        companiesList = new ArrayList<>(repository.loadCompanies());
    //    for (CompanyListItem c : companiesList) {
 //           Log.d("", c.toString());

//        }

        Disposable disposable = repository.loadCompanies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(companyListItems -> {
                    companiesList.addAll(companyListItems);
                    for (CompanyListItem c : companiesList) {
                        Log.d("", c.toString());}

                }, throwable -> {
                    Log.d("123", "123",throwable);
                    Toast.makeText(this,"load error", Toast.LENGTH_SHORT).show();
                });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}
