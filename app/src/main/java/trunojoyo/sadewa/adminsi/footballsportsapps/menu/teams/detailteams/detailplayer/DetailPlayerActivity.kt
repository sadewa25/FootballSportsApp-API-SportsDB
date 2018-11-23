package trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams.detailteams.detailplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import trunojoyo.sadewa.adminsi.footballsportsapps.R
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams.detailteams.DetailTeamsModel
import trunojoyo.sadewa.adminsi.footballsportsapps.network.APIClient

class DetailPlayerActivity : AppCompatActivity(),DetailPlayerView {

    override fun showDataItems(dataItems: ArrayList<DetailTeamsModel>) {
        Picasso.get().load(dataItems[0].strThumb).placeholder(R.mipmap.ic_launcher).into(playerPhoto)
        playerWeight.text = dataItems[0].strWeight
        playerheight.text = dataItems[0].strHeight
        playerPosition.text = dataItems[0].strPosition
        playerDesc.text = dataItems[0].strDescriptionEN
        playerName.text =dataItems[0].strPlayer
    }

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading.visibility = View.INVISIBLE
    }

    private lateinit var detailPlayerPresenter: DetailPlayerPresenter
    private lateinit var playerPhoto:ImageView
    private lateinit var playerWeight:TextView
    private lateinit var playerheight:TextView
    private lateinit var playerPosition:TextView
    private lateinit var playerDesc:TextView
    private lateinit var playerName:TextView
    private lateinit var loading:ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)

        val idPlayer:String = intent.getStringExtra("id")
        playerPhoto= find(R.id.player_detail_photo)
        playerWeight = find(R.id.player_detail_weight)
        playerheight = find(R.id.player_detail_height)
        playerPosition = find(R.id.player_detail_position)
        playerDesc = find(R.id.player_detail_desc)
        playerName = find(R.id.player_detail_name)
        loading = find(R.id.loading)

        detailPlayerPresenter = DetailPlayerPresenter(this, APIClient(),applicationContext)
        detailPlayerPresenter.getItems(idPlayer)

    }

}
