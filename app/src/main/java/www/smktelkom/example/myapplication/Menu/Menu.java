package www.smktelkom.example.myapplication.Menu;

import com.google.gson.annotations.SerializedName;

public class Menu {
    @SerializedName("id_menu")
    private int id_menu;
    @SerializedName("nama_menu")
    private String namaMenu;
    @SerializedName("jenis")
    private String jenis;
    @SerializedName("deskripsi")
    private String deskripsi;
    @SerializedName("path")
    private String gambar;
    @SerializedName("harga")
    private String harga;

    public Menu(String namaMenu, String jenis, String deskripsi, String gambar, String harga, int id_menu){
        this.namaMenu = namaMenu;
        this.jenis = jenis;
        this.deskripsi = deskripsi;
        this.gambar = gambar;
        this.harga = harga;
        this.id_menu = id_menu;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public String getJenis() {
        return jenis;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getGambar() {
        return gambar;
    }

    public String getHarga() {
        return harga;
    }

    public int getId_menu() {
        return id_menu;
    }

}