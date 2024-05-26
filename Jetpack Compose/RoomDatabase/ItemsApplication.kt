package com.codexdroid.roomdatabasestudy

import android.app.Application
import com.codexdroid.roomdatabasestudy.room.RepositoryMediator

class ItemsApplication: Application() {

    lateinit var repositoryMediator: RepositoryMediator

    override fun onCreate() {
        super.onCreate()
        repositoryMediator = RepositoryMediator(context = this)
    }
}