package com.roviery.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.roviery.catetin.R
import com.roviery.todo.databinding.FragmentTodoBinding
import com.roviery.todo.di.todoModule
import org.koin.core.context.loadKoinModules

class TodoFragment : Fragment() {
    private var _binding: FragmentTodoBinding? = null
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

        if (activity != null) {
            loadKoinModules(todoModule)
            setTabBar()

            binding?.todoFb?.setOnClickListener {
                findNavController().navigate(R.id.action_todoFragment_to_todoDialogFragment)
            }

        }
    }

    private fun setTabBar(){
        val sectionsPagerAdapter = SectionsPagerAdapter(requireActivity())
        val viewPager: ViewPager2? = binding?.todoViewpager
        viewPager?.adapter = sectionsPagerAdapter

        val tabs: TabLayout? = binding?.todoTabs

        if (viewPager != null && tabs != null){
            TabLayoutMediator(tabs, viewPager){ tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            com.roviery.todo.R.string.todo,
            com.roviery.todo.R.string.in_progress,
            com.roviery.todo.R.string.done
        )
    }

}