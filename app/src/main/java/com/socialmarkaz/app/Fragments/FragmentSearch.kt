package com.socialmarkaz.app.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import com.socialmarkaz.app.R
import com.socialmarkaz.app.Ui.ActivitySearchResult
import com.socialmarkaz.app.databinding.FragmentSearchBinding

class FragmentSearch : Fragment() {


    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root
binding.searchCardView.setOnClickListener {
    startActivity(Intent(requireContext(),ActivitySearchResult::class.java))
}

        return root
    }
}
