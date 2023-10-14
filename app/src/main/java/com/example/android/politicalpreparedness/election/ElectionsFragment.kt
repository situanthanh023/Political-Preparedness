package com.example.android.politicalpreparedness.election

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.politicalpreparedness.base.BaseFragment
import com.example.android.politicalpreparedness.databinding.FragmentElectionBinding
import com.example.android.politicalpreparedness.election.adapter.ElectionListAdapter
import com.example.android.politicalpreparedness.election.adapter.ElectionListener

class ElectionsFragment : BaseFragment() {

    private val navController by lazy { findNavController() }
    override val viewModel: ElectionsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        val binding = FragmentElectionBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val electionAdapter = ElectionListAdapter(ElectionListener { election ->
            navController.navigate(
                ElectionsFragmentDirections.actionElectionsFragmentToVoterInfoFragment(election)
            )
        })

        binding.upcomingElectionsRecyclerView.adapter = electionAdapter
        viewModel.activeElections.observe(viewLifecycleOwner) { elections ->
            electionAdapter.submitList(elections)
        }


        // saved elections recycle view
        val savedElectionAdapter = ElectionListAdapter(ElectionListener { election ->
            navController.navigate(
                ElectionsFragmentDirections.actionElectionsFragmentToVoterInfoFragment(election)
            )
        })

        binding.savedElectionsRecyclerView.adapter = savedElectionAdapter
        viewModel.savedElections.observe(viewLifecycleOwner) { elections ->
            savedElectionAdapter.submitList(elections)
        }

        return binding.root
    }
}