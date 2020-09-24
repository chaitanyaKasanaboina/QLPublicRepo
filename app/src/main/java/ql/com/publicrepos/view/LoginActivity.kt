package ql.com.publicrepos.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.login_activity.*
import ql.com.publicrepos.R
import ql.com.publicrepos.util.Constants
import ql.com.publicrepos.viewmodel.LoginViewModel
import ql.com.publicrepos.viewmodel.ViewModelFactory
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.login_activity)
        help.setOnClickListener {
            showAssistancePopUp()
        }
        val sharedPreferences = getSharedPreferences(Constants.ENVIRONMENT, Context.MODE_PRIVATE)
        if (sharedPreferences?.getString(Constants.USERNAME, Constants.BLANK)
                ?.isNotBlank() == true
        ) {
            username.setText(sharedPreferences.getString(Constants.USERNAME, Constants.BLANK))
            password.setText(sharedPreferences.getString(Constants.PASSWORD, Constants.BLANK))
            login.isEnabled = true
        }

        val loginViewModel =
            ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password are valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginSuccess.observe(this@LoginActivity, Observer {
            if (it) {
                val intent = Intent(this, RepoActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                //Complete and destroy login activity once successful
                finish()
            }
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login()
                }
                false
            }

            login.setOnClickListener {
                sharedPreferences.edit().putString(Constants.USERNAME, username.text.toString())
                    .apply()
                sharedPreferences.edit().putString(Constants.PASSWORD, password.text.toString())
                    .apply()
                loginViewModel.run { login() }
            }
        }
    }

    private fun showAssistancePopUp() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage(getString(R.string.invalid_username) + "\n" + getString(R.string.invalid_password))
            .setCancelable(false)
            // positive button text and action
            .setPositiveButton(
                getString(R.string.ok)
            ) { dialog, _ ->
                dialog.cancel()
            }
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle(getString(R.string.username_password_help))
        // show alert dialog
        alert.show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })

}