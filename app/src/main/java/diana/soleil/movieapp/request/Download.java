package diana.soleil.movieapp.request;

import android.os.AsyncTask;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import diana.soleil.movieapp.model.Movie;
import diana.soleil.movieapp.utilities.JsonParser;

public class Download extends AsyncTask<String, Void, String> {
    JsonParser jsonParser;
    @Override
    protected String doInBackground(String... strings) {
        StringBuilder result= new StringBuilder();
        URL url;
        HttpsURLConnection httpsURLConnection = null;
        try {
            url = new URL(strings[0]);
            httpsURLConnection = (HttpsURLConnection) url.openConnection();
            InputStream inputStream = httpsURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            int data = inputStreamReader.read();

            while (data != -1) {
                char c = (char) data;
                result.append(c);
                data = inputStreamReader.read();
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

    }

}
