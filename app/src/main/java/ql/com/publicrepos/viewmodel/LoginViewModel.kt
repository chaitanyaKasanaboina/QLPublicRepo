package ql.com.publicrepos.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ql.com.publicrepos.R
import ql.com.publicrepos.model.LoginFormState
import ql.com.publicrepos.util.Constants
import java.util.regex.Pattern
import javax.inject.Inject


class LoginViewModel @Inject constructor() : ViewModel() {

    private val loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = loginForm
    val loginSuccess = MutableLiveData<Boolean>()

    fun login() {
        //place holder method to do some business logic before logging the user in
        loginSuccess.postValue(true)
    }

    fun loginDataChanged(username: String, password: String) {
        if (isUserNameValid(username) && isPasswordValid(password)) {
            loginForm.value = LoginFormState(isDataValid = true)
        } else {
            if (!isUserNameValid(username) && username.isNotEmpty()) {
                loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
            }
            if (!isPasswordValid(password) && password.isNotEmpty()) {
                loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
            }
        }
    }

    //username validation check
    private fun isUserNameValid(username: String): Boolean {
        if (username.contains('@')) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches()
        }
        return false
    }

    //password validation check
    private fun isPasswordValid(password: String): Boolean {
        val passwordREGEX = Pattern.compile(Constants.PASSWORD_PATTERN)
        return passwordREGEX.matcher(password).matches()
    }
}