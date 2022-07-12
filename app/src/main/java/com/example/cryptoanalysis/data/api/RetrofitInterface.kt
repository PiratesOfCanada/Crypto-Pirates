package com.example.cryptoanalysis.data.api


import android.util.Log
import com.example.cryptoanalysis.data.model.ResponseCoins
import com.example.cryptoanalysis.data.model.ResponseNews
import com.example.cryptoanalysis.data.repo.DiscoverRepo
import com.example.cryptoanalysis.data.repo.FavouriteCoinRepository
import com.example.cryptoanalysis.data.repo.NewsRepo
import com.example.cryptoanalysis.utils.AccessToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.core.Observable
import okhttp3.HttpUrl
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import javax.inject.Qualifier
import javax.inject.Singleton

object Api {
    private var BASE_URL = ""
    fun seturl(url: String)  {
        BASE_URL = url
    }
    fun geturl(): String {
        return BASE_URL
    }
}

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
  //  var baseurl = "https://coinranking1.p.rapidapi.com/";

    @Singleton
    @Provides
    @DiscoverApi
    fun provideRetrofit(): Retrofit  {
//        Log.d("Retrofit  1: ", "${Api.geturl()}")
      return  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl("https://coinranking1.p.rapidapi.com")
            .build()
    }

    @Singleton
    @Provides
    @NewsApi
    fun provideRetrofit2(): Retrofit  {
//        Log.d("Retrofit  2: ", "${Api.geturl()}")
        return  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl("https://api.goperigon.com")
           .build()
    }
//    @Singleton
//    @Provides
//    fun providesOAuthInterceptor() = OAuthInterceptor().apply{
//         OAuthInterceptor("Bearer", AccessToken.accessToken)
//    }

    @Singleton
    @Provides
    fun client(): OkHttpClient  {
            return okhttp3.OkHttpClient.Builder()
        .addInterceptor(OAuthInterceptor("Bearer", "eyJhbGciOiJSUzI1NiIsImtpZCI6ImVhNWY2NDYxMjA4Y2ZmMGVlYzgwZDFkYmI1MjgyZTkyMDY0MjAyNWEiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vY3J5cHRvLXBpcmF0ZXMiLCJhdWQiOiJjcnlwdG8tcGlyYXRlcyIsImF1dGhfdGltZSI6MTY1NzU5MjUwMCwidXNlcl9pZCI6IndMTmhuRGRmcnBSWXVqZGZ0Z0QxalZoaXVJVTIiLCJzdWIiOiJ3TE5obkRkZnJwUll1amRmdGdEMWpWaGl1SVUyIiwiaWF0IjoxNjU3NjU4NjM5LCJleHAiOjE2NTc2NjIyMzksImVtYWlsIjoiamluYWxwYXJtYXI0NUBnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiZmlyZWJhc2UiOnsiaWRlbnRpdGllcyI6eyJlbWFpbCI6WyJqaW5hbHBhcm1hcjQ1QGdtYWlsLmNvbSJdfSwic2lnbl9pbl9wcm92aWRlciI6InBhc3N3b3JkIn19.IBUgkdmfD-tRFHuUBJfxTpS3o-Oxm64Kzg35sHMac0TySI53KaQENcI1vaxIbcmle-5jAeACFfUEmRUmArU_5QXKgApanpIaA4yicJCl8dl0l_5VfghsKZMP44pVIZBnF-MB4aTxV6mzZIxkDaZEvJ4Yi3vi70SuZmj15Kf5lnU3tW_EO6G0kqBKKr9izHw7CCVe6RJWMXSTR_gcj06GRdCPKNMpS9uZ90U0L4C4xQZsQLk9mkNGvLMpktmfmoNaRgyKlAuOuQ69v10VHNFoMlqavri9IKNh63tEHQ07lOMlDPtOyf_dpVQJWwoyQUPnU7pHyB75W0TS8wlOHI0m6w"))
            .build()
    }

    @Singleton
    @Provides
    @HomeApi
    fun provideRetrofitHome(OkHttpClient : OkHttpClient): Retrofit  {
//        Log.d("Retrofit  2: ", "${Api.geturl()}")
        return  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://us-central1-crypto-pirates.cloudfunctions.net/api/")
            .client(OkHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(@DiscoverApi retrofit: Retrofit): RetrofitInterface = retrofit.create(RetrofitInterface::class.java)
//
    @Singleton
    @Provides
    fun providesRepository(apiService: RetrofitInterface) = DiscoverRepo(apiService)



    @Singleton
    @Provides
    fun provideApiService2(@NewsApi retrofit: Retrofit): NewsInterface = retrofit.create(NewsInterface::class.java)

    @Singleton
    @Provides
    fun providesRepository2(apiService: NewsInterface) = NewsRepo(apiService)

    @Singleton
    @Provides
    fun provideHomeApiService(@HomeApi retrofit: Retrofit): FavCoinRetroApiInterface = retrofit.create(FavCoinRetroApiInterface::class.java)

    @Singleton
    @Provides
    fun providesHomeRepository(apiService: FavCoinRetroApiInterface) = FavouriteCoinRepository(apiService)

   // retrofit.create(FavCoinRetroApiInterface::class.java)

}


interface RetrofitInterface {
    //var baseurl = "https://coinranking1.p.rapidapi.com/";
    @GET("/coins?referenceCurrencyUuid=yhjMzLPhuIDl&timePeriod=24h&orderBy=marketCap&orderDirection=desc")
    //&limit={limit}&offset={offset}
    @Headers("X-Rapidapi-Key: 870ba89cf6msh2b32fb922a04c36p1ab840jsn47d713b4fe85")
    fun getAllCoins(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Observable<ResponseCoins>
}

interface NewsInterface {
    //https://api.goperigon.com/v1/all?source=cnn.com&sortBy=date&apiKey=ae2b2187-86cb-4da4-bf48-e413951b0f42&topic=Cryptocurrency&from=2022-01-01T00:00:00
    // var baseurl = "https://crypto-news15.p.rapidapi.com";
    @GET("/v1/all?source=cnn.com&sortBy=date&apiKey=ae2b2187-86cb-4da4-bf48-e413951b0f42&topic=Cryptocurrency")
  //  @Headers("X-Rapidapi-Key: 16b1461be2msh515880f0029f0c3p1d5a8ejsne5376e712ac3")
    fun getAllNews() : Observable<ResponseNews>

}

// creating 2 qualifiers
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DiscoverApi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NewsApi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HomeApi