package com.iskan.remote.repository

import com.iskan.domain.model.base.DomainResource
import com.iskan.domain.model.domain.JelloHomeDomainModel
import com.iskan.domain.model.domain.JelloSigninDomainModel
import com.iskan.domain.repository.JelloRepository
import com.iskan.remote.mapper.home.JelloHomeDomainMapper
import com.iskan.remote.mapper.sigin.JelloSignInDomainMapper
import com.iskan.remote.services.JelloService
import com.iskan.remote.utils.handleApiCall
import javax.inject.Inject

/**
 * Implementasi konkret dari JelloRepository interface
 *
 * Repository Pattern bertugas:
 * - Abstraksi sumber data (API, Database, Cache)
 * - Koordinasi antara data sources
 * - Transform data dari Remote ke Domain model
 *
 * Clean Architecture layers:
 * Remote Layer (API) → Repository → Domain Layer
 *
 * @constructor
 * @param jelloService Retrofit service untuk API calls
 */
class JelloRepositoryImpl @Inject constructor(
    private val jelloService: JelloService
) : JelloRepository {

    /**
     * Implementasi sign in
     *
     * @param email Email untuk login
     * @param password Password untuk login
     * @return DomainResource berisi hasil sign in atau error
     *
     * handleApiCall adalah utility function yang:
     * 1. Melakukan API call dengan error handling
     * 2. Transform response ke domain model menggunakan mapper
     * 3. Wrap hasil dalam DomainResource (Success/Error)
     */
    override suspend fun postSignIn(
        email: String,
        password: String
    ): DomainResource<JelloSigninDomainModel> {
        return handleApiCall(
            // Lambda expression untuk API call
            apiServiceTransform = {
                jelloService.postSignIn(email, password)
            },
            // Mapper untuk transform response ke domain
            mapperToDomain = JelloSignInDomainMapper()
        )
    }

    /**
     * Implementasi fetch home data
     *
     * @param token Bearer token untuk autentikasi
     * @return DomainResource berisi home data atau error
     *
     * Flow:
     * 1. Call API dengan token
     * 2. handleApiCall process response
     * 3. Map ke domain model
     * 4. Return wrapped result
     */
    override suspend fun fetchHome(token: String): DomainResource<JelloHomeDomainModel> {
        return handleApiCall(
            apiServiceTransform = {
                jelloService.getHome(token = token)
            },
            mapperToDomain = JelloHomeDomainMapper()
        )
    }
}