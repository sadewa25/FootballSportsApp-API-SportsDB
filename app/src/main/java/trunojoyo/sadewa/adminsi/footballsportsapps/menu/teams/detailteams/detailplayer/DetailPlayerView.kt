package trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams.detailteams.detailplayer

import trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams.detailteams.DetailTeamsModel

interface DetailPlayerView {
    fun showLoading()
    fun hideLoading()
    fun showDataItems(dataItems:ArrayList<DetailTeamsModel>)
}