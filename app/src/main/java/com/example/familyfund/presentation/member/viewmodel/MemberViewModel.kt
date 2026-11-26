package com.example.familyfund.presentation.member.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.familyfund.data.model.PaymentRequestModel
import com.example.familyfund.data.repository.MemberRepository
import com.example.familyfund.data.repository.PaymentRepository
import com.example.familyfund.presentation.member.uistate.MemberHomeUiState
import com.example.familyfund.presentation.member.uistate.MemberNotifyPushUiState
import com.example.familyfund.presentation.member.uistate.MemberPaymentHistoryUiState
import com.example.familyfund.presentation.member.uistate.MemberProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MemberViewModel : ViewModel() {


    private var allPaymentList = listOf<PaymentRequestModel>()
    private val paymentRepository = PaymentRepository()
    private val memberRepository = MemberRepository()

    private val _memberProfileUiState =
        MutableStateFlow<MemberProfileUiState>(MemberProfileUiState.Loading)

    var memberProfileUiState = _memberProfileUiState.asStateFlow()
    private val _memberHomeUiState =
        MutableStateFlow<MemberHomeUiState>(MemberHomeUiState.Loading)

    val memberHomeUiState = _memberHomeUiState.asStateFlow()

    private val _memberPushUiState =
        MutableStateFlow<MemberNotifyPushUiState>(
            MemberNotifyPushUiState.Idel
        )

    val memberPushUiState = _memberPushUiState.asStateFlow()


    @RequiresApi(Build.VERSION_CODES.O)
    fun getMemberDetails(memberId: String) {

        viewModelScope.launch {
            _memberHomeUiState.value = MemberHomeUiState.Loading
            _memberProfileUiState.value = MemberProfileUiState.Loading

            try {
                val member = memberRepository.getMemberById(memberId)

                paymentRepository.getPaymentRequest(
                    onResult = { payments ->

                        _memberHomeUiState.value = MemberHomeUiState.Sucess(member, payments)


                    },
                    onError = { exception ->

                        _memberHomeUiState.value = MemberHomeUiState.Error(
                            exception.localizedMessage ?: "Somthing went wrong"
                        )


                    }
                )


                //profile ma data fetch karn ka lea

                _memberProfileUiState.value = MemberProfileUiState.Success(member)


            } catch (e: Exception) {

                _memberHomeUiState.value = MemberHomeUiState.Error(

                    e.localizedMessage ?: "somthing went wrong"
                )


                _memberProfileUiState.value = MemberProfileUiState.Error(

                    e.localizedMessage ?: "Somthing went wrong"
                )

            }
        }
    }


    fun getProfileMemberDetails(memberId: String) {

        viewModelScope.launch {
            _memberProfileUiState.value = MemberProfileUiState.Loading

            try {
                val member = memberRepository.getMemberById(memberId)


                //profile ma data fetch karn ka lea

                _memberProfileUiState.value = MemberProfileUiState.Success(member)


            } catch (e: Exception) {


                _memberProfileUiState.value = MemberProfileUiState.Error(

                    e.localizedMessage ?: "Somthing went wrong"
                )

            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun submitPaymentRequest(request: PaymentRequestModel) {

        _memberPushUiState.value = MemberNotifyPushUiState.Idel

        viewModelScope.launch {
            _memberPushUiState.value = MemberNotifyPushUiState.Loading

            try {
                paymentRepository.submitRequest(request)

                _memberPushUiState.value =
                    MemberNotifyPushUiState.Success(
                        "Payment request Submitted sucessfully"
                    )

            } catch (exception: Exception) {

                _memberPushUiState.value =
                    MemberNotifyPushUiState.Error(
                        exception.localizedMessage ?: "Somthing went wrong"
                    )
            }
        }
    }


    //payment history

    private val _memberPaymentHistoryUiState = MutableStateFlow<MemberPaymentHistoryUiState>(
        MemberPaymentHistoryUiState.Loading
    )

    val memberPaymentHistoryUiState = _memberPaymentHistoryUiState.asStateFlow()


    @RequiresApi(Build.VERSION_CODES.O)
    fun getMemberPaymetHistory(id: String) {


        viewModelScope.launch {

            _memberPaymentHistoryUiState.value = MemberPaymentHistoryUiState.Loading


            try {

                val memberbyid = memberRepository.getMemberById(id)


                paymentRepository.getAllPaymentRequest(
                    onResult = { allpayments ->

                        allPaymentList = allpayments

                        paymentRepository.getPaymentRequest(
                            onResult = { result ->


                                _memberPaymentHistoryUiState.value =
                                    MemberPaymentHistoryUiState.Success(
                                        memberbyid,
                                        result,
                                        allPaymentList
                                    )
                            },
                            onError = { error ->

                                _memberPaymentHistoryUiState.value =
                                    MemberPaymentHistoryUiState.Error(
                                        error.localizedMessage ?: "Somthing went wrong"
                                    )
                            }
                        )


                    },
                    onError = { error ->

                        _memberPaymentHistoryUiState.value = MemberPaymentHistoryUiState.Error(
                            error.localizedMessage ?: "Failed to load payment list"
                        )
                    }
                )


            } catch (e: Exception) {

                _memberPaymentHistoryUiState.value = MemberPaymentHistoryUiState.Error(
                    e.localizedMessage ?: " Somthing went wrong"
                )
            }
        }


    }
}
