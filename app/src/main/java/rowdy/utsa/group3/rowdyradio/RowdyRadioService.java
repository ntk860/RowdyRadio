package rowdy.utsa.group3.rowdyradio;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

/**
 *
 */
public interface RowdyRadioService {
    @POST("user/create")
    Call<StreamInfoPOJO> createUser(@Field("name") String username, @Field("email") String email, @Field("password") String password);

}
