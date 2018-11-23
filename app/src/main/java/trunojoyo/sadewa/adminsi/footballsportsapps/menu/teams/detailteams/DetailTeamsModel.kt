package trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams.detailteams

import com.google.gson.annotations.SerializedName

data class DetailTeamsModel(
    @SerializedName("strTeam")
    val strTeam:String="",
    @SerializedName("intFormedYear")
    val intFormedYear:String="",
    @SerializedName("strTeamBadge")
    val strTeamBadge:String="",
    @SerializedName("strStadium")
    val strStadium:String = "",
    @SerializedName("strDescriptionEN")
    val strDescriptionEN:String = "",
    @SerializedName("strPlayer")
    val strPlayer:String = "",
    @SerializedName("strPosition")
    val strPosition:String = "",
    @SerializedName("strCutout")
    val strCutout:String = "",
    @SerializedName("idPlayer")
    val idPlayer:String="",
    @SerializedName("strHeight")
    val strHeight:String ="",
    @SerializedName("strWeight")
    val strWeight:String = "",
    @SerializedName("strThumb")
    val strThumb:String = ""
)