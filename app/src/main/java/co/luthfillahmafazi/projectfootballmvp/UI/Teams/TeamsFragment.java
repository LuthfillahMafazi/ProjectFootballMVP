package co.luthfillahmafazi.projectfootballmvp.UI.Teams;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.luthfillahmafazi.projectfootballmvp.Model.TeamItem;
import co.luthfillahmafazi.projectfootballmvp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamsFragment extends Fragment implements TeamsContract.View {


    @BindView(R.id.rv_teams)
    RecyclerView rvTeams;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    Unbinder unbinder;
    @BindView(R.id.edtSearch)
    EditText edtSearch;
    @BindView(R.id.btnSearch)
    ImageButton btnSearch;
    private ProgressDialog progressDialog;
    private TeamsPresenter teamsPresenter = new TeamsPresenter(this);

    public TeamsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_teams, container, false);
        unbinder = ButterKnife.bind(this, view);

        teamsPresenter.getDataListTeams();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                teamsPresenter.getDataListTeams();
            }
        });

        setUpUIListener();

        return view;
    }

    private void setUpUIListener() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mengambil inputan user yang ada di editText
                String searchText = edtSearch.getText().toString().toLowerCase();
                // Kita kirimkan inputan user ke presenter untuk di request ke API
                teamsPresenter.getSearchTeams(searchText);
            }
        });
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        swipeRefresh.setRefreshing(false);
        progressDialog.dismiss();
    }

    @Override
    public void showDataList(List<TeamItem> teamsItemList) {
        rvTeams.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTeams.setAdapter(new TeamsAdapter(getActivity(), teamsItemList));
    }

    @Override
    public void showFailureMessage(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
