package ziemapp.johnzieman.advancedrecyclerview.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ziemapp.johnzieman.advancedrecyclerview.*
import ziemapp.johnzieman.advancedrecyclerview.databinding.FragmentUserDetailsBinding
import ziemapp.johnzieman.advancedrecyclerview.databinding.FragmentUsersListBinding
import ziemapp.johnzieman.advancedrecyclerview.model.User

class UsersListFragment : Fragment() {
    private lateinit var binding: FragmentUsersListBinding
    private lateinit var adapter: UsersAdapter
    private val viewModel: UsersListViewModel by viewModels() { factory() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUsersListBinding.inflate(inflater, container, false)
        adapter = UsersAdapter(object : UserActionListener {
            override fun onUserMove(user: User, moveBy: Int) {
                viewModel.moveUser(user, moveBy)
            }

            override fun onUserDelete(user: User) {
                viewModel.deleteUser(user)
            }

            override fun onUserDetails(user: User) {

            }
        })

        viewModel.users.observe(viewLifecycleOwner, Observer {
            adapter.users = it
        })

        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        return binding.root
    }

}