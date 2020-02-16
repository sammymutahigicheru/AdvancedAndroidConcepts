package sammy.mutahi.advancedandroid.base;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.Router;

import java.util.UUID;

import javax.inject.Inject;

import sammy.mutahi.advancedandroid.R;
import sammy.mutahi.advancedandroid.di.Injector;
import sammy.mutahi.advancedandroid.di.ScreenInjector;
import sammy.mutahi.advancedandroid.ui.ScreenNavigator;

public abstract class BaseActivity extends AppCompatActivity {
    @Inject
    ScreenInjector screenInjector;
    @Inject
    ScreenNavigator screenNavigator;
    public static final String INSTANCE_ID_KEY = "instance_id";
    public String instaceId;
    //similar to fragment manager in fragments,needs a container
    private Router router;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null){
            instaceId = savedInstanceState.getString(INSTANCE_ID_KEY);
        }else{
            instaceId = UUID.randomUUID().toString();
        }
        Injector.inject(this);
        setContentView(layoutRes());
        //container
        ViewGroup screenContainer = findViewById(R.id.screen_container);
        if (screenContainer == null){
            throw new IllegalArgumentException("Activity Must have a container with id screen_container");
        }
        router = Conductor.attachRouter(this,screenContainer,savedInstanceState);
        screenNavigator.initWithRouter(router,initialScreen());
        monitorBackStack();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(INSTANCE_ID_KEY,instaceId);
    }
    public String getInstanceId(){
        return instaceId;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        screenNavigator.clear();
        if (isFinishing()){
            Injector.clearComponent(this);
        }
    }

    public ScreenInjector getScreenInjector() {
        return screenInjector;
    }

    @LayoutRes
    protected abstract int layoutRes();

    protected abstract Controller initialScreen();

    private void monitorBackStack(){
        router.addChangeListener(new ControllerChangeHandler.ControllerChangeListener() {
            @Override
            public void onChangeStarted(@Nullable Controller to,
                                        @Nullable Controller from,
                                        boolean isPush, @NonNull ViewGroup container,
                                        @NonNull ControllerChangeHandler handler) {

            }

            @Override
            public void onChangeCompleted(@Nullable Controller to,
                                          @Nullable Controller from,
                                          boolean isPush, @NonNull ViewGroup container,
                                          @NonNull ControllerChangeHandler handler) {
                if (!isPush && from != null){
                    Injector.clearComponent(from);
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        //finishes the activity
        if (!screenNavigator.pop()){
            super.onBackPressed();
        }

    }
}
