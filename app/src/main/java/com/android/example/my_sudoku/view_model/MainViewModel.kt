package com.android.example.my_sudoku.view_model

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import com.android.example.my_sudoku.model.SquareData
import com.android.example.my_sudoku.model.toFlag

class MainViewModel: ViewModel() {
    private lateinit var squareDataRaw: List<SquareData>

    private lateinit var _squareData: MutableLiveData<List<SquareData>>
    lateinit var squareData: LiveData<List<SquareData>>

    private var _lastSelectedData: SquareData? = null

    init {
        val temp2Array = SudokuAnswers.getExample()
        val list = mutableListOf<SquareData>()

        for (y in (0..8)) {
            for (x in (0..8)) {
                val data = SquareData((y * 9) + x, temp2Array[y][x], false)
                list.add(data)
            }
        }

        squareDataRaw = list
        _lastSelectedData = squareDataRaw.first()
        _squareData = MutableLiveData(squareDataRaw)
        squareData = _squareData.distinctUntilChanged()
    }

    fun onClickExpectButton(num: Int) {
        if (_lastSelectedData != null) {
            val flag = num.toFlag().rawValue
            val target = _lastSelectedData!!.expected.value
            if ((target == null) || (flag and target!! == 0)) {
                _lastSelectedData!!.expected.value = num
            } else {
                _lastSelectedData!!.expected.value = null
            }

            _lastSelectedData!!.memo.value = null
        }
    }

    fun onClickMemoButton(num: Int) {
        if (_lastSelectedData != null) {
            val flag = num.toFlag().rawValue
            val target = _lastSelectedData!!.memo.value!!
            if (flag and target == 0) {
                _lastSelectedData!!.memo.value = target or num
                _lastSelectedData!!._memo1Visibility.value = View.VISIBLE
            } else {
                _lastSelectedData!!.memo.value = target and num.inv()
                _lastSelectedData!!._memo1Visibility.value = View.INVISIBLE
            }

            _lastSelectedData!!.expected?.value = null
        }
    }

    fun onClickSquareData(data: SquareData) {
        _lastSelectedData = data
    }
}

private class SudokuAnswers {
    companion object {
        fun getExample(): Array<Array<Int>> {
            return arrayOf(
                arrayOf(8, 5, 7, 1, 4, 9, 6, 2, 3),
                arrayOf(1, 6, 3, 8, 2, 7, 4, 9, 5),
                arrayOf(9, 4, 2, 3, 5, 6, 1, 8, 7),
                arrayOf(3, 2, 1, 5, 7, 8, 9, 6, 4),
                arrayOf(5, 7, 8, 9, 6, 4, 3, 1, 2),
                arrayOf(6, 9, 4, 2, 3, 1, 7, 5, 8),
                arrayOf(7, 8, 9, 4, 1, 5, 2, 3, 6),
                arrayOf(2, 1, 6, 7, 8, 3, 5, 4, 9),
                arrayOf(4, 3, 5, 8, 9, 2, 8, 7, 1)
            )
        }
    }
}