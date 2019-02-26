package co.luthfillahmafazi.projectfootballmvp.UI.Favorite;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.luthfillahmafazi.projectfootballmvp.Model.TeamItem;
import co.luthfillahmafazi.projectfootballmvp.R;
import co.luthfillahmafazi.projectfootballmvp.UI.Teams.TeamsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment implements FavoriteContract.View {


    //@BindView(R.id.rv_favorite)
    RecyclerView rvFavorite;
    //@BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    Unbinder unbinder;
    private FavoritePresenter favoritePresenter = new FavoritePresenter(this);

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
       // unbinder = ButterKnife.bind(this, view);

        // jika kita menggunakn onOptionsMenuSelected maka cara menampilkan nya dengan cara :

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        favoritePresenter.getDataListTeams(getContext());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rvFavorite = getView().findViewById(R.id.rv_favorite);
        swipeRefresh = getView().findViewById(R.id.swipe_refresh);
        setHasOptionsMenu(true);

        favoritePresenter.getDataListTeams(getContext());
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                favoritePresenter.getDataListTeams(getContext());
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void showDataList(List<TeamItem> teamItemList) {
        rvFavorite.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFavorite.setAdapter(new TeamsAdapter(getContext(), teamItemList));
    }

    @Override
    public void showFailureMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideRefresh() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // Memasukan menu ke fragment
        inflater.inflate(R.menu.search, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                favoritePresenter.searchTeams(getContext(),s);
                return true;
            }
        });
    }
}
