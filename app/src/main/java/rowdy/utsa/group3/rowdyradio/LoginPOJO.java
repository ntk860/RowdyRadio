package rowdy.utsa.group3.rowdyradio;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 *
 */
@Parcel
public class LoginPOJO {
    @SerializedName("name")
    private String name;
    @SerializedName("password")
    private String password;

    public LoginPOJO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
