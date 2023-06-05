package com.example.examplemvvm.di

import com.example.examplemvvm.data.network.QuoteApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//Le decimos a DaggerHilt que esto es un modulo, porque lo modulos son los que proveen
// dependencias. Cuando creamos un modulo le podemos decir el alcance que querramos
// que tenga.
@Module
//@InstallIn nos dice el alcance que tendra nuestro modulo, es decir cuanto tiempo va a
// durar la instancia que hemos creado, por ejemplo podria durar solo mientras el activity
// que la llamo este presente, o mientras el viewmodel este presente, etc.
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton//usa el patron de diseño singleton, para crear una unica instancia de esta clase
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://drawsomething-59328-default-rtdb.europe-west1.firebasedatabase.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideQuoteApiClient(retrofit: Retrofit): QuoteApiClient {
        return retrofit.create(QuoteApiClient::class.java)
    }
}

//Clase de Android          Componente generado                 Alcance
// Application              ApplicationComponent               @Singleton
// View Model               ActivityRetainedComponent          @ActivityRetainedScope
// Activity                 ActivityComponent                  @ActivityScoped
// Fragment                 FragmentComponent                  @FragmentScoped
// View                     ViewComponent                      @ViewScoped
// View anotada con         ViewWithFragmentComponent          @ViewScoped
// @WithFragmentComponent
// Service                  ServiceComponent                    @ServiceScoped.
