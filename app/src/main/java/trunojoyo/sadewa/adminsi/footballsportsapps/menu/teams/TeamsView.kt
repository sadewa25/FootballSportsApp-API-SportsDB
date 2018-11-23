package trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams

import trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.detail.DataModel
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.submenu.MatchModel

interface TeamsView {

    fun showLoading()
    fun hideLoading()
    fun showDataLeague(dataItems:ArrayList<MatchModel>)
    fun showDataTeams(dataItems:ArrayList<DataModel>)

}