package com.android.feedapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.feedapp.utils.loadErrorImage
import com.android.feedapp.utils.loadImageFromRemote
import com.android.feedapp.databinding.DetailedItemBinding
import com.android.feedapp.models.Result

class FeedDetail : Fragment() {
    private lateinit var binding: DetailedItemBinding
    private var feedDetail: Result? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailedItemBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let { bundle ->
            feedDetail = FeedDetailArgs.fromBundle(bundle).feedItem
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detectImageToShow()?.let { url ->
            loadImageFromRemote(binding.banner , url)
        } ?: apply {
            loadErrorImage(binding.banner)
        }
        binding.title.text = feedDetail?.title
        binding.creationDate.text = feedDetail?.published_date
        binding.description.text = feedDetail?.abstract

    }

    private fun detectImageToShow() : String? {
        return if (feedDetail?.media?.isNotEmpty() == true && feedDetail?.media?.get(0)?.media_metadata?.isNotEmpty() == true){
            feedDetail?.media?.get(0)?.media_metadata?.get(feedDetail?.media?.get(0)?.media_metadata?.size!! - 1)?.url
        } else null
    }
}