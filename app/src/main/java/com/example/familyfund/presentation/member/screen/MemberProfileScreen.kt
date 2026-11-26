package com.example.familyfund.presentation.member.screen

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.familyfund.MainActivity
import com.example.familyfund.data.local.UserPreferences
import com.example.familyfund.common.ApplicationViewModel
import com.example.familyfund.presentation.admin.screen.BackgroundColor
import com.example.familyfund.presentation.admin.screen.BoldFont
import com.example.familyfund.presentation.admin.screen.HeadingColor
import com.example.familyfund.presentation.admin.screen.MediumFont
import com.example.familyfund.presentation.admin.screen.PrimaryColor
import com.example.familyfund.presentation.admin.screen.PrimaryTextColor
import com.example.familyfund.presentation.admin.screen.SecondaryTextColor
import com.example.familyfund.presentation.admin.screen.SurfaceColor
import com.example.familyfund.presentation.admin.viewmodel.AdminViewModel
import com.example.familyfund.presentation.member.component.MemberBottomNavigationBar
import com.example.familyfund.presentation.member.shimmerscreen.MemberProfileShimmerScreen
import com.example.familyfund.presentation.member.uistate.MemberProfileUiState
import com.example.familyfund.presentation.member.viewmodel.MemberViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberProfileScreen(controller: NavController) {

    val context = LocalContext.current
    val coroutine = rememberCoroutineScope()
    val userPreferences = remember { UserPreferences(context.applicationContext) }

    val userdata by userPreferences.userSession.collectAsStateWithLifecycle(initialValue = null)

    val id = userdata?.id

    val viewmodel: MemberViewModel = viewModel()
    LaunchedEffect(id) {

        viewmodel.getProfileMemberDetails(id.toString())
    }
    val memberProfileUiState by viewmodel.memberProfileUiState.collectAsStateWithLifecycle()










    when(val state = memberProfileUiState){

        is MemberProfileUiState.Loading->{

            MemberProfileShimmerScreen()
        }

        is MemberProfileUiState.Success->{

            Scaffold(
                containerColor = BackgroundColor,
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                "My Profile",
                                fontFamily = BoldFont,
                                color = HeadingColor
                            )
                        }
                    )
                },

                bottomBar = {

                    MemberBottomNavigationBar(controller)


                }
            ) { innerPadding ->

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    item{

                        Spacer(modifier = Modifier.height(20.dp))

                        Surface(
                            modifier = Modifier.size(100.dp),
                            shape = RoundedCornerShape(50.dp),
                            color = SurfaceColor
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    "${state.member?.name?.firstOrNull()?.uppercase()}",
                                    style = MaterialTheme.typography.headlineLarge,
                                    fontFamily = BoldFont,
                                    color = PrimaryColor
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            "${state.member?.name}",
                            style = MaterialTheme.typography.titleLarge,
                            fontFamily = BoldFont,
                            color = HeadingColor
                        )

                        Text(
                            "Family Member",
                            style = MaterialTheme.typography.bodyMedium,
                            fontFamily = MediumFont,
                            color = SecondaryTextColor
                        )

                        Spacer(modifier = Modifier.height(28.dp))

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(18.dp),
                                verticalArrangement = Arrangement.spacedBy(18.dp)
                            ) {
                                ProfileDetail("Full Name", "${state.member?.name}")
                                HorizontalDivider(color = SurfaceColor)
                                ProfileDetail("Email Address", "null")
                                HorizontalDivider(color = SurfaceColor)
                                ProfileDetail("Phone Number", "+91 ${state.member?.phone}")
                                HorizontalDivider(color = SurfaceColor)
                                ProfileDetail("Member ID", "${state.member?.memberId}")
                                HorizontalDivider(color = SurfaceColor)
                                ProfileDetail("Account Role", "Member")
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        OutlinedButton(
                            onClick = {},
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Text(
                                "Edit Profile",
                                fontFamily = BoldFont,
                                color = PrimaryColor
                            )
                        }
                        OutlinedButton(
                            onClick = {

                                coroutine.launch {
                                    userPreferences.clearSession()


                                }
                                val intent = Intent(context, MainActivity::class.java).apply {
                                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                                            Intent.FLAG_ACTIVITY_CLEAR_TASK
                                }

                                context.startActivity(intent)
                                Toast.makeText(context,"Logout..", Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Text(
                                "Log Out",
                                fontFamily = BoldFont,
                                color = Color.Red
                            )
                        }
                    }
                }
            }
        }

        is MemberProfileUiState.Error->{

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

                Text(state.message)


            }


        }
    }





}

@Composable
private fun ProfileDetail(
    title: String,
    value: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            title,
            style = MaterialTheme.typography.bodySmall,
            fontFamily = MediumFont,
            color = SecondaryTextColor
        )

        Text(
            value,
            style = MaterialTheme.typography.bodyLarge,
            fontFamily = BoldFont,
            color = PrimaryTextColor
        )
    }
}