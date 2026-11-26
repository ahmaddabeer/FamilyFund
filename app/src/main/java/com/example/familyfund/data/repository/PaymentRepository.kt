package com.example.familyfund.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.familyfund.data.model.PaymentRequestModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.tasks.await
import java.time.Instant
import java.time.YearMonth
import java.time.ZoneId

class PaymentRepository {


    private val requestRef = FirebaseDatabase
        .getInstance()
        .getReference("FamilyFund")
        .child("paymentRequests")

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun submitRequest(request: PaymentRequestModel) {

        val currentMonth = YearMonth.now().toString()

        val monthRef = requestRef.child(currentMonth)


        val requestKey = monthRef.push().key
            ?: throw Exception("Request ID not generated")


        monthRef.child(requestKey)
            .setValue(request)
            .await()
    }


    fun getAllPaymentRequest(
        onResult: (List<PaymentRequestModel>) -> Unit,
        onError: (Exception) -> Unit
    ) {


        requestRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val paymentRequest = snapshot.children.flatMap { monthSnapshot ->

                    monthSnapshot.children.mapNotNull {

                        it.getValue(PaymentRequestModel::class.java)

                    }
                }

                onResult(paymentRequest)
            }

            override fun onCancelled(error: DatabaseError) {

                onError(error.toException())

            }
        })
    }


    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun updatePaymentRequestStatus(memberId: String) {


        val currentMonth = YearMonth.now().toString()

        val snapshot = requestRef
            .child(currentMonth)
            .orderByChild("memberId")
            .equalTo(memberId)
            .get()
            .await()

        for (request in snapshot.children) {

            request.ref
                .child("status")
                .setValue("Complete")
                .await()
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun getPaymentRequest(

        onResult: (List<PaymentRequestModel>) -> Unit,
        onError: (Exception) -> Unit

    ) {

        val currentMonth = YearMonth.now().toString()

        val monthRef = requestRef.child(currentMonth)


        monthRef.addValueEventListener(object : ValueEventListener {


            override fun onDataChange(snapshot: DataSnapshot) {

                val paymentrequest = snapshot.children.mapNotNull {

                    it.getValue(PaymentRequestModel::class.java)
                }
                onResult(paymentrequest)
            }

            override fun onCancelled(error: DatabaseError) {

                onError(error.toException())
            }
        })


    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getAvailableMonths(): List<String> {
        return requestRef.get().await().children
            .mapNotNull { it.key }
            .sortedDescending()
            .map { month ->

                YearMonth.parse(month)
                    .atDay(1)
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli()
                    .toString()


            }


    }


    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getPaymentsByMonth(timestamp: Long): List<PaymentRequestModel> {

        val month = Instant.ofEpochMilli(timestamp)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
            .let { YearMonth.from(it).toString() }

        return requestRef.child(month)
            .get()
            .await()
            .children
            .mapNotNull {
                it.getValue(PaymentRequestModel::class.java)
            }
    }
}





