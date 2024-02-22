package com.example.myvideotube

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myvideotube.databinding.FragmentSignInBinding
import com.example.myvideotube.viewmodel.MyVideoTubeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignInFragment : Fragment() {


    private lateinit var signInBinding: FragmentSignInBinding
    private val viewModel:MyVideoTubeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signInBinding = FragmentSignInBinding.inflate(layoutInflater,container,false)
        return signInBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signInBinding.btnSubmit.setOnClickListener {
            val email = signInBinding.etEmail.text.toString()
            val password = signInBinding.etPassword.text.toString()
            if (email.isNotBlank() && password.isNotBlank()){
                viewModel.signIn(email, password).addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(context,"Login Successful",Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
                    }else{
                        Toast.makeText(context,"${it.exception?.message}",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }




}