package sammy.mutahi.advancedandroid.trending;

import javax.inject.Inject;

import sammy.mutahi.advancedandroid.data.RepoRepository;
import sammy.mutahi.advancedandroid.data.RepoRequester;
import sammy.mutahi.advancedandroid.di.ScreenScope;
import sammy.mutahi.advancedandroid.model.Repo;
import sammy.mutahi.advancedandroid.ui.ScreenNavigator;

@ScreenScope
public class TrendingReposPresenter implements RepoAdapter.RepoClickedListener {
    private final TrendingReposViewModel trendingReposViewModel;
    private RepoRepository repoRepository;
    private ScreenNavigator screenNavigator;

    @Inject
    TrendingReposPresenter(TrendingReposViewModel trendingReposViewModel, RepoRepository repoRepository, ScreenNavigator screenNavigator){

        this.trendingReposViewModel = trendingReposViewModel;
        this.repoRepository = repoRepository;
        this.screenNavigator = screenNavigator;
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
        screenNavigator.goToRepoDetails(repo.owner().login(),repo.name());
    }
}
