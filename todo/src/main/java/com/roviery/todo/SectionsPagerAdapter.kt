package com.roviery.todo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.roviery.todo.views.DoneViewFragment
import com.roviery.todo.views.InProgressViewFragment
import com.roviery.todo.views.TodoViewFragment

class SectionsPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = TodoViewFragment()
            1 -> fragment = InProgressViewFragment()
            2 -> fragment = DoneViewFragment()
        }
        return fragment as Fragment
    }
}