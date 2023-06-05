package com.example.examplemvvm.data.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuoteProvider @Inject constructor(){
    //Injectamos el quotes, por lo cual ya no lo necesitamos en un companion object
//    companion object{
//        //aca estaba toda la lista de citas, las cuales en un princio para la primer prueba
//        // fueron escritas aqui, para simular que este Provider le devolvia los datos
//        var quotes:List<QuoteModel> = emptyList()
//    }
    var quotes:List<QuoteModel> = emptyList()
}