package trunojoyo.sadewa.adminsi.footballsportsapps

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.favourite.FavoriteFragment
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.MatchesFragment
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams.TeamsFragment


class MainActivity : AppCompatActivity() {

    private lateinit var mainPresenter: MainPresenter

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_matches -> {
                mainPresenter.changeFragment(MatchesFragment(),supportFragmentManager,R.id.frame_main)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_teams -> {
                mainPresenter.changeFragment(TeamsFragment(),supportFragmentManager,R.id.frame_main)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorite -> {
                mainPresenter.changeFragment(FavoriteFragment(),supportFragmentManager,R.id.frame_main)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(Manifest.permission.READ_CALENDAR,Manifest.permission.WRITE_CALENDAR),10)
        }

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        mainPresenter = MainPresenter()
        mainPresenter.changeFragment(MatchesFragment(),supportFragmentManager,R.id.frame_main)
    }



}
