package trunojoyo.sadewa.adminsi.footballsportsapps.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {

    companion object {
        private var instance: DatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseOpenHelper {
            if (instance == null) {
                instance = DatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as DatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            Favourite.TABLE_FAVORITE, true,
            Favourite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favourite.TEAM_ID_EVENT to TEXT + UNIQUE,
            Favourite.TEAM_AWAY to TEXT,
            Favourite.TEAM_HOME to TEXT,
            Favourite.TEAM_DATE_EVENT to TEXT,
            Favourite.TEAM_SCORE_HOME to TEXT,
            Favourite.TEAM_SCORE_AWAY to TEXT,
            Favourite.TEAM_PARAMETER_ID to TEXT
        )
        db?.createTable(
            FavouriteTeamsModel.TABLE_FAVORITETEAMS,true,
            FavouriteTeamsModel.id to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavouriteTeamsModel.idTeam to TEXT+ UNIQUE,
            FavouriteTeamsModel.strTeamBadge to TEXT,
            FavouriteTeamsModel.strTeam to TEXT
        )

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Favourite.TABLE_FAVORITE, true)
        db?.dropTable(FavouriteTeamsModel.TABLE_FAVORITETEAMS, true)
    }

}

// Access property for Context
val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(applicationContext)