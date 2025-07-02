package com.iskan.domain.usecase

import com.iskan.domain.mapper.sigin.JelloSignUiMapper
import com.iskan.domain.model.base.DomainResource
import com.iskan.domain.model.base.UiState
import com.iskan.domain.model.ui.JelloSignInUiModel
import com.iskan.domain.repository.JelloRepository
import com.iskan.domain.utils.mapTo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostJelloSignInUseCase @Inject constructor(
    private val repository: JelloRepository
) {

    operator fun invoke(
        email: String,
        password: String
    ): Flow<UiState<JelloSignInUiModel>> = flow {
        emit(UiState.OnLoading())

        when (val result = repository.postSignIn(email, password)) {
            is DomainResource.OnError -> {
                emit(UiState.OnFailed(domainResourceError = result.domainResourceError))
            }
            is DomainResource.OnSuccess -> {
                val dataResult = result.data
                emit(UiState.OnSuccess(dataResult.mapTo(JelloSignUiMapper())))
            }
            else -> Unit
        }
    }

}