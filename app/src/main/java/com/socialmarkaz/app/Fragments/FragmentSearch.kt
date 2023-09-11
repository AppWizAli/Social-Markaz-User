package com.socialmarkaz.app.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.enfotrix.lifechanger.Utils
import com.socialmarkaz.app.Adapters.ProductAdapter
import com.socialmarkaz.app.Constants
import com.socialmarkaz.app.Models.Product
import com.socialmarkaz.app.Models.ProductViewModel
import com.socialmarkaz.app.Models.SearchHistoryDialogFragment
import com.socialmarkaz.app.R
import com.socialmarkaz.app.SharedPrefManager
import com.socialmarkaz.app.Ui.ActivityProductDetails
import com.socialmarkaz.app.Ui.ActivitySearchResult
import com.socialmarkaz.app.databinding.FragmentSearchBinding

class FragmentSearch : Fragment() , ProductAdapter.OnItemClickListener, SearchHistoryDialogFragment.SearchHistoryDialogListener{

    private lateinit var utils: Utils
    private lateinit var constants: Constants
    private lateinit var mContext: Context
    private lateinit var sharedPrefManager: SharedPrefManager
    private  val SEARCH_HISTORY_KEY = "search_history"

    private val productViewModel: ProductViewModel by viewModels()


    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root


        constants = Constants()
        mContext = requireContext()
        utils = Utils(requireContext())
        sharedPrefManager = SharedPrefManager(requireContext())

        binding.searchView.setOnSearchClickListener {
            showSearchHistory()
        }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                addSearchQueryToHistory(query)
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }
        })



        binding.rvRecProducts.layoutManager=GridLayoutManager(requireContext(),2)
        binding.rvRecProducts.adapter=productViewModel.getRecProductAdapter(requireContext(),this)




        return root
    }

    override fun onItemClick(product: Product) {
       startActivity(Intent(mContext,ActivityProductDetails::class.java).putExtra("product",product))

    }

    override fun onDeleteClick(product: Product) {
        Toast.makeText(requireContext(), "clicked", Toast.LENGTH_SHORT).show()

    }


    private fun showSearchHistory() {
        val dialogFragment = SearchHistoryDialogFragment()
        dialogFragment.show(childFragmentManager, "SearchHistoryDialogFragment")
    }
    fun addSearchQueryToHistory(query: String) {
        val sharedPreferences = mContext.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val history = sharedPreferences.getStringSet(SEARCH_HISTORY_KEY, HashSet())?.toMutableSet() ?: mutableSetOf()
        history.add(query)
        sharedPreferences.edit().putStringSet(SEARCH_HISTORY_KEY, history).apply()
    }

    override fun onSearchHistoryItemClick(query: String) {
        binding.searchView.setQuery(query, true)
    }

}
