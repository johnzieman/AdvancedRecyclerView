package ziemapp.johnzieman.advancedrecyclerview

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ziemapp.johnzieman.advancedrecyclerview.screens.UserDetailsViewModel
import java.lang.IllegalStateException

class ViewModelFactory(
    private val app: App
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            UsersListViewModel::class.java -> {
                UsersListViewModel(app.userService)
            }

            UserDetailsViewModel::class.java -> {
                UserDetailsViewModel(app.userService)
            }
            else -> {
                throw IllegalStateException("Not viewmodel class")
            }
        }

        return viewModel as T
    }
}

fun Fragment.factory() = ViewModelFactory(requireContext().applicationContext as App)