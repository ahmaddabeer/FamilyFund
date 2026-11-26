package com.example.familyfund

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.familyfund.common.ApplicationViewModel
import com.example.familyfund.data.local.UserPreferences
import com.example.familyfund.presentation.admin.navigation.AdminNavController
import com.example.familyfund.presentation.auth.LoginScreen
import com.example.familyfund.presentation.auth.LoginUIState
import com.example.familyfund.presentation.member.navigation.MemberNavController
import com.example.familyfund.presentation.theme.FamilyFundTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val context = LocalContext.current

            val viewmodel: ApplicationViewModel = viewModel()




            val loginUIState by viewmodel.loginuistate.collectAsStateWithLifecycle()

            val userPreferences = remember { UserPreferences(context.applicationContext) }

            val member by userPreferences.userSession.collectAsStateWithLifecycle(initialValue = null)


            var isLogin = member?.login
            var id = member?.id






            FamilyFundTheme {

                when{

                    member == null ->{

                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

                            CircularProgressIndicator()

                        }
                    }

                    member?.login == true ->{


                        if(id == "-1-FQ5dEOJBNOVi8JpFr"){

                           AdminNavController()
                        }else {
                            MemberNavController()



                        }



                    }

                    else ->{

                        when (val state = loginUIState) {

                            is LoginUIState.Idle -> {

                                LoginScreen(viewmodel)
                            }

                            is LoginUIState.Loading -> {

                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {

                                    CircularProgressIndicator()



                                }


                            }

                            is LoginUIState.Sucess -> {

                                LaunchedEffect(state.id.memberId) {

                                    userPreferences.saveUserSession(
                                        state.id.memberId,
                                        true,
                                        state.id.name
                                    )


                                }


                                Toast.makeText(context, "Welcome ${state.id.name}", Toast.LENGTH_SHORT).show()


                                if(state.id.memberId == "-1-FQ5dEOJBNOVi8JpFr"){

                                    AdminNavController()
                                }else {
                                    MemberNavController()

                                }


                            }

                            is LoginUIState.Empty -> {

                                LoginScreen(viewmodel)
                                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()

                            }

                            is LoginUIState.Error -> {

                                LoginScreen(viewmodel)
                                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()


                            }


                        }

                    }


                }

            }

        }
    }
}

