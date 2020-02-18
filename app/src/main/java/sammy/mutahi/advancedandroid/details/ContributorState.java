package sammy.mutahi.advancedandroid.details;

import androidx.annotation.Nullable;

import com.google.auto.value.AutoValue;

import java.util.List;

import sammy.mutahi.advancedandroid.model.Contributor;

@AutoValue
public abstract class ContributorState {
    abstract boolean loading();

    @Nullable
    abstract List<Contributor> contributors();

    @Nullable
    abstract Integer errorRes();

    boolean isSuccess() {
        return errorRes() == null;
    }

    static Builder builder() {
        return new AutoValue_ContributorState.Builder();
    }

    @AutoValue.Builder
    abstract static class Builder {

        abstract Builder loading(boolean loading);

        abstract Builder contributors(List<Contributor> contributors);

        abstract Builder errorRes(Integer errorRes);

        abstract ContributorState build();
    }
}
