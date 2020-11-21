package luke.app.imagesapp.repository

import androidx.lifecycle.ViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.HostnameVerifier

open class BaseRepository : ViewModel() {

    //api key = api_key=dc6zaTOxFJmzC

    private var retrofitInstance: Retrofit? = null

    private val baseUrl = "https://api.giphy.com/"
    private val appJson = "application/json"

    protected fun getRetrofitInstance(): Retrofit? {
        return getRetrofitInstance(baseUrl)
    }

    private fun getRetrofitInstance(url: String): Retrofit? {
        if (retrofitInstance != null) {
            return retrofitInstance
        }

        val client = getHTTPClient()
        client.addInterceptor{chain ->
            val request = chain.request().newBuilder()
            val originalHttpUrl = chain.request().url
            val url = originalHttpUrl.newBuilder().addQueryParameter("api_key", "dc6zaTOxFJmzC").build()
            request.url(url)
            return@addInterceptor chain.proceed(request.build())
        }

        val headerAuthorizationInterceptor = Interceptor {
            var request = it.request()
            val headerAcceptor = request.headers.newBuilder()
            headerAcceptor.add("Content-Type", appJson)
            request = request.newBuilder().headers(headerAcceptor.build()).build()
            it.proceed(request)
        }

        client.addInterceptor(headerAuthorizationInterceptor)

        client.hostnameVerifier(HostnameVerifier { hostname, session ->
            true
        })

        retrofitInstance = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
        return retrofitInstance
    }


    private fun getHTTPClient(): OkHttpClient.Builder {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
    }
}