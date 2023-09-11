package com.socialmarkaz.app.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.socialmarkaz.app.Adapters.ProductAdapter
import com.socialmarkaz.app.Models.Product
import com.socialmarkaz.app.Models.ProductViewModel
import com.socialmarkaz.app.R
import com.socialmarkaz.app.databinding.FragmentFollowingBinding
import com.socialmarkaz.app.databinding.FragmentRecomendedBinding

class FragmentFollowing : Fragment()  , ProductAdapter.OnItemClickListener{
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
        binding.rvFollProduct.adapter = productViewModel.getFollProductAdapter(requireContext(),this)
        return root
    }

    override fun onItemClick(product: Product) {
        Toast.makeText(requireContext(), "clicked", Toast.LENGTH_SHORT).show()

    }

    override fun onDeleteClick(product: Product) {
        Toast.makeText(requireContext(), "clicked", Toast.LENGTH_SHORT).show()

    }


}
