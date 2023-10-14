package com.example.android.politicalpreparedness.repository

import com.example.android.politicalpreparedness.database.ElectionDatabase
import com.example.android.politicalpreparedness.database.SavedElectionDatabase
import com.example.android.politicalpreparedness.network.CivicsApiInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ElectionsRepository(
    private val electionDatabase: ElectionDatabase,
    savedElectionDatabase: SavedElectionDatabase,
    private val api: CivicsApiInstance
) {

    val activeElections = electionDatabase.getAll()
    val savedElections = savedElectionDatabase.getAll()

    suspend fun refreshElections() {
        withContext(Dispatchers.IO) {
            val electionResponse = api.getElections()
            electionDatabase.insertAll(electionResponse.elections)
        }
    }
}