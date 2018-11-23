package trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams.detailteams

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import trunojoyo.sadewa.adminsi.footballsportsapps.R
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams.detailteams.submenu.overview.OverviewTeamsFragment
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams.detailteams.submenu.players.PlayersFragment

class DetailTeamsAdapter (fm: FragmentManager?, context: Context?, id:String): FragmentStatePagerAdapter(fm) {

    val dataFragment = arrayOf(
        OverviewTeamsFragment(id),
        PlayersFragment(id)
    )
    private val dataFragmentTittle = arrayOf(context?.getString(R.string.title_tab_overview),context?.getString(R.string.title_tab_players))

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