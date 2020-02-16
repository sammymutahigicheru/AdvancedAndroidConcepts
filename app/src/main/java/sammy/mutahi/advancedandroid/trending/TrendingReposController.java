package sammy.mutahi.advancedandroid.trending;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import sammy.mutahi.advancedandroid.R;
import sammy.mutahi.advancedandroid.base.BaseController;

public class TrendingReposController extends BaseController {
    @Inject TrendingReposPresenter presenter;
    @Inject TrendingReposViewModel viewModel;
    @BindView(R.id.repo_list)
    RecyclerView repoList;
    @BindView(R.id.loading_indicator)
    View loadingView;
    @BindView(R.id.tv_error)
    TextView errorText;
    @Override
    protected int layoutRes() {
        return R.layout.screen_trending_repos;
    }

    @Override
    protected void onViewBound(View view) {
        repoList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        repoList.setAdapter(new RepoAdapter(presenter));
    }

    @Override
    protected Disposable[] subscriptions() {
       return new Disposable[]{
               viewModel.loading()
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(loading ->{
                   loadingView.setVisibility(loading ? View.VISIBLE : View.GONE);
                   repoList.setVisibility(loading ?View.GONE : View.VISIBLE);
                   errorText.setVisibility(loading ?View.GONE : View.VISIBLE);
               }),
               viewModel.repos()
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(((RepoAdapter)repoList.getAdapter())::setData),
               viewModel.error()
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(errorRes ->{
                   if (errorRes == -1){
                       errorText.setText(null);
                       errorText.setVisibility(View.GONE);
                   }else{
                       errorText.setVisibility(View.VISIBLE);
                       repoList.setVisibility(View.GONE);
                       errorText.setText(errorRes);
                   }

               })
       };
    }
}
