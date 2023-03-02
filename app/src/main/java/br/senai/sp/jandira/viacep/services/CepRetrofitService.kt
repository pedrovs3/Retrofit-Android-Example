package br.senai.sp.jandira.viacep.services

import br.senai.sp.jandira.viacep.models.Cep
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CepRetrofitService {

    // https://viacep.com.br/ws/06150130/json/

    @GET("{cep}/json/")
    fun getCep(@Path("cep") cep: String): Call<Cep>


    @GET("{uf}/{cidade}/{logradouro}/json/")
    fun getByAddress(
        @Path("uf") uf: String,
        @Path("cidade") cidade: String,
        @Path("logradouro") logradouro: String
    ): Call<List<Cep>>
}