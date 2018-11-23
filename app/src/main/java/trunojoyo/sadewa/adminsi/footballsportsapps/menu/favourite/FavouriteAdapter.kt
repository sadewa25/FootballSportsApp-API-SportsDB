package trunojoyo.sadewa.adminsi.footballsportsapps.menu.favourite

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import trunojoyo.sadewa.adminsi.footballsportsapps.R
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.favourite.submenu.matches.FavouriteMatchesFragment
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.favourite.submenu.teams.FavouriteTeamsFragment

class FavouriteAdapter(fm: FragmentManager?, context: Context?): FragmentStatePagerAdapter(fm) {

    val dataFragment = arrayOf(
        FavouriteMatchesFragment(),
        FavouriteTeamsFragment()
    )
    private val dataFragmentTittle = arrayOf(context?.getString(R.string.tittle_favourite_matches),context?.getString(R.string.tittle_favourite_teams))

    override fun getItem(position: Int): Fragment {
        return dataFragment[position] as Fragment
    }

    override fun getCount(): Int = dataFragment.size

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> dataFragmentTittle[position]
            else -> dataFragmentTittle[position]

        }
    }

}