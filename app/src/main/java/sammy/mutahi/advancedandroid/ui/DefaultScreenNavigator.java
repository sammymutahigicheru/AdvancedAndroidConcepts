package sammy.mutahi.advancedandroid.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;

import javax.inject.Inject;

import sammy.mutahi.advancedandroid.details.RepoDetailsController;
import sammy.mutahi.advancedandroid.di.ActivityScope;

@ActivityScope
public class DefaultScreenNavigator implements ScreenNavigator {
    private Router router;
    //use constructor injection
    @Inject
    DefaultScreenNavigator(){}
    @Override
    public void initWithRouter(Router router, Controller rootScreen) {
        this.router = router;
        if (!router.hasRootController()){
            router.setRoot(RouterTransaction.with(rootScreen));
        }
    }

    @Override
    public boolean pop() {
        //returns true if router handled back request
        return router != null && router.handleBack();
    }

    @Override
    public void clear() {
        router = null;
    }

    @Override
    public void goToRepoDetails(String repoOwner, String repoName) {
        if (router != null){
            router.pushController(RouterTransaction.with(RepoDetailsController.newInstance(repoOwner,repoName))
            .pushChangeHandler(new FadeChangeHandler())
            .popChangeHandler(new FadeChangeHandler()));
        }
    }
}
