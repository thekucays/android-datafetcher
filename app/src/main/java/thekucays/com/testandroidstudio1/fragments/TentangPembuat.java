package thekucays.com.testandroidstudio1.fragments;

//import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thekucays.com.testandroidstudio1.R;

/**
 * Created by thekucays on 7/7/16.
 */
public class TentangPembuat extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.tentang_aplikasi_layout, container, false);
    }
}
