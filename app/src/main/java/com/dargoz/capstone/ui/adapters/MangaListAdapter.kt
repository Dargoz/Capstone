package com.dargoz.capstone.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dargoz.capstone.databinding.MangaListLayoutBinding
import com.dargoz.domain.models.Manga
import com.squareup.picasso.Picasso

class MangaListAdapter : RecyclerView.Adapter<MangaListAdapter.MangaViewHolder>() {
    private lateinit var binding: MangaListLayoutBinding
    private var mangaList: List<Manga> = ArrayList()
    private lateinit var listener: OnClick

    interface OnClick {
        fun onItemClick(manga: Manga)
    }

    fun setMangaList(mangaList: List<Manga>) {
        this.mangaList = mangaList
    }

    fun setListener(listener: OnClick) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = MangaListLayoutBinding.inflate(inflater, parent, false)
        return MangaViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        holder.bindView(position)
    }

    override fun getItemCount(): Int {
        return mangaList.size
    }

    inner class MangaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(position: Int) {
            val manga = mangaList[position]
            Picasso.get()
                .load(manga.imageUrl)
                .resize(120, 180)
                .centerCrop()
                .into(binding.mangaItemPoster)
            binding.mangaItemTitle.text = manga.title
            binding.mangaItemCard.setOnClickListener {
                listener.onItemClick(manga)
            }

        }

    }

}
