package trunojoyo.sadewa.adminsi.footballsportsapps

import org.junit.Test
import org.mockito.Mockito
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.submenu.lastmatch.LastMatchPresenter
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.submenu.nextmatch.NextMatchPresenter
import trunojoyo.sadewa.adminsi.footballsportsapps.network.APIClient

class NetworkingTesting{
    @Test
    fun testDoRequest() {
        val apiRepository = Mockito.mock(APIClient::class.java)
        val url = "eventspastleague.php?id=4328"
        apiRepository.getResponse(url)
        Mockito.verify(apiRepository).getResponse(url)
    }

    @Test
    fun testDoGetItemsLastMatch(){
        val apiRepository = Mockito.mock(LastMatchPresenter::class.java)
        val url = "search_all_leagues.php?s=Soccer"
        apiRepository.getDataLeague(url)
        Mockito.verify(apiRepository).getDataLeague("search_all_leagues.php?s=Soccer")
    }

    @Test
    fun testDoGetItemsNextMatch(){
        val apiRepository = Mockito.mock(NextMatchPresenter::class.java)
        val url = "search_all_leagues.php?s=Soccer"
        apiRepository.getDataLeague(url)
        Mockito.verify(apiRepository).getDataLeague("search_all_leagues.php?s=Soccer")
    }
}