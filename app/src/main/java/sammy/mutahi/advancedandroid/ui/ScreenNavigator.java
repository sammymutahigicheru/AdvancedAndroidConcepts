package sammy.mutahi.advancedandroid.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;

public interface ScreenNavigator {
    void initWithRouter(Router router, Controller rootScreen);
    boolean pop();//where the screen is actually popped
    void clear();
}
