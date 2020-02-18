package sammy.mutahi.advancedandroid.data;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class TestRepoServiceModule {
    //provide our repo service module
    @Binds
    abstract RepoService bindRepoService(TestRepoService repoService);
}
