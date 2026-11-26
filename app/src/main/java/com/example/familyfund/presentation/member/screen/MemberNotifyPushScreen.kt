package com.example.familyfund.presentation.member.screen

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.familyfund.data.model.PaymentRequestModel
import com.example.familyfund.presentation.admin.screen.PrimaryColor
import com.example.familyfund.presentation.admin.screen.SurfaceColor
import com.example.familyfund.presentation.member.uistate.MemberHomeUiState
import com.example.familyfund.presentation.member.uistate.MemberNotifyPushUiState
import com.example.familyfund.presentation.member.viewmodel.MemberViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MemberNotifyPushScreen(

    onDismissRequest:()->Unit,
    name:String,
    id:String,
    context: Context,
    viewmodel: MemberViewModel


) {


    val request = PaymentRequestModel(
        id,
        name,
        amount = 250,
        status = "Verification in progress.",
        message = "Confirm my payment",
        createdAt = System.currentTimeMillis()
    )



    val memberPushUiState = viewmodel.memberPushUiState.collectAsStateWithLifecycle()



    when(val state = memberPushUiState.value){

        is MemberNotifyPushUiState.Idel->{

            AlertDialog(

                onDismissRequest = { onDismissRequest() },
                confirmButton = {

                    TextButton(
                        onClick = {

                            viewmodel.submitPaymentRequest(request)





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

                    TextField(
                        value = name,
                        onValueChange = {  },
                        readOnly = true,
                        singleLine = true



                    )
                },
                title = {

                    Text("Notify to Admin")
                }
            )

        }


        is MemberNotifyPushUiState.Loading->{

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }



        }

        is MemberNotifyPushUiState.Success->{

            AlertDialog(

                onDismissRequest = { onDismissRequest() },
                confirmButton = {

                    TextButton(
                        onClick = {


                            onDismissRequest()




                        },
                        colors = ButtonDefaults.buttonColors(

                            containerColor = PrimaryColor,
                            contentColor = SurfaceColor
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {

                        Text("oky")
                    }
                },



                title = {

                    Text("Request sent to Admin. ")
                },
                text = {

                    Text("Status will be updated within 48 hours.")
                }


            )





        }

        is MemberNotifyPushUiState.Error->{

            AlertDialog(

                onDismissRequest = { onDismissRequest() },
                confirmButton = {

                    TextButton(
                        onClick = {


                            onDismissRequest()




                        },
                        colors = ButtonDefaults.buttonColors(

                            containerColor = PrimaryColor,
                            contentColor = SurfaceColor
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {

                        Text("oky")
                    }
                },


                title = {

                    Text("Somthing wrong Restart your net or aap ")
                }
            )

        }
    }

    }


