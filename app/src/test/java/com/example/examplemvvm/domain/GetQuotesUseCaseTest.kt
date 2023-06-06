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

internal class GetQuotesUseCaseTest{

    //@RelaxedMockK -> si no definimos una respuesta de la clase que estamos creando entonces la va a generar automaticamente por si solo
    //@Mock -> em cambio si Mock accede a algo que no hemos preparado va a fallar el test
    @RelaxedMockK //al usar esta etiqueta estamos dejando que @RelaxedMockK se encarge de instanciar la clase QuoteRepository
    private lateinit var repository: QuoteRepository//la clase GetQuotesUseCase recibe un quoteRepository en su constructor, por lo cual necesitamos mockear esta clase

    private lateinit var getQuotesUseCase: GetQuotesUseCase//esta es la clase del caso de uso que vamos a probar, esta clase si necesitamos instanciarlo nosotros mismo como solemos hacer siempre cuando programamos

    //Todo lo que este en la etiqueta @Before se ejecutara antes de los Test, aca creamos las instancias que vamos a usar en los test
    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getQuotesUseCase = GetQuotesUseCase(repository)
    }

    @Test
    fun whenTheApiDoesntReturnAnythingThenGetValuesFromDatabase() = runBlocking{
        //Given
        coEvery { repository.getAllQuotesFromApi() } returns emptyList() //returns es con "s" ya que te devuelve un Mock. "coEvery" -> se usa para Coroutines, en caso contrario solo usar "Every"
        //When
        getQuotesUseCase()
        //Then
        coVerify(exactly = 1) { repository.getAllQuotesFromDatabase() }
    }

    @Test
    fun `when the api return something then get values from api`() = runBlocking {
//----------------------------------------------------------------Given---------------------------------------------------------
        val myList = listOf(Quote("Déjame un comentario", "AristiDevs"),Quote("Pronto llegara el dia de mi suerte", "Brian Chuquiruna") )
        coEvery { repository.getAllQuotesFromApi() } returns myList

//----------------------------------------------------------------When---------------------------------------------------------
        val response = getQuotesUseCase()

//----------------------------------------------------------------Then---------------------------------------------------------

        //Verify -> verifica si una funncion se la llamado ( coVerify: hace lo mismo pero dentro de una corrutina )
        //exactly -> es un parametro que sirve para indicar el numero de veces que se debe llamar la funcion
        coVerify(exactly = 1) { repository.clearQuotes() }
        coVerify(exactly = 1) { repository.inserQuotes(any()) }
        coVerify(exactly = 0) { repository.getAllQuotesFromDatabase() }
        //assert comprueba que lo que esta dentro de los parentesis es "True"
        assert(response == myList)
    }

}