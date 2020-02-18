package sammy.mutahi.advancedandroid.trending;

import javax.inject.Inject;

import sammy.mutahi.advancedandroid.data.RepoRepository;
import sammy.mutahi.advancedandroid.data.RepoRequester;
import sammy.mutahi.advancedandroid.di.ScreenScope;
import sammy.mutahi.advancedandroid.model.Repo;

@ScreenScope
public class TrendingReposPresenter implements RepoAdapter.RepoClickedListener {
    private final TrendingReposViewModel trendingReposViewModel;
    private RepoRepository repoRepository;

    @Inject
    TrendingReposPresenter(TrendingReposViewModel trendingReposViewModel, RepoRepository repoRepository){

        this.trendingReposViewModel = trendingReposViewModel;
        this.repoRepository = repoRepository;
        //load data upon request
        loadData();
    }

    private void loadData() {
        repoRepository.getTrendingRepos()
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
