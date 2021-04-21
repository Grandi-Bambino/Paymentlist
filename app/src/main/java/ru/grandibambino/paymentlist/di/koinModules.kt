package ru.grandibambino.paymentlist.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.grandibambino.paymentlist.presentation.fragments.AuthViewModel
import ru.grandibambino.paymentlist.presentation.fragments.PaymentsViewModel
import ru.grandibambino.paymentlist.repository.RemoteProvider
import ru.grandibambino.paymentlist.repository.Repository
import ru.grandibambino.paymentlist.repository.RepositoryImpl
import ru.grandibambino.paymentlist.repository.retrofit.RetrofitRemoteProvider
import ru.grandibambino.paymentlist.utils.REPOSITORY
import ru.grandibambino.paymentlist.utils.RETROFIT

val repoModules = module {
    single<RemoteProvider>(named(RETROFIT)) { RetrofitRemoteProvider() }
    single<Repository>(named(REPOSITORY)) { RepositoryImpl(get(named(RETROFIT))) }
}

val viewModelModules = module {
    viewModel { AuthViewModel(get(named(REPOSITORY))) }
    viewModel { PaymentsViewModel(get(named(REPOSITORY))) }
}