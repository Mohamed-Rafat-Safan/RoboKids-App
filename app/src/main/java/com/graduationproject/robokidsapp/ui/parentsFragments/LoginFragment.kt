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
import com.google.firebase.auth.FirebaseAuth
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.databinding.FragmentLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginFragment : Fragment(), TextWatcher {
    private lateinit var mNavController: NavController
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.apply {
            etEmailLogin.addTextChangedListener(this@LoginFragment)
            etPasswordLogin.addTextChangedListener(this@LoginFragment)
        }

        binding.tvSignUp.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            mNavController.navigate(action)
        }



        binding.forgetPasswordBlock.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment()
            mNavController.navigate(action)
        }

        binding.btnLogin.setOnClickListener {
            if(checkInputsStatus()){
                // function to sign in parent in firebase
                loginParent()
            }

        }

        return binding.root
    }


    private fun loginParent() {
        binding.apply {
            progressBarSignIn.visibility = View.VISIBLE
            val email = etEmailLogin.text.toString().trim()
            val password = etPasswordLogin.text.toString().trim()

            CoroutineScope(Dispatchers.IO).launch {
                auth.signInWithEmailAndPassword(email, password) .addOnSuccessListener {
                    progressBarSignIn.visibility = View.GONE
                    val action = LoginFragmentDirections.actionLoginFragmentToParentsHomeFragment()
                    mNavController.navigate(action)
                }.addOnFailureListener {
                    progressBarSignIn.visibility = View.GONE
                    Toast.makeText(activity, "Login Is Failed : "+it.message, Toast.LENGTH_SHORT).show()
                }

            }
        }
    }


    private fun checkInputsStatus(): Boolean {
        binding.apply {
            if (btnLogin.isEnabled) {
                val email = etEmailLogin.text.toString().trim()
                val password = etPasswordLogin.text.toString().trim()

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etEmailLogin.error = getString(R.string.email_error)
                    etPasswordLogin.requestFocus()
                    return false //function ديه معناها انه لو الشرط ده اتنفذ .. كده هو هيخرج من ال
                }

                if (password.length < 6) {
                    etPasswordLogin.error = getString(R.string.password_error)
                    etPasswordLogin.requestFocus()
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
            btnLogin.isEnabled =
                etEmailLogin.text!!.trim().isNotEmpty() &&
                etPasswordLogin.text!!.trim().isNotEmpty()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}