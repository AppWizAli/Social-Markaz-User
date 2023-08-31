package com.socialmarkaz.app.Fragments

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
import com.socialmarkaz.app.Models.Product
import com.socialmarkaz.app.Models.ProductViewModel
import com.socialmarkaz.app.R
import com.socialmarkaz.app.SharedPrefManager
import com.socialmarkaz.app.databinding.FragmentInvestBinding
import com.socialmarkaz.app.databinding.FragmentRecomendedBinding
import kotlinx.coroutines.launch

class FragmentRecomended : Fragment() {
    private lateinit var utils: Utils
    private val productViewModel: ProductViewModel by viewModels()
    private lateinit var sharedPrefManager: SharedPrefManager
    private var _binding: FragmentRecomendedBinding ?=null
    val binding get()=_binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        utils = Utils(requireContext())
        _binding = FragmentRecomendedBinding.inflate(inflater, container, false)
        val root:View=binding.root
sharedPrefManager=SharedPrefManager(requireContext())
getData()
        binding.rvRecProduct.layoutManager=GridLayoutManager(requireContext(),2)
        binding.rvRecProduct.adapter=productViewModel.getRecProductAdapter()

        return root
    }
    fun getData(){
utils.startLoadingAnimation()
        lifecycleScope.launch{
            productViewModel.getProducts()
                .addOnCompleteListener{task ->
                    utils.endLoadingAnimation()
                    if (task.isSuccessful) {
                        val productlist = ArrayList<Product>()
                        if(task.result.size()>0){
                            for (document in task.result) {
                                    productlist.add( document.toObject(Product::class.java))
                            }
                            sharedPrefManager.putRecProductList(productlist)
                        }
                    }
                    else Toast.makeText(requireContext(), "SOMETHING_WENT_WRONG_MESSAGE", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{
                    utils.endLoadingAnimation()


                }
        }


    }
    }
