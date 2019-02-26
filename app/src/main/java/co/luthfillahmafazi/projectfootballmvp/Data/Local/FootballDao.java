package co.luthfillahmafazi.projectfootballmvp.Data.Local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomDatabase;

import java.util.List;

import co.luthfillahmafazi.projectfootballmvp.Model.TeamItem;

@Dao
public interface FootballDao {

    @Insert
    void insertItem(TeamItem teamItem);

    @Query("SELECT * FROM teams WHERE idTeam = :id ")
    TeamItem selectedItem(String id);

    @Delete
    void delete(TeamItem teamItem);

    @Query("SELECT * FROM teams ORDER BY strTeam ASC")
    List<TeamItem> selectFavorite();
}
