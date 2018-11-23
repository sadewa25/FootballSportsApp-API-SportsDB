package trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.detail

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun getFavouriteSize(boolean: Boolean)
    fun setDataItems(dataModel: DataModel?,paramsIndex:String)
    fun setImgItemsBadges(homeBadge:String,awayBadge:String)
}