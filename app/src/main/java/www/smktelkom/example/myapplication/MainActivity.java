package www.smktelkom.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.smktelkom.example.myapplication.IniBaruTransaksi.MenuRepository;
import www.smktelkom.example.myapplication.Meja.Meja;
import www.smktelkom.example.myapplication.Meja.MejaActivity;
import www.smktelkom.example.myapplication.Meja.MejaValue;
import www.smktelkom.example.myapplication.Menu.TambahMenu;
import www.smktelkom.example.myapplication.Transaksi.Transaksi;
import www.smktelkom.example.myapplication.Transaksi.TransaksiActivity;

public class MainActivity extends AppCompatActivity {
String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
Intent yeah = getIntent();
        token = MenuRepository.token.getValue();
//token = yeah.getStringExtra("token");

        Log.d("lalalala", "yayaya" + token);
        CardView btnMoveActivity = findViewById(R.id.btnMenu);
        btnMoveActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MenuActivity.class).putExtra("gataw", token));
            }
        });

        CardView btnMoveActivityTransaksi = findViewById(R.id.btnTransaksi);
        btnMoveActivityTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TransaksiActivity.class).putExtra("lebihGataw",token));
            }
        });

        CardView btnMoveActivityMeja = findViewById(R.id.btnMeja);
        btnMoveActivityMeja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MejaActivity.class).putExtra("SemaqueenGataw",token));
            }
        });

        CardView btnAddMenu = findViewById(R.id.btnAddMenu);
        btnAddMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TambahMenu.class));
            }
        });

        getMeja();
    }
    private List<Meja> inimejaList = new ArrayList<Meja>();
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
//                    mejaListAdapter.setData(inimejaList);

                }
            }

            @Override
            public void onFailure(Call<MejaValue> call, Throwable t) {
                Log.e(TAG,"Failed to get Meja list: "+t.getMessage());
                Toast.makeText(MainActivity.this, "Failed to get Meja list", Toast.LENGTH_SHORT).show();
            }
        });
    }
}