package com.realtimedatabase.callbackflow.firebase

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

object FirebaseDatabaseCallbackFlow {

    fun readFirebaseRealtimeDatabaseFlow(): Flow<String> = callbackFlow {

        val database = FirebaseDatabase.getInstance("https://realtime-db-test-83be1-default-rtdb.europe-west1.firebasedatabase.app/")
        val databaseRef = database.reference

        val firebaseDataListeners = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.value
                Log.d(TAG, "Value is: $value")
                trySend(value?.toString() ?: "")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        }

        databaseRef.addValueEventListener(firebaseDataListeners)

        awaitClose {
            databaseRef.removeEventListener(firebaseDataListeners)
        }

    }

}