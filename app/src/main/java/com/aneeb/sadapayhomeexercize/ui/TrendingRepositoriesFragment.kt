package com.aneeb.sadapayhomeexercize.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aneeb.sadapayhomeexercize.databinding.FragmentTrendingRepositoriesBinding
import com.aneeb.sadapayhomeexercize.model.Item
import com.aneeb.sadapayhomeexercize.network.RetrofitService
import com.aneeb.sadapayhomeexercize.network.TrendingRepoServiceRepository
import com.aneeb.sadapayhomeexercize.ui.adapter.TrendingListAdapter
import com.aneeb.sadapayhomeexercize.ui.viewmodel.TrendingRepositoriesViewModel
import com.aneeb.sadapayhomeexercize.ui.viewmodel.ViewModelFactory
import com.aneeb.sadapayhomeexercize.util.CommonMethods

/**
 * A simple [Fragment] subclass.
 * Use the [TrendingRepositoriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TrendingRepositoriesFragment : Fragment() {

    private val TAG = TrendingRepositoriesFragment::class.java.simpleName
    lateinit var viewModel: TrendingRepositoriesViewModel
    private val retrofitService = RetrofitService.getInstance()
    var adapter: TrendingListAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTrendingRepositoriesBinding.inflate(inflater)

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(TrendingRepoServiceRepository(retrofitService))
        ).get(TrendingRepositoriesViewModel::class.java)

        //run first time when app runs
        getRepositories(binding)

        //observe livedate when data is fetch from the api
        viewModel.movieList.observe(viewLifecycleOwner, Observer { items ->
            onResponseSuccess(binding, items)
        })

        //observe livedate when data is fetch from the api
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
            Log.d(TAG, "onResponseFailure: $errorMessage")
            onResponseFailure(binding)
        })

        //on swipe callback
        binding.swipeRefresh.setOnRefreshListener {
            hitRequest()
        }

        //retry button click
        binding.contentRetry.btnRetry.setOnClickListener {
            if (CommonMethods.isNetworkConnected(requireContext())) {
                getRepositories(binding)
            } else {
                Toast.makeText(requireContext(), "No Internet Connection!", Toast.LENGTH_LONG)
                    .show()
            }
        }

        return binding.root
    }

    //call api request to server
    private fun hitRequest() {
        viewModel.getTrendingRepositories()
    }

    private fun getRepositories(binding: FragmentTrendingRepositoriesBinding) {
        if (CommonMethods.isNetworkConnected(requireContext())) {
            hideRetry(binding)
            showShimmerLoader(binding)
            startShimmerLoader(binding)
            hitRequest()
        } else {
            showRetry(binding)
            hideShimmerLoader(binding)
            hideRecyclerView(binding)
        }
    }

    private fun onResponseSuccess(
        binding: FragmentTrendingRepositoriesBinding,
        items: List<Item>?
    ) {
        try {
            Log.d(TAG, "onResponseSuccess: $items")
            showRecyclerView(binding)
            adapter = TrendingListAdapter(items ?: ArrayList<Item>())
            binding.recyclerview.adapter = adapter
            binding.swipeRefresh.isRefreshing = false
            stopShimmerLoader(binding)
            hideRetry(binding)
            hideShimmerLoader(binding)
        } catch (e: Exception) {
            Log.d(TAG, "onException: $e")
            viewModel.errorMessage.postValue(e.toString())
        }
    }

    private fun onResponseFailure(binding: FragmentTrendingRepositoriesBinding) {
        showRetry(binding)
        binding.swipeRefresh.isRefreshing = false
        stopShimmerLoader(binding)
        hideShimmerLoader(binding)
        hideRecyclerView(binding)
    }

    private fun startShimmerLoader(binding: FragmentTrendingRepositoriesBinding) {
        binding.contentShimmer.shimmerViewContainer.startShimmer()
    }

    private fun stopShimmerLoader(binding: FragmentTrendingRepositoriesBinding) {
        binding.contentShimmer.shimmerViewContainer.stopShimmer()
    }

    private fun showRetry(binding: FragmentTrendingRepositoriesBinding) {
        binding.contentRetry.layoutRetry.visibility = View.VISIBLE
    }

    private fun hideRetry(binding: FragmentTrendingRepositoriesBinding) {
        binding.contentRetry.layoutRetry.visibility = View.GONE
    }

    private fun showShimmerLoader(binding: FragmentTrendingRepositoriesBinding) {
        binding.contentShimmer.shimmerViewContainer.visibility = View.VISIBLE
    }

    private fun hideShimmerLoader(binding: FragmentTrendingRepositoriesBinding) {
        binding.contentShimmer.shimmerViewContainer.visibility = View.GONE
    }

    private fun showRecyclerView(binding: FragmentTrendingRepositoriesBinding) {
        binding.recyclerview.visibility = View.VISIBLE
    }

    private fun hideRecyclerView(binding: FragmentTrendingRepositoriesBinding) {
        binding.recyclerview.visibility = View.GONE
    }

}