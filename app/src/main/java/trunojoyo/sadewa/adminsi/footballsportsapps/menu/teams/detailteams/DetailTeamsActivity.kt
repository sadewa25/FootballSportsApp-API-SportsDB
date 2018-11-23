package trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams.detailteams

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_teams.*
import org.jetbrains.anko.find
import org.jetbrains.anko.imageResource
import trunojoyo.sadewa.adminsi.footballsportsapps.R
import trunojoyo.sadewa.adminsi.footballsportsapps.db.database
import trunojoyo.sadewa.adminsi.footballsportsapps.network.APIClient

class DetailTeamsActivity : AppCompatActivity(),DetailTeamsView {

    override fun showDataItems(dataItems: ArrayList<DetailTeamsModel>) {
        this.dataItems = dataItems
        Picasso.get().load(dataItems[0].strTeamBadge).placeholder(R.mipmap.ic_launcher).into(detailLogo)
        detailFormedYear.text = dataItems[0].intFormedYear
        detailStadium.text = dataItems[0].strStadium
        detailNameTeam.text = dataItems[0].strTeam

    }

    override fun getFavouriteSize(boolean: Boolean) {
        isFavorite = boolean
    }


    private lateinit var detailTeamsPresenter: DetailTeamsPresenter
    private var isFavorite: Boolean = false
    private lateinit var dataItems:ArrayList<DetailTeamsModel>
    private lateinit var detailLogo:ImageView
    private lateinit var detailFormedYear:TextView
    private lateinit var detailStadium:TextView
    private lateinit var detailNameTeam:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_teams)

        detailLogo= find(R.id.detail_logo)
        detailFormedYear= find(R.id.detail_formed_year)
        detailStadium= find(R.id.detail_stadium)
        detailNameTeam= find(R.id.detail_name_team)
        val idTeams = intent.getStringExtra("id")

        dataItems = arrayListOf()

        detailTeamsPresenter = DetailTeamsPresenter(this, APIClient(),this.database,applicationContext)
        detailTeamsPresenter.getInfoTeams(id = idTeams)

        val tabs_main: TabLayout = find(R.id.tabs_main)
        val pager: ViewPager = find(R.id.viewpager_main)

        pager.adapter = DetailTeamsAdapter(supportFragmentManager,applicationContext,idTeams)
        tabs_main.setupWithViewPager(pager)

        detailTeamsPresenter.getFavoriteSize(idTeams)

        fab.setOnClickListener {
            if (isFavorite) {
                detailTeamsPresenter.removeFromFavorite(idTeams)
                isFavorite = false
            }
            else{
                detailTeamsPresenter.addToFavorite(
                    idTeam = idTeams,
                    teamLogo = dataItems[0].strTeamBadge,
                    teamName = dataItems[0].strTeam
                )
                isFavorite = true
            }
            setFavorite()
        }
        setFavorite()
    }

    private fun setFavorite() {
        if (isFavorite)
            fab.imageResource = R.drawable.ic_added_to_favorites
        else
            fab.imageResource = R.drawable.ic_add_to_favorites
    }



}
