package com.example.examplemvvm.domain

import com.example.examplemvvm.data.QuoteRepository
import com.example.examplemvvm.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

//import org.junit.jupiter.api.Assertions.*

internal class GetRandomQuoteUseCaseTest{
    //@RelaxedMockK -> si no definimos una respuesta de la clase que estamos creando entonces la va a generar automaticamente por si solo
    //@Mock -> em cambio si Mock accede a algo que no hemos preparado va a fallar el test
    @RelaxedMockK //al usar esta etiqueta estamos dejando que @RelaxedMockK se encarge de instanciar la clase QuoteRepository
    private lateinit var repository: QuoteRepository//la clase GetQuotesUseCase recibe un quoteRepository en su constructor, por lo cual necesitamos mockear esta clase

    private lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase//esta es la clase del caso de uso que vamos a probar, esta clase si necesitamos instanciarlo nosotros mismo como solemos hacer siempre cuando programamos

    //Todo lo que este en la etiqueta @Before se ejecutara antes de los Test, aca creamos las instancias que vamos a usar en los test
    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getRandomQuoteUseCase = GetRandomQuoteUseCase(repository)
    }

    @Test
    fun `when database is empty then return null`() = runBlocking{
        //Given
        coEvery { repository.getAllQuotesFromDatabase() } returns emptyList() //returns es con "s" ya que te devuelve un Mock. "coEvery" -> se usa para Coroutines, en caso contrario solo usar "Every"
        //When
        val response = getRandomQuoteUseCase()
        //Then
        assert(response==null)
    }

    @Test
    fun `when database is not empty then return quote`() = runBlocking {
//----------------------------------------------------------------Given---------------------------------------------------------
        val myList = listOf(Quote("Pronto llegara el dia de mi suerte", "Brian Chuquiruna") )
        coEvery { repository.getAllQuotesFromDatabase() } returns myList
//----------------------------------------------------------------When---------------------------------------------------------
        val response = getRandomQuoteUseCase()
//----------------------------------------------------------------Then---------------------------------------------------------
        //assert comprueba que lo que esta dentro de los parentesis es "True"
        assert(response == myList.first())
    }

}