package com.anhariasrilgmail.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import Response.ResponseNamaGuru;
import Network.ApiServer;
import Network.BaseApiList;
import Response.ResponseGetNama;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuruActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView tvGuru;
    TextView tvNip;
    String kodeGuru = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guru);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            kodeGuru = extras.getString("kodeGuru");
            Toast.makeText(GuruActivity.this, "dari Login:"+kodeGuru, Toast.LENGTH_SHORT).show();
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);

        tvGuru = (TextView) headerView.findViewById(R.id.tv_nama_guru);
        tvNip = (TextView) headerView.findViewById(R.id.tv_nip_guru);
        loadGuru(kodeGuru);
    }
    private void loadGuru(String kodeGuru){
        BaseApiList apiservice = ApiServer.getClient().create(BaseApiList.class);
        Call<ResponseNamaGuru> call = apiservice.getnamaGuru(kodeGuru);
        call.enqueue((new Callback<ResponseNamaGuru>() {
            @Override
            public void onResponse(Call<ResponseNamaGuru> call, Response<ResponseNamaGuru> response) {
                if(response.code() == 200){
                    Log.d("TAG", "onResponse: "+response.body().getHasil().size());
                    for (int i = 0; i < response.body().getHasil().size() ; i++) {
                        tvGuru.setText(response.body().getHasil().get(i).getNmGuru());
                        tvNip.setText(response.body().getHasil().get(i).getNIP());
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseNamaGuru> call, Throwable t) {

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

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.guru, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_informasi) {
            // Handle the camera action
        } else if (id == R.id.nav_ulangan) {
            Intent nilai = new Intent(GuruActivity.this,NilaiActivity.class);
            startActivity(nilai);

        } else if (id == R.id.nav_absen) {
            Intent absen = new Intent(GuruActivity.this, AbsenActivity.class);
            startActivity(absen);

        }else if (id == R.id.nav_ubah) {
            Intent ubah = new Intent(GuruActivity.this, UbahPasswordGuruActivity.class);
            ubah.putExtra("kodeGuru",kodeGuru);
            startActivity(ubah);

        }else if (id == R.id.nav_logout) {
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
