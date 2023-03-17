package www.smktelkom.example.myapplication.Meja;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.smktelkom.example.myapplication.ApiClient;
import www.smktelkom.example.myapplication.IniBaruTransaksi.MenuRepository;
import www.smktelkom.example.myapplication.R;
import www.smktelkom.example.myapplication.Transaksi.TransaksiActivity;

public class MejaActivity extends AppCompatActivity {
    private RecyclerView recycleMeja;
    private MejaListAdapter mejaListAdapter;
    private List<Meja> inimejaList;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meja);
        Intent unyanya = getIntent();
        token = MenuRepository.token.getValue();
//        token = unyanya.getStringExtra("SemaqueenGataw");
        Log.d("miaww", "onCreate: "+token);

        recycleMeja = findViewById(R.id.recyclerViewMeja);
        recycleMeja.setHasFixedSize(true);
        recycleMeja.setLayoutManager(new LinearLayoutManager(this));
        inimejaList = new ArrayList<>();

        mejaListAdapter = new MejaListAdapter(MejaActivity.this, inimejaList);
        recycleMeja.setAdapter(mejaListAdapter);

        Log.d("MejaActivity", "Setting logging level to verbose");
        Log.d("MejaActivity", "Debug logs will now appear in the logcat");
        Log.d("MejaActivity", "Attempting to get meja list...");

        getMeja();
    }
    public void getMeja(){
        Call<MejaValue> call = ApiClient.getuserService().getMeja("Bearer "+token);
        call.enqueue(new Callback<MejaValue>(){
            @Override
            public void onResponse(Call<MejaValue> call, Response<MejaValue> response){
                if(response.isSuccessful()){
                    MejaValue mejaValue = response.body();
                    Log.d("meja value", "onResponse: "+response.body());
                    Log.d("meja value", "onResponse OBJ: "+mejaValue.toString());
                    List<Meja> tables = mejaValue.getValue();
                    List<Meja> mejaList = new ArrayList<>();
                    for (Meja meja : tables){
                        mejaList.add(meja);
                    }

                    inimejaList.addAll(mejaList);
                    MenuRepository.listMeja.postValue(inimejaList);
                    mejaListAdapter.setData(inimejaList);

                }
            }

            @Override
            public void onFailure(Call<MejaValue> call, Throwable t) {
                Log.e(TAG,"Failed to get Meja list: "+t.getMessage());
                Toast.makeText(MejaActivity.this, "Failed to get Meja list", Toast.LENGTH_SHORT).show();
            }
        });
    }
}