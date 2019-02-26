package co.luthfillahmafazi.projectfootballmvp.UI.Favorite;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import co.luthfillahmafazi.projectfootballmvp.Data.Local.FootballDatabase;
import co.luthfillahmafazi.projectfootballmvp.Model.TeamItem;

public class FavoritePresenter implements FavoriteContract.Presenter {

    private final FavoriteContract.View view;
    private FootballDatabase footballDatabase;


    public FavoritePresenter(FavoriteContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataListTeams(Context context) {
        footballDatabase = FootballDatabase.getFootballDatabase(context);
        if (footballDatabase.footballDao().selectFavorite() != null){
            view.showDataList(footballDatabase.footballDao().selectFavorite());
        }else {
            view.showFailureMessage("Tidak ada data di favorite");
        }
        view.hideRefresh();
    }

    @Override
    public void searchTeams(Context context, String searchText) {
        if (!searchText.isEmpty()){
            footballDatabase = FootballDatabase.getFootballDatabase(context);
            // Memasakan semua data favorite ke dalam variable list
            List<TeamItem> teamItemList = footballDatabase.footballDao().selectFavorite();
            // membuat penampung untuk menampung data yang sudah kita seleksi berdasarkan inputan user
            List<TeamItem> mTeamsItemList = new ArrayList<>();
            if (teamItemList != null){
                // Melakukan perulangan untuk mengecek data yang asa di dalam table favorite
                // melakukan peruralangan berdasarkan dengan data di itemList
                for (TeamItem data: teamItemList){
                    // Pengecekan team berdasarkan dengan permintaan user
                    String namaTeam = data.getStrTeam().toLowerCase();
                    if (namaTeam.contains(searchText.toLowerCase())){
                        // Memasukan team yang sama dengan inputan user ke dalam wadah ke dua
                        mTeamsItemList.add(data);
                    }
                }
                // Mengirimkan wadah ke 2 ke view dengan data yang sudah terisi
                view.showDataList(mTeamsItemList);
            }
        }else {
            // Apabila inputan user kosong ambil data tanpa keyword
            getDataListTeams(context);
        }
    }
}
