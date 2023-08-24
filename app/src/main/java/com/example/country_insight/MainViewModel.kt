package com.example.country_insight

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.country_insight.api.ApiFactory
import com.example.country_insight.pojo.JsonObject
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel: ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val isLoading = MutableLiveData<Boolean>()
    private val isError = MutableLiveData<Boolean>()
    private val data = MutableLiveData<List<JsonObject>>()

    fun getIsLoading(): LiveData<Boolean>{
        return isLoading;
    }
    fun getIsError(): LiveData<Boolean>{
        return isError;
    }

    fun getData(): LiveData<List<JsonObject>> {
        return data
    }

    fun loadData(name: String){
        val disposable = ApiFactory.createApiService().getCountryInfo(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                isLoading.postValue(true)
            }
            .doAfterTerminate {
                isLoading.postValue(false)
            }
            .doOnError {
                isError.postValue(true)
            }
            .subscribe({
                data.postValue(it)
            }, {
                Log.d("MainViewModel", it.message.toString())
            })
        compositeDisposable.add(disposable)

    }



    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}