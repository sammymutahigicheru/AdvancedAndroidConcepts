package sammy.mutahi.advancedandroid.data;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import sammy.mutahi.advancedandroid.model.Repo;

public class RepoRequester {
    private final RepoService repoService;
    @Inject
    RepoRequester(RepoService repoService){

        this.repoService = repoService;
    }
    public Single<List<Repo>> getTrendingRepos(){
        return repoService.getTrendingRepos()
                .map(trendingReposResponse -> trendingReposResponse.repos())
                .subscribeOn(Schedulers.io());
    }

    public Single<Repo> getRepo(String owner,String name ){
        return repoService.getRepo(owner,name)
                .subscribeOn(Schedulers.io());
    }
}
