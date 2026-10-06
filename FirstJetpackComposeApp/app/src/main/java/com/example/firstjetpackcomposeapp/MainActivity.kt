package com.example.firstjetpackcomposeapp

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstjetpackcomposeapp.ui.theme.FirstJetpackComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstJetpackComposeAppTheme {
//                Greeting(
//                    name = "Abdallah"
//                )
                Learning()
            }
        }
    }
}

@SuppressLint("ShowToast")
@Composable
fun Greeting(name: String) {
    var counter = rememberSaveable { mutableIntStateOf(0) }
    val context = LocalContext.current
    Column(modifier = Modifier
        .padding(20.dp)
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Hello $name!", fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(30.dp))
        Text(modifier = Modifier.padding(60.dp),
            text = "Counter : ${counter.intValue}", fontSize = 25.sp,
            fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold)
        Row (verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {

            Button(onClick = {
                counter.intValue++
                Log.d("TAG", "counter : ${counter.intValue}")
            }, modifier = Modifier.padding(5.dp)) {
                Text(text = "Increment", fontSize = 16.sp)
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }

            Button(onClick = {
                if (counter.intValue <= 0) {
                    Toast.makeText(context, "You can't decrement the counter less than 0", Toast.LENGTH_LONG).show()
                } else{
                    counter.intValue--
                }
                Log.d("TAG", "counter : ${counter.intValue}")
            }, colors = ButtonDefaults.buttonColors(Color.Red)
                , modifier = Modifier.padding(5.dp)) {
                Text(text = "Decrement", fontSize = 16.sp)
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
            }

        }

        Button(onClick = {
            counter.intValue = 0
            Log.d("TAG", "counter : ${counter.intValue}")
        }, colors = ButtonDefaults.buttonColors(Color.Yellow)
            , modifier = Modifier.padding(5.dp)) {
            Text(text = "Clear", fontSize = 16.sp, color = Color.Black)
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Icon(imageVector = Icons.Default.Refresh, contentDescription = null,
                tint = Color.Black)
        }


    }

}

@Composable
fun Learning() {
    val context = LocalContext.current
//    var switchState = rememberSaveable { mutableStateOf(false) }
//                                            background(if(switchState.value) Color.DarkGray else Color.White) Dark and Light mode
    Column (modifier = Modifier.fillMaxSize().background(Color.White).padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
//        val fruits = arrayOf("Apple", "Mango", "Orange", "Banana")

//        --------------------- Checkbox lesson ---------------------
//        fruits.forEach { fruit ->
//            var isChecked = rememberSaveable { mutableStateOf(false) }
//            Row (verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.Center) {
//                Checkbox(checked = isChecked.value, onCheckedChange = { Toast.makeText(context, fruit,
//                    Toast.LENGTH_SHORT).show()
//                    isChecked.value = it
//                })
//                Spacer(modifier = Modifier.size(10.dp))
//                Text(text = fruit)
//            }
//        }
//        --------------------- RadioButton lesson ---------------------
//        var radioBtnState = rememberSaveable { mutableIntStateOf(-1) }
//        fruits.forEachIndexed { i , fruit ->
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.Center
//            ) {
//                RadioButton(
//                    selected = radioBtnState.intValue == i,
//                    onClick = {
//                        if (radioBtnState.intValue != i) Toast.makeText(context, fruit, Toast.LENGTH_SHORT).show()
//                        radioBtnState.intValue = i
//                    }, colors = RadioButtonDefaults.colors(
//                        selectedColor = Color.Red,
//                        unselectedColor = Color.Blue
//                    ))
//                Spacer(modifier = Modifier.size(10.dp))
//                Text(text = fruit)
//            }
//        }
//        Spacer(modifier = Modifier.size(20.dp))
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.Center
//            ) {
//            var isSelected = rememberSaveable { mutableStateOf(false) }
//                RadioButton(selected = isSelected.value, onClick = {
////                    isSelected.value = if(isSelected.value == true) false else true
//                    isSelected.value = true
//                }, colors = RadioButtonDefaults.colors(
//                    selectedColor = Color.Red,
//                    unselectedColor = Color.Blue
//                ))
//                Spacer(modifier = Modifier.size(10.dp))
//                Text(text = "Btn 1")
//
//                RadioButton(selected = !isSelected.value, onClick = {
////                    isSelected.value = if(isSelected.value == true) false else true
//                    isSelected.value = false
//                }, colors = RadioButtonDefaults.colors(
//                    selectedColor = Color.Red,
//                    unselectedColor = Color.Blue
//                ))
//                Spacer(modifier = Modifier.size(10.dp))
//                Text(text = "Btn 2")
//            }
//        --------------------- Switch lesson ---------------------
//        Switch(checked = switchState.value, onCheckedChange = { switchState.value = it},
//            modifier = Modifier.padding(20.dp),
//            colors = SwitchDefaults.colors(
//                checkedThumbColor = Color.Red,
//                uncheckedThumbColor = Color.Blue,
//                checkedTrackColor = Color.LightGray,
//                uncheckedTrackColor = Color.LightGray
//            )
//        )
//        --------------------- Image lesson ---------------------
//        Column (
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Image(painter = painterResource(id = R.drawable.unnamed),
//                contentDescription = "Image Description",
//                modifier = Modifier
//                    .width(250.dp)
//                    .height(250.dp)
//                    .padding(6.dp)
//                    .clip(RoundedCornerShape(30.dp))
//                    .border(2.dp, color = Color.Cyan, shape = RoundedCornerShape(30.dp)),
//                contentScale = ContentScale.Crop
//            )
//
//            Image(painter = painterResource(id = R.drawable.unnamed),
//                contentDescription = "Image Description",
//                modifier = Modifier
//                    .width(250.dp)
//                    .height(250.dp)
//                    .padding(6.dp)
//                    .clip(CutCornerShape(topStart = 30.dp, bottomEnd = 30.dp))
//                    .border(2.dp, color = Color.Cyan, shape = CutCornerShape(topStart = 30.dp, bottomEnd = 30.dp)),
//                contentScale = ContentScale.Crop
//            )
//
//            Image(painter = painterResource(id = R.drawable.unnamed),
//                contentDescription = "Image Description",
//                modifier = Modifier
//                    .width(250.dp)
//                    .height(250.dp)
//                    .padding(6.dp)
//                    .clip(CircleShape)
//                    .border(2.dp, color = Color.Cyan, shape = CircleShape),
//                contentScale = ContentScale.Crop
//            )
//        }
//        --------------------- Text lesson ---------------------
//        Method 2 for building Text
//        Text(text = buildAnnotatedString {
//            withStyle (style = SpanStyle(
//                color = Color.Blue,
//                fontSize = 25.sp,
//                fontWeight = FontWeight.Bold,
//                fontStyle = FontStyle.Italic)){
//                append("J")
//            }
//
//            append("etpack ")
//            withStyle (style = SpanStyle(
//                color = Color.Blue,
//                fontSize = 25.sp,
//                fontWeight = FontWeight.Bold,
//                fontStyle = FontStyle.Italic)){
//                append("C")
//            }
//            append("ompose")
//        }, fontSize = 20.sp)
//
//        Text(text = buildAnnotatedString {
//            withStyle(style = ParagraphStyle(textIndent = TextIndent(firstLine = 20.sp))) {
//                withStyle (style = SpanStyle(
//                    color = Color.Blue,
//                    fontSize = 25.sp,
//                    fontWeight = FontWeight.Bold,
//                    fontStyle = FontStyle.Italic)){
//                    append("J")
//                }
//
//                append("etpack ")
//                withStyle (style = SpanStyle(
//                    color = Color.Blue,
//                    fontSize = 25.sp,
//                    fontWeight = FontWeight.Bold,
//                    fontStyle = FontStyle.Italic)){
//                    append("C")
//                }
//                append("ompose")
//            }
//
//        }, modifier = Modifier.background(Color.LightGray), fontSize = 20.sp)
//        --------------------- TextField lesson ---------------------
//        var textFieldState = rememberSaveable { mutableStateOf("") }
//        val keyboardController = LocalSoftwareKeyboardController.current
//        val focusManager = LocalFocusManager.current
////        OutlinedTextField(value = textFieldState.value,
////            onValueChange = {textFieldState.value = it})
////        BasicTextField(value = textFieldState.value, onValueChange = { textFieldState.value = it},
////                modifier = Modifier.width(270.dp).background(Color.LightGray)) // There is no icons or lable in BasicTextField
//        OutlinedTextField(value = textFieldState.value,
//            onValueChange = {textFieldState.value = it},
//            modifier = Modifier.width(270.dp),
//            label = {Text(text = "Enter Your Email")},
//            singleLine = true,
////            maxLines = 2,
////            enabled = false,
//            placeholder = {Text(text = "abcd@example.com")},
////            visualTransformation = PasswordVisualTransformation(),
//            leadingIcon = {Icon(imageVector = Icons.Filled.Email, contentDescription = "Email Icon")},
//            trailingIcon = { IconButton(onClick = {
//                keyboardController?.hide()
//                Toast.makeText(context,textFieldState.value, Toast.LENGTH_LONG).show()
//                focusManager.clearFocus()
//            }) {
//                Icon(imageVector = Icons.Filled.Send, contentDescription = "Send")
//            } },
//            keyboardOptions = KeyboardOptions(
//                capitalization = KeyboardCapitalization.Characters,
////                capitalization = KeyboardCapitalization.Sentences,
////                capitalization = KeyboardCapitalization.Words,
//                keyboardType = KeyboardType.Email,
//                imeAction = ImeAction.Send,
////                imeAction = ImeAction.Done
//            ),
//            keyboardActions = KeyboardActions(
//                onSend = {
//                    keyboardController?.hide()
//                    Toast.makeText(context,textFieldState.value, Toast.LENGTH_LONG).show()
//                    focusManager.clearFocus()
//                }
//            )
//
//        )
//        --------------------- Custom TextFields lesson ---------------------
        var textFieldState = rememberSaveable { mutableStateOf("") }
        val myColor = Color.Blue
        TextField(value = textFieldState.value, onValueChange = { textFieldState.value = it },
            label = {Text(text = "Enter your name")},
            leadingIcon = {Icon(imageVector = Icons.Filled.Person, contentDescription = "Person Icon")},
            modifier = Modifier.background(myColor.copy(alpha = 0.20f)),
            colors = TextFieldDefaults.colors(
                unfocusedLabelColor = myColor,
                focusedLabelColor = myColor,
            ),

            )
    }

}

@Preview
@Composable
fun LearningPreview() {
    FirstJetpackComposeAppTheme {
        Learning()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirstJetpackComposeAppTheme {
        Greeting("Abdallah")
    }
}