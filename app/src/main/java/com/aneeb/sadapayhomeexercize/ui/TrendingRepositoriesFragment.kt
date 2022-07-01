package com.aneeb.sadapayhomeexercize.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aneeb.sadapayhomeexercize.databinding.FragmentTrendingRepositoriesBinding
import com.aneeb.sadapayhomeexercize.network.RetrofitService
import com.aneeb.sadapayhomeexercize.network.TrendingRepoServiceRepository
import com.aneeb.sadapayhomeexercize.ui.adapter.TrendingListAdapter
import com.aneeb.sadapayhomeexercize.ui.viewmodel.MyViewModelFactory
import com.aneeb.sadapayhomeexercize.ui.viewmodel.TrendingRepositoriesViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TrendingRepositoriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TrendingRepositoriesFragment : Fragment() {

    private val TAG = "TrendingRepositoriesFragment"
    lateinit var viewModel: TrendingRepositoriesViewModel
    private val retrofitService = RetrofitService.getInstance()
    var adapter: TrendingListAdapter? = null


    @SuppressLint("LongLogTag")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTrendingRepositoriesBinding.inflate(inflater)

        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(TrendingRepoServiceRepository(retrofitService))
        ).get(TrendingRepositoriesViewModel::class.java)

        binding.shimmerViewContainer.startShimmer()
        viewModel.getTrendingRepositories()
        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter = TrendingListAdapter(it)
            binding.recyclerview.adapter = adapter
            binding.shimmerViewContainer.stopShimmer()
            binding.shimmerViewContainer.visibility = View.GONE
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {

        })

        return binding.root
    }


    companion object {
    }
}