package com.example.android.politicalpreparedness.repository

import androidx.lifecycle.MutableLiveData
import com.example.android.politicalpreparedness.network.CivicsApiInstance
import com.example.android.politicalpreparedness.representative.model.Representative
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepresentativesRepository(private val api: CivicsApiInstance) {

    val representatives = MutableLiveData<List<Representative>?>()
//    val representatives: LiveData<List<Representative>?>
//        get() = this.representatives

    suspend fun refreshRepresentatives(addressStr: String) {
        withContext(Dispatchers.IO) {
            this@RepresentativesRepository.representatives.postValue(null)
            val representativeResponse = CivicsApiInstance.getRepresentatives(addressStr)

            val representativeList = representativeResponse.offices.flatMap { office ->
                office.getRepresentatives(representativeResponse.officials)
            }

            this@RepresentativesRepository.representatives.postValue(representativeList as MutableList<Representative>?)
        }
    }
}