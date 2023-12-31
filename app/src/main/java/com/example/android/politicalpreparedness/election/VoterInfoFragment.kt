package com.example.android.politicalpreparedness.election

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.politicalpreparedness.R
import com.example.android.politicalpreparedness.databinding.FragmentVoterInfoBinding
import com.example.android.politicalpreparedness.base.BaseFragment
import com.google.android.material.snackbar.Snackbar

class VoterInfoFragment : BaseFragment() {

    private val navController by lazy { findNavController() }
    override val viewModel: VoterInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentVoterInfoBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val arguments = VoterInfoFragmentArgs.fromBundle(requireArguments())
        viewModel.refresh(arguments.election)

        binding.stateLocations.setOnClickListener {
            val urlStr = viewModel.voterInfo.value?.votingLocationUrl
            urlStr?.run {
                startActivityUrlIntent(this)
            }
        }

        binding.stateBallot.setOnClickListener {
            val urlStr = viewModel.voterInfo.value?.ballotInformationUrl
            urlStr?.run {
                startActivityUrlIntent(this)
            }
        }

        return binding.root
    }


    private fun startActivityUrlIntent(urlStr: String) {
        val uri: Uri = Uri.parse(urlStr)
        val intent = Intent(Intent.ACTION_VIEW, uri)

        try {
            intent.setPackage("com.android.chrome")
            startActivity(intent)

        } catch (e: ActivityNotFoundException) {

            try {
                intent.setPackage(null)
                startActivity(intent)

            } catch (e: ActivityNotFoundException) {
                val snack = Snackbar.make(
                    requireView(),
                    getString(R.string.no_web_browser_found_msg),
                    Snackbar.LENGTH_LONG
                )
                snack.show()
            }
        }
    }

}