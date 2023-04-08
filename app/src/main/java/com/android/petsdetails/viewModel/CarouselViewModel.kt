package com.android.petsdetails.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.petsdetails.R
import com.android.petsdetails.misc.SealedState
import com.android.petsdetails.misc.Util
import com.android.petsdetails.repository.CarouselRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CarouselViewModel constructor(private val repo: CarouselRepo) :
    ViewModel() {

    private val myList: MutableStateFlow<SealedState> = MutableStateFlow(SealedState.Empty)
    val getInitialList get() = myList

    init {
        getInitialList()
    }

    fun getInitialList() {
        viewModelScope.launch {
            repo.getCarouselList().let {
                it.collect {
                    myList.value = SealedState.Success(it)
                }
            }
        }
    }

    @SuppressLint("ResourceType")
    fun getDataByIndex(index: Int): MutableList<String> {
        var total = listOf(
            Util.Strings.getArray(R.array.cat),
            Util.Strings.getArray(R.array.dog),
            Util.Strings.getArray(R.array.sheep),
            Util.Strings.getArray(R.array.rabbit),
            Util.Strings.getArray(R.array.parrot),
            Util.Strings.getArray(R.array.fish),
            Util.Strings.getArray(R.array.horse),
            Util.Strings.getArray(R.array.rat),
        )
        return total.get(index)
    }
}