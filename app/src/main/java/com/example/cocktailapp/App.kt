package com.example.cocktailapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App() :
    Application()
// эта аннотация нужна для того, чтобы объявить Хилту,
// что здесь стартует наше приложение (именно этот класс инициализируется первым при запуске приложения)