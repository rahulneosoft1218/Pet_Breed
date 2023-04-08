package com.android.petsdetails.misc

import com.android.petsdetails.data.Item

sealed class SealedState {
    class Failure(val error:Throwable) : SealedState()
    class Success(val data:List<Item>) : SealedState()
    object Empty : SealedState()
}