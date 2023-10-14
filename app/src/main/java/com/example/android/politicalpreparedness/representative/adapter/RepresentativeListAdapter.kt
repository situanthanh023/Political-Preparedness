package com.example.android.politicalpreparedness.representative.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.politicalpreparedness.databinding.RepresentativeListItemBinding
import com.example.android.politicalpreparedness.representative.model.Representative

class RepresentativeListAdapter :
    ListAdapter<Representative, RepresentativeViewHolder>(RepresentativeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepresentativeViewHolder {
        return RepresentativeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RepresentativeViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class RepresentativeViewHolder private constructor(private val binding: RepresentativeListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private var twitterUrl: String? = null
    private var facebookUrl: String? = null
    private var wwwUrl: String? = null

    companion object {
        fun from(parent: ViewGroup): RepresentativeViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RepresentativeListItemBinding.inflate(layoutInflater, parent, false)
            return RepresentativeViewHolder(binding)
        }
    }

    fun bind(item: Representative) {
        binding.representative = item
        bindSocialLinks(item)
        binding.executePendingBindings()
    }

    private fun bindSocialLinks(item: Representative) {
        twitterUrl = getTwitterUrl(item)
        facebookUrl = getFacebookUrl(item)
        wwwUrl = getWwwUrl(item)

        if (twitterUrl == null) {
            binding.twitter.visibility = View.GONE
        } else {
            binding.twitter.setOnClickListener {
                startActivityUrlIntent(twitterUrl!!)
            }
        }
        if (facebookUrl == null) {
            binding.facebook.visibility = View.GONE
        } else {
            binding.facebook.setOnClickListener {
                startActivityUrlIntent(facebookUrl!!)
            }
        }

        if (wwwUrl == null) {
            binding.www.visibility = View.GONE
        } else {
            binding.www.setOnClickListener {
                startActivityUrlIntent(wwwUrl!!)
            }
        }
    }

    private fun getTwitterUrl(data: Representative): String? {
        val twitterChannels = data.official.channels?.filter {
            it.type == "Twitter"
        }

        val twitterChannel = twitterChannels?.firstOrNull()

        var twitterUrl: String? = null
        twitterChannel?.run {
            twitterUrl = "https://www.twitter.com/${twitterChannel.id}"
        }

        return twitterUrl
    }

    private fun getFacebookUrl(data: Representative): String? {
        val facebookChannels = data.official.channels?.filter {
            it.type == "Facebook"
        }

        val facebookChannel = facebookChannels?.firstOrNull()

        var facebookUrl: String? = null
        facebookChannel?.run {
            facebookUrl = "https://www.facebook.com/${facebookChannel.id}"
        }

        return facebookUrl
    }

    private fun getWwwUrl(data: Representative): String? {

        return data.official.urls?.firstOrNull()
    }

    private fun startActivityUrlIntent(url: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        itemView.context.startActivity(intent)
    }

}

class RepresentativeDiffCallback : DiffUtil.ItemCallback<Representative>() {
    override fun areItemsTheSame(oldItem: Representative, newItem: Representative): Boolean {
        return oldItem.official.name == newItem.official.name
    }

    override fun areContentsTheSame(oldItem: Representative, newItem: Representative): Boolean {
        return oldItem == newItem
    }
}