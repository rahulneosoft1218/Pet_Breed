package com.android.petsdetails.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.ViewPager
import com.android.petsdetails.viewModel.CommonViewModelFactory
import com.android.petsdetails.adapter.ChildAdapter
import com.android.petsdetails.adapter.ViewPagerAdapter
import com.android.petsdetails.data.Item
import com.android.petsdetails.databinding.ActivityMainBinding
import com.android.petsdetails.misc.SealedState
import com.android.petsdetails.misc.Util
import com.android.petsdetails.repository.CarouselRepo
import com.android.petsdetails.viewModel.CarouselViewModel
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var carouselViewModel: CarouselViewModel
    private var indexList: MutableList<String> = arrayListOf()
    private lateinit var dummy: MutableList<String>
    private lateinit var adapter: ChildAdapter
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        carouselViewModel =
            ViewModelProvider(this, CommonViewModelFactory(this, CarouselRepo())).get(
                CarouselViewModel::class.java
            )

        setRecyclerView(0)
        initListener()
        initObserver()
    }

    private fun setRecyclerView(index: Int) {
        indexList = carouselViewModel.getDataByIndex(index)
        adapter = ChildAdapter(indexList)
        binding.recyclerView.adapter = adapter
    }

    private fun initListener() {
        binding.viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                binding.etSearch.setText("")
                Util.Keyboard.hideKeyboard(this@MainActivity)
                setRecyclerView(position)
            }

        })

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(msg: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun filter(text: String) {
        dummy = ArrayList<String>()
        for (i in 0..indexList.size - 1) {
            if (indexList[i].toLowerCase().contains(text.toLowerCase())) {
                dummy.add(indexList[i])
            }
        }
        binding.ivNoData.visibility = if (!dummy.isEmpty()) View.GONE else View.VISIBLE
        adapter = ChildAdapter(dummy)
        binding.recyclerView.adapter = adapter
    }

    private fun initObserver() {
        lifecycleScope.launch {
            carouselViewModel.getInitialList.let {
                it.collect {
                    when (it) {
                        is SealedState.Success -> {
                            setViewPager(it.data)
                        }
                        is SealedState.Failure -> {

                        }
                        else -> {}
                    }
                }
            }

        }
    }

    private fun setViewPager(data: List<Item>) {
        viewPagerAdapter = ViewPagerAdapter(this@MainActivity, data)
        binding.viewPager.clipToPadding = false
        binding.viewPager.pageMargin = 20
        binding.viewPager.setPadding(0, 0, 70, 0)
        binding.viewPager.adapter = viewPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager, true)
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)
    //Testing build
}