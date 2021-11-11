package com.dev.divig.nestedrecyclerview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.divig.nestedrecyclerview.adapter.ParentItemAdapter
import com.dev.divig.nestedrecyclerview.databinding.ActivityMainBinding
import com.dev.divig.nestedrecyclerview.model.ParentEntity
import com.dev.divig.nestedrecyclerview.utils.DataDummy
import com.dev.divig.nestedrecyclerview.utils.SpacesItemDecoration
import com.dev.divig.nestedrecyclerview.utils.Utils

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this@MainActivity)

        val parentItemAdapter = ParentItemAdapter(
            parentItemList(),
            {
                Toast.makeText(this, "Show All ${it.parentItemTitle}", Toast.LENGTH_SHORT).show()
            },
            {
                Toast.makeText(this, it.title, Toast.LENGTH_SHORT).show()
            }
        )

        binding.parentRecyclerview.adapter = parentItemAdapter
        binding.parentRecyclerview.layoutManager = layoutManager
        binding.parentRecyclerview.addItemDecoration(
            SpacesItemDecoration(
                Utils.dpToPixels(
                    this,
                    10
                ),
                true
            )
        )
    }

    private fun parentItemList(): List<ParentEntity> {
        val itemList: MutableList<ParentEntity> = ArrayList()

        val listGenrePlaceholder: MutableList<String> =
            resources.getStringArray(R.array.genre_list).toMutableList()

        listGenrePlaceholder.forEachIndexed { index, value ->
            val item = ParentEntity(
                value,
                if (index == 0) {
                    DataDummy.getMovies()
                        .sortedByDescending { Utils.dateToMillis(it.releaseDate) }
                } else {
                    DataDummy.getMovies()
                        .filter { item -> splitGenre(item.genres).find { it == value } == value }
                }
            )
            if (item.childItemList.isNotEmpty() && item.childItemList.size >= 4) itemList.add(item)
        }
        return itemList
    }

    private fun splitGenre(genre: String?): Array<String> {
        return genre.orEmpty().split(", ".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
    }
}