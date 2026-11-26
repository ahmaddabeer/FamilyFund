package com.example.familyfund.common

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.familyfund.data.repository.MemberRepository
import com.example.familyfund.presentation.auth.LoginUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ApplicationViewModel : ViewModel() {


    val repository = MemberRepository()


    private val _loginuistate = MutableStateFlow<LoginUIState>(LoginUIState.Idle)
    val loginuistate = _loginuistate.asStateFlow()




    var login by mutableStateOf("")
        private set


    fun onLogin(login1: String) {

        login = login1

    }




    fun getUserById() {

        if(login.isBlank()){

            _loginuistate.value = LoginUIState.Empty("Please enter your ID")
            return
        }

        viewModelScope.launch {

            _loginuistate.value = LoginUIState.Loading

            try {

                val id = repository.getMemberById(login.trim())

                if(id != null){
                    _loginuistate.value = LoginUIState.Sucess(id)


                }else {

                    _loginuistate.value = LoginUIState.Empty()
                }
            }catch (e: Exception){

                _loginuistate.value = LoginUIState.Error(
                    e.localizedMessage ?: "Somthing went wrong"
                )
            }

        }



    }


}