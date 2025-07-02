package com.iskan.domain.repository

interface PreferenceRepository {

    fun getString(key: String) : String

    fun saveString(key: String, value:String)

}