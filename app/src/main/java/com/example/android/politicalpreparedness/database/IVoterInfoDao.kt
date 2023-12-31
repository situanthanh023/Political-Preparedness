package com.example.android.politicalpreparedness.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.politicalpreparedness.network.models.VoterInfo

@Dao
interface IVoterInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(voterInfo: VoterInfo)

    @Query("SELECT * FROM ${DatabaseConstants.VOTER_INFO_TABLE_NAME} WHERE id = :id")
    suspend fun get(id: Int): VoterInfo?
}