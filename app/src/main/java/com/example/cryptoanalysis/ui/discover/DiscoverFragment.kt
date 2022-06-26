package com.example.cryptoanalysis.ui.discover



import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoanalysis.Coin
import com.example.cryptoanalysis.RetrofitInterface
import com.example.cryptoanalysis.databinding.FragmentDiscoverBinding
import com.example.cryptoanalysis.ui.home.RecyclerAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList

class DiscoverFragment : Fragment() {

    private var _binding: FragmentDiscoverBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val dashboardViewModel =
//            ViewModelProvider(this).get(DiscoverViewModel::class.java)

        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        val api = RetrofitInterface.create()
        val repo = DiscoverRepo(api)
        val vm = DiscoverViewModel(repo)

        val list = ArrayList<Coin>()
        var recyclerAdapter: RecyclerAdapter = RecyclerAdapter(list, requireContext())

        val recyclerview: RecyclerView = binding.recyclerview
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        recyclerAdapter.setItemListener(object : RecyclerAdapter.onItemClickListener {
            override fun onClickListener(position: Int) {
                Log.d("Clicked", "element - ${list[position]}")
            }

        })
        recyclerview.adapter = recyclerAdapter
        vm.getAllCoins()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
//                    println("ResponseCoins:: $it")
                    recyclerAdapter.setItem(it.data.coins as java.util.ArrayList<Coin>)

                },
                onComplete = {
                    //println("Completed, $list")
                    //moneyadapter.setItem(list)
                },

                onError = { e ->
                    println("error :: $e")
                }
            )


        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    ///////*****************************


    lateinit var searchText: String
    val list = ArrayList<Coin>()
    var recyclerAdapter: RecyclerAdapter = RecyclerAdapter(list, requireContext())

    private fun searchCoin() {
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                searchText = s.toString().toLowerCase()

                updateRecyclerView()
            }
        })
    }

    private fun updateRecyclerView() {
        val data = ArrayList<Coin>()

        for (item in list) {

            var coinName = item.name.lowercase(Locale.getDefault())
            var coinSymbol = item.symbol.lowercase(Locale.getDefault())

            if (coinName.contains(searchText) || coinSymbol.contains(searchText)) {
                data.add(item)
            }
        }
        recyclerAdapter.updateData(data)


    }
}


