package com.example.familyfund.presentation.admin.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.familyfund.data.Member
import com.example.familyfund.data.repository.MemberRepository
import kotlinx.coroutines.launch

class AdminViewModel: ViewModel(){


    private val repositoy = MemberRepository()

    var searchText by mutableStateOf("")
        private set


    var memberList by mutableStateOf<List<Member>>(emptyList())

    var memberId by mutableStateOf("")
        private set

    var memberName by mutableStateOf("")
        private set


    var phoneNumber by mutableStateOf("")
        private set

    var isActive by mutableStateOf(true)
        private set

    var createdAt by mutableStateOf("")
        private set

    var updatedAt by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf("")
        private set


    var sucessMessage by mutableStateOf("")
        private set


    fun changeMemberName(membername:String){

        memberName = membername
    }


    fun onMemberId(id:String){

        memberId = id
    }






    fun onPhoneNumberChange(phone:String){

        phoneNumber = phone
    }









    fun onSearch(search: String){

        searchText = search
    }


    fun createMember() {

        if (memberName.isBlank()) {
            errorMessage = "Please enter member name"
            return
        }

        if (phoneNumber.isBlank()) {
            errorMessage = "Please enter phone number"
            return
        }

        if (phoneNumber.length != 10 || !phoneNumber.all { it.isDigit() }) {
            errorMessage = "Enter valid 10 digit phone number"
            return
        }

        // Yahan Repository call karna hai

        viewModelScope.launch {

            try{

                isLoading = true

                val memberId = repositoy.addMember(
                    name = memberName,
                    phone = phoneNumber
                )

                sucessMessage = "Member created sucessfully ID:$memberId"

                memberName = ""
                phoneNumber = ""
            }catch (e: Exception){

                errorMessage = e.message?:"somthing went wrong"

                Log.e("FamilyFund", "Firebase Error: ", e)
            }finally {

                isLoading = false
            }




        }
    }


    fun deleteMember(){

        viewModelScope.launch {

            repositoy.removeMember(memberId = memberId)
        }
    }

    fun getMemberById(){

        viewModelScope.launch {

            repositoy.getMemberById(memberId)
        }
    }



    fun getAllMember(){



            repositoy.getMembers(

                onResult = {members->


                    memberList = members

                },
                onError = {error->

                    Log.e("FamilyFound","Fetch Failed",error)
                }
            )

    }
}