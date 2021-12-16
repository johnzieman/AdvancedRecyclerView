package ziemapp.johnzieman.advancedrecyclerview

import android.app.Application
import ziemapp.johnzieman.advancedrecyclerview.model.UserService

class App: Application() {
    val userService = UserService()
}