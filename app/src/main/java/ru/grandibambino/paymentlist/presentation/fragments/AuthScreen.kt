package ru.grandibambino.paymentlist.presentation.fragments

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
import org.koin.android.viewmodel.ext.android.viewModel
import ru.grandibambino.paymentlist.R
import ru.grandibambino.paymentlist.data.DataResult
import ru.grandibambino.paymentlist.utils.AppTextWatcher
import ru.grandibambino.paymentlist.utils.navigate
import ru.grandibambino.paymentlist.utils.toast


class AuthScreen : BaseFragment(R.layout.fragment_auth_screen) {

    private lateinit var loginET: EditText
    private lateinit var passwordEt: EditText
    private lateinit var singUpBtn: Button


    private val viewModel by viewModel<AuthViewModel>()

    override fun onStart() {
        super.onStart()
        initView(rootView)
        setLogin()
        setPassword()
        singUp()
        successAuth()
    }

    private fun successAuth() {
        viewModel.authLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is DataResult.Success<*> -> {
                    navigate().navigate(R.id.playmentsScreen)
                    hideKeyboard()
                }
                is DataResult.Error -> {
                    toast(it.error.message.toString())
                }
            }
        }
    }

    private fun hideKeyboard() {
        val view = activity?.currentFocus
        view?.let { v ->
            val imm: InputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }

    private fun setLogin() {
        loginET.addTextChangedListener(AppTextWatcher {
            viewModel.setLogin(it.toString())
        })
    }

    private fun setPassword() {
        passwordEt.addTextChangedListener(AppTextWatcher {
            viewModel.setPassword(it.toString())
        })
    }

    private fun singUp() {
        singUpBtn.setOnClickListener {
            viewModel.auth()

        }
    }

    private fun initView(rootView: View) {
        loginET = rootView.findViewById(R.id.auth_login)
        passwordEt = rootView.findViewById(R.id.auth_password)
        singUpBtn = rootView.findViewById(R.id.sing_up_btn)
    }

}