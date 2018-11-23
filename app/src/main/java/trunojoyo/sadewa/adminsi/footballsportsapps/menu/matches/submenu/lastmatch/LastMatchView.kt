package trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.submenu.lastmatch

import trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.submenu.MatchModel


interface LastMatchView {
    fun showLoading()
    fun hideLoading()
    fun showDataLeague(dataItems:ArrayList<MatchModel>)
    fun showDataEvents(dataItems: ArrayList<MatchModel>)
}