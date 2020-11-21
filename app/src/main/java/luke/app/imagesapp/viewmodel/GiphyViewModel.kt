package luke.app.imagesapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import luke.app.imagesapp.model.giphy.search.callinfo.GiphyQueryImageDataResponseInfo
import luke.app.imagesapp.model.giphy.trending.callinfo.GiphyTrendingImageDataResponseInfo
import luke.app.imagesapp.repository.GiphyRepository

class GiphyViewModel : ViewModel() {
    var trendingImagesLiveData = MutableLiveData<GiphyTrendingImageDataResponseInfo>()
    var queryLiveData = MutableLiveData<GiphyQueryImageDataResponseInfo>()

    //todo: Can be improved, but fine for now
    private var repository: GiphyRepository? = null

    fun setRepository(repo: GiphyRepository) {
        repository = repo
    }

    fun getTrendingImages() {
        repository?.getTrendingImages(trendingImagesLiveData)
            ?: Log.e("GIPHYVIEWMODEL","Must Set Repository Before Calls")
    }

    fun getQueryImages(userQuery: String) {
        repository?.getQueryImages(queryLiveData,userQuery)
            ?: Log.e("GIPHYVIEWMODEL","Must Set Repository Before Calls")
    }
}