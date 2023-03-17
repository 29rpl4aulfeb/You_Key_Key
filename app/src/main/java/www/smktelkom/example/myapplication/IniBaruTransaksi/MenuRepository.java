package www.smktelkom.example.myapplication.IniBaruTransaksi;

import android.util.Log;


import androidx.lifecycle.MutableLiveData;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;

import www.smktelkom.example.myapplication.Meja.Meja;
import www.smktelkom.example.myapplication.Menu.Menu;

public class MenuRepository {
//    private static MutableLiveData<Menu> selectedmenu = new MutableLiveData<>();
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
