package trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams.detailteams.submenu.players

import trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams.detailteams.DetailTeamsModel

interface PlayersView {
    fun getListPlayers(dataItems:ArrayList<DetailTeamsModel>)
}