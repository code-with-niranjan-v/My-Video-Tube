package com.example.myvideotube

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myvideotube.databinding.FragmentStartBinding


class StartFragment : Fragment() {


    private lateinit var startBinding: FragmentStartBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        startBinding = FragmentStartBinding.inflate(inflater,container,false)
        return startBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startBinding.btnGetStarted.setOnClickListener {
            findNavController().navigate(R.id.signUp)
        }

        startBinding.btnLogIn.setOnClickListener {
            findNavController().navigate(R.id.signInFragment)
        }
    }


}