package com.dargoz.capstone.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dargoz.capstone.databinding.SearchListLayoutBinding
import com.dargoz.domain.models.Anime
import com.squareup.picasso.Picasso


class SearchListAdapter : RecyclerView.Adapter<SearchListAdapter.SearchViewHolder>() {
    private lateinit var binding : SearchListLayoutBinding
    private var searchList: List<Anime> = ArrayList()
    private lateinit var listener: OnClick

    interface OnClick {
        fun onItemClick(anime: Anime)
    }

    fun setListener(listener: OnClick) {
        this.listener = listener
    }

    fun setSearchList(animeList: List<Anime>) {
        this.searchList = animeList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = SearchListLayoutBinding.inflate(inflater, parent, false)
        return SearchViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bindView(position)
    }

    override fun getItemCount(): Int = searchList.size


    inner class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindView(position: Int) {
            Picasso.get()
                .load(searchList[position].imageUrl)
                .into(binding.searchImage)
            binding.searchTitle.text = searchList[position].title
            binding.searchTypeValue.text = searchList[position].type
            binding.searchEpisodeValue.text = searchList[position].episodes.toString()
            binding.searchScoreValue.text = searchList[position].score.toString()
            binding.searchItemContainer.setOnClickListener {
                listener.onItemClick(searchList[position])
            }

        }


    }
}