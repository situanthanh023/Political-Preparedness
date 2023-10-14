package com.example.android.politicalpreparedness.network.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.politicalpreparedness.database.DatabaseConstants

@Entity(tableName = DatabaseConstants.VOTER_INFO_TABLE_NAME)
data class VoterInfo(
    @PrimaryKey val id: Int,
    val stateName: String,
    val votingLocationUrl: String?,
    val ballotInformationUrl: String?
)
