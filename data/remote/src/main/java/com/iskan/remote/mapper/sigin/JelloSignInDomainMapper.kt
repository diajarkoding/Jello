package com.iskan.remote.mapper.sigin

import com.iskan.domain.mapper.base.Mapper
import com.iskan.domain.model.domain.JelloSigninDomainModel
import com.iskan.remote.model.signin.JelloBaseResponse
import com.iskan.remote.model.signin.JelloSignInResponse

class JelloSignInDomainMapper : Mapper<JelloBaseResponse<JelloSignInResponse>, JelloSigninDomainModel> {
    override fun to(t: JelloBaseResponse<JelloSignInResponse>): JelloSigninDomainModel =
        JelloSigninDomainModel(
            code = t.code ?: 0,
            message = t.message.orEmpty(),
            name = t.data?.name.orEmpty(),
            address = t.data?.address.orEmpty(),
            token = t.data?.token.orEmpty(),
            profil = t.data?.profil.orEmpty(),
        )

}