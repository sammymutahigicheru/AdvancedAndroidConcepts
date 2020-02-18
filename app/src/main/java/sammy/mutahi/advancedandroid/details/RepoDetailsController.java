package sammy.mutahi.advancedandroid.details;

import android.os.Bundle;

import com.bluelinelabs.conductor.Controller;

import sammy.mutahi.advancedandroid.R;
import sammy.mutahi.advancedandroid.base.BaseController;

public class RepoDetailsController extends BaseController {

    static final String REPO_NAME = "repo_name";
    static final String REPO_OWNER = "repo_owner";

    public static Controller newInstance(String repoName,String repoOwner){
        Bundle bundle = new Bundle();
        bundle.putString(REPO_NAME,repoName);
        bundle.putString(REPO_OWNER,repoOwner);
        return new RepoDetailsController(bundle);

    }
    public RepoDetailsController(Bundle bundle){
        super(bundle);
    }
    @Override
    protected int layoutRes() {
        return R.layout.screen_repo_details;
    }
}
