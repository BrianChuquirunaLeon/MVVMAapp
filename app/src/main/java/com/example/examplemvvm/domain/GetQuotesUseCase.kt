package com.example.examplemvvm.domain

import com.example.examplemvvm.data.QuoteRepository
import com.example.examplemvvm.data.model.QuoteModel

class GetQuotesUseCase {
    private val repository = QuoteRepository()

//    Esto hace lo mismo que lo que esta abajo, solamente es otra sintaxis
//    suspend operator fun invoke():List<QuoteModel>?{
//        return repository.getAllQuotes()
//    }
    suspend operator fun invoke():List<QuoteModel>? = repository.getAllQuotes()

//Para llamar a esta funcion invoke(), no esta necesario llamar al metodo luego de
// instanciar la clase, como siempre lo hacemos, como por ejemplo:
// getQuoteUseCase = GetQuoteUseCase()
// getQuoteUseCase.invoke( <parametros>)
//
// Sino que en lugar de eso solo haria :
// getQuoteUseCase = GetQuoteUseCase()
// getQuoteUseCase(<parametros>)

// Como vemos nos ahorramos tener que llamar al metodo invoke()

}