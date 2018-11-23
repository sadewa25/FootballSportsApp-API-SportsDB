package trunojoyo.sadewa.adminsi.footballsportsapps.db

data class Favourite (val ID_:Long?,
                      val TEAM_ID_EVENT: String?,
                      val AWAYTEAM: String?,
                      val HOMETEAM: String?,
                      val DATEEVENT: String?,
                      val TEAM_SCORE_HOME:String?,
                      val TEAM_SCORE_AWAY: String?,
                      val TEAM_PARAMETER_ID:String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val TEAM_ID_EVENT: String = "TEAM_ID_EVENT"
        const val TEAM_AWAY: String = "AWAYTEAM"
        const val TEAM_HOME: String = "HOMETEAM"
        const val TEAM_DATE_EVENT: String = "DATEEVENT"
        const val TEAM_SCORE_HOME: String = "TEAM_SCORE_HOME"
        const val TEAM_SCORE_AWAY: String = "TEAM_SCORE_AWAY"
        const val TEAM_PARAMETER_ID: String = "TEAM_PARAMETER_ID"

    }

}