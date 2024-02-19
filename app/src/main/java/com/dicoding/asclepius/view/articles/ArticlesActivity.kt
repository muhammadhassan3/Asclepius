package com.dicoding.asclepius.view.articles

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.R
import com.dicoding.asclepius.data.remote.response.UiState
import com.dicoding.asclepius.databinding.ActivityArticlesBinding

class ArticlesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticlesBinding
    private val viewModel by lazy {
        ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        )[ArticlesViewModel::class.java]
    }

    private lateinit var adapter: ArticlesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticlesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        viewModel.data.observe(this) {
            when (it) {
                is UiState.Error -> {
                    showLoading(2)
                    binding.tvMessage.text = it.message
                }

                UiState.Loading -> {
                    showLoading(0)
                }

                is UiState.Success -> {
                    if (it.data.isNotEmpty()) {
                        showLoading(1)
                        adapter = ArticlesAdapter(
                            it.data
                        ) { url ->
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            startActivity(intent)
                        }

                        binding.rvList.apply{
                            layoutManager = LinearLayoutManager(this@ArticlesActivity)
                            adapter = this@ArticlesActivity.adapter
                        }
                    } else {
                        showLoading(2)
                        binding.tvMessage.text = resources.getText(R.string.no_data_found)
                    }
                }
            }
        }

    }

    private fun showLoading(state: Int) {
        binding.apply {
            progressBar.visibility = View.GONE
            rvList.visibility = View.GONE
            tvMessage.visibility = View.GONE

            when (state) {
                0 -> progressBar.visibility = View.VISIBLE
                1 -> rvList.visibility = View.VISIBLE
                2 -> tvMessage.visibility = View.VISIBLE
            }
        }
    }
}