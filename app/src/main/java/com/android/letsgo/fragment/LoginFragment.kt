package com.android.letsgo.fragment

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.android.letsgo.R
import com.android.letsgo.activity.MainActivity
import com.android.letsgo.activity.WelcomeActivity
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {

    private var auth: FirebaseAuth? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                LoginScreen()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): LoginFragment = LoginFragment()
    }

    @Composable
    @Preview
    fun LoginScreen() {
        var back = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
        val textFieldColor = Color.White
        val backgroundColor = Color(0xFFCDE5DC)

        var valueLogin by remember {
            mutableStateOf("")
        }
        var showPassword by remember {
            mutableStateOf(false)
        }
        var valuePass by remember {
            mutableStateOf("")
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = backgroundColor)
                .padding(16.dp)
        ) {

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth()
                        .align(Alignment.Start)
                ) {
                    IconButton(onClick = {back?.onBackPressed() }) {
                        Image(
                            painter = painterResource(id = R.drawable.leftarrow),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = 5.dp)
                                .size(30.dp)
                        )
                    }
                }


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.twochelicks),
                        contentDescription = null,
                        modifier = Modifier
                            .size(200.dp)
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 55.dp)
                    )

                    TextField(
                        value = valueLogin,
                        onValueChange = { newText -> valueLogin = newText },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min)
                            .padding(bottom = 25.dp)
                            .background(color = Color(0xFFCDE5DC)),
                        placeholder = { Text(text = stringResource(id = R.string.enter_email)) },
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = backgroundColor
                        )
                    )

                    TextField(
                        value = valuePass,
                        onValueChange = { newText -> valuePass = newText },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min)
                            .padding(bottom = 25.dp),
                        placeholder = { Text(text = stringResource(id = R.string.enter_password)) },
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = backgroundColor
                        ),
                        trailingIcon = {
                            IconButton(onClick = { showPassword = !showPassword }) {
                                Icon(
                                    imageVector = if (showPassword) Icons.Default.Visibility else Icons.Filled.VisibilityOff,
                                    contentDescription = if (showPassword) "Show Password" else "Hide Password"
                                )
                            }
                        }
                    )

                    Button(
                        onClick = {
                            if (TextUtils.isEmpty(valueLogin) || TextUtils.isEmpty(valuePass)) {
                            Toast.makeText(context, "Заполните все поля", Toast.LENGTH_LONG).show()
                        } else {
                            auth(valueLogin, valuePass)
                        } },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 50.dp, bottom = 10.dp),
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF92D0BE))
                    ) {
                        Text(
                            text = "Войти",
                            color = Color.White
                        )
                    }

                    Row(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(top = 10.dp)
                            .align(Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Ещё нет аккаунта?",
                            color = Color.Black
                        )
                        Text(
                            text = "Зарегистрироваться",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(start = 5.dp)
                                .clickable(onClick = {(activity as MainActivity?)!!.replaceFragment(RegistrationFragment.newInstance(),
                                    true)})
                        )
                    }

                    Row(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(top = 10.dp)
                            .align(Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Забыли пароль?", fontSize = 14.sp,
                            modifier = Modifier.clickable {
                                (activity as MainActivity?)!!.replaceFragment(
                                    ForgotPassFragment.newInstance(),
                                    false
                                )
                            })
                    }
                }
            }
        }
    }
    private fun auth(login: String, pass: String) {
        auth = FirebaseAuth.getInstance()
        if(login != "" && pass !=""){
            auth?.signInWithEmailAndPassword(login, pass)
                ?.addOnCompleteListener{task->
                    if(task.isSuccessful){
                        startActivity(Intent(activity, WelcomeActivity::class.java))
                    } else{
                        Toast.makeText(context, "Ошибка аунтефикации", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}