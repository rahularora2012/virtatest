package com.rahularora.virtatest.api.methods

import com.rahularora.virtatest.utils.GsonUtils
import com.rahularora.virtatest.viewmodel.MainViewModel
import org.koin.dsl.module

var appModule = module {
    single { GsonUtils() }
    factory { MainViewModel(get()) }
}