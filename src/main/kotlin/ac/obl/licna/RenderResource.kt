package ac.obl.licna

object RenderResource {

	operator fun invoke(templateName: String, data: Map<String, String>): String {
		var template = this.javaClass.getResource("/${templateName}")!!.readText()

		data.forEach { (key, value) ->
			template = template.replace("\${${key}}", value)
		}

		return template
	}
}