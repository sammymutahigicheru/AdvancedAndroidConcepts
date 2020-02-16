package sammy.mutahi.advancedandroid.trending;

import javax.inject.Inject;

import sammy.mutahi.advancedandroid.data.RepoRequester;
import sammy.mutahi.advancedandroid.di.ScreenScope;
import sammy.mutahi.advancedandroid.model.Repo;

@ScreenScope
public class TrendingReposPresenter implements RepoAdapter.RepoClickedListener {
    private final TrendingReposViewModel trendingReposViewModel;
    private final RepoRequester repoRequester;

    @Inject
    TrendingReposPresenter(TrendingReposViewModel trendingReposViewModel,RepoRequester repoRequester){

        this.trendingReposViewModel = trendingReposViewModel;
        this.repoRequester = repoRequester;
        //load data upon request
        loadData();
    }

    private void loadData() {
        repoRequester.getTrendingRepos()
                //tell the requester that we are still loading
        .doOnSubscribe(__ ->trendingReposViewModel.loadingUpdated().accept(true))
                //on any event be it data retrieved successfully or throwable
                //set loading to false
        .doOnEvent((data,throwable) -> trendingReposViewModel.loadingUpdated().accept(false))
                .subscribe(trendingReposViewModel.reposUpdated(),trendingReposViewModel.onError());

    }

    @Override
    public void onRepoClicked(Repo repo) {

    }
}
