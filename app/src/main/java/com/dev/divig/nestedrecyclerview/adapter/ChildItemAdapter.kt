package com.dev.divig.nestedrecyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dev.divig.nestedrecyclerview.R
import com.dev.divig.nestedrecyclerview.databinding.ItemChildBinding
import com.dev.divig.nestedrecyclerview.model.MovieEntity

class ChildItemAdapter(
    private var childItemList: List<MovieEntity>,
    private val itemClick: (MovieEntity) -> Unit
) : RecyclerView.Adapter<ChildItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemChildBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bindView(childItemList[position])
    }

    override fun getItemCount(): Int = childItemList.size

    class ViewHolder(
        private val binding: ItemChildBinding,
        private val itemClick: (MovieEntity) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: MovieEntity) {
            with(item) {
                val imgUrl = "https://image.tmdb.org/t/p/w500$posterPath"
                binding.ivMoviePoster.load(imgUrl) {
                    placeholder(R.drawable.ic_launcher_background)
                    error(R.drawable.ic_launcher_foreground)
                }
                binding.llItemChild.setOnClickListener {
                    itemClick(this)
                }
            }
        }
    }
}