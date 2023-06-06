package com.example.examplemvvm.data

import com.example.examplemvvm.data.database.dao.QuoteDao
import com.example.examplemvvm.data.database.entities.QuoteEntity
import com.example.examplemvvm.data.model.QuoteModel
import com.example.examplemvvm.data.network.QuoteService
import com.example.examplemvvm.domain.model.Quote
import com.example.examplemvvm.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api :QuoteService,
    private val quoteDao: QuoteDao
//    private val quoteProvider: QuoteProvider //esto funcionaba como una especie de cache local donde se guardaban los datos en memoria despues de traer los datos de la API, pero ahora que ya hemos implementado una BD con ROOM ya no lo necesitamos
){
    //inyectamos esta clase
//    private val api =QuoteService()

//    A partir de ahora el modelo de datos que usara la UI y el domain, sera diferente al de la capa de data
//    suspend fun getAllQuotesFromApi():List<QuoteModel>{
    suspend fun getAllQuotesFromApi():List<Quote>{//Despues de invalidar "quoteProvider.quotes=response" este metodo recupera las citas de internet y se las devuelve al dominio
        val response:List<QuoteModel> = api.getQuotes()
//        quoteProvider.quotes=response //Aca se guardaba en cache los datos traidos de la API
        return response.map { quoteModel ->  quoteModel.toDomain() }
    }
    suspend fun getAllQuotesFromDatabase():List<Quote>{
        val response:List<QuoteEntity> = quoteDao.getAllQuotes()
        return response.map {quoteDao -> quoteDao.toDomain() }
    }
    suspend fun inserQuotes(quotes:List<QuoteEntity>){
        quoteDao.insertAll(quotes)
    }

    suspend fun clearQuotes(){
        quoteDao.deleteAllQuotes()
    }
}