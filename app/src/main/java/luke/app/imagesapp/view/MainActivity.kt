package luke.app.imagesapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import luke.app.imagesapp.R
import luke.app.imagesapp.repository.GiphyRepository
import luke.app.imagesapp.viewmodel.GiphyViewModel


private lateinit var giphyViewModel: GiphyViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        giphyViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(GiphyViewModel::class.java)
        giphyViewModel.setRepository(GiphyRepository())
        giphyViewModel.trendingImagesLiveData.observe(this, Observer {
            Log.d("GIPHY TRENDING", it.toString())
        })

        giphyViewModel.queryLiveData.observe(this, Observer {
            Log.d("GIPHY QUERY", it.toString())
        })

        giphyViewModel.getTrendingImages()
    }
}