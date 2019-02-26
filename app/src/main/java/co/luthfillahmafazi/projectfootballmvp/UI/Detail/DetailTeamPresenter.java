package co.luthfillahmafazi.projectfootballmvp.UI.Detail;

import android.content.Context;
import android.os.Bundle;

import java.util.List;

import co.luthfillahmafazi.projectfootballmvp.Data.Local.FootballDatabase;
import co.luthfillahmafazi.projectfootballmvp.Model.TeamItem;
import co.luthfillahmafazi.projectfootballmvp.Utils.Constant;

public class DetailTeamPresenter implements DetailTeamContract.Presenter {
    private final DetailTeamContract.View view;
    private FootballDatabase footballDatabase;
    private List<TeamItem> teamItemList;

    public DetailTeamPresenter(DetailTeamContract.View view) {
        this.view = view;
    }

    @Override
    public void getDetailTeam(Bundle bundle) {
        if (bundle != null){
            TeamItem teamItem = (TeamItem) bundle.getSerializable(Constant.KEY_DATA);
            view.showDetailTeam(teamItem);
        }else {
            view.showFailureMessage("Data Kosong");
        }
    }

    @Override
    public void addToFavorite(Context context, TeamItem teamItem) {
        footballDatabase = FootballDatabase.getFootballDatabase(context);
        footballDatabase.footballDao().insertItem(teamItem);
        view.showSuccesMessage("Tersimpan");
    }

    @Override
    public void removeFavorite(Context context, TeamItem teamItem) {
        footballDatabase = FootballDatabase.getFootballDatabase(context);
        footballDatabase.footballDao().delete(teamItem);
        view.showSuccesMessage("Terhapus");
    }

    @Override
    public Boolean checkFavorite(Context context, TeamItem teamItem) {
        Boolean bFavorite = false;
        footballDatabase = FootballDatabase.getFootballDatabase(context);

        return bFavorite = footballDatabase.footballDao().selectedItem(teamItem.getIdTeam()) != null;
    }
}
