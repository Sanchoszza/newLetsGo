package com.android.letsgo.fragment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
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
import androidx.compose.foundation.Image
import androidx.compose.material.*
import com.android.letsgo.R


class ForgotPassFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(): ForgotPassFragment = ForgotPassFragment()
    }

    @Composable
    fun ForgotPassScreen() {
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
                        onClick = { },
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

    @Preview
    @Composable
    fun PreviewForgotPassScreen() {
        ForgotPassScreen()
    }

}