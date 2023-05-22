package com.okankkl.cebimdekimarket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.okankkl.cebimdekimarket.Adapter.MyCardAdapter
import com.okankkl.cebimdekimarket.Model.MyCard
import com.okankkl.cebimdekimarket.ViewModel.MyCardViewModel
import com.okankkl.cebimdekimarket.ViewModel.ProductViewModel
import com.okankkl.cebimdekimarket.databinding.MyCardBottomsheetBinding

class BottomFragment : BottomSheetDialogFragment() {

    private var _binding : MyCardBottomsheetBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter : MyCardAdapter
    private lateinit var viewModel : MyCardViewModel
    private var myCardList = emptyList<MyCard>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MyCardBottomsheetBinding.inflate(layoutInflater,container,false)
        context?.let {
            viewModel = MyCardViewModel(it)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(this.context)
        binding.myCardsRecyclerView.layoutManager = layoutManager
        adapter = MyCardAdapter()
        adapter.setData(myCardList)
        binding.myCardsRecyclerView.adapter = adapter

        viewModel.myCardList.observe(this) {
            myCardList = it
            adapter.setData(myCardList)
            adapter.notifyDataSetChanged()
        }

        adapter.deleteClick = {
            viewModel.deleteCard(it)
        }

        adapter.increaseClick = {
            viewModel.increaseProduct(it)
        }

        adapter.decreaseClick = {
            viewModel.decreaseProduct(it)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}