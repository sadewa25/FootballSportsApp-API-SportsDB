package trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.submenu

import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.CalendarContract
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import org.jetbrains.anko.find
import trunojoyo.sadewa.adminsi.footballsportsapps.R
import java.text.SimpleDateFormat
import java.util.*

class MatchAdapter(var dataItems:ArrayList<MatchModel>, val context: Context?,val param:Int, private val listener: (MatchModel) -> Unit):
    RecyclerView.Adapter<MatchAdapter.itemHolder>(),Filterable{

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    dataItems = dataFiltered
                } else {
                    val filteredList = arrayListOf<MatchModel>()
                    for (row in dataFiltered) {

                        if (row.strHomeTeam.toLowerCase().contains(charString.toLowerCase())||row.strAwayTeam.toLowerCase().contains(charString.toLowerCase())){
                            filteredList.add(row)
                        }
                    }

                    dataItems = filteredList
                }

                val filterResults = Filter.FilterResults()
                filterResults.values = dataItems
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                dataItems = filterResults.values as ArrayList<MatchModel>
                notifyDataSetChanged()
            }
        }
    }

    val dataFiltered = dataItems

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): itemHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.match_item,parent,false)
        return itemHolder(view)
    }

    override fun getItemCount(): Int = dataItems.size

    override fun onBindViewHolder(holder: itemHolder, position: Int) {
        holder.bindItems(dataItems[position],listener,context,param)
    }

    class itemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemDate:TextView = itemView.find(R.id.match_date)
        var itemtime:TextView = itemView.find(R.id.match_time)
        var itemHomeTeam:TextView = itemView.find(R.id.match_home_team)
        var itemAwayTeam:TextView = itemView.find(R.id.match_away_team)
        var itemNotif:ImageView = itemView.find(R.id.match_notif)

        @SuppressLint("SetTextI18n")
        fun bindItems(model: MatchModel, listener: (MatchModel) -> Unit, context: Context?, param: Int){
            itemDate.text = model.dateEvent
            itemtime.text = changeTimeZone(model.strTime)
            var homeScore = ""
            var awayScore = ""
            if(!model.intHomeScore.equals("null"))
                homeScore = model.intHomeScore

            if (!model.intAwayScore.equals("null"))
                awayScore = model.intAwayScore

            if (param == 2)
                itemNotif.visibility = View.INVISIBLE
            itemHomeTeam.text = "${model.strHomeTeam} \n $homeScore"
            itemAwayTeam.text = "${model.strAwayTeam} \n $awayScore"
            itemNotif.setOnClickListener {
                val startMillis: Long = 1000
                val builder: Uri.Builder = CalendarContract.CONTENT_URI.buildUpon()
                    .appendPath("time")
                ContentUris.appendId(builder, startMillis)
                val intent = Intent(Intent.ACTION_VIEW)
                    .setData(builder.build())
                context?.startActivity(intent)
            }
            itemView.setOnClickListener { listener(model) }

        }

        @SuppressLint("SimpleDateFormat")
        private fun changeTimeZone(time:String):String{
            val simpleDateFormat = SimpleDateFormat("HH:mm:ss")
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"))
            val myDate = simpleDateFormat.parse(time)
            return simpleDateFormat.format(myDate).toString()
        }
    }


}