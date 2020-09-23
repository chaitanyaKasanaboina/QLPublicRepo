package ql.com.publicrepos.dagger.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ql.com.publicrepos.network.RetrofitBuilder;
import ql.com.publicrepos.service.RepoService;
import ql.com.publicrepos.viewmodel.RepoViewModel;

@Module
public class RepoServiceModule {

    @Provides
    @Singleton
    RepoService provideRepoService() {
        return RetrofitBuilder.INSTANCE.getRetroInstance().create(RepoService.class);
    }
}
