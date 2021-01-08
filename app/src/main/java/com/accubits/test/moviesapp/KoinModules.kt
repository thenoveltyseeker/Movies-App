package com.accubits.test.moviesapp

import androidx.annotation.LayoutRes
import androidx.room.Room
import com.accubits.test.moviesapp.model.data.Movie
import com.accubits.test.moviesapp.model.db.AppDatabase
import com.accubits.test.moviesapp.model.network.NetworkRequestFactory
import com.accubits.test.moviesapp.model.repo.MoviesApi
import com.accubits.test.moviesapp.model.repo.MoviesRepo
import com.accubits.test.moviesapp.model.repo.MoviesRepoImpl
import com.accubits.test.moviesapp.ui.MoviesAdapter
import com.accubits.test.moviesapp.ui.viewmodel.MoviesViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * @author Rolbin, created on 08-01-2021.
 */
private const val DATABASE_NAME = "movies-list.db"

val networkModule = module {
    single<Converter.Factory> { MoshiConverterFactory.create() }
    single { buildHttpClient() }
    single {
        val baseUrl = "https://api.themoviedb.org/"
        Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(get())
                .client(get())
                .build()
    }
    single { NetworkRequestFactory((get())) }
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, DATABASE_NAME).build()
    }
    single {
        get<AppDatabase>().moviesDao()
    }
}

private fun buildHttpClient(): OkHttpClient {
    val httpClient = OkHttpClient.Builder()
    return httpClient.build()
}

val moviesModule = module {
    single { get<NetworkRequestFactory>().createApiService(MoviesApi::class.java) }
    factory<MoviesRepo> { MoviesRepoImpl(get(), get(), get()) }
    factory { (movies: List<Movie>, @LayoutRes layout: Int) -> MoviesAdapter(movies, layout) }
    viewModel { MoviesViewModel(get()) }
}