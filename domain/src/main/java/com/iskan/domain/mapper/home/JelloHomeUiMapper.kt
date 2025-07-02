package com.iskan.domain.mapper.home

import com.iskan.domain.mapper.base.Mapper
import com.iskan.domain.model.domain.JelloHomeDomainModel
import com.iskan.domain.model.ui.JelloHomeUiModel

class JelloHomeUiMapper : Mapper<JelloHomeDomainModel, JelloHomeUiModel> {
    override fun to(t: JelloHomeDomainModel): JelloHomeUiModel =
        JelloHomeUiModel(
            header = t.header.map { it.toHeaderUiModel() } ,
            body = t.body.map { it.toBodyUiModel() } ,
        )

    fun JelloHomeDomainModel.HeaderDomainModel.toHeaderUiModel(): JelloHomeUiModel.HeaderUiModel {
        return JelloHomeUiModel.HeaderUiModel(
            id = this.id,
            image = this.image,
        )
    }

    fun JelloHomeDomainModel.BodyDomainModel.toBodyUiModel(): JelloHomeUiModel.BodyUiModel {
        return JelloHomeUiModel.BodyUiModel(
            title = this.title,
            data = this.data.map { it.toDataUiModel() },
        )
    }

    fun JelloHomeDomainModel.BodyDomainModel.DataDomainModel.toDataUiModel(): JelloHomeUiModel.BodyUiModel.DataUiModel {
        return JelloHomeUiModel.BodyUiModel.DataUiModel(
            id = this.id,
            image = this.image,
        )
    }

}