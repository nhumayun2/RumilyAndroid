package com.example.rumily

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rumily.ui.screens.LoginScreen
import com.example.rumily.ui.screens.RegisterScreen
import com.example.rumily.ui.screens.VerifyEmailScreen
import com.example.rumily.ui.theme.RumilyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RumilyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    // We keep track of the email temporarily here to pass it between screens
                    var tempEmail by remember { mutableStateOf("") }

                    NavHost(navController = navController, startDestination = "login") {

                        // --- LOGIN ---
                        composable("login") {
                            LoginScreen(
                                onLoginSuccess = {
                                    Toast.makeText(this@MainActivity, "Login Successful!", Toast.LENGTH_SHORT).show()
                                    // TODO: Go to Home
                                },
                                onNavigateToRegister = {
                                    navController.navigate("register")
                                }
                            )
                        }

                        // --- REGISTER ---
                        composable("register") {
                            RegisterScreen(
                                onRegisterSuccess = {
                                    // 1. Save the email entered
                                    // Note: In a real app, you might pass this via ViewModel or Route Arguments
                                    // But for now, we assume the user just typed it.
                                    // Since we don't have the email variable here easily,
                                    // we will just navigate to verify and let them re-type or pass it via arguments in next step.

                                    navController.navigate("verify_email")
                                },
                                onNavigateToLogin = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        // --- VERIFY EMAIL ---
                        composable("verify_email") {
                            // Ideally, we pass the email as an argument.
                            // For simplicity now, we will ask user to confirm it or hardcode a placeholder.
                            // In the next step, I can show you how to pass arguments properly.

                            VerifyEmailScreen(
                                emailToVerify = "your-email@example.com", // Placeholder for now
                                onVerificationSuccess = {
                                    Toast.makeText(this@MainActivity, "Account Verified! Logging in...", Toast.LENGTH_LONG).show()
                                    // Auto login successful -> Go to Home (we will build Home next)
                                    navController.navigate("login") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}