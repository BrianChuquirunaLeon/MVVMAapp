package com.example.examplemvvm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.examplemvvm.data.database.dao.QuoteDao
import com.example.examplemvvm.data.database.entities.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class QuoteDatabase :RoomDatabase(){
    //Por cada Dao deberemos crear una funcion abstracta
    abstract fun getQuoteDao():QuoteDao
}