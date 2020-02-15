package sammy.mutahi.advancedandroid.base;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bluelinelabs.conductor.Controller;

import sammy.mutahi.advancedandroid.di.Injector;

public abstract class BaseController extends Controller {
    private boolean injected = false;

    @Override
    protected void onContextAvailable(@NonNull Context context) {
        //controllers are maintained accross allconfiguration changes,so this method can be called more than once.
        //this make sure we dont waste anytime injecting more than once
        if (!injected){
            Injector.inject(this);
            injected = true;
        }
        super.onContextAvailable(context);
    }
}
