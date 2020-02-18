package sammy.mutahi.advancedandroid.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import sammy.mutahi.advancedandroid.model.Contributor;
import sammy.mutahi.advancedandroid.model.Repo;

@Singleton
public class RepoRepository {
    /*
    * we will use repo requester
    * but we will put it behind provider
    * this allows it to be garbage collected whenever its no longer needed
    * */
    private final Provider<RepoRequester> repoRequesterProvider;
    private final List<Repo> cachedTrendingRepos = new ArrayList<>();
    private final Map<String,List<Contributor>> cachedContributors = new HashMap<>();

    @Inject
    RepoRepository(Provider<RepoRequester> repoRequesterProvider){

        this.repoRequesterProvider = repoRequesterProvider;
    }
    /*
     * a single returns an item or an error
     * */
    public Single<List<Repo>> getTrendingRepos(){
        /*
         * here check if there are cached repos
         * if there are ,dont hit the api
         * return repos from cache
         * @param concat kind oof helps with that
         * */
        return Maybe.concat(cachedTrendingRepos(),apiTrendingRepos())
                .firstOrError()//converts to a single
                .subscribeOn(Schedulers.io());
    }

    public Single<List<Contributor>> getContributors(String url){
        return Maybe.concat(cachedContributors(url),apiContributors(url))
                .firstOrError()
                .subscribeOn(Schedulers.io());
    }
    private Maybe<List<Contributor>> cachedContributors(String url){
        return Maybe.create(e ->{
            //check if map has value
            if (cachedContributors.containsKey(url)){
                /*
                * call on success and pass the value
                * */
                e.onSuccess(cachedContributors.get(url));
            }
            e.onComplete();
        });
    }
    private Maybe<List<Contributor>> apiContributors(String url){
        return repoRequesterProvider.get().getContributors(url)
                .doOnSuccess(contributors -> cachedContributors.put(url,contributors))
                .toMaybe();
    }
    /*
    * a single repo is also need
    * returned in context of repo owner and name
    * */
    public Single<Repo> getRepo(String repoOwner,String repoName){
        return Maybe.concat(cachedRepo(repoOwner,repoName),apiRepo(repoOwner,repoName))
                .firstOrError()
                .subscribeOn(Schedulers.io());
    }
    private Maybe<Repo> cachedRepo(String repoOwmer,String repoName){
        return Maybe.create(e ->{
            for (Repo cachedRepo:cachedTrendingRepos){
               if (cachedRepo.owner().login().equals(repoOwmer)&&cachedRepo.name().equals(repoName)){
                   e.onSuccess(cachedRepo);
                   break;
               }
               e.onComplete();
            }
        });
    }
    private Maybe<Repo> apiRepo(String repoOwner,String repoName){
        return repoRequesterProvider.get().getRepo(repoOwner,repoName)
                .toMaybe();
    }


    /*
    * maybe can finish without returning an item or an error
    * */
    public Maybe<List<Repo>> cachedTrendingRepos(){
        return Maybe.create(emitter ->{
            if (!cachedTrendingRepos.isEmpty()){
                emitter.onSuccess(cachedTrendingRepos);
            }else{
                emitter.onComplete();
            }
        });
    }
    private Maybe<List<Repo>> apiTrendingRepos(){
        return repoRequesterProvider.get().getTrendingRepos()
                .doOnSuccess(repos -> {
                    cachedTrendingRepos.clear();
                    cachedTrendingRepos.addAll(repos);
                }).toMaybe();
    }

}
