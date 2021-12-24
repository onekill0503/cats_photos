package id.prasetio.aji

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import id.prasetio.aji.network.Cats
import id.prasetio.aji.overview.ApiStatus
import id.prasetio.aji.overview.PhotoGridAdapter


// Disini adalah kumpulan fungsi binding ke layout

// adaptor binding data dengan photoGridAdapter
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<Cats>?) {
    // adapter recyclerview menjadi PhotoGridAdapter dan dmasukan ke object 'adapter'
    val adapter = recyclerView.adapter as PhotoGridAdapter
    // menampilkan data
    adapter.submitList(data)
}

// Adapter yang digunkanakan untuk membinding data gambar
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    // Jika data imgUrl tidak null maka fungsi dalam akan berjalan.
    imgUrl?.let {
        // Konfigurasi akses ke data
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        // Proses mengambil/mendownload resource atau gambar
        Glide.with(imgView.context).load(imgUri)
            .placeholder(R.drawable.loading_animation)
            .error(Glide.with(imgView.context).load(R.drawable.failed_load))
            .into(imgView)
    }
}

// Adapter yang digunakan untuk membinding data status koneksi
@BindingAdapter("catsApiStatus")
fun bindStatus(statusImageView: ImageView, status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
            // Jika status koneksi LOADING, fungsi ini akan dijalankan
            statusImageView.visibility = View.VISIBLE
            // menampilkan animasi saat meload data
            Glide.with(statusImageView.context).load(R.drawable.cat_load).into(statusImageView)
        }
        ApiStatus.ERROR -> {
            // Jika status koneksi ERROR, fungsi ini akan dijalankan
            statusImageView.visibility = View.VISIBLE
            Glide.with(statusImageView.context).load(R.drawable.cat_error).into(statusImageView)
        }
        ApiStatus.DONE -> {
            // Jika status koneksi DONE, maka gambar yang diload akan ditampilkan
            // dan status koneksi akan di hilangkan.
            statusImageView.visibility = View.GONE
        }
    }
}
