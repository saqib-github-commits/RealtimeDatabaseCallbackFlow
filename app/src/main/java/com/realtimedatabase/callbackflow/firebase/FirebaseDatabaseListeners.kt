package com.realtimedatabase.callbackflow.firebase

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

object FirebaseDatabaseListeners {

    fun readFirebaseRealtimeDatabase() {
        // Create Database Object
        val database = FirebaseDatabase.getInstance("https://realtime-db-test-83be1-default-rtdb.europe-west1.firebasedatabase.app/")
        val databaseRef = database.reference
        // Read from the database
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.value
                Log.d(TAG, "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })

    }
}