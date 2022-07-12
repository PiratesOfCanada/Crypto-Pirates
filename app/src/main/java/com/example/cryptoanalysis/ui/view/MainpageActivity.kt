package com.example.cryptoanalysis.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.cryptoanalysis.R
import com.example.cryptoanalysis.data.api.FavCoinRetroApiInterface
import com.example.cryptoanalysis.data.model.Coin
import com.example.cryptoanalysis.data.repo.FavouriteCoinRepository
import com.example.cryptoanalysis.databinding.MainpageBinding
import com.example.cryptoanalysis.ui.viewmodel.FavouriteCoinViewModel

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth

import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

@AndroidEntryPoint
class MainpageActivity : AppCompatActivity() {

    private lateinit var binding: MainpageBinding

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm: FavouriteCoinViewModel by viewModels()
        binding = MainpageBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        auth = Firebase.auth
//        var currentUser = auth.currentUser
//
//        //update the access token
//        currentUser?.getIdToken(true)?.addOnCompleteListener {
//            if (it.isSuccessful) {
//                AccessToken.accessToken = it.result.token
//            } else {
//                println("Error accessing token.")
//            }
//        }

        val navView: BottomNavigationView = binding.navView
//        val inter = FavCoinRetroApiInterface.create()
//        val repo = FavouriteCoinRepository(inter)
//        vm = FavouriteCoinViewModel(repo)

        val navController = findNavController(R.id.nav_host_fragment_mainpage)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_discover, R.id.navigation_news
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        binding.btnLogout.setOnClickListener {
            Firebase.auth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.saveCoinBtn.setOnClickListener {
            val userId = Firebase.auth.uid
            if (userId != null) {
                var coin = Coin(iconUrl = "", symbol = "New", color = "#ffffff", name = "NewCoin", price = 12345.67f)
                val jsonString = Gson().toJson(coin).toString()
                val reqBody =
                    jsonString.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

                vm.saveFavouriteCoin(reqBody)
            }
            println(userId)
        }
    }
}