package trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.detail

import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("idEvent")
    var idEvent:String="",
    @SerializedName("dateEvent")
    var dateEvent:String="",
    @SerializedName("strHomeTeam")
    var strHomeTeam:String="",
    @SerializedName("strAwayTeam")
    var strAwayTeam:String="",
    @SerializedName("intHomeScore")
    var intHomeScore:String="",
    @SerializedName("intAwayScore")
    var intAwayScore:String="",
    @SerializedName("strHomeGoalDetails")
    var strHomeGoalDetails:String="",
    @SerializedName("strHomeLineupGoalkeeper")
    var strHomeLineupGoalkeeper:String="",
    @SerializedName("strHomeLineupDefense")
    var strHomeLineupDefense:String="",
    @SerializedName("strHomeLineupMidfield")
    var strHomeLineupMidfield:String="",
    @SerializedName("strHomeLineupForward")
    var strHomeLineupForward:String="",
    @SerializedName("strHomeLineupSubstitutes")
    var strHomeLineupSubstitutes:String="",
    @SerializedName("strAwayGoalDetails")
    var  strAwayGoalDetails:String="",
    @SerializedName("strAwayLineupGoalkeeper")
    var strAwayLineupGoalkeeper:String="",
    @SerializedName("strAwayLineupDefense")
    var strAwayLineupDefense:String="",
    @SerializedName("strAwayLineupMidfield")
    var strAwayLineupMidfield:String="",
    @SerializedName("strAwayLineupForward")
    var strAwayLineupForward:String="",
    @SerializedName("strAwayLineupSubstitutes")
    var strAwayLineupSubstitutes:String="",
    @SerializedName("intHomeShots")
    var intHomeShots:String="",
    @SerializedName("intAwayShots")
    var intAwayShots:String="",
    @SerializedName("idHomeTeam")
    var idHomeTeam:String="",
    @SerializedName("idAwayTeam")
    var idAwayTeam:String="",
    @SerializedName("idLeague")
    val idLeague:String = "",
    @SerializedName("strLeague")
    val strLeague:String = "",
    @SerializedName("strTeam")
    val strTeam:String="",
    @SerializedName("strTeamBadge")
    val strTeamBadge:String="",
    @SerializedName("idTeam")
    val idTeam:String=""
)