package ziemapp.johnzieman.advancedrecyclerview.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ziemapp.johnzieman.advancedrecyclerview.model.User
import ziemapp.johnzieman.advancedrecyclerview.model.UserDetails
import ziemapp.johnzieman.advancedrecyclerview.model.UserService
import java.lang.Exception

class UserDetailsViewModel(
    private val userService: UserService
) : ViewModel() {

    private val _userDetails = MutableLiveData<UserDetails>()
    val userDetails = _userDetails

    fun loadUsers(userId: Long) {
        if (userDetails.value != null) return
        try {
            _userDetails.value = userService.getById(userId)
        } catch (e: Exception) {

        }
    }

    fun deleteUser() {
        val userDetails = this.userDetails.value ?: return
        userService.deleteUsers(userDetails.user)
    }
}