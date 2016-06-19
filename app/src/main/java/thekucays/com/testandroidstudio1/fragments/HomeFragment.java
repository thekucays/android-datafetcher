package thekucays.com.testandroidstudio1.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import thekucays.com.testandroidstudio1.R;
import thekucays.com.testandroidstudio1.helpers.JSONParser;
import thekucays.com.testandroidstudio1.pojos.Posting;

/**
 * Created by thekucays on 6/16/16.
 */
public class HomeFragment extends Fragment{

    private static String url = "http://jsonplaceholder.typicode.com/posts";
    //ArrayList<Posting> listPosting = null;
    private String[] userId = {};
    private String[] id = {};
    private String[] title = {};
    private String[] body = {};

    private ArrayList<Posting> listPosting = new ArrayList<Posting>();

    public HomeFragment(){
        //listPosting = new ArrayList<Posting>();
        //new JSONParse().execute();
    }

    public void setListPosting(ArrayList<Posting> posting){
        listPosting = posting;
    }
    public ArrayList<Posting> getListPosting(){
        return listPosting;
    }

    private class JSONParse extends AsyncTask<String, String, ArrayList<Posting>> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Sedang mengambil data..");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected ArrayList<Posting> doInBackground(String... params) {
            JSONParser jsonParser = new JSONParser();

            /*JSONObject jsonObject = jsonParser.getFromURL(url);
            return jsonObject;*/
            try {
                JSONArray jsonArray = jsonParser.getFromURL(url);
                System.out.println("jsonArray length: " + jsonArray.length());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    // create new Posting class object
                    Posting posting = new Posting();
                    posting.setId(jsonObject.getString("id"));
                    posting.setUserId(jsonObject.getString("userId"));
                    posting.setTitle(jsonObject.getString("title"));
                    posting.setBody(jsonObject.getString("body"));

                    listPosting.add(posting);
                }
                //return listPosting;
            }
            catch(JSONException jse){
                jse.printStackTrace();
            }
            //return null;
            return listPosting;
        }


        @Override
        protected void onPostExecute(ArrayList<Posting> listPosting){
            progressDialog.dismiss();
            //refreshData();
            System.out.println("listPosting lengthjjhjjh: " + listPosting.size());
            setListPosting(listPosting);
            updateUI();  //updateUI dipindah ke sini karena kalo dibikin di onCreateView suka ga ke detek
        }
    }

    // dummy buat nge tes
    /*private String[] id = {"1", "2"};
    private String[] title = {"sunt aut facere repellat provident", "ea molestias quasi"};
    private String[] body =
    {
            "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto",
            "est rerum tempore vitae\\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\\nqui aperiam non debitis possimus qui neque nisi nulla"
    }; */

    private RecyclerView postingRecyclerView;
    private PostingAdapter postingAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        //listPosting = new ArrayList<Posting>();
        new JSONParse().execute();

        refreshData();
        super.onCreate(savedInstanceState);
    }
    public void refreshData(){
        /*if(!listPosting.isEmpty()){
            listPosting.clear();
        }*/

        //listPosting = new ArrayList<Posting>();
        for(int i=0; i<userId.length; i++){
            Posting p = new Posting();
            p.setUserId(userId[i]);
            p.setId(id[i]);
            p.setTitle(title[i]);
            p.setBody(body[i]);

            listPosting.add(p);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //View viewTentang = inflater.inflate(R.layout.home_layout, container, false);
        View viewHome = inflater.inflate(R.layout.home_layout, container, false);

        postingRecyclerView = (RecyclerView) viewHome.findViewById(R.id.my_recycler_view);
        // ini cuman buat dekorasi bikin separator doang
        //postingRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        postingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //updateUI();
        return viewHome;
    }

    // INNER CLASS - bikin holder buat event pas list nya di klik
    private class PostingHolder extends RecyclerView.ViewHolder{
        private Posting posting;
        public TextView textJudul, textIsiPreview;

        public PostingHolder(View itemView) {
            super(itemView);

            textJudul = (TextView) itemView.findViewById(R.id.text_judul);
            textIsiPreview = (TextView) itemView.findViewById(R.id.text_isi_preview);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    // ini nanti bisa di ganti untuk jalanin activity untuk tampilin isi postingan nya
                    Toast.makeText(getActivity(),
                            posting.getTitle() + " clicked!", Toast.LENGTH_SHORT)
                            .show();
                    // pindah dari fragment home ke fragment posting nya..untuk ngeliat detail nya
                    //Fragment viewPostingFragment = new ViewPostingFragment();
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    //fragmentTransaction.replace(R.id.frame_container, viewPostingFragment);

                    // passing argument, jadi masukin fragment nya kayak gini..
                    fragmentTransaction.replace(R.id.frame_container, ViewPostingFragment.newInstance(
                            posting.getTitle(),
                            posting.getBody()
                    ));

                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }

        public void bindData(Posting p){
            posting = p;
            textJudul.setText(p.getTitle());
            textIsiPreview.setText(p.getBody().substring(0, Math.min(p.getBody().length(), 10)));  // 10 karakter pertama, buat preview ajah
        }
    }

    // INNER CLASS - bikin adapter untuk item  nya.. ini akan jalanin PostingHolder dan nge return item list nya kayaknya
    private class PostingAdapter extends RecyclerView.Adapter<PostingHolder>{
        private ArrayList<Posting> posting;

        public PostingAdapter(ArrayList<Posting> p){
            posting = p;
        }

        @Override
        public PostingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.posting_list_item, parent, false);

            return new PostingHolder(view);
        }

        @Override
        public void onBindViewHolder(PostingHolder holder, int position) {
            Posting p = posting.get(position);
            holder.bindData(p);
        }

        @Override
        public int getItemCount() {
            return posting.size();
        }
    }

    private void updateUI(){
        //postingAdapter = new PostingAdapter(listPosting);
        System.out.println("listposting length: "+ getListPosting().size());
        postingAdapter = new PostingAdapter(getListPosting());
        postingRecyclerView.setAdapter(postingAdapter);
    }

}
