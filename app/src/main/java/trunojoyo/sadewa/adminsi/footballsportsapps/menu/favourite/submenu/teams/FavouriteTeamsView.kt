package trunojoyo.sadewa.adminsi.footballsportsapps.menu.favourite.submenu.teams

import trunojoyo.sadewa.adminsi.footballsportsapps.db.FavouriteTeamsModel

interface FavouriteTeamsView {
    fun showLoading()
    fun hideLoading()
    fun showDataItemsFavourite(dataItems: List<FavouriteTeamsModel>)
}