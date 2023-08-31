package com.socialmarkaz.app.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socialmarkaz.app.R
import com.socialmarkaz.app.Ui.ActivityProductDetails
import com.socialmarkaz.app.Ui.ActivitySetting
import com.socialmarkaz.app.Ui.ActivityStoreDetails
import com.socialmarkaz.app.databinding.FragmentInvestBinding
import com.socialmarkaz.app.databinding.FragmentMyAccountBinding

class FragmentInvest : Fragment() {
    private var _binding: FragmentInvestBinding?=null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInvestBinding.inflate(inflater, container, false)
        val root:View=binding.root

        return root
    }

}