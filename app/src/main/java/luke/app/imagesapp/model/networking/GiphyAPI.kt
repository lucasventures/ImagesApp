package luke.app.imagesapp.model.networking

import luke.app.imagesapp.model.giphy.trending.callinfo.GiphyImageDataResponseInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyAPI {

    //api key = api_key=dc6zaTOxFJmzC

    @GET("/v1/gifs/trending")
    fun getTrendingImages(): Call<GiphyImageDataResponseInfo>

    @GET("/v1/gifs/search")
    fun getSearchedImages(@Query("q") query : String): Call<GiphyImageDataResponseInfo>

}