package com.example.familyfund.data.repository

import android.util.Log
import com.example.familyfund.data.Member
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


        val member = Member(

            memberId,
            name,
            250,
            phone,
            createdAt = System.currentTimeMillis(),
            updatedAt = 0L


        )

        memberRef.setValue(member).await()

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

    suspend fun getMemberById(uid: String): Member? {

        val snapshot = memberRef.child(uid).get().await()

        return if (snapshot.exists()) {

            snapshot.getValue(Member::class.java)

        } else {

            null
        }
    }


    fun getMembers(

        onResult: (List<Member>) -> Unit,
        onError: (Exception) -> Unit
    ) {

        memberRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val members = snapshot.children.mapNotNull {

                    it.getValue(Member::class.java)
                }

                onResult(members)
            }

            override fun onCancelled(error: DatabaseError) {

                onError(error.toException())
            }

        })
    }


//    fun updateMember()
}