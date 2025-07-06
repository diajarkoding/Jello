package com.iskan.remote.repository

import com.iskan.domain.model.base.DomainResource
import com.iskan.domain.model.domain.JelloHomeDomainModel
import com.iskan.domain.model.domain.JelloSigninDomainModel
import com.iskan.domain.repository.JelloRepository
import com.iskan.remote.mapper.sigin.JelloHomeDomainMapper
import com.iskan.remote.mapper.sigin.JelloSignInDomainMapper
import com.iskan.remote.services.JelloService
import com.iskan.remote.utils.handleApiCall
import javax.inject.Inject

class JelloRepositoryImpl @Inject constructor(
    private val jelloService: JelloService
) : JelloRepository {
    override suspend fun postSignIn(
        email: String,
        password: String
    ): DomainResource<JelloSigninDomainModel> {
        return handleApiCall(
            apiServiceTransform = {
              jelloService.postSignIn(
                  email, password
              )
            },
            mapperToDomain = JelloSignInDomainMapper()
        )
    }

    override suspend fun fetchHome(token: String): DomainResource<JelloHomeDomainModel> {
        return handleApiCall(
            apiServiceTransform = {
                jelloService.getHome(
                    token = token
                )
            },
            mapperToDomain = JelloHomeDomainMapper()
        )
    }


}