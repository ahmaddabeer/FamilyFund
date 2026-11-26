package com.example.familyfund.data.repository

import android.util.Log
import com.example.familyfund.data.model.PaymentRequestModel
import com.example.familyfund.data.model.MemberModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.tasks.await

class MemberRepository {


    private val memberRef = FirebaseDatabase
        .getInstance()
        .getReference("FamilyFund")
        .child("members")




    suspend fun addMember(name: String, phone: String): String {

        val memberRef = memberRef.push()

        val memberId = memberRef.key
            ?: throw Exception("Member Id not generated")


        val memberModel = MemberModel(

            memberId,
            name,
            250,
            phone,
            createdAt = System.currentTimeMillis(),
            updatedAt = 0L


        )

        memberRef.setValue(memberModel).await()

        return memberId
    }

    suspend fun removeMember(memberId: String): Boolean {


        return try {

            memberRef
                .child(memberId)
                .removeValue()
                .await()
            true
        } catch (e: Exception) {

            Log.e("FamilyFund", "Delete Failed", e)
            false

        }


    }

    suspend fun getMemberById(uid: String): MemberModel? {

        val snapshot = memberRef.child(uid).get().await()

        return if (snapshot.exists()) {

            snapshot.getValue(MemberModel::class.java)

        } else {

            null
        }
    }






    fun getMembers(

        onResult: (List<MemberModel>) -> Unit,
        onError: (Exception) -> Unit
    ) {

        memberRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val memberModels = snapshot.children.mapNotNull {

                    it.getValue(MemberModel::class.java)
                }

                onResult(memberModels)
            }

            override fun onCancelled(error: DatabaseError) {

                onError(error.toException())
            }

        })
    }


//    fun updateMember()




}