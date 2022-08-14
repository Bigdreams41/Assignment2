package com.example.assignment2.view.adapter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.R
import com.example.assignment2.databinding.MusicItemBinding
import com.example.assignment2.model.MusicItem
import com.squareup.picasso.Picasso

class MusicAdapter(): ListAdapter<MusicItem, MusicAdapter.ViewHolder>(MusicItemDiffUtil) {

    class ViewHolder(private val binding: MusicItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(dataItem: MusicItem) {

            Picasso.get().load(dataItem.artworkUrl60).
            placeholder(R.drawable.movies)
                .error(R.drawable.ic_baseline_error_24)
                .into(binding.imageSong)

        binding.songName.text = dataItem.collectionName
        binding.songAuthor.text = dataItem.artistName
        binding.songPrice.text = dataItem.collectionPrice

            binding.root.setOnClickListener{
                dataItem.previewUrl
                Toast.makeText(itemView.context, "Play Music", Toast.LENGTH_LONG).show()

                val intent = Intent()
                val bundle = Bundle()
                intent.setAction(android.content.Intent.ACTION_VIEW)
                intent.setDataAndType(Uri.parse(dataItem.previewUrl), "audio/*")
                startActivity(binding.root.context,intent,bundle)

            }
        }
}

object MusicItemDiffUtil: DiffUtil.ItemCallback<MusicItem>() {
    override fun areItemsTheSame(oldItem: MusicItem, newItem: MusicItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MusicItem, newItem: MusicItem): Boolean {
        return oldItem.collectionName == newItem.collectionName
    }
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        MusicItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}
