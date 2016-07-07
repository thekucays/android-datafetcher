package thekucays.com.testandroidstudio1.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import thekucays.com.testandroidstudio1.R;
import thekucays.com.testandroidstudio1.helpers.JSONParser;
import thekucays.com.testandroidstudio1.pojos.Posting;

/**
 * Created by thekucays on 7/7/16.
 */
public class ViewPosting extends AppCompatActivity {

    public String url;
    private TextView textJudul;
    private EditText textIsi;

    // id posting untuk ngambil data nya
    private String postID = new String();
    public String getPostID() {
        return postID;
    }
    public void setPostID(String postID) {
        this.postID = postID;
    }

    // objek class posting nya
    protected Posting posting = new Posting();
    public Posting getPosting() {
        return posting;
    }
    public void setPosting(Posting p) {
        posting = p;
    }


    // default constructor
    public ViewPosting(){}

    // inner class untuk ambil data single JSON nya
    private class JSONParse extends AsyncTask<String, Posting, Posting>{
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog = new ProgressDialog(ViewPosting.this);
            progressDialog.setMessage(getResources().getString(R.string.const_loadingtext));
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Posting doInBackground(String... params) {
            JSONParser jsonParser = new JSONParser();
            Posting temp = new Posting();

            try{
                //JSONArray jsonArray = jsonParser.getFromURL(url);
                System.out.println("Sending request to: " + url);
                //JSONObject jsonObject = jsonArray.getJSONObject(0);
                JSONObject jsonObject = (JSONObject)jsonParser.getFromURL(url, 1);

                temp.setId(jsonObject.getString("id"));
                temp.setUserId(jsonObject.getString("userId"));
                temp.setTitle(jsonObject.getString("title"));
                temp.setBody(jsonObject.getString("body"));
            }
            catch(JSONException jse){
                jse.printStackTrace();
            }

            return temp;
        }

        protected void onPostExecute(Posting posting){
            progressDialog.dismiss();
            //setPosting(posting);
            updateUI(posting);
        }
    }

    protected void updateUI(Posting posting){
        textJudul.setText(posting.getTitle());
        textIsi.setText(posting.getBody());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_posting_layout);
        textJudul = (TextView) findViewById(R.id.text_judul);
        textIsi = (EditText) findViewById(R.id.text_isi);

        // ambil id posting yang dikirim dari HomeFragment
        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
            setPostID(bundle.getString("postID"));
        url = getResources().getString(R.string.url_test_single) + getPostID();

        // baru eksekusi asynctask nya untuk dapet data nya
        new JSONParse().execute();
    }
}
