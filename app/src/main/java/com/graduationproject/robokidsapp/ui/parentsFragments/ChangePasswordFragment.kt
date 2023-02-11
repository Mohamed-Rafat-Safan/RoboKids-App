package com.graduationproject.robokidsapp.ui.parentsFragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.databinding.FragmentChangePasswordBinding
import com.graduationproject.robokidsapp.databinding.FragmentRegisterBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class ChangePasswordFragment : Fragment(), TextWatcher {
    private var _binding: FragmentChangePasswordBinding? = null
    private val binding get() = _binding!!

    private lateinit var mNavController: NavController

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChangePasswordBinding.inflate(inflater, container, false)

        binding.apply {
            etOldPassword.addTextChangedListener(this@ChangePasswordFragment)
            etNewPassword.addTextChangedListener(this@ChangePasswordFragment)
            etConfirmNewPassword.addTextChangedListener(this@ChangePasswordFragment)
        }


        binding.btnChangePassword.setOnClickListener {
            val oldPassword = binding.etOldPassword.text.toString().trim()
            val newPassword = binding.etNewPassword.text.toString().trim()

            if (checkInputsStatus()) {
                val parent = auth.currentUser!!
                if (parent != null && parent.email != null) {
                    CoroutineScope(Dispatchers.IO).launch {
                        val credential = EmailAuthProvider.getCredential(parent.email!!, oldPassword)

                        parent.reauthenticate(credential).addOnSuccessListener {
                            if(newPassword != oldPassword){
                                parent.updatePassword(newPassword).addOnSuccessListener {
                                    Toast.makeText(activity, ""+getString(R.string.password_update) , Toast.LENGTH_SHORT).show()

                                    auth.signOut()

                                    val action = ChangePasswordFragmentDirections.actionChangePasswordFragmentToLoginFragment()
                                    mNavController.navigate(action)

                                }.addOnFailureListener {
                                    Toast.makeText(activity, "Failure"+ it.message , Toast.LENGTH_SHORT).show()
                                }
                            }else{
                                Toast.makeText(activity, ""+getString(R.string.different_password) , Toast.LENGTH_SHORT).show()
                            }
                        }.addOnFailureListener {
                            Toast.makeText(activity, "" + getString(R.string.password_match), Toast.LENGTH_SHORT).show()
                        }

                    }
                }
            }
        }


        return binding.root
    }


    private fun checkInputsStatus(): Boolean {
        binding.apply {
            if (btnChangePassword.isEnabled) {
                val newPassword = etNewPassword.text.toString().trim()
                val confirmNewPassword = etConfirmNewPassword.text.toString().trim()

                if (newPassword.length < 6) {
                    etNewPassword.error = getString(R.string.password_error)
                    etNewPassword.requestFocus()
                    return false
                }

                if (confirmNewPassword != newPassword) {
                    etConfirmNewPassword.error = getString(R.string.confirmPass_error)
                    etConfirmNewPassword.requestFocus()
                    return false
                }

                return true
            }
        }
        return false
    }


    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun afterTextChanged(p0: Editable?) {
        binding.apply {
            btnChangePassword.isEnabled =
                etOldPassword.text!!.trim().isNotEmpty() &&
                etNewPassword.text!!.trim().isNotEmpty() &&
                etConfirmNewPassword.text!!.trim().isNotEmpty()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}