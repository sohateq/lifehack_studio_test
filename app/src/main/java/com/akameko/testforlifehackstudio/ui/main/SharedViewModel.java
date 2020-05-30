package com.akameko.testforlifehackstudio.ui.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.akameko.testforlifehackstudio.repository.CompanyDetailItem;
import com.akameko.testforlifehackstudio.repository.CompanyListItem;
import com.akameko.testforlifehackstudio.repository.Repository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SharedViewModel extends ViewModel {
    private Repository repository = new Repository();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<List<CompanyListItem>> companyListLiveData;
    private int select = 1;
    private MutableLiveData<List<CompanyDetailItem>> details = new MutableLiveData<>();


    public LiveData<List<CompanyListItem>> getCompanies() {
        if (companyListLiveData == null) {

            companyListLiveData = new MutableLiveData<>();
            loadCompanies();
        }
        return companyListLiveData;
    }

    private void loadCompanies() {
       // ArrayList<CompanyListItem> companiesList = new ArrayList<>();

        Disposable disposable = repository.loadCompanies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(companyListItems -> {
                    companyListLiveData.setValue(companyListItems);

                    Log.d("123", "Companies loaded!!");


                }, throwable -> {
                    Log.d("123", "Companies loading failed", throwable);
                    //Toast.makeText(this,"load error", Toast.LENGTH_SHORT).show();
                });
        compositeDisposable.add(disposable);


    }

    public void select(int item) {
        select = item;
        loadDetails();
    }

    private void loadDetails() {


        Disposable disposable = repository.loadCompaniesDetails(String.valueOf(select))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(companyDetailItems -> {
                    details.setValue(companyDetailItems);
                    Log.d("123", "Details loaded!!");
//                    for (CompanyDetailItem c : companyDetailItems) {
//                        Log.d("", c.toString());}
                }, throwable -> {
                    Log.d("123", "Details loading failed", throwable);
                });
        compositeDisposable.add(disposable);


    }

    public LiveData<List<CompanyDetailItem>> getDetails() {
//        if (details == null){
//
//        }
        return details;
    }

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }
}
