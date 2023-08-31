package com.socialmarkaz.app.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socialmarkaz.app.R
import com.socialmarkaz.app.Ui.ActivitySetting
import com.socialmarkaz.app.databinding.FragmentCartBinding
import com.socialmarkaz.app.databinding.FragmentMyAccountBinding

class FragmentMyAccount : Fragment() {
    private var _binding:FragmentMyAccountBinding?=null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyAccountBinding.inflate(inflater, container, false)
        val root:View=binding.root
        binding.setting.setOnClickListener {
            startActivity(Intent(requireContext(),ActivitySetting:: class.java))
        }
        return root
    }

}