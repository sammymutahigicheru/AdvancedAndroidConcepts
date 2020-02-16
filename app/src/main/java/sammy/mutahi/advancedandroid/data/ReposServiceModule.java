package sammy.mutahi.advancedandroid.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public abstract class ReposServiceModule {
    @Provides
    @Singleton
    static RepoService provideReposService(Retrofit retrofit){
        return retrofit.create(RepoService.class);
    }
}
