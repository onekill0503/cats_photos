package id.prasetio.aji.network

import com.squareup.moshi.Json

// Class berisi object data
data class Cats (
    val id: String,
    // mengambil data yang ada pada attribute 'url' di data json yang diterima dan dimasukan ke obj img
    @Json(name = "url") val img: String
)