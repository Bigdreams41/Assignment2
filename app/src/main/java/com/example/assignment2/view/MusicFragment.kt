package com.example.assignment2.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment2.R
import com.example.assignment2.databinding.MusicItemBinding
import com.example.assignment2.databinding.MusicListBinding
import com.example.assignment2.model.MusicItem
import com.example.assignment2.view.adapter.MusicAdapter
import com.example.assignment2.viewmodel.MusicViewModel

class MusicFragment(private val position: Int): Fragment(){

  private lateinit var binding: MusicListBinding

    private val viewModel: MusicViewModel by lazy {
        ViewModelProvider(this)[MusicViewModel::class.java]
    }

    private lateinit var adapter: MusicAdapter

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MusicListBinding.inflate(inflater, container, false)
        when(position){
            0 -> {
                viewModel.itunesMusic("rock")
                binding.root.setBackgroundResource(R.color.red)
            }
            1 -> {
                viewModel.itunesMusic(" classic")
                binding.root.setBackgroundResource(R.color.blue)
            }
            2 -> {
                viewModel.itunesMusic("pop")
                binding.root.setBackgroundResource(R.color.Pink)
            }

        }
        binding.refresh.setOnRefreshListener {
            binding.refresh.isRefreshing = false
        }

        initObservable()

        adapter = MusicAdapter()

        binding.songList.adapter = adapter
        binding.songList.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    private fun initObservable() {

        viewModel.musicSearchResult.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, "resultCount ${viewModel.resultCount}", Toast.LENGTH_SHORT).show()
            updateAdapter(it) })
    }

    private fun updateAdapter(dataSet: List<MusicItem>) {
        adapter.submitList(dataSet)
    }
}