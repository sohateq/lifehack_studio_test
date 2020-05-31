package com.akameko.testforlifehackstudio.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akameko.testforlifehackstudio.repository.CompanyDetailItem
import com.akameko.testforlifehackstudio.repository.CompanyListItem
import com.akameko.testforlifehackstudio.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SharedViewModel : ViewModel() {
    private val repository = Repository()
    private val compositeDisposable = CompositeDisposable()
    private var companyListLiveData: MutableLiveData<List<CompanyListItem>>? = null
    private var select = 1
    val details = MutableLiveData<List<CompanyDetailItem>>()
    val companies: LiveData<List<CompanyListItem>>
        get() {
            if (companyListLiveData == null) {
                companyListLiveData = MutableLiveData()
                loadCompanies()
            }
            return companyListLiveData!!
        }

    private fun loadCompanies() { // ArrayList<CompanyListItem> companiesList = new ArrayList<>();
        val disposable = repository.loadCompanies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ companyListItems: List<CompanyListItem> ->
                    companyListLiveData!!.value = companyListItems
                    Log.d("123", "Companies loaded!!")
                }) { throwable: Throwable? -> Log.d("123", "Companies loading failed", throwable) }
        compositeDisposable.add(disposable)
    }

    fun select(item: Int) {
        select = item
        loadDetails()
    }

    private fun loadDetails() {
        val disposable = repository.loadCompaniesDetails(select.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ companyDetailItems: List<CompanyDetailItem> ->
                    details.value = companyDetailItems
                    Log.d("123", "Details loaded!!")
                }) { throwable: Throwable? -> Log.d("123", "Details loading failed", throwable) }
        compositeDisposable.add(disposable)
    }

    fun getDetails(): LiveData<List<CompanyDetailItem>> { //        if (details == null){
//
//        }
        return details
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}