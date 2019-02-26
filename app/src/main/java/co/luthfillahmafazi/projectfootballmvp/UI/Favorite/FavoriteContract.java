package co.luthfillahmafazi.projectfootballmvp.UI.Favorite;

import android.content.Context;

import java.util.List;

import co.luthfillahmafazi.projectfootballmvp.Model.TeamItem;

public interface FavoriteContract {
    interface View{
        void showDataList(List<TeamItem> teamItemList);
        void showFailureMessage(String msg);
        void hideRefresh();
    }
    interface Presenter{
        void getDataListTeams(Context context);
    }
}
