package trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import trunojoyo.sadewa.adminsi.footballsportsapps.R
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.submenu.lastmatch.LastMatchFragment
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.submenu.nextmatch.NextMatchFragment

class MatchesPresenter(fm: FragmentManager?, context: Context?):FragmentStatePagerAdapter(fm) {

    private val dataFragment = arrayOf(NextMatchFragment(),
        LastMatchFragment()
    )
    private val dataFragmentTittle = arrayOf(context?.getString(R.string.title_tab_next),context?.getString(R.string.title_tab_last))

    override fun getItem(position: Int): Fragment {
        return dataFragment[position]
    }

    override fun getCount(): Int = dataFragment.size

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> dataFragmentTittle[position]
            else -> dataFragmentTittle[position]

        }
    }

}