package ziemapp.johnzieman.advancedrecyclerview.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import ziemapp.johnzieman.advancedrecyclerview.Navigator
import ziemapp.johnzieman.advancedrecyclerview.R
import ziemapp.johnzieman.advancedrecyclerview.databinding.FragmentUserDetailsBinding
import ziemapp.johnzieman.advancedrecyclerview.databinding.FragmentUsersListBinding
import ziemapp.johnzieman.advancedrecyclerview.factory

class UserDetailsFragment : Fragment() {

    private var navigator: Navigator? = null
    private lateinit var binding: FragmentUserDetailsBinding
    private val userDetailsViewModel: UserDetailsViewModel by viewModels { factory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { userDetailsViewModel.loadUsers(it.getLong(ARG_USER_ID)) }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserDetailsBinding.inflate(layoutInflater, container, false)

        userDetailsViewModel.userDetails.observe(viewLifecycleOwner, Observer {
            binding.userNameTextView.text = it.user.name
            if (it.user.photo.isNotEmpty()) {
                Glide.with(this).load(it.user.photo).circleCrop().into(binding.photoImageView)
            }
            binding.userDetailsTextView.text = it.value
        })



        binding.deleteButton.setOnClickListener {
            userDetailsViewModel.deleteUser()
            navigator?.toast(requireActivity().getString(R.string.deleted))
        }


        return binding.root
    }


    companion object {
        private const val ARG_USER_ID = "ARG_USER_ID"

        fun newInstance(userId: Long): UserDetailsFragment {
            val args = Bundle()

            val fragment = UserDetailsFragment()
            fragment.arguments = bundleOf(ARG_USER_ID to userId)
            return fragment
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        navigator = null
    }
}