package sammy.mutahi.advancedandroid.base;

import android.app.Application;

import javax.inject.Inject;

import dagger.android.DaggerApplication;
import sammy.mutahi.advancedandroid.BuildConfig;
import sammy.mutahi.advancedandroid.di.ActivityInjector;
import timber.log.Timber;

public class MyApplication extends Application {
    //get activity Injector
    @Inject
    ActivityInjector activityInjector;
    private ApplicationComponent applicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);

        if (BuildConfig.DEBUG){
            /*
            * Timber makes use of trees which are just classes
            * to log output
            *
            * */
            Timber.plant(new Timber.DebugTree());
        }
    }

    public ActivityInjector getActivityInjector() {
        return activityInjector;
    }
}
