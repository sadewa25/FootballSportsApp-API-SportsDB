package trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.detail

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import trunojoyo.sadewa.adminsi.footballsportsapps.R
import trunojoyo.sadewa.adminsi.footballsportsapps.db.database
import trunojoyo.sadewa.adminsi.footballsportsapps.network.APIClient

class DetailActivity : AppCompatActivity(),DetailView {

    override fun setImgItemsBadges(homeBadge: String, awayBadge: String) {
        Picasso.get().load(homeBadge).placeholder(R.mipmap.ic_launcher).error(R.drawable.ic_error_black_24dp).into(detail_logo_home_team)
        Picasso.get().load(awayBadge).placeholder(R.mipmap.ic_launcher).error(R.drawable.ic_error_black_24dp).into(detail_logo_away_team)
    }


    @SuppressLint("SetTextI18n")
    override fun setDataItems(dataModel: DataModel?, paramsIndex:String) {
        detail_name_away_team.text = dataModel?.strAwayTeam
        detail_name_home_team.text = dataModel?.strHomeTeam
        detail_date_event.text = dataModel?.dateEvent
        if (paramsIndex.equals("1")) {
            homeScore = dataModel?.intHomeScore.toString()
            awayScore = dataModel?.intAwayScore.toString()
            detail_score_team.text = "${dataModel?.intHomeScore} VS ${dataModel?.intAwayScore}"
            detail_goal_home_team.text = dataModel?.strHomeGoalDetails
            detail_goalkeeper_home_team.text = dataModel?.strHomeLineupGoalkeeper
            detail_defense_home_team.text = dataModel?.strHomeLineupDefense
            detail_midfield_home_team.text = dataModel?.strHomeLineupMidfield
            detail_forward_home_team.text = dataModel?.strHomeLineupForward
            detail_subtitutes_home_team.text = dataModel?.strHomeLineupSubstitutes
            detail_goal_away_team.text = dataModel?.strAwayGoalDetails
            detail_goalkeeper_away_team.text = dataModel?.strAwayGoalDetails
            detail_defense_away_team.text = dataModel?.strAwayLineupDefense
            detail_midfield_away_team.text = dataModel?.strAwayLineupMidfield
            detail_forward_away_team.text = dataModel?.strAwayLineupForward
            detail_subtitutes_away_team.text = dataModel?.strAwayLineupSubstitutes
            detail_shots_home_team.text = dataModel?.intHomeShots
            detail_shots_away_team.text = dataModel?.intAwayShots
        }
    }

    override fun getFavouriteSize(boolean: Boolean){
        isFavorite = boolean
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    private lateinit var apiClient: APIClient
    private var menuItem: Menu? = null
    private lateinit var id_item:String
    private lateinit var awayScore:String
    private lateinit var homeScore:String
    private lateinit var paramsIndex:String

    private var isFavorite: Boolean = false
    private lateinit var detailPresenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        homeScore = ""
        awayScore = ""
        id_item = intent.getStringExtra("id")
        apiClient = APIClient()
        paramsIndex = intent.getStringExtra("param")

        detailPresenter = DetailPresenter(this,this.database,this)
        detailPresenter.getFavoriteSize(id_item)

        detailPresenter.showDetailDataItems(id_item,apiClient,paramsIndex)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.add_to_favorite -> {
                if (isFavorite) {
                    detailPresenter.removeFromFavorite(id_item)
                }
                else{
                    detailPresenter.addToFavorite(
                        id_item,
                        detail_name_away_team.text.toString(),
                        detail_name_home_team.text.toString(),
                        detail_date_event.text.toString(),
                        homeScore,
                        awayScore,
                        paramsIndex
                    )
                }

                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }


}
