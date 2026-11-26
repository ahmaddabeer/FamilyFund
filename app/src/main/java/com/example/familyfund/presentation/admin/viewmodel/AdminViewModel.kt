package com.example.familyfund.presentation.admin.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.familyfund.data.model.PaymentRequestModel
import com.example.familyfund.data.model.MemberModel
import com.example.familyfund.data.repository.MemberRepository
import com.example.familyfund.data.repository.PaymentRepository
import com.example.familyfund.presentation.admin.AdminHomeScreenUiState
import com.example.familyfund.presentation.admin.screen.AdminMembersScreen
import com.example.familyfund.presentation.admin.shimmerscreen.AdminMemberScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Year
import java.time.YearMonth
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
class AdminViewModel : ViewModel() {


    private val memberRepo = MemberRepository()
    private val paymentRepo = PaymentRepository()


    var availableMonths by mutableStateOf<List<String>>(emptyList())
        private set

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAvilableMoths() {

        viewModelScope.launch {

            availableMonths = paymentRepo.getAvailableMonths()
        }
    }

    var filterDate by mutableLongStateOf(
        YearMonth.now()
            .atDay(1)
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()
    )
        private set


    fun onFilterDateChange(date: Long) {

        filterDate = date
    }


    var searchText by mutableStateOf("")
        private set


    var memberModelList by mutableStateOf<List<MemberModel>>(emptyList())
    var paymentList by mutableStateOf<List<PaymentRequestModel>>(emptyList())

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


    fun changeMemberName(membername: String) {

        memberName = membername
    }


    fun onMemberId(id: String) {

        memberId = id
    }


    fun onPhoneNumberChange(phone: String) {

        phoneNumber = phone
    }


    fun onSearch(search: String) {

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

            try {

                isLoading = true

                val memberId = memberRepo.addMember(
                    name = memberName,
                    phone = phoneNumber
                )

                sucessMessage = "Member created sucessfully ID:$memberId"

                memberName = ""
                phoneNumber = ""
            } catch (e: Exception) {

                errorMessage = e.message ?: "somthing went wrong"

                Log.e("FamilyFund", "Firebase Error: ", e)
            } finally {

                isLoading = false
            }


        }
    }


    fun deleteMember() {

        viewModelScope.launch {

            memberRepo.removeMember(memberId = memberId)
        }
    }

    fun getMemberById() {

        viewModelScope.launch {

            memberRepo.getMemberById(memberId)
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun updatePaymentRequestStatus(id: String) {

        viewModelScope.launch {

            paymentRepo.updatePaymentRequestStatus(id)

        }


    }


    fun getAllMember() {

        memberRepo.getMembers(

            onResult = { members ->


                memberModelList = members

            },
            onError = { error ->

                Log.e("FamilyFound", "Fetch Failed", error)
            }
        )

    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun getAllPaymentList() {

        paymentRepo.getPaymentRequest(

            onResult = { payments ->

                paymentList = payments
            },
            onError = { error ->


            }
        )
    }

    private val _adminHomeScreenUiState =
        MutableStateFlow<AdminHomeScreenUiState>(AdminHomeScreenUiState.Loading)


    var adminHomeScreenUiState = _adminHomeScreenUiState.asStateFlow()


    //get payment record for admin home screen

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAllCurrentMonthPaymentForAdminHomeScreen() {


        viewModelScope.launch {

            _adminHomeScreenUiState.value = AdminHomeScreenUiState.Loading


            try {


                paymentRepo.getAllPaymentRequest(

                    onResult = { allPaymentRecord ->

                        paymentRepo.getPaymentRequest(
                            onResult = { monthlyPaymentRecord ->


                                memberRepo.getMembers(
                                    onResult = { allMember ->

                                        _adminHomeScreenUiState.value =
                                            AdminHomeScreenUiState.Success(
                                                allPaymentRecord,
                                                monthlyPaymentRecord,
                                                allMember

                                            )
                                    },
                                    onError = {

                                    }
                                )


                            },
                            onError = { error ->

                                _adminHomeScreenUiState.value = AdminHomeScreenUiState.Error(
                                    error.localizedMessage ?: "Somthing Went Wrong"
                                )
                            }
                        )


                    },
                    onError = { error ->

                        _adminHomeScreenUiState.value = AdminHomeScreenUiState.Error(
                            error.localizedMessage ?: "Somthing Went Wrong"
                        )
                    }
                )


            } catch (e: Exception) {

                _adminHomeScreenUiState.value =
                    AdminHomeScreenUiState.Error(e.localizedMessage ?: "Somthing went wrong")
            }
        }


    }

    // admin member ui state handle and get data

    private val _adminMemberUiState = MutableStateFlow<AdminMemberScreenUiState>(
        AdminMemberScreenUiState.Loading
    )

    var adminMemberUiState = _adminMemberUiState.asStateFlow()

    fun getMonthlyPaymentRecord() {

        viewModelScope.launch {

            val paymentList = paymentRepo.getPaymentsByMonth(filterDate)


            _adminMemberUiState.value = AdminMemberScreenUiState.Loading


            try {

                memberRepo.getMembers(

                    onResult = { members ->

                        _adminMemberUiState.value =
                            AdminMemberScreenUiState.Success(members, paymentList)


                    },
                    onError = { error ->

                        _adminMemberUiState.value =
                            AdminMemberScreenUiState.Error(error.localizedMessage ?: "Somthing went wrong")


                    }
                )
            } catch (e: Exception) {

                _adminMemberUiState.value =
                    AdminMemberScreenUiState.Error(e.localizedMessage ?: "Somthing went wrong")
            }


        }


    }


}