package ql.com.publicrepos.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ql.com.publicrepos.model.RepoDetails
import ql.com.publicrepos.service.RepoService
import javax.inject.Inject

class RepoViewModel @Inject constructor(private val repoService: RepoService) : ViewModel() {
    val repoDetailsLiveData = MutableLiveData<List<RepoDetails>>()
    private val repoDetailsErrorData = MutableLiveData<String>()
    private val compositeDisposable = CompositeDisposable()

    init {
        getRepoDetailsList()
    }

    private fun getRepoDetailsList() {
        compositeDisposable.add(
            repoService.getPublicRepoDetails()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe({ t -> repoDetailsLiveData.postValue(t) },
                    { throwable ->
                        repoDetailsErrorData.postValue("Error from service")
                        Log.e("error", throwable.toString())
                    })
        )
    }
}