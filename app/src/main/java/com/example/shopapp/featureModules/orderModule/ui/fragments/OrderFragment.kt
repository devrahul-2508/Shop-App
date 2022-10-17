package com.example.shopapp.featureModules.orderModule.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentOrderBinding
import com.example.shopapp.databinding.FragmentProfileBinding


class OrderFragment : Fragment() {


    private lateinit var binding: FragmentOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_order, container, false)
        return binding.root
    }


    }
