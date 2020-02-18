package sammy.mutahi.advancedandroid.details;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import sammy.mutahi.advancedandroid.di.ScreenScope;

@ScreenScope
@Subcomponent
public interface  RepoDetailsComponent extends AndroidInjector<RepoDetailsController> {
    //create a builder
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<RepoDetailsController>{
        /*
        * what dagger knows is that it can provide Strings with this qualifiers
        *
        * */
        @BindsInstance
        public abstract void buildRepoOwner(@Named("repo_owner") String repoOwner);

        @BindsInstance
        public abstract void buildRepoName(@Named("repo_name") String repoName);

        /*
        * call the methods inorder to set values for them
        *
        * */

        @Override
        public void seedInstance(RepoDetailsController instance) {
            buildRepoOwner(instance.getArgs().getString(RepoDetailsController.REPO_OWNER_KEY));
            buildRepoName(instance.getArgs().getString(RepoDetailsController.REPO_NAME_KEY));
        }
    }
}
