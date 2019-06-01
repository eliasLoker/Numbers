package com.example.myplaceinfo.app.inject

import android.app.Application
import com.example.myplaceinfo.app.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by Alexandr Mikhalev on 01.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@AppScope
@Component(modules = [FragmentBuilder::class, RoomModule::class, AppModule::class, AndroidSupportInjectionModule::class])
interface AppComponent: AndroidInjector<App> {

    override fun inject(app: App)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}