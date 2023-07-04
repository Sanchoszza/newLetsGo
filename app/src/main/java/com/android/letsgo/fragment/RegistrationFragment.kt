package com.android.letsgo.fragment

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.android.letsgo.R


class RegistrationFragment : Fragment() {

    companion object {
        @JvmStatic
      fun newInstance(): RegistrationFragment = RegistrationFragment()
    }

private fun getReg(){

}

    @Composable
    fun RegistrationScreen() {
        var back = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
        val backgroundColor = Color(0xFFCDE5DC)
        val mContent = LocalContext.current
        var mText by remember { mutableStateOf("") }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFCDE5DC))
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
                    Text(
                        text = "Регистрация",
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    TextField(
                        value = mText,
                        onValueChange = { mText = it},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min)
                            .padding(bottom = 25.dp)
                            .background(color = Color(0xFFCDE5DC)),
//                        label = { Text(text = "Enter Your Name") },
                        placeholder = { Text(text = stringResource(id = R.string.enter_name)) },
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = backgroundColor
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )

                    TextField(
                        value = mText,
                        onValueChange = { mText = it },
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
                        value = mText,
                        onValueChange = { mText = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min)
                            .padding(bottom = 25.dp),
                        placeholder = { Text(text = stringResource(id = R.string.enter_password)) },
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = backgroundColor
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )

                    TextField(
                        value = mText,
                        onValueChange = { mText = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min)
                            .padding(bottom = 25.dp),
                        placeholder = { Text(text = stringResource(id = R.string.config_password)) },
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = backgroundColor
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )

                    Button(
                        onClick = { getReg() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 25.dp, bottom = 10.dp)
                            .height(IntrinsicSize.Min),
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF92D0BE))
                    ) {
                        Text(
                            text = "Зарегистрироваться",
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
                            text = "Уже есть аккаунт?",
                            color = Color.Black
                        )
                        Text(
                            text = "Войти",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(start = 5.dp)
                                .clickable(onClick = {LoginFragment.newInstance()})
                        )
                    }
                }
            }
        }
    }
    @Preview
    @Composable
    fun PreviewRegistrationScreen() {
        RegistrationScreen()
    }

}