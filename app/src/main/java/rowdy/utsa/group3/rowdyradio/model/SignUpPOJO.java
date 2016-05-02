package rowdy.utsa.group3.rowdyradio.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 *
 */
@Parcel
public class SignUpPOJO {
    @SerializedName("UserId")
    private String userId;

    public SignUpPOJO() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
