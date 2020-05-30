package com.akameko.testforlifehackstudio.ui.main;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.akameko.testforlifehackstudio.repository.CompanyDetailItem;
import com.akameko.testforlifehackstudio.repository.CompanyListItem;
import com.akameko.testforlifehackstudio.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SharedViewModel extends ViewModel {
    private Repository repository = new Repository();
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<List<CompanyListItem>> companyListLiveData;
    private int select = 1;
    private MutableLiveData<List<CompanyDetailItem>> selected = new MutableLiveData<>();


    public LiveData<List<CompanyListItem>> getCompanies() {
        if (companyListLiveData == null) {

            companyListLiveData = new MutableLiveData<>();
            loadCompanies();
        }
        return companyListLiveData;
    }

    private void loadCompanies() {

        // Do an asynchronous operation to fetch users.
        ArrayList<CompanyListItem> companiesList = new ArrayList<>();

        Disposable disposable = repository.loadCompanies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(companyListItems -> {
                    companyListLiveData.setValue(companyListItems);

                    Log.d("123", "loaded!!");


                }, throwable -> {
                    Log.d("123", "123",throwable);
                    //Toast.makeText(this,"load error", Toast.LENGTH_SHORT).show();
                });
        compositeDisposable.add(disposable);


    }

    public void select(int item) {
        select = item;
        loadDetails();
    }

    private void loadDetails() {
        // Do an asynchronous operation to fetch users.
    }

    public LiveData<List<CompanyDetailItem>> getSelected() {
        return selected;
    }

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }
}
