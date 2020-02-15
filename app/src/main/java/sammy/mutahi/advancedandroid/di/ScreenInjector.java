package sammy.mutahi.advancedandroid.di;

import android.app.Activity;

import com.bluelinelabs.conductor.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.android.AndroidInjector;
import sammy.mutahi.advancedandroid.base.BaseActivity;
import sammy.mutahi.advancedandroid.base.BaseController;

@ActivityScope //if injected more than once use the one we already created
public class ScreenInjector {
    private final Map<Class<? extends Controller>, Provider<AndroidInjector.Factory<? extends Controller>>> screenInjectors;
    private final Map<String,AndroidInjector<Controller>> cache = new HashMap<>();

    @Inject
    ScreenInjector(Map<Class<? extends Controller>, Provider<AndroidInjector.Factory<? extends Controller>>> screenInjectors){

        this.screenInjectors = screenInjectors;
    }
    void inject(Controller controller){
        if (!(controller instanceof BaseController)){
            throw new IllegalArgumentException("Controller Must Extend BaseController");
        }
        String instanceId = controller.getInstanceId();
        if (cache.containsKey(instanceId)){
            cache.get(instanceId).inject(controller);
            return;
        }
        //create injector factory
        AndroidInjector.Factory<Controller> injectorFactory =
                (AndroidInjector.Factory<Controller>) screenInjectors.get(controller.getClass()).get();
        //create injector
        AndroidInjector<Controller> injector = injectorFactory.create(controller);

        //cache the id
        cache.put(instanceId,injector);
        injector.inject(controller);

    }
    void clear(Controller controller){
        cache.remove(controller.getInstanceId());
    }
    static ScreenInjector get(Activity activity){
        if (!(activity instanceof BaseActivity)){
            throw new IllegalArgumentException("Activity Must be extend BaseActivity");
        }
        return ((BaseActivity) activity).getScreenInjector();
    }
}
