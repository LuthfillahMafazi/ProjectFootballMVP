package co.luthfillahmafazi.projectfootballmvp.UI.Teams;

import java.util.List;

import co.luthfillahmafazi.projectfootballmvp.Model.TeamItem;

public interface TeamsContract {

    interface View{
        void showProgress();
        void hideProgress();
        void showDataList(List<TeamItem> teamsItemList);
        void showFailureMessage(String msg);
    }

    interface Presenter{
        void getDataListTeams();
    }


}
