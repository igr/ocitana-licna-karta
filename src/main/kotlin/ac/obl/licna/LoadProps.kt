package ac.obl.licna

import java.io.File

object LoadProps {
	operator fun invoke(fileName: String): Map<String, String> {
		return File(fileName)
			.readLines()
			.filter { it.trim().isNotEmpty() }
			.associate {
				val (key, value) = it.split("=")
				Pair(key.trim(), value.trim())
			}
	}
}