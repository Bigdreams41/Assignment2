package com.example.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assignment2.databinding.ActivityMainBinding
import com.example.assignment2.view.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

val musicArray = arrayOf(
    "Rock",
    "Classic",
    "Pop"
)
val iconArray = arrayOf(
    R.drawable.rock,
    R.drawable.classic,
    R.drawable.pop
)

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)


        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(iconArray[position])
            tab.text = musicArray[position]

        }.attach()
    }
}
