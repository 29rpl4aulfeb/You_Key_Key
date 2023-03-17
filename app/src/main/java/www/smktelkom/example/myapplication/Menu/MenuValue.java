package www.smktelkom.example.myapplication.Menu;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import www.smktelkom.example.myapplication.Menu.Menu;

public class MenuValue {
    @SerializedName("values")
    private List<Menu> value;

    public List<Menu> getValue() {
        return value;
    }
}
