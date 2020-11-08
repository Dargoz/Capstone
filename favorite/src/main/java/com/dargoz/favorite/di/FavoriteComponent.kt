package com.dargoz.favorite.di

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import com.dargoz.capstone.di.FavoriteModuleDependencies
import com.dargoz.favorite.FavoriteActivity
import com.dargoz.favorite.ui.FavoriteFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [FavoriteModuleDependencies::class],
    modules = [FavoriteFragmentModule::class]
)
interface FavoriteComponent {

    fun inject(fragment: FavoriteFragment)


    @Component.Factory
    interface Factory {
        fun create(
            dependentModule: FavoriteModuleDependencies,
            @BindsInstance fragment: Fragment
        ): FavoriteComponent
    }
}