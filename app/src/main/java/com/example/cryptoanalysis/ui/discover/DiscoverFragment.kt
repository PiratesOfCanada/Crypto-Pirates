package com.example.cryptoanalysis.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoanalysis.RetrofitInterface
import com.example.cryptoanalysis.databinding.FragmentDiscoverBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

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

        val api =  RetrofitInterface.create()
        val repo = DiscoverRepo(api)
        val vm = DiscoverViewModel(repo)

        vm.getallAPIMoney()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (
                onNext = {
                         println("JsonObject:: $it")
                        //item -> list.addAll(item)
                    //moneyadapter.setItem(item as ArrayList<Money>)
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