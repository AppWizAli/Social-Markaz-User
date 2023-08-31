package com.socialmarkaz.app.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.socialmarkaz.app.Fragments.FragmentFollowing
import com.socialmarkaz.app.Fragments.FragmentInvest
import com.socialmarkaz.app.Fragments.FragmentRecomended

class HomePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val fragments = listOf(
        FragmentRecomended(),
        FragmentFollowing(),
        FragmentInvest()
    )

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Recomended"
            1 -> "Following"
            2 -> "Invest"
            else -> null
        }
    }
}
