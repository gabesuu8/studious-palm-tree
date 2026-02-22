package com.example.helloapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.helloapp.R
import com.example.helloapp.util.LanguageHelper
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class VaccinationGrowthFragment : Fragment() {

    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager2? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vaccination_growth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<MaterialToolbar>(R.id.toolbar)?.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_language -> {
                    showLanguageDialog()
                    true
                }
                else -> false
            }
        }

        tabLayout = view.findViewById(R.id.tabLayout)
        viewPager = view.findViewById(R.id.viewPager)

        viewPager?.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 2
            override fun createFragment(position: Int): Fragment = when (position) {
                0 -> GrowthTrackerFragment().apply { arguments = Bundle().apply { putBoolean(ARG_EMBEDDED, true) } }
                1 -> VaccinationFragment().apply { arguments = Bundle().apply { putBoolean(ARG_EMBEDDED, true) } }
                else -> GrowthTrackerFragment().apply { arguments = Bundle().apply { putBoolean(ARG_EMBEDDED, true) } }
            }
        }

        TabLayoutMediator(
            tabLayout!!,
            viewPager!!
        ) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.growth)
                1 -> getString(R.string.vaccinations)
                else -> ""
            }
        }.attach()
    }

    private fun showLanguageDialog() {
        val ctx = context ?: return
        val languages = arrayOf(getString(R.string.english), getString(R.string.french))
        val languageCodes = arrayOf(LanguageHelper.ENGLISH, LanguageHelper.FRENCH)
        val currentLanguage = LanguageHelper.getLanguage(ctx)
        val currentIndex = languageCodes.indexOf(currentLanguage)

        AlertDialog.Builder(ctx)
            .setTitle(getString(R.string.language))
            .setSingleChoiceItems(languages, currentIndex) { dialog, which ->
                val selectedCode = languageCodes[which]
                if (currentLanguage != selectedCode) {
                    LanguageHelper.setLanguage(ctx, selectedCode)
                    dialog.dismiss()
                    activity?.recreate()
                } else {
                    dialog.dismiss()
                }
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tabLayout = null
        viewPager = null
    }

    companion object {
        const val ARG_EMBEDDED = "embedded"
    }
}
