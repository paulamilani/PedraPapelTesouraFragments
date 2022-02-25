package com.ifsp.pedrapapeltesourafragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResult
import com.ifsp.pedrapapeltesourafragments.databinding.FragmentConfigBinding


class ConfigFragment : Fragment() {

    private lateinit var configFragmentConfigBinding: FragmentConfigBinding
    private var jg = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        configFragmentConfigBinding = FragmentConfigBinding.inflate(inflater, container, false)

        configFragmentConfigBinding.doisRb.setOnClickListener{
            jg = 2
        }
        configFragmentConfigBinding.tresRb.setOnClickListener{
            jg = 3
        }

        configFragmentConfigBinding.cancelarBtn.setOnClickListener {
            cancela()
        }

        configFragmentConfigBinding.salvarBtn.setOnClickListener {
            salva()
        }

        return configFragmentConfigBinding.root
    }

    private fun cancela() {
        activity?.supportFragmentManager?.commit {
            setReorderingAllowed(true)
            addToBackStack("principal")
            replace(R.id.frag, MainFragment(), "MainFragment")
        }

    }

    private fun salva() {
        //to do
        configFragmentConfigBinding.salvarBtn.setOnClickListener {
            setFragmentResult("requestKey", bundleOf("JOGADORES" to jg))
            activity?.supportFragmentManager?.popBackStack()
        }
    }

}

