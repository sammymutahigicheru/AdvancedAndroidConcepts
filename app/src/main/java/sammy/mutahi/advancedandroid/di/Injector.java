package sammy.mutahi.advancedandroid.di;

import android.app.Activity;

import javax.inject.Inject;

import sammy.mutahi.advancedandroid.base.BaseActivity;

public class Injector {
    private Injector(){

    }

    public static void inject(Activity activity){
        ActivityInjector.get(activity).inject(activity);
    }

    public static void clearComponent(Activity activity) {
        ActivityInjector.get(activity).clear(activity);
    }
}
