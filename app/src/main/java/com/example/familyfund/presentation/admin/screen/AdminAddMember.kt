package com.example.familyfund.presentation.admin.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.familyfund.presentation.admin.viewmodel.AdminViewModel
import dev.seyfarth.tablericons.TablerIcons
import dev.seyfarth.tablericons.outlined.ArrowNarrowLeft

@Composable
fun AddMember(

    onDismiss: () -> Unit,
    onConfirm: () -> Unit,


    ) {


    val context = LocalContext.current

    val viewmodel: AdminViewModel = viewModel()

    var isError by remember { mutableStateOf(false) }




    Dialog(
        onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),

            shape = RoundedCornerShape(20.dp)
        ) {


            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),

                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Icon(
                        imageVector = TablerIcons.Outlined.ArrowNarrowLeft,
                        contentDescription = "Back",
                        modifier = Modifier.clickable(onClick = { onDismiss() })

                    )
                    Text(
                        "FamilyFund",
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.titleMedium,
                        fontFamily = BoldFont,
                        color = PrimaryTextColor,
                        textAlign = TextAlign.Center
                    )


                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "Create Member",
                    style = MaterialTheme.typography.labelLarge,
                    color = PrimaryTextColor,
                    fontFamily = MediumFont
                )
                Text(
                    "Add a new Member to your family",
                    style = MaterialTheme.typography.labelMedium,
                    color = SecondaryTextColor,
                    fontFamily = MediumFont
                )


                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),

                    value = viewmodel.memberName,
                    onValueChange = { viewmodel.changeMemberName(it); isError = it.isBlank() },
                    label = {
                        Text(
                            "Enter member Name", style = MaterialTheme.typography.labelMedium,
                            color = SecondaryTextColor, fontFamily = MediumFont
                        )
                    },
                    isError = isError,
                    supportingText = {
                        if (isError) {
                            Text(
                                "Please enter member name",
                                color = MaterialTheme.colorScheme.error,


                                )

                        }
                    },

                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {

                        if (viewmodel.memberName.isNotEmpty()) onConfirm() else isError = true
                    })

                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = viewmodel.phoneNumber,
                    onValueChange = { viewmodel.onPhoneNumberChange(it); isError = it.isBlank() },
                    isError = isError,

                    supportingText = {
                        if (isError) {

                            Text(
                                "Please enter phone number",
                                color = MaterialTheme.colorScheme.error
                            )

                        }

                    },

                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Phone
                    ),
                    keyboardActions = KeyboardActions(onDone = {

                        if (viewmodel.phoneNumber.isNotEmpty()) onConfirm() else isError = true
                    }),
                    label = {
                        Text(
                            "Enter member phone Number",
                            style = MaterialTheme.typography.labelMedium,
                            color = SecondaryTextColor,
                            fontFamily = MediumFont
                        )
                    }


                )

                TextButton(


                    onClick = {


                        if (viewmodel.memberName.isNotBlank() && viewmodel.phoneNumber.isNotBlank()) {
                            onConfirm()
                            Toast.makeText(context, "Member Saved", Toast.LENGTH_SHORT).show()
                            onDismiss()
                        } else isError =
                            true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .combinedClickable(

                            onLongClick = {
                                },
                            onClick = {}
                        ),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.textButtonColors(

                        containerColor = PrimaryColor,
                        contentColor = Color.White
                    )


                ) {
                    Text(
                        "Create Member",
                        style = MaterialTheme.typography.labelLarge,
                        fontFamily = BoldFont
                    )
                }


                Text(
                    "Member ID and other detail \n will be generated automatically.",
                    style = MaterialTheme.typography.labelSmall,
                    color = SecondaryTextColor,
                    fontFamily = MediumFont,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center

                )
            }

        }
    }


}


