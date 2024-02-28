package com.example.myvideotube

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.myvideotube.databinding.FragmentHomeBinding
import com.example.myvideotube.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.checkerframework.checker.units.qual.A

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding
    private val sharedViewModel:SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.hideBottomNavigationBar.observe(viewLifecycleOwner){hide->
            homeBinding.bottomNavigation.visibility = if (hide) View.GONE else View.VISIBLE
        }
       // val navController = Navigation.findNavController(requireActivity(),R.id.homeContainer)
        val navController = childFragmentManager.findFragmentById(R.id.homeContainer)?.findNavController()
        if (navController != null) {
            NavigationUI.setupWithNavController(homeBinding.bottomNavigation,navController)
        }
       
    }

}