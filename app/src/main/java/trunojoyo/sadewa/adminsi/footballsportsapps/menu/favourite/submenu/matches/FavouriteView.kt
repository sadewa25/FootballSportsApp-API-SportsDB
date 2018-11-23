package trunojoyo.sadewa.adminsi.footballsportsapps.menu.favourite.submenu.matches

import trunojoyo.sadewa.adminsi.footballsportsapps.db.Favourite

interface FavouriteView {
    fun showLoading()
    fun hideLoading()
    fun showDataItemsFavourite(dataItems:List<Favourite>)
}