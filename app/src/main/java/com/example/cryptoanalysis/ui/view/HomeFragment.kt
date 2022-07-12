package com.example.cryptoanalysis.ui.view


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoanalysis.data.api.FavCoinRetroApiInterface
import com.example.cryptoanalysis.data.model.Coin
import com.example.cryptoanalysis.data.repo.FavouriteCoinRepository
import com.example.cryptoanalysis.databinding.FragmentHomeBinding
import com.example.cryptoanalysis.ui.viewmodel.HomeViewModel
import com.example.cryptoanalysis.ui.adapters.RecyclerAdapter
import com.example.cryptoanalysis.ui.viewmodel.FavouriteCoinViewModel
import com.example.cryptoanalysis.ui.viewmodel.NewsViewModel
import com.example.cryptoanalysis.utils.AccessToken
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val vm : FavouriteCoinViewModel by viewModels()
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        println("abc:: ${AccessToken.accessToken}")
        val recyclerview :RecyclerView = binding.recyclerView

        var list = ArrayList<Coin>()
        list.add(Coin(name = "Bitcoin", symbol = "BTC", color = "#f7931A", price = 21173.51734301634f, iconUrl = "https://cdn.coinranking.com/bOabBYkcX/bitcoin_btc.svg"))

        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        val RecyclerAdapter = RecyclerAdapter(list, requireContext())

        RecyclerAdapter.setItemListener(object : RecyclerAdapter.onItemClickListener {
            override fun onClickListener(position: Int) {
                Log.d("Clicked", "element - ${list[position]}")
            }

        })
        recyclerview.adapter = RecyclerAdapter

//        val inter = FavCoinRetroApiInterface.create()
//        val repo = FavouriteCoinRepository(inter)
//        vm = FavouriteCoinViewModel((repo))

        vm.favouriteCoinList.observe(viewLifecycleOwner) {
           println("abc:: ${AccessToken.accessToken}")
            list.addAll(it)
            RecyclerAdapter.setItem(list)
        }

        vm.getAllFavouriteCoins()
        binding.settingsButton.setOnClickListener(){
            val intent= Intent(requireContext(), Settings::class.java)
            startActivity(intent)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}