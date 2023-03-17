package www.smktelkom.example.myapplication.IniBaruTransaksi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import www.smktelkom.example.myapplication.Menu.Menu;

public class DetailTransaksiModel {
    @SerializedName("barang")
    private List<Menu> barang;

    @SerializedName("id_meja")
    private Integer idMeja;

    @SerializedName("message")
    private String message;

    @SerializedName("nama_pelanggan")
    private String namaPelanggan;

    @SerializedName("status")
    private String status;

    @SerializedName("tgl_transaksi")
    private String tglTransaksi;

    @SerializedName("total_harga")
    private Integer totalHarga;

    @SerializedName("user")
    private Integer user;

    public List<Menu> getBarang() {
        return barang;
    }

    public void setBarang(List<Menu> barang) {
        this.barang = barang;
    }

    public Integer getIdMeja() {
        return idMeja;
    }

    public void setIdMeja(Integer idMeja) {
        this.idMeja = idMeja;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTglTransaksi() {
        return tglTransaksi;
    }

    public void setTglTransaksi(String tglTransaksi) {
        this.tglTransaksi = tglTransaksi;
    }

    public Integer getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(Integer totalHarga) {
        this.totalHarga = totalHarga;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }
}