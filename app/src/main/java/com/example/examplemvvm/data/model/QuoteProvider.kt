package com.example.examplemvvm.data.model

class QuoteProvider {
    companion object{
        //aca estaba toda la lista de citas, las cuales en un princio para la primer prueba
        // fueron escritas aqui, para simular que este Provider le devolvia los datos
        var quotes:List<QuoteModel> = emptyList()
    }
}