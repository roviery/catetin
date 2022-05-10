package com.roviery.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.roviery.catetin.R
import com.roviery.todo.databinding.FragmentTodoBinding

class TodoFragment : Fragment() {

    private var _binding : FragmentTodoBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){


            binding?.todoFb?.setOnClickListener {
                findNavController().navigate(R.id.action_todoFragment_to_todoDialogFragment)
            }


        }

    }

}