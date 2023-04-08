package com.android.petsdetails.repository

import com.android.petsdetails.R
import com.android.petsdetails.data.Item
import com.android.petsdetails.misc.Util
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CarouselRepo() {

    open fun getCarouselList(): Flow<List<Item>> = flow {
            val petList = arrayListOf<Item>(
                Item(getStringValue(R.string.name_cat), R.drawable.cat),
                Item(getStringValue(R.string.name_dog), R.drawable.dog),
                Item(getStringValue(R.string.name_sheep), R.drawable.sheep),
                Item(getStringValue(R.string.name_rabbit), R.drawable.rabbit),
                Item(getStringValue(R.string.name_parrot), R.drawable.parrot),
                Item(getStringValue(R.string.name_fish), R.drawable.fish),
                Item(getStringValue(R.string.name_horse), R.drawable.horse),
                Item(getStringValue(R.string.name_rat), R.drawable.rat)
            )
            emit(petList)

    }.flowOn(Dispatchers.Default)

    private fun getStringValue(pets: Int): String {
        return Util.Strings.get(pets)
    }


}