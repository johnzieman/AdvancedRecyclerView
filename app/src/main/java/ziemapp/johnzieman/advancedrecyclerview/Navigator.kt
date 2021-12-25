package ziemapp.johnzieman.advancedrecyclerview

import ziemapp.johnzieman.advancedrecyclerview.model.User

interface Navigator {
    fun showDetails(user: User)
    fun goBack()
    fun toast(messageRes: String)
}