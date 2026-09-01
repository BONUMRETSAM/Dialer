package com.voipcallph.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Navy = Color(0xFF07111F)
private val Blue = Color(0xFF087BFF)
private val Green = Color(0xFF22C55E)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { VoipCallPhApp() }
    }
}

@Composable
fun VoipCallPhApp() {
    var operator by remember { mutableStateOf("57213") }
    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    var loggedIn by remember { mutableStateOf(false) }

    MaterialTheme {
        Surface(Modifier.fillMaxSize(), color = Navy) {
            Column(
                modifier = Modifier.fillMaxSize().padding(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(18.dp))
                Text("VOIPCALL PH", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Black)
                Text("FREE INTERNET CALLING", color = Blue, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(18.dp))

                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF4F7FA)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.padding(16.dp)) {
                        OutlinedTextField(operator, { operator = it }, label = { Text("Operator Code") }, modifier = Modifier.fillMaxWidth())
                        Spacer(Modifier.height(8.dp))
                        OutlinedTextField(user, { user = it }, label = { Text("User ID") }, modifier = Modifier.fillMaxWidth())
                        Spacer(Modifier.height(8.dp))
                        OutlinedTextField(
                            password, { password = it },
                            label = { Text("Password") },
                            visualTransformation = PasswordVisualTransformation(),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(Modifier.height(10.dp))
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Status", fontWeight = FontWeight.Bold)
                            Text(if (loggedIn) "● REGISTERED" else "● NOT REGISTERED",
                                color = if (loggedIn) Green else Color.Gray, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Spacer(Modifier.height(14.dp))
                OutlinedTextField(
                    value = number, onValueChange = { number = it.filter { c -> c.isDigit() || c in "+*#" } },
                    label = { Text("Enter number") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(12.dp))
                val keys = listOf("1","2","3","4","5","6","7","8","9","*","0","#")
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    keys.chunked(3).forEach { row ->
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            row.forEach { key ->
                                Button(
                                    onClick = { number += key },
                                    modifier = Modifier.weight(1f).height(58.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF17283B))
                                ) { Text(key, fontSize = 22.sp, color = Color.White) }
                            }
                        }
                    }
                }

                Spacer(Modifier.height(12.dp))
                Button(
                    onClick = { loggedIn = user.isNotBlank() && password.isNotBlank() },
                    modifier = Modifier.fillMaxWidth().height(58.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Green),
                    shape = RoundedCornerShape(30.dp)
                ) {
                    Text("☎  CALL", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(Modifier.weight(1f))
                Text("VOIP TO VOIP • INTERNATIONAL CALLING", color = Color.LightGray, fontSize = 12.sp)
                Text("Prototype UI — real calling requires SIP/VoIP backend configuration",
                    color = Color.Gray, fontSize = 10.sp)
            }
        }
    }
}
