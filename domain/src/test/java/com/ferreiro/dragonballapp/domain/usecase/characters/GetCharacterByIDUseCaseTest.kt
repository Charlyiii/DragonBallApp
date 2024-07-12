package com.ferreiro.dragonballapp.domain.usecase.characters

import com.ferreiro.dragonballapp.domain.model.CharacterModel
import com.ferreiro.dragonballapp.domain.repository.CharactersRepository
import com.ferreiro.dragonballapp.domain.utils.Either
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class GetCharacterByIDUseCaseTest {

    private val charactersRepository: CharactersRepository = mockk()

    private lateinit var getCharacterByIDUseCase: GetCharacterByIDUseCase

    @BeforeEach
    fun setup() {

        getCharacterByIDUseCase = GetCharacterByIDUseCase(charactersRepository)
    }

    @Test
    fun `invoke should return a character when repository succeeds`() = runBlocking {
        //Given
        val characterMock = mockk<CharacterModel>()
        coEvery { characterMock.id } returns 1
        coEvery { charactersRepository.getCharacterById(1) } returns flowOf(Either.Success(characterMock))
        //When
        characterMock.id
        val response = getCharacterByIDUseCase(characterMock.id)

        //Then
        coVerify(exactly = 1) {charactersRepository.getCharacterById(1)}
        response.collect{result ->
            assertEquals( Either.Success(characterMock), result )
        }
    }
}
