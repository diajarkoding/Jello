package com.iskan.domain.repository

import com.iskan.domain.model.base.DomainResource
import com.iskan.domain.model.domain.JelloHomeDomainModel
import com.iskan.domain.model.domain.JelloSigninDomainModel

interface JelloRepository {

    /**
     * Post sign in
     * with param email and password
     */
    suspend fun postSignIn(email: String, password: String) : DomainResource<JelloSigninDomainModel>

    /**
     * Get Home with token barear
     */
    suspend fun fetchHome(token: String) : DomainResource<JelloHomeDomainModel>

}