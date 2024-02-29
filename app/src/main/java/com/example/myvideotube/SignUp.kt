package com.example.myvideotube

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.myvideotube.data.User
import com.example.myvideotube.databinding.FragmentSignUpBinding
import com.example.myvideotube.viewmodel.MyVideoTubeViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUp : Fragment() {

    private lateinit var signUpBinding: FragmentSignUpBinding
    private val myVideoTubeViewModel:MyVideoTubeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signUpBinding = FragmentSignUpBinding.inflate(layoutInflater,container,false)
        return signUpBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signUpBinding.btnSubmit.setOnClickListener {
            val email = signUpBinding.etEmail.text.toString()
            val channelName = signUpBinding.etChannleName.text.toString()
            val password = signUpBinding.etPassword.text.toString()
            val confirmPassword = signUpBinding.etConfirm.text.toString()

            if(email.isNotBlank() && channelName.isNotBlank() && password.isNotBlank() && confirmPassword.isNotBlank()){
                if(password.equals(confirmPassword)){
                    val user = User(channelName,"",email,null,null,null)
                    myVideoTubeViewModel.createUserWithPassword(email, password,user)
                    signUpBinding.etEmail.text.clear()
                    signUpBinding.etConfirm.text.clear()
                    signUpBinding.etChannleName.text.clear()
                    signUpBinding.etPassword.text.clear()
                }
            }else{
                Toast.makeText(context,"Blank Fields are not allowed",Toast.LENGTH_SHORT).show()

            }
        }
    }


}