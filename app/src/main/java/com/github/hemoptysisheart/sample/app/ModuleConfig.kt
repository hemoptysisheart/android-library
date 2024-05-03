package com.github.hemoptysisheart.sample.app

import com.github.hemoptysisheart.sample.model.SampleModel
import com.github.hemoptysisheart.sample.model.SampleModelImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleConfig {
    @Provides
    @Singleton
    fun provideSampleModel(): SampleModel = SampleModelImpl()
}
