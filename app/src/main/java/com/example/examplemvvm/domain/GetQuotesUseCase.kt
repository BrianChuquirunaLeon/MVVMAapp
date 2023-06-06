package com.example.examplemvvm.domain

import com.example.examplemvvm.data.QuoteRepository
import com.example.examplemvvm.data.database.entities.toDatabase
import com.example.examplemvvm.data.model.QuoteModel
import com.example.examplemvvm.domain.model.Quote
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
//    suspend operator fun invoke():List<QuoteModel>? = repository.getAllQuotes()

    //Esta funcion se llama una sola ves para pedir la lsita de datos al servidor
    suspend operator fun invoke():List<Quote>{
        val quotes = repository.getAllQuotesFromApi()

        return if(quotes.isNotEmpty()){
            repository.clearQuotes()//Debemos borrar la BD local cada que traigamos los datos de internet, para tener en nuestra BD local solamente los datos nuevoszz
            repository.inserQuotes(quotes.map {it.toDatabase()} )
            quotes//se devuelve esto en caso de que se verdadero, siempre se devulve la ultima linea de codigo
        }else{
            repository.getAllQuotesFromDatabase()//se devuelve esto en caso de que sea falso
        }
    }
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