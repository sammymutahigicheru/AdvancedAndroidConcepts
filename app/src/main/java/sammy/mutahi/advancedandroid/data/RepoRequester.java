package sammy.mutahi.advancedandroid.data;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import sammy.mutahi.advancedandroid.model.Contributor;
import sammy.mutahi.advancedandroid.model.Repo;

public class RepoRequester {
    private final RepoService repoService;
    @Inject
    RepoRequester(RepoService repoService){

        this.repoService = repoService;
    }
   Single<List<Repo>> getTrendingRepos(){
        return repoService.getTrendingRepos()
                .map(trendingReposResponse -> trendingReposResponse.repos());
    }

   Single<Repo> getRepo(String owner,String name ){
        return repoService.getRepo(owner,name);
    }
    Single<List<Contributor>> getContributors(String url){
        return repoService.getContributors(url);
    }
}
