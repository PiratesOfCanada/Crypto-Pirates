package com.example.cryptoanalysis.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.SimpleAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoanalysis.R
import com.example.cryptoanalysis.data.model.ArticlesItem
import com.example.cryptoanalysis.data.model.Coin
import com.example.cryptoanalysis.databinding.FragmentNewsBinding
import com.example.cryptoanalysis.ui.home.NewsAdapter

import com.example.cryptoanalysis.ui.home.RecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NewsViewModel::class.java)

        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val recyclerview :RecyclerView = binding.recyclerView
        var list = ArrayList<ArticlesItem>()
        list.add(ArticlesItem(imageUrl = "https://cdn.coinranking.com/bOabBYkcX/bitcoin_btc.svg", title = "ABCDEF GHI JKL", url = "www.google.com"))
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        val RecyclerAdapter = NewsAdapter(list, requireContext())

        RecyclerAdapter.setItemListener(object : NewsAdapter.onItemClickListener{
            override fun onClickListener(position: Int) {
                Log.d("Clicked", "element - ${list[position]}")
            }

        })
        recyclerview.adapter = RecyclerAdapter


//        val textView: TextView = binding.textNotifications
//        notificationsViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}