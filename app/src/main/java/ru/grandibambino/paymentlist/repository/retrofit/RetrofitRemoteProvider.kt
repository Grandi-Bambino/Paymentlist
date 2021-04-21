package ru.grandibambino.paymentlist.repository.retrofit


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.grandibambino.paymentlist.repository.RemoteProvider
import ru.grandibambino.paymentlist.utils.BASE_URL
import ru.grandibambino.paymentlist.utils.CONNECT_TIMEOUT
import ru.grandibambino.paymentlist.utils.READ_TIMEOUT
import java.util.concurrent.TimeUnit

class RetrofitRemoteProvider() : RemoteProvider{

    private val interceptorsList = mutableListOf<Interceptor>(
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    )

    private val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .also { builder ->
                interceptorsList.forEach {
                    builder.addInterceptor(it)
                }
            }
            .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()

    override fun getApi(): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}