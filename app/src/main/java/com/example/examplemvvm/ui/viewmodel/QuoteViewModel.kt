package com.example.examplemvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examplemvvm.data.model.QuoteModel
import com.example.examplemvvm.domain.GetQuotesUseCase
import com.example.examplemvvm.domain.GetRandomQuoteUseCase
import com.example.examplemvvm.domain.model.Quote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//En el caso de la clas clases viewmodel necesitamos poner la etiqueta @HiltViewModel arriba de la clase,
// y ademas poner la etiqueta @Inject constructor.
@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase,
    private val getRandomQuoteUseCase : GetRandomQuoteUseCase
): ViewModel(){
    val quoteModel = MutableLiveData<Quote>()
    val isLoading = MutableLiveData<Boolean>()

    //Vamos a injectar estas 2 Clases, por lo cual ya no las instanciaremos de esta forma
//    var getQuotesUseCase = GetQuotesUseCase()//instanciamos la clase GetQuotesUseCase
//    var getRandomQuoteUseCase = GetRandomQuoteUseCase()//instanciamos la clase GetRandomQuoteUseCase

    // Hacemos la llamada a nuestro caso de uso para que nos devuelva todas las Quotes y almacene
    // en memoria esas Quotes que recibe.
    fun onCreate() {
        // MVVM nos permite crear una corrutina que se controla automaticamente, a diferencia de MVP donde si la
        // Activity muere debemos nosotros mismos parar la corutina para evitar que la aplicacion crashee,en MVVM
        // esto se gestiona automaticamente por la libreria, es por eso que ejecutarmos la Coroutine dentro del viewModelScope.
        viewModelScope.launch {
            isLoading.postValue(true)//mostramos la barra de carga mientras hacemos la llamada al servidor
            val result:List<Quote>? = getQuotesUseCase()// ejecutamos el metodo invoke() de la clase GetQuotesUseCase
            if (!result.isNullOrEmpty()){
                quoteModel.postValue(result[0])
                isLoading.postValue(false)//ocultamos la barra de carga despues de la llamada al servidor
            }
        }
    }

    fun randomQuote(){
//        val currenQuote = QuoteProvider.random()
//        quoteModel.postValue(currenQuote)
        viewModelScope.launch {
            isLoading.postValue(true)//mostramos la barra de carga mientras hacemos la llamada al servidor
            val quote:Quote? = getRandomQuoteUseCase()// ejecutamos el metodo invoke() de la clase GetRandomQuoteUseCase
            if(quote!=null){
                quoteModel.postValue(quote)
            }
            isLoading.postValue(false)//ocultamos la barra de carga despues de la llamada al servidor
        }
    }
}