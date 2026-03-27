package com.jair.scrobbbbler

data class ParsedTrack(
    val artist: String?,
    val title: String?,
    val album: String?
)

object NotificationParser {

    fun parse(title: String, text: String, subText: String): ParsedTrack {
        val cleanTitle = title.ifBlank { null }

        var artist: String? = null
        var album: String? = null

        if (text.contains(" - ")) {
            val parts = text.split(" - ", limit = 2)
            artist = parts.getOrNull(0)?.trim()?.ifBlank { null }
            album = parts.getOrNull(1)?.trim()?.ifBlank { null }
        } else {
            artist = text.ifBlank { null }
        }

        if (album == null) {
            album = subText.ifBlank { null }
        }

        return ParsedTrack(
            artist = artist,
            title = cleanTitle,
            album = album
        )
    }
}