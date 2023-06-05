package com.example.examplemvvm.domain

import com.example.examplemvvm.data.QuoteRepository
import com.example.examplemvvm.data.model.QuoteModel
import javax.inject.Inject

//Las clases que NO son viewmodel ni activitys, simplemente se prepara la inyeccion de dependencias poniendo
// @Inject constructor().
class GetQuotesUseCase @Inject constructor(
    private val repository : QuoteRepository //ya que lo estamos inyectando no instanciamos la clase, solo le decimos de que tipo es
) {
    //ya no instanciamos la clase QuoteRepository() aca, sino que lo pasaremos por el constructor con DaggerHilt
//    private val repository = QuoteRepository()

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