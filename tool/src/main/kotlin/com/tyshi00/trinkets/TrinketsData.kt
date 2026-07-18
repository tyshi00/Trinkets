package com.tyshi00.trinkets

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

// === Entities ===
/**
 * A single countdown to a date the person cares about (birthday, trip,
 * anniversary, etc). Notes are optional and free-form. Up to
 * [MAX_COUNTDOWNS] can exist at once, enforced in the repository, not here.
 */
@Entity(tableName = "countdown_entries")
data class CountdownEntry(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val date: String, // YYYY-MM-DD, may be in the past (anniversary) or future
    val notes: String? = null,
    val createdAt: Long = System.currentTimeMillis(), // used to keep a stable display order
)

@Entity(tableName = "preferences")
data class PreferenceEntry(
    @PrimaryKey val key: String,
    val value: String,
)

// === DAOs ===
@Dao
interface CountdownDao {
    @Query("SELECT * FROM countdown_entries ORDER BY createdAt ASC")
    suspend fun getAll(): List<CountdownEntry>

    @Query("SELECT COUNT(*) FROM countdown_entries")
    suspend fun count(): Int

    @Insert
    suspend fun insert(entry: CountdownEntry): Long

    @Query("UPDATE countdown_entries SET name = :name, date = :date, notes = :notes WHERE id = :id")
    suspend fun update(id: Long, name: String, date: String, notes: String?)

    @Query("DELETE FROM countdown_entries WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("DELETE FROM countdown_entries")
    suspend fun resetAll()
}

@Dao
interface PreferenceDao {
    @Query("SELECT * FROM preferences WHERE key = :key LIMIT 1")
    suspend fun get(key: String): PreferenceEntry?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun set(entry: PreferenceEntry)

    @Query("DELETE FROM preferences")
    suspend fun resetAll()
}

// === Database ===
@Database(
    entities = [CountdownEntry::class, PreferenceEntry::class],
    version = 1,
    exportSchema = false,
)
abstract class TrinketsDatabase : RoomDatabase() {
    abstract fun countdownDao(): CountdownDao
    abstract fun preferenceDao(): PreferenceDao
}
