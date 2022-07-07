package com.example.cryptoanalysis.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.cryptoanalysis.databinding.ActivityCoinDetailPageBinding
import kotlinx.android.synthetic.main.activity_coin_detail_page.*

class CoinDetailPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoinDetailPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinDetailPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var coin = "BTC"

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
}