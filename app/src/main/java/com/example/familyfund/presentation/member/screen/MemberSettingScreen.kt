package com.example.familyfund.presentation.member.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.familyfund.presentation.admin.screen.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberSettingScreen(controller: NavHostController) {

    Scaffold(
        containerColor = BackgroundColor,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Settings",
                        fontFamily = BoldFont,
                        color = HeadingColor
                    )
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            Text(
                "Account",
                style = MaterialTheme.typography.titleMedium,
                fontFamily = BoldFont,
                color = HeadingColor
            )

            SettingsCard {
                SettingItem("👤", "Personal Information", "Manage your profile")
                HorizontalDivider(color = SurfaceColor)
                SettingItem("🔒", "Change Password", "Update your account password")
            }

            Text(
                "Preferences",
                style = MaterialTheme.typography.titleMedium,
                fontFamily = BoldFont,
                color = HeadingColor
            )

            SettingsCard {
                SettingItem("🔔", "Notifications", "Manage notification preferences")
                HorizontalDivider(color = SurfaceColor)
                SettingItem("🌐", "Language", "English")
            }

            Text(
                "Support",
                style = MaterialTheme.typography.titleMedium,
                fontFamily = BoldFont,
                color = HeadingColor
            )

            SettingsCard {
                SettingItem("❓", "Help & Support", "Get assistance")
                HorizontalDivider(color = SurfaceColor)
                SettingItem("ℹ️", "About FamilyFund", "App information")
            }

            Spacer(modifier = Modifier.height(4.dp))

            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color(0xFFDC2626)
                )
            ) {
                Text(
                    "Logout",
                    fontFamily = BoldFont
                )
            }

            Text(
                "FamilyFund v1.0.0",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodySmall,
                color = SecondaryTextColor,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}

@Composable
private fun SettingsCard(
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            content = content
        )
    }
}

@Composable
private fun SettingItem(
    icon: String,
    title: String,
    subtitle: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            icon,
            style = MaterialTheme.typography.titleLarge
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                title,
                fontFamily = BoldFont,
                color = PrimaryTextColor,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                subtitle,
                fontFamily = MediumFont,
                color = SecondaryTextColor,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}