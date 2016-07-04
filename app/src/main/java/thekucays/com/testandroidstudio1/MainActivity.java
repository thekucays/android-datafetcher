package thekucays.com.testandroidstudio1;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import thekucays.com.testandroidstudio1.fragments.HomeFragment;
import thekucays.com.testandroidstudio1.fragments.NoConnectionFragment;
import thekucays.com.testandroidstudio1.fragments.TentangFragment;
import thekucays.com.testandroidstudio1.helpers.ConnectionTester;

public class MainActivity extends AppCompatActivity {
    // ini buat nyimpen posisi dari navigation drawer nya
    private final int home = 0;
    private final int tentang = 1;
    private final int keterangan = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // first things first, cek koneksi nya..kalo egga ada, tampilin warning, lalu tutup
        boolean connection = ConnectionTester.getInstance().isConnectionAvailable(getApplicationContext());
        if(!connection){
            displayView(keterangan);
        }
        else {
            // pertama dibuka tampilin home
            displayView(home);
        }

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // ini parent nya..liat activity_main.xml nya
        DrawerLayout drawer =  (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // navigation view nya..di dalem nya ada header(nav_header_main) sama isinya (activity_main_drawer)
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                // ini id dari menu navigasi yang ada di activity_main_drawer.xml
                int id = menuItem.getItemId();

                if(id == R.id.nav_preferences){
                    displayView(home);
                }
                else if(id == R.id.nav_about){
                    displayView(tentang);
                }

                DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    // ini buat nge ganti isi dari fragment nya
    private void displayView(int position){
        Fragment fragment = null;
        switch(position){
            case home:
                fragment = new HomeFragment();
                break;
            case tentang:
                fragment = new TentangFragment();
                break;
            case keterangan:
                fragment = new NoConnectionFragment();
                break;
            default:
                break;
        }

        if(fragment != null){
            // ini pakek yang dari android.app
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .commit();
        }
        else{
            Log.e("Fragment Changer", "gagal mengganti fragment nya");
        }
    }

    @Override
    public void onBackPressed(){
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // typo dari tutorial? karena yang di tutorial "R.menu.main"
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        return super.onOptionsItemSelected(item);
    }
}
