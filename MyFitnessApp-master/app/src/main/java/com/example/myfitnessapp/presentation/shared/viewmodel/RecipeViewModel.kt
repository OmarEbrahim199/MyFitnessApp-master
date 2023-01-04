package  com.example.myfitnessapp.presentation.shared.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.Assisted
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfitnessapp.domain.models.Recipe
import com.example.myfitnessapp.util.RecipeEvent
import com.example.myfitnessapp.domain.repository.RecipeRepository
import com.example.myfitnessapp.util.STATE_KEY_RECIPE_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named

private const val TAG = "RecipeViewModel"



@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val repository: RecipeRepository,
    @Named("auth_token") private val token: String,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val recipe: MutableState<Recipe?> = mutableStateOf(null)
    val loading: MutableState<Boolean> = mutableStateOf(false)


    init {
        //restore from process death
        savedStateHandle.get<Int>(STATE_KEY_RECIPE_ID)?.let {
            onTriggerEvent(RecipeEvent.GetRecipeEvent(it))

        }

    }

    fun onTriggerEvent(recipeEvent: RecipeEvent) {
        viewModelScope.launch {
            try {
                when (recipeEvent) {
                    is RecipeEvent.GetRecipeEvent -> {
                        if (recipe.value == null) {
                            getRecipe(recipeEvent.id)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, "onTriggerEvent: Error: ${e.cause}")
            }
        }
    }

    suspend fun getRecipe(recipeId: Int) {
        loading.value = true
       delay(100)
        val result = repository.getRecipe(
            token,
            recipeId
        )
        recipe.value = result
        savedStateHandle.set(STATE_KEY_RECIPE_ID, recipeId)
        loading.value = false

    }

}