package com.example.examplemvvm.data.network

import com.example.examplemvvm.core.RetrofitHelper
import com.example.examplemvvm.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

//Deberiamos tener un repositorio, el cual cuando pida que se le de la cita, ese repositorio
// tendra que decidir de donde sacar la cita, por ejemplo podria tener 2 opciones para
// obtener la cita: BD o de internet. Y si lo decide obtener tanto de BD o INTERNET, siempre
// llamara a la clase QuoteService.
class QuoteService {
    private val retrofit = RetrofitHelper.getRetrofit()
    suspend fun getQuotes():List<QuoteModel>{
        return withContext(Dispatchers.IO){//Ejecutamos la llamada a la API en el hilo secundario "IO"
            val response: Response<List<QuoteModel>> = retrofit.create(QuoteApiClient::class.java).getAllQuotes()
            response.body() ?: emptyList()
        }
    }
}