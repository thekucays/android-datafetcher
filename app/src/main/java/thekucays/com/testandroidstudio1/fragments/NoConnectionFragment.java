package thekucays.com.testandroidstudio1.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thekucays.com.testandroidstudio1.R;

/**
 * Created by thekucays on 7/5/16.
 */
public class NoConnectionFragment extends Fragment {
    public NoConnectionFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View viewNoKoneksi = inflater.inflate(R.layout.nokoneksi_layout, container, false);
        return viewNoKoneksi;
    }
}
