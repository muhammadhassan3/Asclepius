package com.dicoding.asclepius.view.result

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import com.dicoding.asclepius.R
import com.dicoding.asclepius.databinding.ActivityResultBinding
import com.dicoding.asclepius.utils.formatToString
import com.dicoding.asclepius.view.articles.ArticlesActivity
import kotlin.math.roundToInt

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private val viewModel by lazy {
        ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory(application)
        )[ResultViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val imageUri = intent.getStringExtra(IMAGE_URI) ?: ""
        var confidenceScore = intent.getFloatExtra(CONFIDENCE_SCORE, 0.0f)
        val result = intent.getStringExtra(RESULT) ?: ""
        val inferenceTime = intent.getLongExtra(INFERENCE_TIME, 0L)
        val detail = intent.getBooleanExtra(DETAIL_PAGE, false)

        if (!detail) {
            viewModel.saveRecord(imageUri, confidenceScore, result)
        }

        with(binding) {
            confidenceScore *= 100
            Log.i("Inference Time", inferenceTime.toString().plus(" ms"))

            setSupportActionBar(toolbar)
            toolbar.setNavigationOnClickListener {
                finish()
            }

            articlesButton.setOnClickListener {
                startActivity(Intent(this@ResultActivity, ArticlesActivity::class.java))
            }

            resultImage.setImageURI(imageUri.toUri())
            resultText.text = resources.getString(R.string.result, result)
            score.text = confidenceScore.formatToString().plus("%")
            progressScore.progress = confidenceScore.roundToInt()
        }
    }

    companion object {
        const val IMAGE_URI = "image_uri"
        const val CONFIDENCE_SCORE = "confidence_score"
        const val RESULT = "result"
        const val INFERENCE_TIME = "inference_time"
        const val DETAIL_PAGE = "detail_page"
    }
}