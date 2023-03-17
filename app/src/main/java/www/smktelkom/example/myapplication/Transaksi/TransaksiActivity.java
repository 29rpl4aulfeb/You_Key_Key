package www.smktelkom.example.myapplication.Transaksi;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.smktelkom.example.myapplication.ApiClient;
import www.smktelkom.example.myapplication.IniBaruTransaksi.MenuRepository;
import www.smktelkom.example.myapplication.Menu.Menu;
import www.smktelkom.example.myapplication.Menu.MenuListAdapter;
import www.smktelkom.example.myapplication.Menu.MenuValue;
import www.smktelkom.example.myapplication.MenuActivity;
import www.smktelkom.example.myapplication.R;

public class TransaksiActivity extends AppCompatActivity {
    private RecyclerView recycleTransaksi;
    private TransaksiListAdapter transaksiListAdapter;
    private List<Transaksi> initransaksiList;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);
        Intent ulala = getIntent();
        token = MenuRepository.token.getValue();//ulala.getStringExtra("lebihGataw");
        Log.d("miaww", "onCreate: "+token);

        recycleTransaksi = findViewById(R.id.recyclerViewTransaksi);
        recycleTransaksi.setHasFixedSize(true);
        recycleTransaksi.setLayoutManager(new LinearLayoutManager(this));
        initransaksiList = new ArrayList<>();

        transaksiListAdapter = new TransaksiListAdapter(TransaksiActivity.this, initransaksiList);
        recycleTransaksi.setAdapter(transaksiListAdapter);

        Log.d("TransaksiActivity", "Setting logging level to verbose");
        Log.d("TransaksiActivity", "Debug logs will now appear in the logcat");
        Log.d("TransaksiActivity", "Attempting to get transaksi list...");

        getTransaksi();
    }
    private void getTransaksi() {
        Call<TransaksiValue> call = ApiClient.getuserService().getTransaksi("Bearer "+token);
        call.enqueue(new Callback<TransaksiValue>() {
            @Override
            public void onResponse(Call<TransaksiValue> call, Response<TransaksiValue> response) {
                if (response.isSuccessful()){
                    TransaksiValue transaksiValue = response.body();
                    Log.d("transaksi value", "onResponse: "+transaksiValue);
                    List<Transaksi> transaction = transaksiValue.getValue();
                    List<Transaksi> transaksiList = new ArrayList<>();

                    for (Transaksi transaksi : transaction){
                        transaksiList.add(transaksi);
                    }
                    initransaksiList.addAll(transaksiList);
                    Collections.reverse(initransaksiList);
                    transaksiListAdapter.setData(initransaksiList);
//                    transaksiListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<TransaksiValue> call, Throwable t) {
                Log.e(TAG,"Failed to get transaksi list: "+t.getMessage());
                Toast.makeText(TransaksiActivity.this, "Failed to get transaksi list", Toast.LENGTH_SHORT).show();
            }
        });
    }
    }
