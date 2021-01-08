package com.accubits.test.moviesapp.model.network

/**
 * @author Rolbin, created on 08-01-2021.
 */
sealed class Result<out RESPONSE_TYPE> {

    data class Success<out RESPONSE_TYPE>(val data: RESPONSE_TYPE) : Result<RESPONSE_TYPE>()

    data class Failure(val throwable: Throwable) : Result<Nothing>()
}