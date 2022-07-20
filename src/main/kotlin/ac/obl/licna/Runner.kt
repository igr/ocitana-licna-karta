package ac.obl.licna

fun main() {
	val props = LoadProps("licna.txt")
	val xhtml = RenderResource("licna.xhtml", props)
	PDFRender.createPDF(xhtml, "licna.pdf")
	println("Done")
}
