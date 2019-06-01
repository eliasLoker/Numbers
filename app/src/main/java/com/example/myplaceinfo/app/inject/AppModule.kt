package com.example.myplaceinfo.app.inject

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by Alexandr Mikhalev on 01.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@Module
class AppModule {

    @Provides
    @AppScope
    fun provideContext(application: Application) : Context {
        return application
    }
}