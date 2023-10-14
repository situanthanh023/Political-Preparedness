package com.example.android.politicalpreparedness.launch

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android.politicalpreparedness.databinding.FragmentLaunchBinding

class LaunchFragment : Fragment() {

    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLaunchBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.representativesButton.setOnClickListener { navToRepresentatives() }
        binding.upcomingElectionsButton.setOnClickListener { navToElections() }

        return binding.root
    }

    private fun navToElections() {
        navController.navigate(LaunchFragmentDirections.actionLaunchFragmentToElectionsFragment())
    }

    private fun navToRepresentatives() {
        navController.navigate(LaunchFragmentDirections.actionLaunchFragmentToRepresentativesFragment())
    }

}
