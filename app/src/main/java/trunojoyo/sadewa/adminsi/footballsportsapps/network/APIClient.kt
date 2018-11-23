package trunojoyo.sadewa.adminsi.footballsportsapps.network

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class APIClient {

    fun getResponse(url: String): String? {
        val request = Request.Builder().url("https://www.thesportsdb.com/api/v1/json/1/${url}").get().build()
        val client = OkHttpClient()
        val response: Response = client.newCall(request).execute()
        val jsonResponse = response.body()?.string()

        return jsonResponse
    }

}