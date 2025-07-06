package com.iskan.domain.mapper.home

import com.iskan.domain.mapper.base.Mapper
import com.iskan.domain.model.domain.JelloHomeDomainModel
import com.iskan.domain.model.ui.JelloHomeUiModel

/**
 * Mapper untuk mengkonversi Domain Model ke UI Model
 *
 * UI Model adalah model yang siap digunakan di UI layer:
 * - Format data sesuai kebutuhan tampilan
 * - Tidak ada logic business
 * - Immutable dan simple
 *
 * Keuntungan memisahkan UI Model:
 * 1. UI tidak tergantung pada domain structure
 * 2. Mudah menambah computed properties untuk UI
 * 3. Testing UI jadi lebih mudah
 */
class JelloHomeUiMapper : Mapper<JelloHomeDomainModel, JelloHomeUiModel> {

    /**
     * Convert Domain Model ke UI Model
     *
     * Tidak perlu null checking karena domain model sudah clean
     * (null handling sudah dilakukan di JelloHomeDomainMapper)
     */
    override fun to(t: JelloHomeDomainModel): JelloHomeUiModel =
        JelloHomeUiModel(
            header = t.header.map { it.toHeaderUiModel() },
            body = t.body.map { it.toBodyUiModel() },
        )

    /**
     * Convert Header Domain ke UI
     * Simple mapping tanpa transformasi
     */
    fun JelloHomeDomainModel.HeaderDomainModel.toHeaderUiModel(): JelloHomeUiModel.HeaderUiModel {
        return JelloHomeUiModel.HeaderUiModel(
            id = this.id,
            image = this.image,
        )
    }

    /**
     * Convert Body Domain ke UI
     * Including nested data conversion
     */
    fun JelloHomeDomainModel.BodyDomainModel.toBodyUiModel(): JelloHomeUiModel.BodyUiModel {
        return JelloHomeUiModel.BodyUiModel(
            title = this.title,
            data = this.data.map { it.toDataUiModel() },
        )
    }

    /**
     * Convert nested Data Domain ke UI
     */
    fun JelloHomeDomainModel.BodyDomainModel.DataDomainModel.toDataUiModel(): JelloHomeUiModel.BodyUiModel.DataUiModel {
        return JelloHomeUiModel.BodyUiModel.DataUiModel(
            id = this.id,
            image = this.image,
        )
    }
}