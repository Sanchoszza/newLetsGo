package com.android.letsgo.fragment

import android.app.ProgressDialog
import android.content.Intent
import android.content.res.ColorStateList
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
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.android.letsgo.R
import com.android.letsgo.activity.MainActivity
import com.android.letsgo.activity.StartActivity
import com.android.letsgo.db.PersonData
import com.android.letsgo.db.UserRegData
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore


class RegistrationFragment : Fragment() {

    private var dbUsers: CollectionReference? = null
    private var db: FirebaseFirestore? = null
    private var databaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    private var progressBar: ProgressDialog? = null

    private val TAG = "CreateAccountActivity"
    companion object {
        @JvmStatic
      fun newInstance(): RegistrationFragment = RegistrationFragment()
    }

    private var auth: FirebaseAuth? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                RegistrationScreen()
            }
        }
    }

    @Composable
    @Preview
    fun RegistrationScreen() {
        val back = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
        val backgroundColor = Color(0xFFCDE5DC)
        var valueLogin by remember {
            mutableStateOf("")
        }
        var valueName by remember {
            mutableStateOf("")
        }
        var valuePass by remember {
            mutableStateOf("")
        }
        var valueConfPass by remember {
            mutableStateOf("")
        }
        var showPassword by remember {
            mutableStateOf(false)
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
                        value = valueName,
                        onValueChange = { newText -> valueName = newText },
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
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            IconButton(onClick = { showPassword = !showPassword }) {
                                Icon(
                                    imageVector = if (showPassword) Icons.Default.Visibility else Icons.Filled.VisibilityOff,
                                    contentDescription = if (showPassword) "Show Password" else "Hide Password"
                                )
                            }
                        }
                    )

                    TextField(
                        value = valueConfPass,
                        onValueChange = { newText -> valueConfPass = newText },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min)
                            .padding(bottom = 25.dp),
                        placeholder = { Text(text = stringResource(id = R.string.config_password)) },
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = backgroundColor
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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
                            if (TextUtils.isEmpty(valueName)
                                || TextUtils.isEmpty(valueLogin)
                                || TextUtils.isEmpty(valuePass)
                                || TextUtils.isEmpty(valueConfPass)) {
                                Toast.makeText(context, "Заполните все поля", Toast.LENGTH_LONG).show()
                            } else {
                                regNew(valueName, valueLogin, valuePass, valueConfPass)
                            }
                        },
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
                                .clickable(onClick = {(activity as MainActivity?)!!.replaceFragment(LoginFragment.newInstance(),
                                    true)})
                        )
                    }
                }
            }
        }
    }


    fun regNew(name: String, login: String, password: String, confPass: String){

        progressBar = ProgressDialog(getContext())

        mDatabase = FirebaseDatabase.getInstance()
        databaseReference = mDatabase!!.reference!!.child("Users")
        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance();

        if (!TextUtils.isEmpty(name)  && !TextUtils.isEmpty(login)
            && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(confPass)
        ) {
            if(confPass.equals(password)) {

                mAuth!!.createUserWithEmailAndPassword(login!!, password!!)
                    .addOnCompleteListener() { task ->
                        progressBar!!.hide()

                        if (task.isSuccessful) {
                            Log.d(TAG, "createUserWithEmail:success")

                            val userId = mAuth!!.currentUser!!.uid

                            verifyEmail()
                            dbUsers = db?.collection("Users")
                            val currentUserIdBb = databaseReference!!.child(userId)

                            currentUserIdBb.child("name").setValue(name)
                            currentUserIdBb.child("email").setValue(login)
                            currentUserIdBb.child("password").setValue(password)

                            val data = UserRegData(name, login, password)

                            dbUsers?.add(data)
                                ?.addOnSuccessListener(OnSuccessListener<DocumentReference?> {
                                    Toast.makeText(
                                        context,
                                        "Аккаунт успешно создан!",
                                        Toast.LENGTH_LONG
                                    ).show()
                                })?.addOnFailureListener(OnFailureListener {
                                    Toast.makeText(
                                        context,
                                        "Произошла ошибка, попробуйте позднее!",
                                        Toast.LENGTH_LONG
                                    ).show()
                                })
                            updateUserInfoAndUi()
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                getContext(),
                                "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
            }else{
                Toast.makeText(context, "Пароли не совпадают!", Toast.LENGTH_SHORT).show()
            }
        } else{
            Toast.makeText(getContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUserInfoAndUi(){
        val intent = Intent(getContext(), StartActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
    private fun verifyEmail(){
        val mUser = mAuth!!.currentUser
        mUser!!.sendEmailVerification().
        addOnCompleteListener() {
                task ->
            if(task.isSuccessful){
                Toast.makeText(getContext(), "Verification email sent to " +
                        mUser.getEmail(), Toast.LENGTH_SHORT).show()
            } else{
                Log.e(TAG, "sendEmailVerification", task.exception)
                Toast.makeText(getContext(), "Failed to send verification email",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}