package com.socialmarkaz.app.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.enfotrix.lifechanger.Utils
import com.socialmarkaz.app.Adapters.HomePagerAdapter
import com.socialmarkaz.app.Constants
import com.socialmarkaz.app.Models.Product
import com.socialmarkaz.app.Models.ProductViewModel
import com.socialmarkaz.app.R
import com.socialmarkaz.app.SharedPrefManager
import com.socialmarkaz.app.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class FragmentHome : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    private val productViewModel: ProductViewModel by viewModels()



    private lateinit var utils: Utils
    private lateinit var constants: Constants
    private lateinit var mContext: Context
    private lateinit var sharedPrefManager: SharedPrefManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root



        constants = Constants()
        mContext = requireContext()
        utils = Utils(requireContext())
        sharedPrefManager = SharedPrefManager(requireContext())


getData()


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





    fun getData() {
        utils.startLoadingAnimation()
        lifecycleScope.launch {
            productViewModel.getProducts()
                .addOnCompleteListener { task ->
                    utils.endLoadingAnimation()
                    if (task.isSuccessful) {
                        val RecProductlist = ArrayList<Product>()
                        val FollProductlist = ArrayList<Product>()
                        val CartProductlist = ArrayList<Product>()
                        val PurchProductlist = ArrayList<Product>()
                        if (task.result.size() > 0) {
                            for (document in task.result) {
                                if(document.toObject(Product::class.java).type==constants.PRODUCT_TYPE_REC) RecProductlist.add(document.toObject(Product::class.java))
                                else   if(document.toObject(Product::class.java).type==constants.PRODUCT_TYPE_CART)CartProductlist.add(document.toObject(Product::class.java))
                                else   if(document.toObject(Product::class.java).type==constants.PRODUCT_TYPE_FOLL)FollProductlist.add(document.toObject(Product::class.java))
                                else   if(document.toObject(Product::class.java).type==constants.PRODUCT_TYPE_PURCH)PurchProductlist.add(document.toObject(Product::class.java))
                            }
                            sharedPrefManager.putRecProductList(RecProductlist)
                            sharedPrefManager.putFollProductList(FollProductlist)
                            sharedPrefManager.putCartProductList(CartProductlist)
                            sharedPrefManager.putPurchProductList(PurchProductlist)
                        }
                    } else Toast.makeText(
                        requireContext(),
                        "SOMETHING_WENT_WRONG_MESSAGE",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnFailureListener {
                    utils.endLoadingAnimation()


                }
        }


    }





}