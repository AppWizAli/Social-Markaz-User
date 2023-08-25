package com.socialmarkaz.app.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socialmarkaz.app.Adapters.HomePagerAdapter
import com.socialmarkaz.app.R
import com.socialmarkaz.app.databinding.FragmentHomeBinding

class FragmentHome : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.viewPager.adapter=HomePagerAdapter(childFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        /*
        val viewPager: binding
        val tabLayout: TabLayout = view.findViewById(R.id.tabLayout)

        val adapter = HomePagerAdapter(childFragmentManager)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)*/

        return root
    }

}