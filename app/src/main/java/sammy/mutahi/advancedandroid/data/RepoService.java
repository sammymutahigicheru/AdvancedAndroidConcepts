package sammy.mutahi.advancedandroid.data;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import sammy.mutahi.advancedandroid.model.Contributor;
import sammy.mutahi.advancedandroid.model.Repo;

public interface RepoService {
//    singles emit only one item or an error
    @GET("search/repositories?q=language:java&order=desc&sort=stars")
    Single<TrendingReposResponse> getTrendingRepos();

    @GET("repos/{owner}/{name}")
    Single<Repo> getRepo(@Path("owner") String owner,@Path("name") String name);

    @GET
    Single<List<Contributor>> getContributors(@Url String url);
}
