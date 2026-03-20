package com.example.helloapp.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "vaccinations",
    indices = [
        Index("childId"),
        Index(value = ["name", "childId"], unique = true)
    ]
)
data class Vaccination(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val totalDoses: Int = 1,
    val completedDoses: Int = 0,
    /** Pipe-separated recommended ages per dose, e.g. "At birth|6 weeks|10 weeks|14 weeks" */
    val doseSchedule: String,
    val category: String,
    val lastDoseDate: Long? = null,
    /** 0 = template (schedule definition); >0 = Child.id from Growth tracker */
    val childId: Long = 0,
    /** Pipe-separated timestamps per dose, e.g. "1700000000000||1703000000000". Empty = not recorded. */
    val doseDates: String = ""
) {
    /** True when all doses have been administered. */
    val isFullyCompleted: Boolean get() = completedDoses >= totalDoses

    /** The recommended age string for the next dose, or null if fully completed. */
    val nextDoseAge: String?
        get() = if (completedDoses < totalDoses)
            doseSchedule.split("|").getOrNull(completedDoses)
        else null

    /** Per-dose timestamps parsed from [doseDates]. List size equals [totalDoses]; null = not recorded. */
    @get:Ignore
    val parsedDoseDates: List<Long?>
        get() {
            val parts = if (doseDates.isBlank()) emptyList() else doseDates.split("|")
            return List(totalDoses) { i -> parts.getOrNull(i)?.toLongOrNull() }
        }

    /** Returns a copy with the given dose slot recorded. */
    fun withDoseRecorded(doseIndex: Int, dateMillis: Long): Vaccination {
        val dates = parsedDoseDates.toMutableList()
        if (doseIndex !in dates.indices || dates[doseIndex] != null) return this
        dates[doseIndex] = dateMillis
        return copy(
            completedDoses = completedDoses + 1,
            lastDoseDate = dateMillis,
            doseDates = dates.joinToString("|") { it?.toString() ?: "" }
        )
    }

    /** Returns a copy with the given dose slot cleared. */
    fun withDoseUndone(doseIndex: Int): Vaccination {
        val dates = parsedDoseDates.toMutableList()
        if (doseIndex !in dates.indices || dates[doseIndex] == null) return this
        dates[doseIndex] = null
        val remaining = dates.filterNotNull()
        return copy(
            completedDoses = (completedDoses - 1).coerceAtLeast(0),
            lastDoseDate = remaining.maxOrNull(),
            doseDates = dates.joinToString("|") { it?.toString() ?: "" }
        )
    }
}
