package rowdy.utsa.group3.rowdyradio;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rowdy.utsa.group3.rowdyradio.model.LoginPOJO;
import rowdy.utsa.group3.rowdyradio.model.UserUpdatePOJO;

/**
 *
 */
public interface RowdyRadioService {
    //user endpoints
    @POST("user/create")
    Call<LoginPOJO> createAccount(@Query("name") String name, @Query("email") String email, @Query("password") String password);

    @POST("user/login")
    Call<LoginPOJO> login(@Query("name") String name, @Query("password") String password);

    @POST("user/update")
    Call<UserUpdatePOJO> updateInfo(@Query("id") String id, @Query("password") String password,
                                    @Query("name") String name, @Query("email") String email,
                                    @Query("newpwd") String newPassword);


}
