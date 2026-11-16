package com.example.familyfund.presentation.admin.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.familyfund.data.Member
import com.example.familyfund.presentation.admin.component.BottomNavigationBar
import com.example.familyfund.presentation.admin.viewmodel.AdminViewModel
import dev.seyfarth.tablericons.TablerIcons
import dev.seyfarth.tablericons.outlined.ArrowRight
import dev.seyfarth.tablericons.outlined.UserPlus
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MembersScreen(controller: NavController) {

    val months = listOf(
        "Jan", "Feb", "Mar", "Apr", "May", "Jun",
        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    )

    val context = LocalContext.current






    val viewmode: AdminViewModel = viewModel()

    LaunchedEffect(Unit) {

        viewmode.getAllMember()


    }

    var addMemberScreenActive by remember { mutableStateOf(false) }


    var selecteMonth by remember { mutableStateOf("Jan") }


    Scaffold(


        topBar = {

            TopAppBar(
                title = {
                    Text("Member")
                },
                actions = {

                    IconButton(
                        onClick = {





                        },
                        shape = RoundedCornerShape(12.dp),

                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color.White
                        ),


                        ) {

                        Icon(
                            imageVector = TablerIcons.Outlined.UserPlus,
                            contentDescription = "User Add"
                        )
                    }
                },

                )


        },

        bottomBar = {

            BottomNavigationBar(controller)
        }

    ) { innerPadding ->


        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(12.dp)
        ) {
            item {


                Text(
                    "Monthly Fees Overview",
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = MediumFont,
                    color = PrimaryTextColor
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                )
                {

                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 4.dp),
                        colors = CardDefaults.cardColors(

                            containerColor = PrimaryColor,
                            contentColor = Color.White


                        )

                    ) {

                        Column(
                            modifier = Modifier.padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            Text(
                                "Total Member",
                                style = MaterialTheme.typography.bodySmall,
                                fontFamily = MediumFont,

                                )
                            Text(
                                "6", fontFamily = MediumFont,
                                style = MaterialTheme.typography.titleSmall
                            )
                        }

                    }
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 4.dp),
                        colors = CardDefaults.cardColors(

                            containerColor = PrimaryColor,
                            contentColor = Color.White
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(8.dp),

                            verticalArrangement = Arrangement.Center
                        ) {

                            Text(
                                " 7 Paid",
                                style = MaterialTheme.typography.bodySmall,
                                fontFamily = MediumFont,

                                )
                            Text(
                                "6Pending", fontFamily = MediumFont,
                                style = MaterialTheme.typography.titleSmall
                            )
                        }

                    }


                }









                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    val list = listOf("All", "Paid", "Pending")
                    var selected by remember { mutableStateOf("All") }

                    list.forEach { item ->

                        FilterChip(
                            selected = selected == item,
                            onClick = { selected = item },
                            label = { Text(item) }
                        )
                    }


                }
                Spacer(modifier = Modifier.height(16.dp))


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        "ALL MEMBERS",
                        style = MaterialTheme.typography.bodySmall,
                        color = SecondaryTextColor
                    )


//                    Box(modifier = Modifier.width(50.dp).height if () 12.dp)


                }

                Spacer(modifier = Modifier.height(16.dp))


            }

           items(
               items = viewmode.memberList,
               key = {it.memberId}
           ) {member->

                CardMember(member)
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )


            }

            item {

                Spacer(modifier = Modifier.height(12.dp))

                TextButton(
                    onClick = {


                        addMemberScreenActive = true


                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.textButtonColors(

                        containerColor = PrimaryColor,
                        contentColor = PrimaryTextColor
                    )
                ) {

                    Text("Add Member")
                }

            }


        }


    }

//    SearchBar1()

    if (addMemberScreenActive) {


        AddMember({ addMemberScreenActive = false }, {viewmode.createMember()})


    }

}


@Composable
private fun CardMember(member: Member) {




    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {

            Box(
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .background(color = BackgroundColor, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {


                Text(
                    member.name.firstOrNull()?.uppercase()?: "?",
                    color = PrimaryColor,
                    fontFamily = BoldFont,
                    style = MaterialTheme.typography.bodyMedium
                )


            }

            Column() {

                Text(
                    member.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontFamily = MediumFont,
                    color = PrimaryTextColor
                )
                Text(
                    "Member Fee ${member.monthlyFees}",
                    style = MaterialTheme.typography.labelMedium,
                    fontFamily = MediumFont,
                    color = SecondaryTextColor
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {


            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(25.dp)
                    .background(Color.Green.copy(0.7f), shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    "Pay",
                    style = MaterialTheme.typography.labelSmall,
                    fontFamily = MediumFont,
                    color = Color.White
                )

            }
            Icon(
                imageVector = TablerIcons.Outlined.ArrowRight,
                contentDescription = "Arow Right",
                modifier = Modifier.size(20.dp)
            )
        }


    }


}


fun formatterDate(date: Long):String{


    return SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date(date))


}

