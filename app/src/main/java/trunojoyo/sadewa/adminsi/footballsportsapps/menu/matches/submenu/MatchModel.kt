package trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.submenu

import com.google.gson.annotations.SerializedName

data class MatchModel(
    @SerializedName("idLeague")
    val idLeague:String = "",
    @SerializedName("strLeague")
    val strLeague:String = "",
    @SerializedName("strHomeTeam")
    val strHomeTeam:String = "",
    @SerializedName("strAwayTeam")
    val strAwayTeam:String = "",
    @SerializedName("dateEvent")
    val dateEvent:String = "",
    @SerializedName("strTime")
    val strTime:String="",
    @SerializedName("intHomeScore")
    val intHomeScore:String = "",
    @SerializedName("intAwayScore")
    val intAwayScore:String="",
    @SerializedName("idEvent")
    var idEvent:String=""
)