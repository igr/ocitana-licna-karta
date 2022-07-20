package ac.obl.licna

import org.xhtmlrenderer.pdf.ITextOutputDevice
import org.xhtmlrenderer.pdf.ITextRenderer
import org.xhtmlrenderer.pdf.ITextUserAgent
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream

object PDFRender {
	fun createPDF(xhtml: String, pdfName: String) {
		val os = FileOutputStream(pdfName)
		os.use {
			val renderer = ITextRenderer()
			val callback = ResourceLoaderUserAgent(renderer.outputDevice)

			callback.sharedContext = renderer.sharedContext
			renderer.sharedContext.userAgentCallback = callback

			renderer.setDocumentFromString(xhtml, "")
			renderer.layout()
			renderer.createPDF(os)
			renderer.finishPDF()
			os.close()
		}
	}

	private class ResourceLoaderUserAgent(outputDevice: ITextOutputDevice) : ITextUserAgent(outputDevice) {
		override fun resolveAndOpenStream(uri: String): InputStream {
			var inputStream = super.resolveAndOpenStream(uri)

			if (inputStream == null) {
				// Resource is on the classpath
				try {
					inputStream = this.javaClass.getResourceAsStream("/$uri")
				} catch (ignore: Exception) {
				}

				if (inputStream == null) {
					// Resource is in the file system
					try {
						inputStream = FileInputStream(File(uri))
					} catch (ignore: Exception) {
					}
				}
			}
			return inputStream
		}
	}
}