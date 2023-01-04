package  com.example.myfitnessapp.presentation.shared.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.Assisted
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfitnessapp.domain.models.Recipe
import com.example.myfitnessapp.util.RecipeListEvent
import com.example.myfitnessapp.util.RecipeListEvent.*
import com.example.myfitnessapp.domain.repository.RecipeRepository
import com.example.myfitnessapp.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

private const val TAG = "RecipeListViewModel"

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val repository: RecipeRepository,
    @Named("auth_token") private val token: String,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val recipes: MutableState<List<Recipe>> = mutableStateOf(listOf())
    val query = mutableStateOf("")
    val loading: MutableState<Boolean> = mutableStateOf(false)
    val page = mutableStateOf(1)
    private var recipeListScrollPosition = 0
    private var _categoryScrollPosition: Float = 0f

    init {
        restoreFromSavedState()
        if (recipeListScrollPosition != 0 && recipes.value.isNotEmpty() && query.value.isNotBlank()) {
            onTriggerEvent(RestoreEvent)
        } else onTriggerEvent(SearchEvent)
        Log.e("resultRecipeViewModel",recipes.toString())

    }


    fun onTriggerEvent(event: RecipeListEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is SearchEvent -> {
                        search()
                    }
                    is NextPageEvent -> {
                        nextPage()
                    }
                    is RestoreEvent -> {
                        restoreState()
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "onTriggerEvent: Error occured: ${e.cause}")
            }
        }
    }

    fun onQueryChanged(q: String) {
        setQuery(q)
    }

    private suspend fun search() {
        loading.value = true
        resetSearchState()
        delay(2000)
        val result = repository.search(
            token,
            1,
            query.value
        )
        recipes.value = result
        loading.value = false
    }

    private suspend fun nextPage() {
        if ((recipeListScrollPosition + 1) >= (page.value * PAGE_SIZE)) {
            loading.value = true
            incrementPage()

            if (page.value > 1) {
                val result = repository.search(
                    token,
                    page.value,
                    query.value
                )
                appendRecipes(result)
                loading.value = false
            }
        }

    }

    private fun incrementPage() {
        setPage(page.value + 1)
    }

    private fun appendRecipes(newList: List<Recipe>) {
        val current = ArrayList(recipes.value)
        current.addAll(newList)
        recipes.value = current
    }

    fun onRecipeScrollPositionChanged(position: Int) {
        setRecipeListScrollPosition(position)
    }



    fun onCategoryScrollPositionChanged(position: Float) {
        _categoryScrollPosition = position
    }

    fun categoryScrollPosition() = _categoryScrollPosition

    private fun resetSearchState() {
        recipes.value = listOf()
        page.value = 1
        onRecipeScrollPositionChanged(0)

    }



    private fun setRecipeListScrollPosition(position: Int) {
        recipeListScrollPosition = position
        savedStateHandle.set(STATE_KEY_RECIPE_LIST_POSITION, position)
    }

    private fun setPage(p: Int) {
        page.value = p
        savedStateHandle.set(STATE_KEY_RECIPE_LIST_PAGE, p)
    }



    private fun setQuery(q: String) {
        query.value = q
        savedStateHandle.set(STATE_KEY_RECIPE_LIST_QUERY, q)
    }

    private fun restoreFromSavedState() {
        with(savedStateHandle) {
            get<Int>(STATE_KEY_RECIPE_LIST_PAGE)?.let { setPage(it) }
            get<String>(STATE_KEY_RECIPE_LIST_QUERY)?.let { setQuery(it) }
            get<Int>(STATE_KEY_RECIPE_LIST_POSITION)?.let { setRecipeListScrollPosition(it) }
        }
    }

    private suspend fun restoreState() {
        loading.value = true
        val results = mutableListOf<Recipe>()
        for (p in 1 until page.value) {
            val result = repository.search(
                token,
                p,
                query.value
            )
            results.addAll(result)
            if (p == page.value) {
                recipes.value = results
                loading.value = false
            }
        }
    }
}