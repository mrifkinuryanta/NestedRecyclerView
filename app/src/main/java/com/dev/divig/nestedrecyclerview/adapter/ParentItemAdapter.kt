package com.dev.divig.nestedrecyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import com.dev.divig.nestedrecyclerview.databinding.ItemParentBinding
import com.dev.divig.nestedrecyclerview.model.MovieEntity
import com.dev.divig.nestedrecyclerview.model.ParentEntity
import com.dev.divig.nestedrecyclerview.utils.SpacesItemDecoration
import com.dev.divig.nestedrecyclerview.utils.Utils

class ParentItemAdapter(
    private val parentItemList: List<ParentEntity>,
    private val onClickShowAll: (ParentEntity) -> Unit,
    private val onClickItemChild: (MovieEntity) -> Unit
) : RecyclerView.Adapter<ParentItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemParentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onClickShowAll, onClickItemChild)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bindView(parentItemList[position])
    }

    override fun getItemCount(): Int = parentItemList.size

    class ViewHolder(
        private val binding: ItemParentBinding,
        private val onClickShowAll: (ParentEntity) -> Unit,
        private val onClickItemChild: (MovieEntity) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: ParentEntity) {
            with(item) {
                binding.parentItemTitle.text = parentItemTitle
                val layoutManager = LinearLayoutManager(
                    binding.root.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )

                //Define how many child items
                layoutManager.initialPrefetchItemCount = childItemList.size

                val childItemAdapter = ChildItemAdapter(childItemList) {
                    onClickItemChild(it)
                }
                binding.childRecyclerView.apply {
                    this.layoutManager = layoutManager
                    addItemDecoration(
                        SpacesItemDecoration(
                            Utils.dpToPixels(binding.root.context, 10), false
                        )
                    )
                    adapter = childItemAdapter
                    setRecycledViewPool(RecycledViewPool())
                }

                binding.btnShowAll.setOnClickListener {
                    onClickShowAll(this)
                }
            }
        }
    }
}