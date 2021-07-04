package com.android.feedapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.feedapp.utils.loadErrorImage

import com.android.feedapp.utils.loadImageFromRemote
import com.android.feedapp.databinding.ListingItemBinding
import com.android.feedapp.models.Result


class FeedListingAdapter(private var list: ArrayList<Result>, var itemListener: OnItemClick) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemHolder(ListingItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ItemHolder
        holder.binding.itemName.text = list[position].title
        if (list[position].media?.isNotEmpty() == true && list[position].media?.get(0)?.media_metadata?.isNotEmpty() == true
            && list[position]?.media?.get(0)?.media_metadata?.get(0)?.url?.isNotBlank() == true
        ) {
            loadImageFromRemote(
                holder.binding.thumb,
                list[position]?.media?.get(0)?.media_metadata?.get(0)?.url
            )
        } else {
            loadErrorImage(holder.binding.thumb)
        }


        holder.itemView.setOnClickListener {
            itemListener.onItemPressed(list[position])
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    class ItemHolder(item: ListingItemBinding) : RecyclerView.ViewHolder(item.root) {
        var binding = item
    }

    interface OnItemClick {
        fun onItemPressed(feedItem: Result)
    }
}