package com.jet.mvvmsample.di

import com.jet.mvvmsample.model.data.FoodMenuApi
import com.jet.mvvmsample.model.data.FoodMenuApi.Companion.API_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class FoodMenuApiProvider {
    @Provides
    @Singleton
    fun provideAuthInterceptorOkHttpClient() : OkHttpClient {
        return OkHttpClient
            .Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ) : Retrofit {
        return Retrofit
            .Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(API_URL)
            .build()
    }


    @Provides
    @Singleton
    fun provideFoodMenuApiService(
        retrofit: Retrofit
    ): FoodMenuApi.Service{
        return retrofit.create(FoodMenuApi.Service::class.java)
    }
}