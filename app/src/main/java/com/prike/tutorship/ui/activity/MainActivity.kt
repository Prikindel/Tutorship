package com.prike.tutorship.ui.activity

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.prike.tutorship.R

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        val currentUser = auth.currentUser
        Log.d("TAG", currentUser.toString())
        if (currentUser != null) {
            val profileUpdates = userProfileChangeRequest {
                displayName = "Jane Q. User"
                photoUri = Uri.parse("https://example.com/jane-q-user/profile.jpg")
            }

            currentUser!!.updateProfile(profileUpdates)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("TAG", "User profile updated.")
                    }
                }
            currentUser?.let {
                // Name, email address, and profile photo Url
                val name = currentUser.displayName
                val email = currentUser.email
                val photoUrl = currentUser.photoUrl

                // Check if user's email is verified
                val emailVerified = currentUser.isEmailVerified

                // The user's ID, unique to the Firebase project. Do NOT use this value to
                // authenticate with your backend server, if you have one. Use
                // FirebaseUser.getToken() instead.
                val uid = currentUser.uid
                Log.i("TAG", "name $name")
                Log.i("TAG", "email $email")
                Log.i("TAG", photoUrl.toString())
                Log.i("TAG", emailVerified.toString())
                Log.i("TAG", uid)
            }
        } else {
            Log.i("TAG", "false")
        }

        currentUser ?: auth.createUserWithEmailAndPassword("email@ya.ru", "password")
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "createUserWithEmail:success")
                    Log.d("TAG", currentUser.toString())
                    val user = auth.currentUser
                    Log.d("TAG", user.toString())
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    Log.d("TAG", null.toString())
                }

                // ...
            }

        val database = Firebase.database
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!")
    }
}