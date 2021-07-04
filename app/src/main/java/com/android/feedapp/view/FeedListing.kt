package com.android.feedapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController


import androidx.recyclerview.widget.LinearLayoutManager
import com.android.feedapp.R
import com.android.feedapp.view.adapters.FeedListingAdapter
import com.android.feedapp.viewmodels.FeedsViewModel
import com.android.feedapp.databinding.ListingBinding

import com.android.feedapp.models.Result

class FeedListing : Fragment(), FeedListingAdapter.OnItemClick {
    private val feedsViewModel: FeedsViewModel by viewModels()
    private lateinit var binding: ListingBinding
    private lateinit var feedListingAdapter: FeedListingAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        feedsViewModel.getFeeds()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        feedsViewModel.screenState.observe(viewLifecycleOwner, { screen ->
            when (screen) {
                ScreenState.ERROR -> {
                    manageScreen(-1)
                }
                ScreenState.NETWORK_ERROR -> {
                    manageScreen(-3)
                    Toast.makeText(context, "Please turn your internet ON", Toast.LENGTH_LONG)
                        .show()
                }
                ScreenState.DATA_LOADED -> {
                    setAdapterAndShowData()
                }
                ScreenState.DATA_LOADING -> {
                    manageScreen(0)
                } else -> {

                }
            }
        })
    }

    private fun manageScreen(state: Int) {
        when (state) {
            -1 -> {
                binding.loading.visibility = View.GONE
                binding.listing.visibility = View.GONE
                binding.error.visibility = View.VISIBLE
                binding.error.setText(resources.getString(R.string.something_went_wrong))
            }
            0 -> {
                binding.loading.visibility = View.VISIBLE
                binding.listing.visibility = View.GONE
                binding.error.visibility = View.GONE
            }
            -3 -> {
                binding.loading.visibility = View.GONE
                binding.listing.visibility = View.GONE
                binding.error.visibility = View.VISIBLE
                binding.error.setText(resources.getString(R.string.internet_issue))
            }
            else -> {
                binding.loading.visibility = View.GONE
                binding.listing.visibility = View.VISIBLE
                binding.error.visibility = View.GONE
            }
        }
    }

    private fun setAdapterAndShowData() {
        manageScreen(1)
        binding.listing.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        feedListingAdapter = FeedListingAdapter(feedsViewModel.data, this)
        binding.listing.adapter = feedListingAdapter
    }


    override fun onItemPressed(item: Result) {
        navigateToDetails(findNavController(), item)
    }

}