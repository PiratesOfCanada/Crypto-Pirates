package com.example.cryptoanalysis.ui.home

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoanalysis.Coin
import com.example.cryptoanalysis.databinding.FragmentHomeBinding


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
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerview :RecyclerView = binding.recyclerView

        var list = ArrayList<Coin>()
        list.add(Coin(name = "Bitcoin", symbol = "BTC", color = "#f7931A", price = 21173.51734301634f, iconUrl = "https://cdn.coinranking.com/bOabBYkcX/bitcoin_btc.svg"))

        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        val RecyclerAdapter = RecyclerAdapter(list, requireContext())

        RecyclerAdapter.setItemListener(object : RecyclerAdapter.onItemClickListener{
            override fun onClickListener(position: Int) {
                Log.d("Clicked", "element - ${list[position]}")
            }

        })
        recyclerview.adapter = RecyclerAdapter
//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }


//        for(i in 0..10){
//            val map: HashMap<String, String> = HashMap()
//            map["line1"] = "Foo $i"
//            map["line2"] = "Bar $i"
//            list.add(map)
//        }
//        val from = arrayOf("line1", "line2")
//        val to = intArrayOf(R.id.text1, R.id.text2)
//        val adapter  = SimpleAdapter(
//            requireContext(), list,
//            R.layout.simple_list_item_2, from, to
//        )
//        //setListAdapter(adapter)
//
//        RecyclerView.Adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}