package com.android.sounddigitvisualiser.di

import android.app.Application
import com.android.sounddigitvisualiser.data.local.AppDatabase
import com.android.sounddigitvisualiser.data.repository.AnimationRepositoryImpl
import com.android.sounddigitvisualiser.domain.repository.AnimationRepository
import com.android.sounddigitvisualiser.domain.usecase.DeleteAllParameters
import com.android.sounddigitvisualiser.domain.usecase.DeleteParametersPreset
import com.android.sounddigitvisualiser.domain.usecase.GetAllParameters
import com.android.sounddigitvisualiser.domain.usecase.LoadParametersPreset
import com.android.sounddigitvisualiser.domain.usecase.SaveParametersPreset
import com.android.sounddigitvisualiser.presentation.viewmodel.AnimationViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.factory
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase.getDatabase(get()) }
    single { get<AppDatabase>().parametersDao() }
    single<AnimationRepository> { AnimationRepositoryImpl(get()) }
    factory<GetAllParameters> { GetAllParameters(get()) }
    factory<SaveParametersPreset> { SaveParametersPreset(get()) }
    factory<LoadParametersPreset> { LoadParametersPreset(get()) }
    factory<DeleteParametersPreset> { DeleteParametersPreset(get()) }
    factory<DeleteAllParameters> {DeleteAllParameters(get())}
    viewModel<AnimationViewModel> {
        AnimationViewModel(
            getAllParameters = get(),
            saveParametersPreset = get(),
            loadParametersPreset = get(),
            deleteParametersPreset = get(),
            deleteAllParameters = get()
        )
    }
}

class AnimationApp : Application() {
    override fun onCreate() {
        super.onCreate()
        applicationContext
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@AnimationApp)
            modules(appModule)
        }
    }
}