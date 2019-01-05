package com.anhariasrilgmail.login;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import Response.ResponseGetKelas;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import HasilResponse.HasilItem;
import HasilResponse.HasilJadwal;
import HasilResponse.HasilNilaiOrangtua;
import Network.ApiServer;
import Network.BaseApiList;
import Response.ResponseJadwal;
import View.RecyclerViewJadwal;
import View.RecyclerViewNilaiorangtua;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalActivity extends AppCompatActivity {
RecyclerView recyclerView;
    Context mContext;
    //String kodeNIs = null;
    TextView tvHari;
    Spinner spnKelas;
    String selectedHari;
    String selectedKelas;
    Spinner spnHari;
    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);
        mContext = this;
        spnKelas = (Spinner) findViewById(R.id.spinner_kelas);
        spnHari = (Spinner) findViewById(R.id.spinner_hari);
        tvHari = (TextView) findViewById(R.id.tv_hari);
        SpinerKelas();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_jadwal);
        btnBack = (ImageButton) findViewById(R.id.btn_back_input_nilai);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        spnKelas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                selectedKelas = parent.getItemAtPosition(i).toString();
                jadwal(selectedKelas,selectedHari);
                Toast.makeText(mContext, "Kelas Terpilih " + selectedKelas, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnHari.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedHari = adapterView.getItemAtPosition(i).toString();
                jadwal(selectedKelas,selectedHari);
                tvHari.setText(selectedHari);
                Toast.makeText(mContext, "Hari " + selectedHari, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void jadwal( String kodeKelas,  String hari){
        BaseApiList apiService = ApiServer.getClient().create(BaseApiList.class);
        Call<ResponseJadwal> call = apiService.getreadJadwal(kodeKelas,hari);
        call.enqueue((new Callback<ResponseJadwal>() {
            @Override
            public void onResponse(Call<ResponseJadwal> call, Response<ResponseJadwal> response) {
                Log.d("TAG", "onResponse: "+response.code());
                if(response.code() == 200){
                    List<HasilJadwal> hasilJadwal = response.body().getHasil();
                    Log.d("TAG", "onResponse: "+hasilJadwal.size());
                    RecyclerViewJadwal recyclerViewJadwal = new RecyclerViewJadwal(hasilJadwal,spnKelas.getSelectedItem().toString(),tvHari.toString(),JadwalActivity.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(JadwalActivity.this));
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(recyclerViewJadwal);
                }else{
                    Toast.makeText(mContext,"Gagal",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseJadwal> call, Throwable t) {

            }
        }));

    }
    /*private void initSpinerKelas(){
        BaseApiList apiService = ApiServer.getClient().create(BaseApiList.class);
        Call<ResponseGetKelas> call = apiService.getreadkelas();
        call.enqueue((new Callback<ResponseGetKelas>() {
            @Override
            public void onResponse(Call<ResponseGetKelas> call, retrofit2.Response<ResponseGetKelas> response) {
                if(response.code() == 200){
                    List<HasilItem> hasilItems = response.body().getHasil();
                    List<String> listspinner = new ArrayList<String>();
                    for (int i = 0; i < response.body().getHasil().size(); i++) {
                        listspinner.add(hasilItems.get(i).getKdKelas());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,listspinner);
                    spnKelas.setAdapter(adapter);
                }else{
                    Toast.makeText(mContext,"Gagal",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetKelas> call, Throwable throwable) {

            }
        }));
    }*/

    private void SpinerKelas(){
        BaseApiList apiService = ApiServer.getClient().create(BaseApiList.class);
        Call<ResponseGetKelas> call = apiService.getreadkelas();
        call.enqueue((new Callback<ResponseGetKelas>() {
            @Override
            public void onResponse(Call<ResponseGetKelas> call, retrofit2.Response<ResponseGetKelas> response) {
                Log.d("TAG", "onResponse: "+response.body().getHasil().size());
                if(response.code() == 200){
                    List<HasilItem> hasilItems = response.body().getHasil();
                    List<String> listspinner = new ArrayList<String>();
                    for (int i = 0; i < response.body().getHasil().size(); i++) {
                        listspinner.add(hasilItems.get(i).getKdKelas());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,listspinner);
                    spnKelas.setAdapter(adapter);
                }else{
                    Toast.makeText(mContext,"Gagal",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetKelas> call, Throwable throwable) {

            }
        }));
    }

}
