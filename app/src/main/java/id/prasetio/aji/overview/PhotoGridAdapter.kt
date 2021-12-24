package id.prasetio.aji.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.prasetio.aji.network.Cats
import id.prasetio.aji.databinding.GridViewItemBinding


// Adapter yand digunakan untuk gambar dengan layout grid
class PhotoGridAdapter : ListAdapter<Cats,
        PhotoGridAdapter.CatsPhotoViewHolder>(DiffCallback) {

    // inner class view holder
    class CatsPhotoViewHolder(private var binding:
                              GridViewItemBinding
    ):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(Cats: Cats) {
            binding.photo = Cats
            binding.executePendingBindings()
        }
    }

    // digunakan untuk mendownload dan memuat gambar yang hanya ditampilkan pada layar.
    companion object DiffCallback : DiffUtil.ItemCallback<Cats>() {
        override fun areItemsTheSame(oldItem: Cats, newItem: Cats): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cats, newItem: Cats): Boolean {
            return oldItem.img == newItem.img
        }
    }

    // digunakan untuk membinding / menghubungkan data ke layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsPhotoViewHolder {
        return CatsPhotoViewHolder(GridViewItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    // binding adapter atau adapter yang menghubungkan view holder dengan data Cats
    override fun onBindViewHolder(holder: CatsPhotoViewHolder, position: Int) {
        // Memasukan list data foto kucing ke objek catasPhoto
        val catsPhoto = getItem(position)
        // lalu di binding ke layout
        holder.bind(catsPhoto)
    }
}