package ru.dmitryzyrynov.lab18

import android.os.AsyncTask
import android.os.Bundle
import android.util.Xml
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStreamReader
import java.io.StringReader
import java.net.URL
import java.nio.charset.Charset
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.list_view)
        progressBar = findViewById(R.id.progress_bar)

        val buttonGetRates: Button = findViewById(R.id.button_get_rates)
        buttonGetRates.setOnClickListener {
            progressBar.visibility = ProgressBar.VISIBLE
            DownloadRatesTask().execute("https://www.cbr.ru/scripts/XML_daily.asp")
        }
    }

    inner class DownloadRatesTask : AsyncTask<String, Void, List<String>>() {
        override fun doInBackground(vararg urls: String?): List<String>? {
            val rates = mutableListOf<String>()
            try {
                val url = URL(urls[0])
                val connection = url.openConnection() as HttpsURLConnection
                connection.connect()
                val inputStream = connection.inputStream
                val xml = InputStreamReader(inputStream, Charset.forName("Windows-1251")).use { it.readText() }
                rates.addAll(parseXML(xml))
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return rates
        }

        override fun onPostExecute(result: List<String>?) {
            progressBar.visibility = ProgressBar.GONE
            if (result != null) {
                val adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, result)
                listView.adapter = adapter
            }
        }
    }

    private fun parseXML(xmlData: String): List<String> {
        val rateList = mutableListOf<String>()
        val parser: XmlPullParser = Xml.newPullParser()
        parser.setInput(StringReader(xmlData))

        var eventType = parser.eventType
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG && parser.name == "Valute") {
                var currencyName: String? = null
                var currencyValue: String? = null

                while (eventType != XmlPullParser.END_TAG || parser.name != "Valute") {
                    if (eventType == XmlPullParser.START_TAG) {
                        when (parser.name) {
                            "Name" -> currencyName = parser.nextText()
                            "Value" -> currencyValue = parser.nextText()
                        }
                    }
                    eventType = parser.next()
                }

                if (currencyName != null && currencyValue != null) {
                    rateList.add("$currencyName: $currencyValue")
                }
            }
            eventType = parser.next()
        }
        return rateList
    }
}