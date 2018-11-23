package trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams.detailteams.detailplayer

import android.content.Context
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import trunojoyo.sadewa.adminsi.footballsportsapps.R
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams.detailteams.DetailTeamsModel
import trunojoyo.sadewa.adminsi.footballsportsapps.network.APIClient

class DetailPlayerPresenter(val detailPlayerView: DetailPlayerView,
                            val apiClient: APIClient,
                            val context: Context?) {

    fun getItems(id:String){
        detailPlayerView.showLoading()
        val dataItems:ArrayList<DetailTeamsModel> = arrayListOf()
        doAsync {
            val jsonObject = JSONObject(apiClient.getResponse(context?.getString(R.string.url_detail_player)+id))
            val arrayEvents = jsonObject.getJSONArray("players")
            for (i in 0..arrayEvents.length()-1) {
                val data = arrayEvents.getJSONObject(i)
                dataItems.add(
                    DetailTeamsModel(
                        strPlayer = data.getString("strPlayer"),
                        strDescriptionEN = data.getString("strDescriptionEN"),
                        strPosition = data.getString("strPosition"),
                        strWeight = data.getString("strWeight"),
                        strHeight = data.getString("strHeight"),
                        strThumb = data.getString("strThumb")
                    )
                )
            }
            uiThread {
                detailPlayerView.showDataItems(dataItems)
                detailPlayerView.hideLoading()
            }
        }

    }
}