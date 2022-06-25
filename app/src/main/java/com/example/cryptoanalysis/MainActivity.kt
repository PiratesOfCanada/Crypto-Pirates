package com.example.cryptoanalysis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.cryptoanalysis.ui.view.LoginActivity

class MainActivity : AppCompatActivity() {
//    private lateinit var requestBuilder: GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var imageV = findViewById<ImageView>(R.id.imageView)
//
//        val imageLoader = ImageLoader.Builder(this)
//            .componentRegistry { add(SvgDecoder(imageV.context)) }
//            .build()
//
//        val request = ImageRequest.Builder(this)
////            .crossfade(true)
////            .crossfade(500)
//            .data("https://cdn.coinranking.com/rk4RKHOuW/eth.svg")
//            .target(imageV)
//            .build()
//
//        imageLoader.enqueue(request)


//        Glide.with(imageV).load("https://cdn.coinranking.com/bOabBYkcX/bitcoin_btc.svg").into(imageV)

//        //Only for testing
//        val intent = Intent(this, LoginActivity::class.java)
//        startActivity(intent)

            val intent = Intent(this, MainpageActivity::class.java)
        // To pass any data to next activity
            // intent.putExtra("keyIdentifier", value)
        // start your next activity
            startActivity(intent)
    }
}