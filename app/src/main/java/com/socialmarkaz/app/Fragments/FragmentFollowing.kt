package com.socialmarkaz.app.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.socialmarkaz.app.Models.ProductViewModel
import com.socialmarkaz.app.R
import com.socialmarkaz.app.databinding.FragmentFollowingBinding
import com.socialmarkaz.app.databinding.FragmentRecomendedBinding

class FragmentFollowing : Fragment() {
    private var _binding: FragmentFollowingBinding? =null


    private val productViewModel: ProductViewModel by viewModels()
    val binding get()=_binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        val root:View=binding.root

        binding.rvFollProduct.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvFollProduct.adapter = productViewModel.getFollProductAdapter()
        return root
    }


    }
