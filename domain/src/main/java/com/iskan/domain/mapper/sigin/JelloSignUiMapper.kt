package com.iskan.domain.mapper.sigin

import com.iskan.domain.mapper.base.Mapper
import com.iskan.domain.model.domain.JelloSigninDomainModel
import com.iskan.domain.model.ui.JelloSignInUiModel

class JelloSignUiMapper : Mapper<JelloSigninDomainModel, JelloSignInUiModel> {
    override fun to(t: JelloSigninDomainModel): JelloSignInUiModel {
        return JelloSignInUiModel(
            code = t.code,
            message = t.message,
            name = t.name,
            address = t.address,
            token = t.token,
            profil = t.profil,
        )
    }


}