package com.example.cryptoanalysis.ui.view

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cryptoanalysis.databinding.ActivityLoginBinding
import com.example.cryptoanalysis.utils.AccessToken
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore //Firestore Database/Cloud Firestore
    private lateinit var binding: ActivityLoginBinding
    private lateinit var preferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = getSharedPreferences("prefs", MODE_PRIVATE)

        auth = Firebase.auth
        db = Firebase.firestore
        // Check if user is signed in (non-null) and update UI accordingly.
        var currentUser = auth.currentUser

        //update the access token
        currentUser?.getIdToken(true)?.addOnCompleteListener {
            if (it.isSuccessful) {
                AccessToken.accessToken = it.result.token
            } else {
                println("Error accessing token.")
            }
        }

        if (currentUser != null) {
//                    db.collection("users")
//                        .get()
//                        .addOnSuccessListener { result ->
//                            for (document in result) {
//                                Log.d(TAG, "${document.id} => ${document.data}")
//                            }
//                        }
//                        .addOnFailureListener { exception ->
//                            Log.w(TAG, "Error getting documents.", exception)
//                        }

            val intent = Intent(this, MainpageActivity::class.java)
            startActivity(intent)
        }

        if (preferences.getBoolean("isChecked", false)) {
            binding.username.setText(preferences.getString("userName", ""))
            binding.password1.setText(preferences.getString("password", ""))
            binding.checkBox.isChecked = true
        }

        binding.btnLogin.setOnClickListener {
            val userName = binding.username.text.toString()
            val password = binding.password1.text.toString()
            if (userName.isNotEmpty() && password.isNotEmpty()) {
                val b = preferences.getBoolean("isChecked", false)
                if (preferences.getBoolean("isChecked", false)) {
                    with(preferences.edit()) {
                        putString("userName", binding.username.text.toString())
                        putString("password", binding.password1.text.toString())
                        apply()
                    }
                } else {
                    with(preferences.edit()) {
                        preferences.edit().putString("userName", "")
                        preferences.edit().putString("password", "")
                        apply()
                    }
                }
                auth.signInWithEmailAndPassword(userName, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            currentUser = auth.currentUser
                            // Sign in success, update UI with the signed-in user's information
                            if (currentUser?.isEmailVerified == true) {
                                val intent = Intent(this, MainpageActivity::class.java)
                                Log.d(TAG, "signInWithEmail:success")
                                startActivity(intent)
                            } else {
                                Log.d(TAG, "Please verify your email")
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(
                                TAG,
                                "signInWithEmail:failure: ${task.exception?.message}",
                                task.exception
                            )
                        }
                    }
            }
        }

        binding.checkBox.setOnCheckedChangeListener { checkBox, isChecked ->
            with(preferences.edit()) {
                putBoolean("isChecked", isChecked)
                apply()
            }
        }

        binding.signup.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.forgetpassword.setOnClickListener {
            val intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}