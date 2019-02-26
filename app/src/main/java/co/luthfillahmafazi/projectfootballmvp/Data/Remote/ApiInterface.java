package co.luthfillahmafazi.projectfootballmvp.Data.Remote;

import co.luthfillahmafazi.projectfootballmvp.Model.TeamResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("search_all_teams.php")
    Call<TeamResponse> getAllTeams(@Query("s") String s, @Query("c") String c);
}
