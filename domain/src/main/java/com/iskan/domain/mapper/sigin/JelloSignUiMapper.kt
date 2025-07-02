package com.iskan.domain.mapper.sigin

import com.iskan.domain.mapper.base.Mapper
import com.iskan.domain.model.domain.JelloSigninDomainModel
import com.iskan.domain.model.ui.JelloSigninUiModel

class JelloSignUiMapper : Mapper<JelloSigninDomainModel, JelloSigninUiModel> {
    override fun to(t: JelloSigninDomainModel): JelloSigninUiModel {
        return JelloSigninUiModel(
            code = t.code,
            message = t.message,
            name = t.name,
            address = t.address,
            token = t.token,
            profil = t.profil,
        )
    }


}