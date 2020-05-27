@file:Suppress("AndroidUnresolvedRoomSqlReference")

package ferit.bozidarkelava.myapplication2.interfaces

import androidx.room.*
import ferit.bozidarkelava.myapplication2.InspiringPerson

//prirucnik str 70
@Dao
interface InspiringPersonDao {

    @Insert
    fun insert(inspiringPerson: InspiringPerson)

    @Delete
    fun delete(inspiringPerson: InspiringPerson)

    @Update
    fun update(inspiringPerson: InspiringPerson)

    //nekakav unersolved problem
    @Query("SELECT * FROM inspiringPersons")
    fun selectAll(): List<InspiringPerson>
    //nekakav unersolved problem
    @Query ("SELECT * FROM inspiringPersons WHERE id = :id")
    fun selectId(id: Int): InspiringPerson
    //nekakav unresolved problem
    @Query ("SELECT MAX(id) FROM inspiringPersons")
    fun selectMaxElement(): Int
}