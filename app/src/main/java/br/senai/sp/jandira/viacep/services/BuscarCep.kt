package br.senai.sp.jandira.viacep.services

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.senai.sp.jandira.viacep.models.Cep
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import kotlin.concurrent.thread
import kotlin.math.log
import kotlin.math.log2

fun buscarCep(cep: String, onComplete: (String) -> Unit) {
    val call = RetrofitFactory()
        .retrofitService().getCep(cep)

    call.enqueue(object : Callback<Cep> {
        override fun onResponse(call: Call<Cep>, response: Response<Cep>) {
            if(response.body()!!.cep.isNullOrEmpty()) {
                    onComplete.invoke("CEP não encontrado")
                return
            } else {
                var stringResult = "${response.body()!!.logradouro}, ${response.body()!!.bairro}, ${response.body()!!.cidade} - ${response.body()!!.cep} - ${response.body()!!.estado}"

                onComplete.invoke(stringResult)
            }

        }

        override fun onFailure(call: Call<Cep>, t: Throwable) {
            TODO("Not yet implemented")
        }
    })
}