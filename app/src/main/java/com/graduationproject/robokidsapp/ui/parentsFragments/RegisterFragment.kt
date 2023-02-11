package com.graduationproject.robokidsapp.ui.parentsFragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.databinding.FragmentRegisterBinding
import com.hbb20.countrypicker.models.CPCountry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class RegisterFragment : Fragment(), TextWatcher {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private var countryName = ""

    private lateinit var mNavController: NavController

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        binding.apply {
            etEmailRegister.addTextChangedListener(this@RegisterFragment)
            etPasswordRegister.addTextChangedListener(this@RegisterFragment)
            etConfirmPasswordRegister.addTextChangedListener(this@RegisterFragment)
        }

        binding.btnRegister.setOnClickListener {
            if (checkInputsStatus()) {
                // create parent on firebase
                createParent()
            }

        }


        binding.tvBackToLogin.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            mNavController.navigate(action)
        }

        binding.countryPicker.cpViewHelper.cpViewConfig.viewTextGenerator =
            { cpCountry: CPCountry ->
                countryName = cpCountry.name
//                Toast.makeText(activity, "${cpCountry.name} (${cpCountry.alpha2})", Toast.LENGTH_SHORT).show()
                "${cpCountry.name} (${cpCountry.alpha2})"
            }

        return binding.root

    }


    private fun createParent() {
        binding.apply {
            progressBarRegister.visibility = View.VISIBLE
            val email = etEmailRegister.text.toString().trim()
            val password = etPasswordRegister.text.toString().trim()

            CoroutineScope(Dispatchers.IO).launch {
                auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                    progressBarRegister.visibility = View.GONE
                    val action = RegisterFragmentDirections.actionRegisterFragmentToParentsDataFragment(countryName)
                    mNavController.navigate(action)
                }.addOnFailureListener {
                    progressBarRegister.visibility = View.GONE
                    Toast.makeText(
                        activity,
                        "Register Is Failed : " + it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


    private fun checkInputsStatus(): Boolean {
        binding.apply {
            if (btnRegister.isEnabled) {
                val email = etEmailRegister.text.toString().trim()
                val password = etPasswordRegister.text.toString().trim()
                val confirmPassword = etConfirmPasswordRegister.text.toString().trim()

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etEmailRegister.error = getString(R.string.email_error)
                    etEmailRegister.requestFocus()
                    return false //function ديه معناها انه لو الشرط ده اتنفذ .. كده هو هيخرج من ال
                }

                if (password.length < 6) {
                    etPasswordRegister.error = getString(R.string.password_error)
                    etPasswordRegister.requestFocus()
                    return false
                }


                if (confirmPassword != password) {
                    etConfirmPasswordRegister.error = getString(R.string.confirmPass_error)
                    etConfirmPasswordRegister.requestFocus()
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
            btnRegister.isEnabled =
                etEmailRegister.text!!.trim().isNotEmpty() &&
                etPasswordRegister.text!!.trim().isNotEmpty() &&
                 etConfirmPasswordRegister.text!!.trim().isNotEmpty()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}