package com.okankkl.cebimdekimarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.okankkl.cebimdekimarket.Adapter.ProductAdapter
import com.okankkl.cebimdekimarket.Model.Product
import com.okankkl.cebimdekimarket.Model.Sorted
import com.okankkl.cebimdekimarket.ViewModel.MyCardViewModel
import com.okankkl.cebimdekimarket.ViewModel.ProductViewModel
import com.okankkl.cebimdekimarket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : ProductViewModel
    private lateinit var adapter : ProductAdapter
    private var productList = emptyList<Product>()
    private lateinit var cardViewModel : MyCardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ProductViewModel(this)
        cardViewModel = MyCardViewModel(this)
        val bottomFragment = BottomFragment()


        val layoutManager = GridLayoutManager(this,2)
        binding.recyclerView.layoutManager = layoutManager
        adapter = ProductAdapter()
        adapter.setData(productList)
        binding.recyclerView.adapter = adapter



        adapter.addCardClick = { product,quality ->
            cardViewModel.addCard(product,quality)
            Toast.makeText(applicationContext,"Sepete başarıyla eklendi.",Toast.LENGTH_SHORT).show()

        }

        viewModel.productList.observe(this, Observer{
            productList = it
            adapter.setData(productList)
            adapter.notifyDataSetChanged()
        })

        binding.btnOpenMyCard.setOnClickListener {
            bottomFragment.show(supportFragmentManager,"TAG")
        }

        binding.refreshLayout.setOnRefreshListener {
            viewModel.getProductsFromInternet()
            binding.refreshLayout.isRefreshing = false
        }


    }




}