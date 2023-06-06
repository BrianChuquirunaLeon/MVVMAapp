package com.example.examplemvvm.domain.model

import com.example.examplemvvm.data.database.entities.QuoteEntity
import com.example.examplemvvm.data.model.QuoteModel

//Este sera el modelo de datos final con el que la UI y el domain van a trabajar (solo con este)da
data class Quote (val quote:String, val author:String)

//Este es codigo significa lo mismo que lo que esta abajo, solo lo dejo para mirarlo y tenerlo en cuenta
//fun QuoteModel.toDomain():Quote{
//    return Quote(quote, author)
//}
fun QuoteModel.toDomain() =Quote(quote, author)
//Este es codigo significa lo mismo que lo que esta abajo, solo lo dejo para mirarlo y tenerlo en cuenta
fun QuoteEntity.toDomain():Quote{
    return Quote(quote, author)
}
//fun QuoteEntity.toDomain() =Quote(quote, author)
