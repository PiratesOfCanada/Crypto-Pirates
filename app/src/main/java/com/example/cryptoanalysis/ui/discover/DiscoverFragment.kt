package com.example.cryptoanalysis.ui.discover



import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoanalysis.Coin
import com.example.cryptoanalysis.RetrofitInterface
import com.example.cryptoanalysis.databinding.FragmentDiscoverBinding
import com.example.cryptoanalysis.ui.home.RecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

@AndroidEntryPoint
class DiscoverFragment : Fragment() {

    private var _binding: FragmentDiscoverBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var list = ArrayList<Coin>()
    lateinit var recyclerAdapter: RecyclerAdapter
    val vm : DiscoverViewModel by viewModels()
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

       // val api =  RetrofitInterface.create()
        //val repo = DiscoverRepo(api)

        recyclerAdapter = RecyclerAdapter(list, requireContext())
        val recyclerview : RecyclerView = binding.recyclerview
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        recyclerAdapter.setItemListener(object : RecyclerAdapter.onItemClickListener{
            override fun onClickListener(position: Int) {
                Log.d("Clicked", "element - ${list[position]}")
            }

        })
        recyclerview.adapter = recyclerAdapter
        vm.getAllCoins()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (
                onNext = {
//                    println("ResponseCoins:: $it")
                    list = it.data.coins as java.util.ArrayList<Coin>
                    recyclerAdapter.setItem(list)

                },
                onComplete ={
                    //println("Completed, $list")
                    //moneyadapter.setItem(list)
                },

                onError = {
                        e -> println("error :: $e")
                }
            )
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
