package www.smktelkom.example.myapplication.IniBaruTransaksi;

import android.util.Log;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.smktelkom.example.myapplication.ApiClient;
import www.smktelkom.example.myapplication.BasicResponse;
import www.smktelkom.example.myapplication.Meja.Meja;
import www.smktelkom.example.myapplication.Meja.MejaValue;
import www.smktelkom.example.myapplication.Menu.Menu;

public class MenuRepository {
    public static MutableLiveData<BasicResponse> addMejaResponse = new MutableLiveData<>();
    private static MutableLiveData<Menu> addMenu = new MutableLiveData<>();
    public static MutableLiveData<DetailTransaksiModel> detailTransaksi = new MutableLiveData<>();
    public static MutableLiveData<ArrayList<Menu>> keranjang = new MutableLiveData<>(new ArrayList<>());
    public static MutableLiveData<List<Meja>> listMeja = new MutableLiveData<>(new ArrayList<>());
    public static MutableLiveData<String> token = new MutableLiveData<>(new String());

    public static void addToKeranjang(Menu menuModel) {
        if (keranjang.getValue().size() < 10) {
            keranjang.getValue().add(menuModel);
            Log.d("MenuRepository", "observeSelectedMenu: " + keranjang.getValue().size());
        }
    }

    public static void clearKeranjang() {
        keranjang.getValue().clear();
        Log.d("MenuRepository", "observeSelectedMenu: " + keranjang.getValue().size());
    }

    public static void removeFromKeranjang(Menu menuModel) {
        keranjang.getValue().remove(menuModel);
        Log.d("MenuRepository", "observeSelectedMenu: " + keranjang.getValue().size());
    }


    public static LiveData<DetailTransaksiModel> getDetailTransaksi(String token, int idTransaksi) {
        Call<DetailTransaksiModel> call = ApiClient.getuserService().getTransaksiById("Bearer "+token, idTransaksi);
        call.enqueue(new Callback<DetailTransaksiModel>(){
            @Override
            public void onResponse(Call<DetailTransaksiModel> call, Response<DetailTransaksiModel> response) {
                if(response.isSuccessful()){
                 detailTransaksi.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<DetailTransaksiModel> call, Throwable t) {

            }
        });

        return detailTransaksi;
    }

    public static LiveData<Menu> addMenu(String token, Menu menuModel) {
        Call<Menu> call = ApiClient.getuserService().addMenu("Bearer "+token, menuModel.getNamaMenu(), menuModel.getHarga(), menuModel.getJenis(), menuModel.getDeskripsi());
        call.enqueue(new Callback<Menu>() {
            @Override
            public void onResponse(Call<Menu> call, Response<Menu> response) {
                if(response.isSuccessful()){
                    addMenu.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Menu> call, Throwable t) {

            }
        });
        return addMenu;
    }

    public static void addMeja(String token, String namaMeja) {
        Call<BasicResponse> call = ApiClient.getuserService().addMeja("Bearer " + token, namaMeja);
        call.enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                if (response.isSuccessful()){
                    addMejaResponse.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BasicResponse> call, Throwable t) {

            }
        });
    }

//    public static LiveData<ListMenuResponse> getAllMenus(String token) {
//        MenuRemoteDataSource.getListMenu(token);
//        return MenuRemoteDataSource.getListMenuLiveData();
//    }
//
//    public static LiveData<MenuModel> getMenuById(String token, int id_menu) {
//        MenuRemoteDataSource.getMenuById(token, id_menu);
//        return MenuRemoteDataSource.getMenuLiveData();
//    }

//    public static LiveData<MenuModel> addMenu(String token, MenuModel menuModel) {
//        MenuRemoteDataSource.addMenu(token, menuModel);
//        return MenuRemoteDataSource.getMenuLiveData();
//    }
//
//    public static LiveData<MenuModel> edtMenu(String token, MenuModel menuModel) {
//        MenuRemoteDataSource.edtMenu(token, menuModel);
//        return MenuRemoteDataSource.getMenuLiveData();
//    }
}
