package com.socialmarkaz.app.Fragments

import android.content.Context
import android.content.Intent
import android.graphics.drawable.GradientDrawable.Orientation
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.enfotrix.lifechanger.Utils
import com.socialmarkaz.app.Adapters.ProductAdapter
import com.socialmarkaz.app.Adapters.RecCartProductAdapter
import com.socialmarkaz.app.Constants
import com.socialmarkaz.app.Models.Product
import com.socialmarkaz.app.Models.ProductViewModel
import com.socialmarkaz.app.R
import com.socialmarkaz.app.SharedPrefManager
import com.socialmarkaz.app.Ui.ActivityProductDetails
import com.socialmarkaz.app.Ui.ActivityProductPurchased
import com.socialmarkaz.app.databinding.FragmentCartBinding
import com.socialmarkaz.app.databinding.FragmentHomeBinding

class FragmentCart : Fragment(), ProductAdapter.OnItemClickListener,RecCartProductAdapter.OnItemClickListener {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!


    private lateinit var utils: Utils
    private lateinit var constants: Constants
    private lateinit var mContext: Context
    private lateinit var sharedPrefManager: SharedPrefManager


    private val productViewModel: ProductViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val root: View = binding.root

        constants = Constants()
        mContext = requireContext()
        utils = Utils(requireContext())
        sharedPrefManager = SharedPrefManager(requireContext())
        binding.rvRecCartPro.layoutManager =LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        binding.rvRecCartPro.adapter =RecCartProductAdapter(sharedPrefManager.getRecProductList(),mContext,this)
        binding.rvCartList.layoutManager = GridLayoutManager(mContext,2,LinearLayoutManager.HORIZONTAL,false)
        binding.rvCartList.adapter = productViewModel.getCartProductAdapter(requireContext(), this)

        binding.layProductPurchasedList.setOnClickListener {
            startActivity(Intent(mContext,ActivityProductPurchased::class.java))
        }


        return root
    }

    override fun onItemClick(product: Product) {
startActivity(Intent(mContext,ActivityProductDetails::class.java).putExtra("product",product))
    }
    override fun onDeleteClick(product: Product) {

    }

}