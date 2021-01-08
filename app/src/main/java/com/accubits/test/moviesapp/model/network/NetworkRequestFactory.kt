package com.accubits.test.moviesapp.model.network

import retrofit2.Retrofit

/**
 * @author Rolbin, created on 08-01-2021.
 */
class NetworkRequestFactory(private val retrofitClient: Retrofit) {

    fun <REQUEST> createApiService(service: Class<REQUEST>): REQUEST {
        return retrofitClient.create(service)
    }

    internal inline fun <RESPONSE_TYPE> performRequest(request: () -> RESPONSE_TYPE): Result<RESPONSE_TYPE> {
        return try {
            with(request.invoke()) {
                Result.Success(this)
            }
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }
    }
}