package com.dicoding.asclepius.view.history

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.databinding.ActivityHistoryBinding
import com.dicoding.asclepius.view.result.ResultActivity

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory()
        )[HistoryViewModel::class.java]
    }

    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            setSupportActionBar(toolbar)
            toolbar.setNavigationOnClickListener {
                finish()
            }
            viewModel.data.observe(this@HistoryActivity){
                if(it.isEmpty()){
                    tvNoData.visibility = View.VISIBLE
                    rvList.visibility = View.GONE
                }else{
                    tvNoData.visibility = View.GONE
                    rvList.visibility = View.VISIBLE

                    adapter = HistoryAdapter(it, ::navigateToDetailPage)
                    rvList.layoutManager = LinearLayoutManager(this@HistoryActivity)
                    rvList.adapter = adapter
                }
            }
        }

    }

    private fun navigateToDetailPage(uri: String, result: String, score: Float) {
        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra(ResultActivity.RESULT, result)
            putExtra(ResultActivity.CONFIDENCE_SCORE, score)
            putExtra(ResultActivity.IMAGE_URI, uri)
            putExtra(ResultActivity.DETAIL_PAGE, true)
        }
        startActivity(intent)
    }
}