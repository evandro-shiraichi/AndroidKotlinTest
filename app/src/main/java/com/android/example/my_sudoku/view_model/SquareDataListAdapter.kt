package com.android.example.my_sudoku.view_model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.example.my_sudoku.databinding.SquareDataViewBinding
import com.android.example.my_sudoku.model.SquareData

private object DiffCallback : DiffUtil.ItemCallback<SquareData>() {
    override fun areItemsTheSame(oldItem: SquareData, newItem: SquareData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SquareData, newItem: SquareData): Boolean {
        return oldItem == newItem
    }

}

class SquareDataListAdapter(
    private val viewLifecycleOwner: LifecycleOwner,
    private val viewModel: MainViewModel)
    : ListAdapter<SquareData, SquareDataListAdapter.SquareDataViewHolder>(DiffCallback) {

    class SquareDataViewHolder(private val binding: SquareDataViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SquareData, viewLifecycleOwner: LifecycleOwner, viewModel: MainViewModel) {
            binding.run {
                lifecycleOwner = viewLifecycleOwner
                squareData = item
                this.viewModel = viewModel

                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SquareDataViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SquareDataViewHolder(SquareDataViewBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: SquareDataViewHolder, position: Int) {
        holder.bind(getItem(position), viewLifecycleOwner, viewModel)
    }
}

