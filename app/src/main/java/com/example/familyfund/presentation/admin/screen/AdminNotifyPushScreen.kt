package com.example.familyfund.presentation.admin.screen

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp


@Composable
fun NotifyPushScreen(

    onDismissRequest:()->Unit,
    onConfirmRequest:()->Unit,
    isOpen: Boolean
) {


    var value by remember { mutableStateOf("") }


    if(isOpen){

        AlertDialog(

            onDismissRequest = { onDismissRequest() },
            confirmButton = {

                TextButton(
                    onClick = {
                        onConfirmRequest()
                        onDismissRequest()

                    },
                    colors = ButtonDefaults.buttonColors(

                        containerColor = PrimaryColor,
                        contentColor = SurfaceColor
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {

                    Text("Notify")
                }
            },

            text = {

                OutlinedTextField(
                    value = value,
                    onValueChange = { value = it },
                    label = { Text("Enter Your Name") }


                )
            },
            title = {

                Text("Notify to Admin")
            }
        )
    }


}