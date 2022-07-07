package com.example.cryptoanalysis.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.cryptoanalysis.databinding.ActivityCoinDetailPageBinding
import kotlinx.android.synthetic.main.activity_coin_detail_page.*

class CoinDetailPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoinDetailPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinDetailPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val coinName = intent.getStringExtra("coinName").toString()
        val coinIcon = intent.getStringExtra("coinURL").toString()
        val coinSymbol = intent.getStringExtra("coinSymbol").toString()

        binding.imageView.loadUrl(coinIcon)

        currencyname.text = coinName
        var coin = coinSymbol

        loadChart("1", coin)

        binding.Btn1Min.setOnClickListener {
            loadChart("1", coin)
        }

        binding.Btn15Min.setOnClickListener {
            loadChart("15", coin)
        }

        binding.Btn1Hr.setOnClickListener {
            loadChart("1H", coin)
        }
        binding.Btn1Day.setOnClickListener {
            loadChart("1D", coin)
        }

        binding.Btn1Wk.setOnClickListener {
            loadChart("1W", coin)
        }

        binding.Btn1Month.setOnClickListener {
            loadChart("1M", coin)
        }

        binding.Btn1Year.setOnClickListener {
            loadChart("1Y", coin)
        }
    }

    private fun loadChart(s: String, coin: String){

        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        webView.loadUrl(
            "https://s.tradingview.com/widgetembed/?frameElementId=tradingview_76d87&symbol=" + coin +
                    "USD&interval=" + s + "&hidesidetoolbar=1&hidetoptoolbar=1&symboledit=1&saveimage=1&toolbarbg=" +
                    "F1F3F6&studies=[]&hideideas=1&theme=Dark&style=1&timezone=Etc%2FUTC&studies_overrides={}&overrides={}&enabled_features=" +
                    "[]&disabled_features=[]&locale=en&utm_source=coinmarketcap.com&utm_medium=widget&utm_campaing=chart&utm_term=BTCUSDT"
        )
    }

    fun ImageView.loadUrl(url: String) {
        val imageLoader = ImageLoader.Builder(this.context)
            .componentRegistry { add(SvgDecoder(this@loadUrl.context)) }
            .build()
        val request = ImageRequest.Builder(this.context)
            .crossfade(true)
            .crossfade(500)
            .data(url)
            .target(this)
            .build()

        imageLoader.enqueue(request)
    }
}