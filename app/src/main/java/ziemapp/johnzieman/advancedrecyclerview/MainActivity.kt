package ziemapp.johnzieman.advancedrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ziemapp.johnzieman.advancedrecyclerview.databinding.ActivityMainBinding
import ziemapp.johnzieman.advancedrecyclerview.model.User
import ziemapp.johnzieman.advancedrecyclerview.model.UserService
import ziemapp.johnzieman.advancedrecyclerview.model.UsersListener
import ziemapp.johnzieman.advancedrecyclerview.screens.UserDetailsFragment
import ziemapp.johnzieman.advancedrecyclerview.screens.UsersListFragment

class MainActivity : AppCompatActivity(), Navigator {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        savedInstanceState?.let {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, UsersListFragment())
                .commit()
        }
    }

    override fun showDetails(user: User) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, UserDetailsFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun goBack() {
        onBackPressed()
    }

    override fun toast(messageRes: String) {
        Toast.makeText(this, messageRes, Toast.LENGTH_SHORT).show()
    }
}