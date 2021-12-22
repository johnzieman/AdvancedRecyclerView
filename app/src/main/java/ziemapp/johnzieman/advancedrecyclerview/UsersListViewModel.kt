package ziemapp.johnzieman.advancedrecyclerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ziemapp.johnzieman.advancedrecyclerview.model.User
import ziemapp.johnzieman.advancedrecyclerview.model.UserService
import ziemapp.johnzieman.advancedrecyclerview.model.UsersListener

class UsersListViewModel(
    private val userService: UserService
) : ViewModel() {
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    private val listener: UsersListener = {
        _users.value = it
    }

    init {
        loadUsers()
    }


    fun loadUsers() {
        userService.addListener(listener)
    }

    fun moveUser(user: User, moveBy: Int) {
        userService.moveUser(user, moveBy)
    }

    fun deleteUser(user: User) {
        userService.deleteUsers(user)
    }




    override fun onCleared() {
        super.onCleared()
        userService.removeListener(listener)
    }
}