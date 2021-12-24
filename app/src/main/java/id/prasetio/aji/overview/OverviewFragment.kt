package id.prasetio.aji.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import id.prasetio.aji.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)

        // Memberikan akses data binding untuk melihat LiveData
        binding.lifecycleOwner = this

        // Memberikan kases kepada penghubung/binding ke OverviewViewModel
        binding.viewModel = viewModel

        // Mengset adapater dari photosgrid RecyclerView
        binding.photosGrid.adapter = PhotoGridAdapter()

        return binding.root
    }
}