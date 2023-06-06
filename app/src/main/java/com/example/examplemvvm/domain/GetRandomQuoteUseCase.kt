package com.example.examplemvvm.domain

import com.example.examplemvvm.data.QuoteRepository
import com.example.examplemvvm.data.model.QuoteModel
import com.example.examplemvvm.domain.model.Quote
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(
    private val repository: QuoteRepository
//    private val quoteProvider:QuoteProvider //Esta clase fue eliminada, ya que solo almacenaba los datos traidos por la API de internet en memoria, cuando este trabajo ahora lo hace de mejor manera la BD local
){
//    private val repository = QuoteRepository()

//    operator fun invoke():QuoteModel?{
    suspend operator fun invoke(): Quote?{
//        val quotes = quoteProvider.quotes
        val quotes = repository.getAllQuotesFromDatabase()//Esta funcion llama a la BD local, y toda llamada a la API o la BD se debe hacer desde una Coroutine, por lo tanto debemos hacer la funcion del tipo "suspend"
        if(!quotes.isNullOrEmpty()){
            val randomNumber=(0..quotes.size-1).random()
            return quotes[randomNumber]
        }else{
            return null
        }
    }
}