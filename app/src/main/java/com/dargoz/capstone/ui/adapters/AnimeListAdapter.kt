package com.dargoz.capstone.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dargoz.capstone.databinding.AnimeListLayoutBinding
import com.dargoz.domain.models.Anime
import com.squareup.picasso.Picasso

class AnimeListAdapter : RecyclerView.Adapter<AnimeListAdapter.AnimeViewHolder>() {
    private lateinit var binding: AnimeListLayoutBinding

    private var animeList: List<Anime> = ArrayList()
    private lateinit var listener: OnClick

    interface OnClick {
        fun onItemClick(anime: Anime)
    }

    fun setAnimeList(animeList: List<Anime>) {
        this.animeList = animeList
    }

    fun setOnItemClickListener(listener: OnClick) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = AnimeListLayoutBinding.inflate(inflater, parent, false)
        return AnimeViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bindView(position)
    }

    override fun getItemCount(): Int = animeList.size


    inner class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(position: Int) {
            Picasso.get()
                .load(animeList[position].imageUrl)
                .resize(120, 180)
                .centerCrop()
                .into(binding.animeItemPoster)
            binding.animeItemTitle.text = animeList[position].title
            binding.animeItemCard.setOnClickListener {
                listener.onItemClick(animeList[position])
            }
        }

    }
}