package com.anhariasrilgmail.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import Network.ApiServer;
import Network.BaseApiList;
import Response.ResponseGetNama;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrangtuaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String kodeNIs = null;
    TextView namaProfil;
    TextView nisProfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orangtua);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
     //   loadProfil(kodeNIs);

        /*ActionBar ab = getSupportActionBar();
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);*/


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            kodeNIs = extras.getString("nis");
            Toast.makeText(OrangtuaActivity.this, "dari Login:"+kodeNIs, Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);


        namaProfil = (TextView) headerView.findViewById(R.id.tv_profil_orangtua);
        nisProfil = (TextView) headerView.findViewById(R.id.tv_profil_nis_orangtua);
       loadProfil(kodeNIs);
    }
    private void loadProfil(String nis){
        BaseApiList apiservice = ApiServer.getClient().create(BaseApiList.class);
        Call<ResponseGetNama> call = apiservice.getnamaHadir(nis);
        call.enqueue((new Callback<ResponseGetNama>() {
            @Override
            public void onResponse(Call<ResponseGetNama> call, Response<ResponseGetNama> response) {
                if(response.code() == 200){
                    Log.d("TAG", "onResponse: "+response.body().getHasil().size());
                    for (int i = 0; i < response.body().getHasil().size() ; i++) {
                        namaProfil.setText(response.body().getHasil().get(i).getNama());
                        nisProfil.setText(response.body().getHasil().get(i).getNIS());
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseGetNama> call, Throwable t) {

            }
        }));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_siswa) {
           Intent profil = new Intent(OrangtuaActivity.this,ProfilActivity.class);
            profil.putExtra("nis",kodeNIs);
            startActivity(profil);

        }  else if (id == R.id.nav_raport) {
            Intent raport = new Intent(OrangtuaActivity.this,NilaiOrangtuaActivity.class);
            raport.putExtra("nis",kodeNIs );
            startActivity(raport);

        } else if (id == R.id.nav_absen) {
            Intent absen = new Intent(OrangtuaActivity.this,AbsenOrangtuaActivity.class);
            absen.putExtra("nis",kodeNIs );
            startActivity(absen);

        } else if (id == R.id.nav_ubah) {
            Intent ubah = new Intent(OrangtuaActivity.this,UbahPasswordActivity.class);
            ubah.putExtra("nis",kodeNIs);
            startActivity(ubah);
        } else if (id == R.id.nav_Jadwal) {
            Intent jadwal = new Intent(OrangtuaActivity.this,JadwalActivity.class);
            jadwal.putExtra("nis",kodeNIs );
            startActivity(jadwal);


        } else if (id == R.id.nav_logout) {
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
