package ru.skillbranch.devintensive.utils

import java.util.*

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")

        val firstName = if (parts?.get(0)?.isBlank() == true) null else parts?.getOrNull(0)
        val lastName = if (parts?.getOrNull(1).isNullOrBlank()) null else parts?.getOrNull(1)
//        return Pair(firstName, lastName)
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val mapTrans = mapOf<Char, String>(
            'а' to "a",
            'б' to "b",
            'в' to "v",
            'г' to "g",
            'д' to "d",
            'е' to "e",
            'ё' to "e",
            'ж' to "zh",
            'з' to "z",
            'и' to "i",
            'й' to "i",
            'к' to "k",
            'л' to "l",
            'м' to "m",
            'н' to "n",
            'о' to "o",
            'п' to "p",
            'р' to "r",
            'с' to "s",
            'т' to "t",
            'у' to "u",
            'ф' to "f",
            'х' to "h",
            'ц' to "c",
            'ч' to "ch",
            'ш' to "sh",
            'щ' to "sh\'",
            'ъ' to "",
            'ы' to "i",
            'ь' to "",
            'э' to "e",
            'ю' to "yu",
            'я' to "ya",
        )
        var payLayout = ""
        payload.forEach {
            payLayout += when {
                it.isUpperCase() -> if (it.lowercaseChar() in mapTrans.keys) {
                    mapTrans[it.lowercaseChar()]?.first()?.uppercase() +
                    mapTrans[it.lowercaseChar()]?.substring(1)
                } else it
                else -> if (it in mapTrans.keys) mapTrans[it] else it
            }

        }
        return payLayout.replace(" ", divider)
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val firstLit = if (firstName.isNullOrBlank()) null else firstName[0].uppercase()
        val lastLit = if (lastName.isNullOrBlank()) null else lastName[0].uppercase()
        return when {
            firstLit == null && lastLit == null -> null
            firstLit == null -> lastLit
            lastLit == null -> firstLit
            else -> "$firstLit$lastLit"

        }
    }
}