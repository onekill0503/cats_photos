package id.prasetio.aji.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.prasetio.aji.network.Cats
import id.prasetio.aji.network.CatsApi
import kotlinx.coroutines.launch



enum class ApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    // membuat object _status yang berisi status pengambilan data dari internet
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    // Mengambil daftar data URL foto dari Cats dan di masukan menjadi list ke dalam object _photos
    private val _photos = MutableLiveData<List<Cats>>()
    val photos: LiveData<List<Cats>> = _photos

    // Memanggil fungsi getCatsPhotos() pada fungsi init
    init {
        getCatsPhotos()
    }

    // Mengambil Data Foto Kucing menggunakan servis API Restorfit
    private fun getCatsPhotos() {

        viewModelScope.launch {
            // menampilkan status loading
            _status.value = ApiStatus.LOADING
            try {
                // Jika tidak ada error, maka foto tampil dengan status DONE
                _photos.value = CatsApi.retrofitService.getCats()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                // Jika Error maka akan menampilkan status ERROR serta data _photos akan di isi dengan list kosong.
                _status.value = ApiStatus.ERROR
                _photos.value = listOf()
            }
        }
    }
}