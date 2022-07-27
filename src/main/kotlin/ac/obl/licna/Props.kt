package ac.obl.licna

class Props(private val map: Map<String, String>) {
	operator fun get(key: String): String? = map[key]
	fun hasAddressDate(): Boolean = map.containsKey("adresa-datum")
	fun forEach(action: (Pair<String, String>) -> Unit) = map.forEach { action(it.key to it.value) }
}