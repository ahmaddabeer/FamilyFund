package com.example.familyfund

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.familyfund.presentation.screen.HomeScreen
import com.example.familyfund.presentation.ui.theme.FamilyFundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FamilyFundTheme {

                HomeScreen()

            }
        }
    }
}

