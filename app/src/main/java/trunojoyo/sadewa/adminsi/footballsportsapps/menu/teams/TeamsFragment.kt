package trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Spinner
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity

import trunojoyo.sadewa.adminsi.footballsportsapps.R
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.detail.DataModel
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.submenu.MatchModel
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.submenu.MatchSpinner
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams.detailteams.DetailTeamsActivity
import trunojoyo.sadewa.adminsi.footballsportsapps.network.APIClient

class TeamsFragment : Fragment(), TeamsView{

    override fun showDataTeams(dataItems: ArrayList<DataModel>) {
        dataTeams.clear()
        dataTeams.addAll(dataItems)
        teamsAdapter.notifyDataSetChanged()
    }

    override fun showLoading() {loading.visibility = View.VISIBLE}

    override fun hideLoading() {loading.visibility = View.INVISIBLE}

    override fun showDataLeague(dataItems: ArrayList<MatchModel>) {
        dataItemsLeague.addAll(dataItems)
        adapterSpinner.notifyDataSetChanged()
    }

    private lateinit var teamsPresenter: TeamsPresenter
    private lateinit var apiClient: APIClient
    private lateinit var dataItemsLeague:ArrayList<MatchModel>
    private lateinit var adapterSpinner:MatchSpinner
    private lateinit var teamsAdapter: TeamsAdapter
    private lateinit var dataTeams:ArrayList<DataModel>
    private lateinit var loading:ProgressBar
    private var idLeague:String = ""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_teams, container, false)

        val spinner:Spinner = view.find(R.id.spinner_teams)
        val recycler:RecyclerView = view.find(R.id.recycler_teams)
        val search:EditText = view.find(R.id.search_teams)
        loading = view.find(R.id.loading)

        apiClient = APIClient()
        dataItemsLeague = arrayListOf()
        teamsPresenter = TeamsPresenter(this,apiClient,context)
        adapterSpinner = MatchSpinner(context,dataItems = dataItemsLeague)
        teamsPresenter.getDataLeague("${context?.getString(R.string.url_league)}")
        spinner.adapter = adapterSpinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                idLeague = dataItemsLeague[position].idLeague
                teamsPresenter.getDataTeams(context?.getString(R.string.url_allteams_league)+idLeague)
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {}
        }

        dataTeams = arrayListOf()
        teamsAdapter = TeamsAdapter(dataTeams){
            startActivity<DetailTeamsActivity>("id" to it.idTeam)
        }
        teamsPresenter.getDataTeams(context?.getString(R.string.url_allteams_league)+idLeague)
        val layout:RecyclerView.LayoutManager = LinearLayoutManager(context)
        recycler.layoutManager = layout
        recycler.adapter = teamsAdapter

        search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(edit: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, position: Int, p2: Int, p3: Int) {
                teamsAdapter.filter.filter(s.toString())
            }
        })
        return view
    }


}
