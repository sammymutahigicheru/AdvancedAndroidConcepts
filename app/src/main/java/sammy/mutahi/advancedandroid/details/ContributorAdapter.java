package sammy.mutahi.advancedandroid.details;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sammy.mutahi.advancedandroid.R;
import sammy.mutahi.advancedandroid.model.Contributor;

class ContributorAdapter extends RecyclerView.Adapter<ContributorAdapter.ContributorViewHolder> {

    private final List<Contributor> data = new ArrayList<>();

    ContributorAdapter() {
        setHasStableIds(true);
    }

    @Override
    public ContributorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_user_list_item, parent, false);
        return new ContributorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContributorViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).id();
    }

    void setData(List<Contributor> contributors) {
        if (contributors != null) {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ContributorDiffCallback(data, contributors));
            data.clear();
            data.addAll(contributors);
            diffResult.dispatchUpdatesTo(this);
        } else {
            data.clear();
            notifyDataSetChanged();
        }
    }

    static final class ContributorViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_user_name)
        TextView usernameText;
        @BindView(R.id.iv_avatar)
        ImageView avatarImageView;

        ContributorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Contributor contributor) {
            usernameText.setText(contributor.login());
            Glide.with(avatarImageView.getContext())
                    .load(contributor.avatarUrl())
                    .into(avatarImageView);
        }
    }
}
