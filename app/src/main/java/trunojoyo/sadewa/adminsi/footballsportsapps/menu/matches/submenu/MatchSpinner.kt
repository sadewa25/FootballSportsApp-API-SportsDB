package trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.submenu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import trunojoyo.sadewa.adminsi.footballsportsapps.R

class MatchSpinner(val context: Context?, val dataItems:ArrayList<MatchModel>):BaseAdapter() {

    val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view:View
        val vh: ItemRowHolder
        if (convertView == null){
            view = mInflater.inflate(R.layout.match_spinner_item, parent, false)
            vh = ItemRowHolder(view)
            view?.tag = vh
        }else{
            view = convertView
            vh = view.tag as ItemRowHolder
        }
        vh.label.text = dataItems[position].strLeague
        return view
    }

    override fun getItem(position: Int): Any = dataItems[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = dataItems.size

    private class ItemRowHolder(row: View?) {
        val label: TextView
        init {
            this.label = row?.findViewById(R.id.match_spinner_title) as TextView
        }
    }
}