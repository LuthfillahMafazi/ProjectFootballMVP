package co.luthfillahmafazi.projectfootballmvp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamResponse {

    @SerializedName("teams")
    private List<TeamItem> teams;

    public List<TeamItem> getTeams() {
        return teams;
    }
}
