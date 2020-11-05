package com.dargoz.capstone.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dargoz.capstone.R
import com.dargoz.capstone.databinding.ReviewListLayoutBinding
import com.dargoz.domain.models.Review
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ReviewListAdapter : RecyclerView.Adapter<ReviewListAdapter.ReviewViewHolder>() {
    private var _binding: ReviewListLayoutBinding? = null
    private val binding get() = _binding!!

    var reviewList : List<Review> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        _binding = ReviewListLayoutBinding.inflate(inflater, parent, false)
        return ReviewViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bindView(position)
    }

    override fun getItemCount(): Int = reviewList.size


    inner class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(position: Int) {
            Picasso.get()
                .load(reviewList[position].reviewer.imageUrl)
                .into(binding.reviewerImage)
            binding.reviewerName.text = reviewList[position].reviewer.username
            binding.reviewHelpfulCount.text =
                itemView.resources.getString(R.string.helpful_string_format,
                    reviewList[position].helpfulCount)
            binding.reviewerOverallRating.text = itemView.resources
                .getString(R.string.overall_rating_string_format, 
                    reviewList[position].reviewer.scores.overall)

            binding.reviewerContent.text = reviewList[position].content
            val dateFormat = SimpleDateFormat("MMM dd, yyy", Locale.getDefault())
            binding.reviewDate.text = dateFormat.format(reviewList[position].date)
        }

    }
}