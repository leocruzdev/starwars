import app.cash.turbine.test
import com.dacruz.home.domain.usecase.GetCategoriesHome
import com.dacruz.home.presentation.HomeState
import com.dacruz.home.presentation.HomeViewModel
import com.dacruz.home.presentation.mapper.CategoryMapper
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import com.dacruz.home.domain.model.Category as DomainCategory
import com.dacruz.home.presentation.model.Category as ViewCategory

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val usecase: GetCategoriesHome = mockk()
    private val mapper: CategoryMapper = mockk()
    private val viewModel = HomeViewModel(usecase, mapper)

    @Test
    fun `loadCategories should return HomeState_Success when categories are available`() = runTest {
        // Given
        val domainCategory = DomainCategory(starships = "", species = "", planets = "", people = "", films = "", vehicles = "")
        val viewCategory = ViewCategory(categories = listOf(
            Pair("Starships", ""),
            Pair("Species",""),
            Pair("Planets",""),
            Pair("People",""),
            Pair("Films",""),
            Pair("Vehicles","")
        ))

        // When
        coEvery { usecase() } returns flowOf(domainCategory)
        every { mapper.toView(domainCategory) } returns viewCategory

        // Then
        viewModel.loadCategories().test {
            assertEquals(HomeState.Success(viewCategory), awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `loadCategories should return HomeState_Empty when no categories are available`() = runTest {
        // Given
        val domainCategory = DomainCategory(starships = "", species = "", planets = "", people = "", films = "", vehicles = "")
        val viewCategory = ViewCategory(categories = emptyList())

        // When
        coEvery { usecase() } returns flowOf(domainCategory)
        every { mapper.toView(domainCategory) } returns viewCategory

        // Then
        viewModel.loadCategories().test {
            assertEquals(HomeState.Empty, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `loadCategories should return HomeState_Error when an error occurs`() = runTest {
        // Given
        val errorMessage = "Error fetching categories"
        val error = Exception(errorMessage)

        // When
        coEvery { usecase() } returns flow { throw error }

        // Then
        viewModel.loadCategories().test {
            assertEquals(HomeState.Error(error), awaitItem())
            awaitComplete()
        }
    }
}