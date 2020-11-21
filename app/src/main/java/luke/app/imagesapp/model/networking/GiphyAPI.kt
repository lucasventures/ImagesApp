package luke.app.imagesapp.model.networking

import luke.app.imagesapp.model.giphy.search.callinfo.GiphyQueryImageDataResponseInfo
import luke.app.imagesapp.model.giphy.trending.callinfo.GiphyTrendingImageDataResponseInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyAPI {

    //api key = api_key=dc6zaTOxFJmzC

    @GET("/v1/gifs/trending")
    fun getTrendingImages(): Call<GiphyTrendingImageDataResponseInfo>

    @GET("/v1/gifs/search")
    fun getQueryImages(@Query("q") query : String): Call<GiphyQueryImageDataResponseInfo>

}