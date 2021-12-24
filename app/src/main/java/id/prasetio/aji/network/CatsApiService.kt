package id.prasetio.aji.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// membuat objek BASE_URL yang berisi link base api
private const val BASE_URL = "https://api.thecatapi.com/v1/images/"

// Membuat objek moshi yang akan digunakan retrofit nantinya.
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

// Membuat Object retrofit dari Moshi Converter yang sebelumnya dibuat.
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CatsApiService {
    // Mengambil data dari url BASE_URL + search?limit=10&order=Desc
    // (https://api.thecatapi.com/v1/images/search?limit=10&order=Desc)
    @GET("search?limit=10&order=Desc")
    suspend fun getCats() : List<Cats>
}

object CatsApi {
    val retrofitService :CatsApiService by lazy { retrofit.create(CatsApiService::class.java) }
}
