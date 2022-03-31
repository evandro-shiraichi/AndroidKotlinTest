package com.android.example.my_sudoku.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.example.my_sudoku.R
import com.android.example.my_sudoku.databinding.MainFragmentBinding
import com.android.example.my_sudoku.view_model.MainViewModel
import com.android.example.my_sudoku.view_model.SquareDataListAdapter

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()
    private lateinit var userListAdapter: SquareDataListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return MainFragmentBinding.inflate(inflater, container, false)
            .apply {
                this.viewModel = this@MainFragment.viewModel
                lifecycleOwner = viewLifecycleOwner

                list.run {
                    layoutManager = GridLayoutManager(context, 9, RecyclerView.VERTICAL, false)
                    ///*
                    addItemDecoration(
                        DividerItemDecoration(
                            context,
                            DividerItemDecoration.HORIZONTAL
                        )
                    )
                    addItemDecoration(
                        DividerItemDecoration(
                            context,
                            DividerItemDecoration.VERTICAL
                        )
                    )
                    //*/
                    adapter =
                        SquareDataListAdapter(viewLifecycleOwner, this@MainFragment.viewModel).also {
                            userListAdapter = it
                        }

                }
            }
            .run {
                root
            }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.run {
            squareData.observe(viewLifecycleOwner) {
                userListAdapter.submitList(it)
            }
        }
    }
}