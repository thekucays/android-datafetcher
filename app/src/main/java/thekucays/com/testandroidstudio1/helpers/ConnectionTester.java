package thekucays.com.testandroidstudio1.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;

import thekucays.com.testandroidstudio1.R;

/**
 * Created by thekucays on 7/5/16.
 */
public class ConnectionTester {

    public static ConnectionTester getInstance(){
        return new ConnectionTester();
    }

    public boolean isConnectionAvailable(Context ctx){
        boolean connected = false;
        String url = "http://jsonplaceholder.typicode.com/posts";
        ConnectivityManager connectivityManager = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        // cek apakah connected
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED
                || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED){
            // jika terkoneksi, coba download sample json nya
            /*JSONParser jsonParser = new JSONParser();
            JSONArray test = jsonParser.getFromURL(url);  // semua yang nge refer ke "R" mengacu ke reference iya (int).. jadi ngambil nya harus kaya gini
            if(test != null || test.length() > 0){*/
                connected = true;
            //}
        }
        return connected;
    }
}
