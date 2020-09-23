package ql.com.publicrepos.dagger.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ql.com.publicrepos.viewmodel.LoginViewModel
import ql.com.publicrepos.viewmodel.RepoViewModel
import ql.com.publicrepos.viewmodel.ViewModelKey

@Module
abstract class ViewModelModules {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepoViewModel::class)
    abstract fun bindRepoViewModel(repoViewModel: RepoViewModel): ViewModel
}