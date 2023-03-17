package www.smktelkom.example.myapplication.Meja;

import com.google.gson.annotations.SerializedName;

public class Meja {
    @SerializedName("id_meja")
    private int idMeja;
    @SerializedName("nomor_meja")
    private String nomorMeja;
    @SerializedName("available")
    private int available;

    public Meja(int idMeja, String nomorMeja, int available) {
        this.idMeja = idMeja;
        this.nomorMeja = nomorMeja;
        this.available = available;
    }

    public int getIdMeja() {
        return idMeja;
    }

    public String getNomorMeja() {
        return nomorMeja;
    }

    public int getAvailable() {
        return available;
    }
}
