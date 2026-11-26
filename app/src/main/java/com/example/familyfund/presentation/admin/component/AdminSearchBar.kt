package com.example.familyfund.presentation.admin.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.familyfund.presentation.admin.screen.MediumFont
import com.example.familyfund.presentation.admin.screen.SecondaryTextColor
import com.example.familyfund.presentation.admin.viewmodel.AdminViewModel
import dev.seyfarth.tablericons.TablerIcons
import dev.seyfarth.tablericons.outlined.History
import dev.seyfarth.tablericons.outlined.Search
import dev.seyfarth.tablericons.outlined.X

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar1() {


    val viewmodel: AdminViewModel = viewModel()
    var active by rememberSaveable {

        mutableStateOf(false)
    }


    val searchHistory = remember {
        mutableStateListOf(
            "Abeer",
            "Nazeer",
            "Dabeer",
            "Faiz",
            "Masood",

            )
    }

    val allItem = remember {

        mutableStateListOf(


            "Abeer",
            "Nazeer",
            "Dabeer",
            "Faiz",
            "Masood",
            "Alkisha",
            "Alfia",
            ""
        )
    }


    val filterItem = allItem.filter { it == viewmodel.searchText }





    Box(modifier = Modifier.fillMaxWidth()) {

        SearchBar(


            modifier = Modifier.fillMaxWidth(),
            query = viewmodel.searchText,
            onQueryChange = { viewmodel.onSearch(it) },

            onSearch = { query ->

                val searchQuery = query.trim()

                if (searchQuery.isNotEmpty()) {

                    searchHistory.remove(searchQuery)
                    searchHistory.add(0, searchQuery)
                }
                active = false


            },


            active = active,
            onActiveChange = {


                active = it
            },

            placeholder = { Text("Search Member") },
            leadingIcon = {

                Icon(imageVector = TablerIcons.Outlined.Search, contentDescription = "Search")


            },

            trailingIcon = {


                if (active) {


                    Icon(
                        imageVector = TablerIcons.Outlined.X, contentDescription = "Clear",
                        modifier = Modifier.clickable(
                            onClick = {
                                if (viewmodel.searchText.isNotEmpty()) viewmodel.onSearch("") else active =
                                    false
                            }
                        ))
                }
            }
        ) {


            Column(modifier = Modifier.fillMaxWidth()) {

                if (viewmodel.searchText.isEmpty()) {


                    allItem.forEach { item ->

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),

                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {


                            Icon(
                                imageVector = TablerIcons.Outlined.History,
                                contentDescription = "History",
                                tint = SecondaryTextColor,
                                modifier = Modifier.size(20.dp)
                            )

                            Text(item, fontFamily = MediumFont,style = MaterialTheme.typography.labelLarge, color = SecondaryTextColor)
                        }

                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )
                    }


                } else {


                    filterItem.forEach { item ->


                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),

                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {


                            Icon(
                                imageVector = TablerIcons.Outlined.History,
                                contentDescription = "History",
                                tint = SecondaryTextColor,
                                modifier = Modifier.size(20.dp)
                            )
                            Text(item,style = MaterialTheme.typography.labelLarge, color = SecondaryTextColor, fontFamily = MediumFont)

                        }

                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )


                    }


                }




            }
        }


    }


}