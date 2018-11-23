package trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams.detailteams

interface DetailTeamsView {
    fun getFavouriteSize(boolean: Boolean)
    fun showDataItems(dataItems:ArrayList<DetailTeamsModel>)
}