package co.luthfillahmafazi.projectfootballmvp.UI.Teams;

import android.telecom.Call;

import co.luthfillahmafazi.projectfootballmvp.Data.Remote.ApiClient;
import co.luthfillahmafazi.projectfootballmvp.Data.Remote.ApiInterface;
import co.luthfillahmafazi.projectfootballmvp.Model.TeamResponse;
import co.luthfillahmafazi.projectfootballmvp.Utils.Constant;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamsPresenter implements TeamsContract.Presenter {

    private final TeamsContract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public TeamsPresenter(TeamsContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataListTeams() {
        view.showProgress();

        retrofit2.Call<TeamResponse> call = apiInterface.getAllTeams(Constant.S, Constant.C);
        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(retrofit2.Call<TeamResponse> call, Response<TeamResponse> response) {
                view.hideProgress();

                if (response.body() != null){
                    view.showDataList(response.body().getTeams());
                }else {
                    view.showFailureMessage("Data Kosong");
                }
            }

            @Override
            public void onFailure(retrofit2.Call<TeamResponse> call, Throwable t) {
                view.hideProgress();
                view.showFailureMessage(t.getMessage());
            }
        });
    }

    @Override
    public void getSearchTeams(String searchText) {
        // Mencek apakah inputan dari user ada isinya
        if (!searchText.isEmpty()){
            // apabila ada lakukan request ke API
            view.showProgress();
            retrofit2.Call<TeamResponse> call = apiInterface.getSearchTeams(searchText);
            call.enqueue(new Callback<TeamResponse>() {
                @Override
                public void onResponse(retrofit2.Call<TeamResponse> call, Response<TeamResponse> response) {
                    view.hideProgress();
                    if (response.body().getTeams() != null){
                        view.showDataList(response.body().getTeams());
                    }else {
                        view.showFailureMessage("Data Team Kosong");
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<TeamResponse> call, Throwable t) {
                    view.hideProgress();
                    view.showFailureMessage(t.getMessage());
                }
            });
        }else {
            // Jika data nya kosong maka akan nge load data awall
            getDataListTeams();
        }
    }
}
