package com.anhariasrilgmail.login;
import Network.BaseApiList;
import HasilResponse.HasilItem;
import Response.ResponseGetKelas;
import View.RecylerViewAdapter;
import View.HasilSiswa;
import View.ResponseGetNama;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Network.ApiServer;
import retrofit2.Call;
import retrofit2.Callback;

public class AbsenActivity extends AppCompatActivity {
    RecyclerView listSiswa;
    ProgressDialog progressDialog;
    Spinner spnKelas;
    Context mContext;
    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absen);
        mContext = this;
        progressDialog = new ProgressDialog(this);
        spnKelas = (Spinner) findViewById(R.id.spinner_kelas);
        initSpinerKelas();

        listSiswa = (RecyclerView) findViewById(R.id.list_siswa);
        spnKelas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                String selectedName = parent.getItemAtPosition(i).toString();
                loadData(selectedName);
                Toast.makeText(mContext, "Kelas Terpilih " + selectedName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnBack = (ImageButton) findViewById(R.id.btn_back_input_absen);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
       /* spnMatpel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selecedMatpel = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(mContext, "Mata Pelajaran " + selecedMatpel , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
*/
    }
    public void loadData(String kodeKelas){
       // listSiswa.setLayoutManager(new LinearLayoutManager(this));
        BaseApiList apiService = ApiServer.getClient().create(BaseApiList.class);
        Call<ResponseGetNama> call = apiService.getreadsiswa(kodeKelas);
        call.enqueue(new Callback<ResponseGetNama>() {

            @Override
            public void onResponse(Call<ResponseGetNama> call,retrofit2.Response<ResponseGetNama> response) {
                Log.d("TAG", "onResponse: "+response.code());
                if(response.code() == 200){
                    List<HasilSiswa> hasilSiswa = response.body().getHasil();
                    Log.d("TAG", "onResponse: "+hasilSiswa.size());
                        RecylerViewAdapter recylerViewAdapter = new RecylerViewAdapter(hasilSiswa,AbsenActivity.this);
                    listSiswa.setLayoutManager(new LinearLayoutManager(AbsenActivity.this));
                    listSiswa.setItemAnimator(new DefaultItemAnimator());
                    listSiswa.setAdapter(recylerViewAdapter);
                        //recyclerView.setAdapter(new RecylerViewAdapter(hasilSiswa, R.layout.activity_absen, getApplicationContext()));
                    }else{
                    Toast.makeText(mContext,"Gagal",Toast.LENGTH_SHORT).show();
                }
                }

            @Override
            public void onFailure(Call<ResponseGetNama> call, Throwable t) {
                    Toast.makeText(AbsenActivity.this,"Error bro "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }

        });
    }
    private void initSpinerKelas(){
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
    }

}

