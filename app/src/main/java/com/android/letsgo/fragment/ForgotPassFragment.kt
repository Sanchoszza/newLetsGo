package com.android.letsgo.fragment

import android.content.ContentValues
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.ComposeView
import com.android.letsgo.R
import com.google.firebase.auth.FirebaseAuth


class ForgotPassFragment : Fragment() {
    private var auth: FirebaseAuth? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                ForgotPassScreen()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): ForgotPassFragment = ForgotPassFragment()
    }

    @Composable
    @Preview
    fun ForgotPassScreen() {
        var valueLogin by remember {
            mutableStateOf("")
        }
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
                    Image(
                        painter = painterResource(id = R.drawable.leftarrow),
                        contentDescription = null,
                        modifier = Modifier.padding(start = 5.dp).size(30.dp)
                    )
                    Text(
                        text = "Забыли пароль",
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
                        value = "",
                        onValueChange = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(vertical = 60.dp, horizontal = 50.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(4.dp)),
                        placeholder = { Text(text = stringResource(id = R.string.enter_login)) },
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                        textStyle = TextStyle(color = Color.Black)
                    )

                    Button(
                        onClick = {
                            if (TextUtils.isEmpty(valueLogin)) {
                                Toast.makeText(context, "Введите e-mail", Toast.LENGTH_LONG).show()
                            } else {
                                resetPass(valueLogin)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 30.dp, bottom = 10.dp)
                            .height(IntrinsicSize.Min),
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF5DC097))
                    ) {
                        Text(
                            text = "Восстановить пароль",
                            color = Color.White
                        )
                    }
                }
            }
        }
    }

    private fun resetPass(login: String){
        auth = FirebaseAuth.getInstance()
        if (login != "" && login !=null) {
            auth!!.sendPasswordResetEmail(login)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(ContentValues.TAG, "Email sent.")
                        Toast.makeText(activity, "Инструкции по восстановлению пароля\nотправлены на почту", Toast.LENGTH_LONG).show()
                        activity?.supportFragmentManager?.popBackStack()
                    } else {
                        Toast.makeText(activity, "Произошла ошибка, повторите попытку позднее", Toast.LENGTH_LONG).show()

                    }
                }
        } else Toast.makeText(context, "Введите почту!", Toast.LENGTH_LONG).show()
    }

}