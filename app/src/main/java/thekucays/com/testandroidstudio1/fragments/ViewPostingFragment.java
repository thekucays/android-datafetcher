package thekucays.com.testandroidstudio1.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import thekucays.com.testandroidstudio1.R;
import thekucays.com.testandroidstudio1.pojos.Posting;

/**
 * Created by thekucays on 6/17/16.
 */
public class ViewPostingFragment extends Fragment{

    private String isi, judul;
    private TextView textJudul;
    private EditText textIsi;

    public ViewPostingFragment(){

    }

    // karena gabisa modify constructor, alternatif nya begini
    // .. refer to http://stackoverflow.com/questions/10450348/do-fragments-really-need-an-empty-constructor
    public static final ViewPostingFragment newInstance(String judul, String isi){
        ViewPostingFragment viewPostingFragment = new ViewPostingFragment();

        Bundle bundle = new Bundle(2);
        bundle.putString("isi", isi);
        bundle.putString("judul", judul);

        viewPostingFragment.setArguments(bundle);
        return viewPostingFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        // ambil parameter yang dimasukin pake newInstance() disini..
        judul = getArguments().getString("judul");
        isi = getArguments().getString("isi");

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View viewTentang = inflater.inflate(R.layout.view_posting_layout, container, false);

        // set ke text nya
        textIsi = (EditText) viewTentang.findViewById(R.id.text_isi);
        textJudul = (TextView) viewTentang.findViewById(R.id.text_judul);

        textIsi.setText(isi);
        textJudul.setText(judul);

        return viewTentang;
    }
}
