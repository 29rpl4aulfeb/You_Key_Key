package www.smktelkom.example.myapplication.Transaksi;

import com.google.gson.annotations.SerializedName;

public class Transaksi {
    @SerializedName("id_transaksi")
    private int id_transaksi;
    @SerializedName("id_meja")
    private int id_meja;
    @SerializedName("nama_pelanggan")
    private String nama_pelanggan;
    @SerializedName("status")
    private String status;
    @SerializedName("tgl_transaksi")
    private String tgl_transaksi;

    public Transaksi(int id_transaksi, int id_meja, String nama_pelanggan, String status, String tgl_transaksi) {
        this.id_transaksi = id_transaksi;
        this.id_meja = id_meja;
        this.nama_pelanggan = nama_pelanggan;
        this.status = status;
        this.tgl_transaksi = tgl_transaksi;
    }

    public int getId_transaksi() {
        return id_transaksi;
    }

    public int getId_meja() {
        return id_meja;
    }

    public String getNama_pelanggan() {
        return nama_pelanggan;
    }

    public String getStatus() {
        return status;
    }

    public String getTgl_transaksi() {
        return tgl_transaksi;
    }

}
