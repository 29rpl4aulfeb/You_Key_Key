
package www.smktelkom.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.GenericLifecycleObserver;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//import androidx.lifecycle.Observer;
import www.smktelkom.example.myapplication.IniBaruTransaksi.Checkout;
import www.smktelkom.example.myapplication.IniBaruTransaksi.MenuRepository;
import www.smktelkom.example.myapplication.Menu.Menu;
import www.smktelkom.example.myapplication.Menu.MenuListAdapter;
import www.smktelkom.example.myapplication.Menu.MenuValue;

public class MenuActivity extends AppCompatActivity {
    private RecyclerView recycleMenu;
    private MenuListAdapter menuListAdapter;
    private List<Menu> inimenuList;
    public ExtendedFloatingActionButton btnCheckout;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnCheckout = findViewById(R.id.btnCheckout);
        Intent yeah = getIntent();
        token = MenuRepository.token.getValue();
//        token = yeah.getStringExtra("gataw");
        Log.d("miawww", "onCreate: "+token);

        recycleMenu = findViewById(R.id.recyclerView);
        recycleMenu.setHasFixedSize(true);
        recycleMenu.setLayoutManager(new LinearLayoutManager(this));
        inimenuList = new ArrayList<>();

        menuListAdapter = new MenuListAdapter(MenuActivity.this, inimenuList, this::observeSelectedMenu);
        recycleMenu.setAdapter(menuListAdapter);

        Log.d("MenuActivity", "Setting logging level to verbose");
        Log.d("MenuActivity", "Debug logs will now appear in the logcat");
        Log.d("MenuActivity", "Attempting to get menu list...");

        getMenu();


        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, Checkout.class).putExtra("YaGataw", token);
                startActivity(intent);
            }
        });
    }

    public void observeSelectedMenu() {
        MenuRepository.keranjang.observe(MenuActivity.this, it -> {
            Log.d("FragmentMenu", "observeSelectedMenu: " + it.size());
            Log.d("FragmentMenu", "btnCheckout visibility: " + btnCheckout.getVisibility());
//                MenuActivity.this().getLifecycleScope().launch(Dispatchers.getMain(), () -> {
            runOnUiThread(() -> {
                if (!it.isEmpty()) {
                    btnCheckout.setVisibility(View.VISIBLE);
                } else {
                    btnCheckout.setVisibility(View.GONE);
                }
                btnCheckout.setText("Checkout (" + it.size() + ") Item");
            });

        });

    }
    private void getMenu() {

        Call<MenuValue> call = ApiClient.getuserService().getMenu("Bearer "+token);
        call.enqueue(new Callback<MenuValue>() {
            @Override
            public void onResponse(Call<MenuValue> call, Response<MenuValue> response) {
                if (response.isSuccessful()){
                    MenuValue menuValue = response.body();
                    Log.d("menu value", "onResponse: "+menuValue);
                    List<Menu> menus = menuValue.getValue();
                    List<Menu> menuList = new ArrayList<>();

                    for (Menu menu : menus){
                        menuList.add(menu);
                    }
                    inimenuList.addAll(menuList);
                    menuListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MenuValue> call, Throwable t) {
                Log.e(TAG,"Failed to get menu list: "+t.getMessage());
                Toast.makeText(MenuActivity.this, "Failed to get menu list", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

