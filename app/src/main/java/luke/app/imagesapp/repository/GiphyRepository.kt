package luke.app.imagesapp.repository

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import luke.app.imagesapp.model.giphy.search.callinfo.GiphyQueryImageDataResponseInfo
import luke.app.imagesapp.model.giphy.trending.callinfo.GiphyTrendingImageDataResponseInfo
import luke.app.imagesapp.model.networking.GiphyAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GiphyRepository : BaseRepository() {
    private val apiCaller: GiphyAPI
        get() = getRetrofitInstance()!!.create(GiphyAPI::class.java)


    fun getTrendingImages(liveDataTrending: MutableLiveData<GiphyTrendingImageDataResponseInfo>) {
        apiCaller.getTrendingImages()
            .enqueue(object : Callback<GiphyTrendingImageDataResponseInfo> {
                override fun onFailure(
                    call: Call<GiphyTrendingImageDataResponseInfo>,
                    t: Throwable
                ) {
                    Log.e(ContentValues.TAG, "onFailure: Call for giphy failed. ${t.message}")
                }

                override fun onResponse(
                    call: Call<GiphyTrendingImageDataResponseInfo>,
                    response: Response<GiphyTrendingImageDataResponseInfo>
                ) {
                    liveDataTrending.postValue(response.body())
                }

            })
    }

    fun getQueryImages(
        liveDataQuery: MutableLiveData<GiphyQueryImageDataResponseInfo>,
        query: String
    ) {
        apiCaller.getQueryImages(query).enqueue(object : Callback<GiphyQueryImageDataResponseInfo> {
            override fun onFailure(call: Call<GiphyQueryImageDataResponseInfo>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: Call for giphy failed. ${t.message}")
            }

            override fun onResponse(
                call: Call<GiphyQueryImageDataResponseInfo>,
                response: Response<GiphyQueryImageDataResponseInfo>
            ) {
                liveDataQuery.postValue(response.body())
            }

        })
    }

}