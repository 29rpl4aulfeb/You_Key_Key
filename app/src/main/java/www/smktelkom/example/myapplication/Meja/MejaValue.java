package www.smktelkom.example.myapplication.Meja;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MejaValue {
    @SerializedName("values")
    private List<Meja> value;

    public List<Meja> getValue() {
        return value;
    }
}
