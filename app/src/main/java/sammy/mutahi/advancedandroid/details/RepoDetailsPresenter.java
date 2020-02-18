package sammy.mutahi.advancedandroid.details;

import javax.inject.Inject;
import javax.inject.Named;

import sammy.mutahi.advancedandroid.data.RepoRepository;
import sammy.mutahi.advancedandroid.di.ScreenScope;

@ScreenScope
public class RepoDetailsPresenter {
    @Inject
    RepoDetailsPresenter(
            @Named("repo_owner") String repoOwner,
            @Named("repo_name") String repoName,
            RepoRepository repoRepository,
            RepoDetailsViewModel viewModel) {
        repoRepository.getRepo(repoOwner, repoName)
                .doOnSuccess(viewModel.processRepo())
                .doOnError(viewModel.detailsError())
                .flatMap(repo -> repoRepository.getContributors(repo.contributorsUrl())
                        .doOnError(viewModel.contributorsError()))
                .subscribe(viewModel.processContributors(), throwable -> {
                    // We handle logging in the view model
                });
    }
}
