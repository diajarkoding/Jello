package com.iskan.remote.mapper.home

import com.iskan.domain.mapper.base.Mapper
import com.iskan.domain.model.domain.JelloHomeDomainModel
import com.iskan.remote.model.home.JelloHomeResponse
import com.iskan.remote.model.signin.JelloBaseResponse

/**
 * Mapper untuk mengkonversi Response Model (dari API) ke Domain Model
 *
 * Mapper Pattern digunakan untuk:
 * - Memisahkan layer Remote/API dari Domain
 * - Transformasi data dari format API ke format yang dibutuhkan business logic
 * - Menghindari coupling antara API response dengan domain model
 *
 * Flow: API Response → Domain Model → UI Model
 *
 * @see Mapper Base interface untuk mapping
 */
class JelloHomeDomainMapper : Mapper<JelloBaseResponse<JelloHomeResponse>, JelloHomeDomainModel> {

    /**
     * Fungsi utama untuk convert dari Response ke Domain Model
     *
     * @param t Response dari API yang dibungkus JelloBaseResponse
     * @return JelloHomeDomainModel yang sudah di-transform
     *
     * Menggunakan safe call (?.) dan elvis operator (?:) untuk handle null:
     * - Jika data null, return list kosong
     * - Mencegah NullPointerException
     */
    override fun to(t: JelloBaseResponse<JelloHomeResponse>): JelloHomeDomainModel =
        JelloHomeDomainModel(
            // Map list header, jika null return empty list
            header = t.data?.header?.map { it.toHeaderDomain() } ?: listOf(),
            // Map list body, jika null return empty list
            body = t.data?.body?.map { it.toBodyDomain() } ?: listOf(),
        )

    /**
     * Extension function untuk convert HeaderResponse ke HeaderDomainModel
     *
     * Extension function membuat kode lebih readable dan reusable
     * Dipanggil untuk setiap item dalam list header
     *
     * orEmpty() digunakan untuk:
     * - Menghindari null values
     * - Memastikan domain model selalu punya value (tidak null)
     */
    fun JelloHomeResponse.HeaderResponse.toHeaderDomain(): JelloHomeDomainModel.HeaderDomainModel {
        return JelloHomeDomainModel.HeaderDomainModel(
            id = this.id ?: 0,  // Default 0 jika id null
            image = this.image.orEmpty(),  // Default empty string jika image null
        )
    }

    /**
     * Extension function untuk convert BodyResponse ke BodyDomainModel
     *
     * Body berisi title dan list data
     * Nested data juga di-map menggunakan extension function
     */
    fun JelloHomeResponse.BodyResponse.toBodyDomain(): JelloHomeDomainModel.BodyDomainModel {
        return JelloHomeDomainModel.BodyDomainModel(
            title = this.title.orEmpty(),
            // Recursive mapping untuk nested data
            data = this.data?.map { it.toDataDomain() } ?: listOf(),
        )
    }

    /**
     * Extension function untuk convert nested DataResponse ke DataDomainModel
     *
     * Menangani level paling dalam dari response structure
     * Setiap data item punya id dan image
     */
    fun JelloHomeResponse.BodyResponse.DataResponse.toDataDomain(): JelloHomeDomainModel.BodyDomainModel.DataDomainModel {
        return JelloHomeDomainModel.BodyDomainModel.DataDomainModel(
            id = this.id ?: 0,
            image = this.image.orEmpty(),
        )
    }
}