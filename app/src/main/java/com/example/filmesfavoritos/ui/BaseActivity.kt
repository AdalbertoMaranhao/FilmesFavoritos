package com.example.filmesfavoritos.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

abstract class BaseActivity : AppCompatActivity() {

    protected val fbAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var hasInitCalled = false
    private var authListener: FirebaseAuth.AuthStateListener =
        FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                if (!hasInitCalled) {
                    hasInitCalled = true

                }
            } else {
                finish()
                val it = Intent(this, SingInActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                }
                startActivity(it)
            }
        }

    public override fun onStart() {
        super.onStart()
        fbAuth.addAuthStateListener(authListener)
    }

    public override fun onStop() {
        super.onStop()
        fbAuth.removeAuthStateListener(authListener)
    }
}