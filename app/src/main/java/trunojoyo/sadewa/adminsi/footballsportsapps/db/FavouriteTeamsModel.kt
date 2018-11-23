package trunojoyo.sadewa.adminsi.footballsportsapps.db

data class FavouriteTeamsModel (val id:Long?,
                                val idTeam:String?,
                                val strTeamBadge:String?,
                                val strTeam:String?
) {

    companion object {
        const val TABLE_FAVORITETEAMS: String = "TABLE_FAVORITE_TEAMS"
        const val id: String = "id"
        const val idTeam: String = "idTeam"
        const val strTeamBadge: String = "strTeamBadge"
        const val strTeam: String = "strTeam"

    }

}