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
    protected ApplicationComponent applicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = initComponent();
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

    protected ApplicationComponent initComponent(){
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ActivityInjector getActivityInjector() {
        return activityInjector;
    }
}
