package com.socialmarkaz.app.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.enfotrix.lifechanger.Utils
import com.socialmarkaz.app.Adapters.ProductAdapter
import com.socialmarkaz.app.Constants
import com.socialmarkaz.app.Models.Product
import com.socialmarkaz.app.Models.ProductViewModel
import com.socialmarkaz.app.R
import com.socialmarkaz.app.SharedPrefManager
import com.socialmarkaz.app.Ui.ActivityProductDetails
import com.socialmarkaz.app.databinding.FragmentInvestBinding
import com.socialmarkaz.app.databinding.FragmentRecomendedBinding
import kotlinx.coroutines.launch

class FragmentRecomended : Fragment() , ProductAdapter.OnItemClickListener{

    private lateinit var utils: Utils
    private lateinit var constants: Constants
    private lateinit var mContext: Context
    private lateinit var sharedPrefManager: SharedPrefManager


    private val productViewModel: ProductViewModel by viewModels()


    private var _binding: FragmentRecomendedBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        constants = Constants()
        mContext = requireContext()
        utils = Utils(requireContext())
        sharedPrefManager = SharedPrefManager(requireContext())


        _binding = FragmentRecomendedBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.rvRecProduct.layoutManager = GridLayoutManager(requireContext(),2)
        binding.rvRecProduct.adapter = productViewModel.getRecProductAdapter(requireContext(),this)

        return root
    }

    override fun onItemClick(product: Product) {
     startActivity(Intent(requireContext(),ActivityProductDetails::class.java).putExtra("product", product))
    }

    override fun onDeleteClick(product: Product) {
        Toast.makeText(requireContext(), "clicked", Toast.LENGTH_SHORT).show()

    }


}
