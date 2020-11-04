package com.dargoz.capstone.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dargoz.capstone.databinding.CharacterStaffListLayoutBinding
import com.dargoz.domain.models.Characters
import com.squareup.picasso.Picasso

class CharacterStaffListAdapter :
    RecyclerView.Adapter<CharacterStaffListAdapter.CharacterStaffViewHolder>() {
    private var _binding: CharacterStaffListLayoutBinding? = null
    private val binding get() = _binding!!

    var characterStaffList : List<Characters> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterStaffViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        _binding = CharacterStaffListLayoutBinding.inflate(inflater, parent, false)
        return CharacterStaffViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CharacterStaffViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bindView(position)
    }

    override fun getItemCount(): Int {
        return characterStaffList.size
    }

    inner class CharacterStaffViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(position: Int) {
            showCharacter(position)
            showVoiceActors(position)
        }

        private fun showCharacter(position: Int) {
            Picasso.get()
                .load(characterStaffList[position].imageUrl)
                .into(binding.characterImage)
            binding.characterName.text = characterStaffList[position].name
            binding.characterRole.text = characterStaffList[position].role
        }

        private fun showVoiceActors(position: Int) {
            if(characterStaffList[position].voiceActors.isNotEmpty()) {
                Picasso.get()
                    .load(characterStaffList[position].voiceActors[0].imageUrl)
                    .into(binding.voiceActorImage)
                binding.voiceActorName.text = characterStaffList[position].voiceActors[0].name
                binding.voiceActorLanguage.text = characterStaffList[position].voiceActors[0].language
            }

        }

    }
}