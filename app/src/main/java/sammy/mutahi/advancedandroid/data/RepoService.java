package sammy.mutahi.advancedandroid.data;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import sammy.mutahi.advancedandroid.model.Repo;

public interface RepoService {
//    singles emit only one item or an error
    @GET("search/repositories?q=language:java&order=desc&sort=stars")
    Single<TrendingReposResponse> getTrendingRepos();

    @GET("repos/{owner}/{name}")
    Single<Repo> getRepo(@Path("owner") String owner,@Path("name") String name);
}
