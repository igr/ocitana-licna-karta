package ac.obl.licna

object RenderResource {

	private val fragmentNames = arrayOf("fragment-adresa-datum")

	operator fun invoke(templateName: String, data: Props): String {
		var template = prepareResource(templateName, data)

		for (name in fragmentNames) {
			val fragment = prepareResource("$name.xhtml", data)
			template = template.replace("\${${name}}", resolvedFragment(fragment))
		}

		return template
	}

	private fun resolvedFragment(fragment: String): String {
		return if (fragment.contains("\${")) "" else fragment
	}

	private fun prepareResource(templateName: String, data: Props): String {
		var template = loadResource(templateName)

		data.forEach { (key, value) ->
			template = template.replace("\${${key}}", value)
		}
		return template
	}

	private fun loadResource(templateName: String): String = this.javaClass.getResource("/${templateName}")!!.readText()
}