package rowdy.utsa.group3.rowdyradio;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 *
 */
@Parcel
public class SignUpPOJO {
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public SignUpPOJO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
