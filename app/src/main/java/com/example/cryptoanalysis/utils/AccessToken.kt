package com.example.cryptoanalysis.utils

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AccessToken {
    companion object {
        var accessToken: String? = null
    }
}