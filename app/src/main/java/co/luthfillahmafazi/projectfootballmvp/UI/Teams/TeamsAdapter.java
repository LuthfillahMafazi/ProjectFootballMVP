package co.luthfillahmafazi.projectfootballmvp.UI.Teams;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.luthfillahmafazi.projectfootballmvp.Model.TeamItem;
import co.luthfillahmafazi.projectfootballmvp.R;
import co.luthfillahmafazi.projectfootballmvp.UI.Detail.DetailTeamActivity;
import co.luthfillahmafazi.projectfootballmvp.Utils.Constant;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.ViewHolder> {

    private final Context context;
    private final List<TeamItem> teamItemList;

    public TeamsAdapter(Context context, List<TeamItem> teamItemList) {
        this.context = context;
        this.teamItemList = teamItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_team, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final TeamItem teamItem = teamItemList.get(i);

        RequestOptions requestOptions = new RequestOptions().error(R.drawable.ic_broken_image).placeholder(R.drawable.ic_broken_image);
        Glide.with(context).load(teamItem.getStrTeamBadge()).apply(requestOptions).into(viewHolder.imgLogoTeam);
        viewHolder.txtNameTeam.setText(teamItem.getStrTeam());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, DetailTeamActivity.class).putExtra(Constant.KEY_DATA, teamItem));            }
        });
    }

    @Override
    public int getItemCount() {
        return teamItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_logo_team)
        ImageView imgLogoTeam;
        @BindView(R.id.txt_name_team)
        TextView txtNameTeam;
        @BindView(R.id.card_view)
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this.itemView);
            imgLogoTeam = itemView.findViewById(R.id.img_logo_team);
            txtNameTeam = itemView.findViewById(R.id.txt_name_team);
        }
    }
}
