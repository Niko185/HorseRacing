package com.example.horseracing.presentation.activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.horseracing.presentation.fragments.race.RaceFragment

class MainViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> RaceFragment()
            1 -> RaceFragment()
            else -> RaceFragment()
        }
    }
}
