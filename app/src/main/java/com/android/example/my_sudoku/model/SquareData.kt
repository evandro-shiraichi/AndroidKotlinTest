package com.android.example.my_sudoku.model

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class SquareData(val id: Int, val answer: Int, val isFixed: Boolean, val expected: MutableLiveData<Int?> = MutableLiveData(null), val memo: MutableLiveData<Int> = MutableLiveData(0)) {
    val memo1Visibility: LiveData<Int>
        get() = _memo1Visibility
    val _memo1Visibility = MutableLiveData<Int>(View.VISIBLE)
    fun contains(number: NumberEnum): Boolean {
        if(memo == null)
            return false

        return (number.rawValue and memo?.value!!) != 0
    }
}
