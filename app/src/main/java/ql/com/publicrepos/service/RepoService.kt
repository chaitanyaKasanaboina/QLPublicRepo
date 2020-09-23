package ql.com.publicrepos.service

import io.reactivex.Single
import ql.com.publicrepos.model.RepoDetails
import retrofit2.http.GET

interface RepoService {
    @GET("users/QuickenLoans/repos")
    fun getPublicRepoDetails(): Single<List<RepoDetails>>
}