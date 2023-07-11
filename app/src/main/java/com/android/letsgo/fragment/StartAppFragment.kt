package com.android.letsgo.fragment

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.foundation.background
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import com.android.letsgo.R
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.ComposeView
import com.android.letsgo.activity.MainActivity


class StartAppFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                StartAppScreen()
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(): StartAppFragment = StartAppFragment()
    }

    @Composable
    @Preview
    fun StartAppScreen(){
        val shadowColor = Color(0xFF92D0BE)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFCDE5DC))
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ){

                Text(
                    text = "LetsGo",
                    color = colorResource(R.color.color_for_enter_log),
                    fontSize = 60.sp,
                    fontFamily= FontFamily.Cursive,
                    letterSpacing= 1.3.sp,
                    modifier = Modifier
                        .padding(start = 100.dp, top = 17.dp, bottom = 46.dp),
                    style = TextStyle(shadow = Shadow(color = colorResource(R.color.color_for_reg),
                        Offset(10.0f, 16.5f), 1.0f))
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ){
//                    Image(
//                        painter = painterResource(id = R.drawable.twochelicks),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .size(250.dp)
//                            .align(Alignment.CenterHorizontally)
//                            .padding(bottom = 55.dp, top = 50.dp)
//                    )
                    Button(
                        onClick = { (activity as MainActivity?)!!.replaceFragment(LoginFragment.newInstance(), true)  },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 25.dp, bottom = 10.dp)
                            .height(IntrinsicSize.Min),
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF92D0BE))
                    ) {
                        Text(
                            text = "Войти",
                            color = Color.White
                        )
                    }

                    Button(
                        onClick = { (activity as MainActivity?)!!.replaceFragment(RegistrationFragment.newInstance(), true) },
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
                }
            }
        }
    }

}