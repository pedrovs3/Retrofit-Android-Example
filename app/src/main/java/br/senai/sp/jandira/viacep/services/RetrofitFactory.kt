package br.senai.sp.jandira.viacep.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    val baseURL = "https://viacep.com.br/ws/"

    val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun retrofitService() : CepRetrofitService {
        return retrofitFactory.create(CepRetrofitService::class.java)
    }
}