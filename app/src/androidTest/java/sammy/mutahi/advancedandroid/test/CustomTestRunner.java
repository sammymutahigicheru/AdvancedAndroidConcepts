package sammy.mutahi.advancedandroid.test;

import android.app.Application;
import android.content.Context;

import androidx.test.runner.AndroidJUnitRunner;

import sammy.mutahi.advancedandroid.base.TestApplication;

public class CustomTestRunner extends AndroidJUnitRunner {
    /*
    * Tells the app to use our own rather than the main one
    * */
    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return super.newApplication(cl, TestApplication.class.getName(), context);
    }
}
