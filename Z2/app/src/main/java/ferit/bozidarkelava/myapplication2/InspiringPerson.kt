package ferit.bozidarkelava.myapplication2

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity (tableName = "inspiringPersons")
class InspiringPerson(@PrimaryKey val id: Int = 0,
                      @ColumnInfo(name="name") val name: String,
                      @ColumnInfo(name="lastName") val lastName: String,
                      @ColumnInfo(name="birthday") val birthday: String,
                      @ColumnInfo(name="description") val description: String,
                      @ColumnInfo(name="image") val image: String,
                      @ColumnInfo(name="quotes") val quotes: String) {
//empty
}