package com.example.cleanarchitecture;


import com.example.core.AppHash;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class ApplicationModule {

    @Provides
    @AppHash
    String provideHash(){
        return String.valueOf(hashCode());
    }
}
