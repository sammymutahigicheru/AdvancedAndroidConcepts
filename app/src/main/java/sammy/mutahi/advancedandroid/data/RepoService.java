package sammy.mutahi.advancedandroid.data;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface RepoService {
//    singles emit only one item or an error
    @GET("search/repositories?q=language:java&order=desc&sort=stars")
    Single<TrendingReposResponse> getTrendingRepos();
}
