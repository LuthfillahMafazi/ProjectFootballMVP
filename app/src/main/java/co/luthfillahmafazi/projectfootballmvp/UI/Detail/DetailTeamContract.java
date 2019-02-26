package co.luthfillahmafazi.projectfootballmvp.UI.Detail;

import android.content.Context;
import android.os.Bundle;

import co.luthfillahmafazi.projectfootballmvp.Model.TeamItem;

public interface DetailTeamContract {
    interface View{
        void showDetailTeam(TeamItem teamItem);
        void showFailureMessage(String msg);
        void showSuccesMessage(String msg);
    }
    interface Presenter{
        void getDetailTeam(Bundle bundle);
        void addToFavorite(Context context, TeamItem teamItem);
        void removeFavorite(Context context, TeamItem teamItem);
        Boolean checkFavorite(Context context, TeamItem teamItem);
    }
}
