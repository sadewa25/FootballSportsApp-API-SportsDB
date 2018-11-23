package trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.submenu.nextmatch

import trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.submenu.MatchModel

interface NextMatchView {
    fun showLoading()
    fun hideLoading()
    fun showDataLeague(dataItems:ArrayList<MatchModel>)
    fun showDataEvents(dataItems: ArrayList<MatchModel>)
}