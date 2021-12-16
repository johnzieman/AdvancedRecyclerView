package ziemapp.johnzieman.advancedrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ziemapp.johnzieman.advancedrecyclerview.databinding.ActivityMainBinding
import ziemapp.johnzieman.advancedrecyclerview.model.User
import ziemapp.johnzieman.advancedrecyclerview.model.UserService
import ziemapp.johnzieman.advancedrecyclerview.model.UsersListener

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UsersAdapter

    private val userService: UserService
        get() = (applicationContext as App).userService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UsersAdapter(object : UserActionListener {
            override fun onUserMove(user: User, moveBy: Int) {
                userService.moveUser(user, moveBy)
            }

            override fun onUserDelete(user: User) {
                userService.deleteUsers(user)
            }

            override fun onUserDetails(user: User) {
                Toast.makeText(this@MainActivity, user.name, Toast.LENGTH_SHORT).show()
            }
        })
        val layoutManager = LinearLayoutManager(this)
        binding.recycler.layoutManager = layoutManager
        binding.recycler.adapter = adapter
        userService.addListener(usersListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        userService.removeListener { usersListener }
    }

    private val usersListener: UsersListener = {
        adapter.users = it
    }
}