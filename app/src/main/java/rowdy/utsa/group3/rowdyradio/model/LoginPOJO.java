package rowdy.utsa.group3.rowdyradio.model;

import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class LoginPOJO {
    @SerializedName("UserId")
    private String userId;
    @SerializedName("Name")
    private String username;
    @SerializedName("Email")
    private String email;

    public LoginPOJO() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
