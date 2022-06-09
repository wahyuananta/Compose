package com.coder.compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coder.compose.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    Greeting("Android")
                    Column {
                        HeaderLogin()
                        InputForm()
                        ActionItem(
                            {
                                Toast.makeText(this@MainActivity, "Login Clicked", Toast.LENGTH_SHORT).show()
                            },{
                                Toast.makeText(this@MainActivity, "Register Clicked", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderLogin() {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Login", fontSize = 36.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Image(painter = painterResource(id = R.drawable.default_profile),
            contentDescription = "Image App",
            modifier = Modifier.size(256.dp, 128.dp),
            contentScale = ContentScale.None
        )
    }
}

@Composable
fun InputForm() {
    Column(modifier = Modifier
        .padding(horizontal = 16.dp)
        .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(32.dp))
        val email = remember{
            mutableStateOf("")
        }
        TextField(
            value = email.value,
            onValueChange = {email.value = it},
            placeholder = { Text(text = "Email")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        val password = remember{
            mutableStateOf("")
        }
        TextField(
            value = password.value,
            onValueChange = {password.value = it},
            placeholder = { Text(text = "Password")},
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
    }

}

@Composable
fun ActionItem(onClickLogin:() -> Unit, onClickRegister:() -> Unit) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onClickLogin, modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(), colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)) {
            Text(text = "LOGIN", style = TextStyle(color = Color.Yellow))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Belum punya akun?", modifier = Modifier.clickable(onClick = onClickRegister), style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Blue))
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ComposeTheme {
//        Greeting("Android")
        Column {
            HeaderLogin()
            InputForm()
            ActionItem({},{})
        }
    }
}