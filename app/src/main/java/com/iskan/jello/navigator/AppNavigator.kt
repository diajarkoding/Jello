package com.iskan.jello.navigator

import android.content.Context
import android.content.Intent
import com.iskan.navigator.Navigator
import com.iskan.auth.MainActivity
import com.iskan.home.HomeActivity
import javax.inject.Inject


class AppNavigator @Inject constructor() : Navigator {
    override fun navigateToFeatureHome(context: Context) {
        val intent = Intent(context, HomeActivity::class.java)
        context.startActivity(intent)
    }

    override fun navigateToFeatureAuth(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }
}