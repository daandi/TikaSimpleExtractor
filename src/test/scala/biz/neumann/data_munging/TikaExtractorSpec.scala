package biz.neumann.data_munging

import org.specs2.mutable.Specification

/**
 * AN-iT
 *
 * User: Andreas Neumann
 * Mail: andreas.neumann@an-it.com
 * URL: http://www.an-it.com
 * Date: 18.03.12
 * Time: 18:24
 * Package: biz.neumann.data_munging
 */

class TikaExtractorSpec extends Specification {
  "A TikaExtractor" should {
    "extract the Text Content of a FileStream" in {
      val example = new TikaExtractor(getClass.getResourceAsStream("/Sprache_als_Magie_-_Magie_als_Sprache.pdf"))
      example.getText.size must_== 23061
    }
    "extract the MetaData of FileStream" in {
      val pdf = new TikaExtractor(getClass.getResourceAsStream("/Sprache_als_Magie_-_Magie_als_Sprache.pdf"))
      pdf.getMetaData must_==
        Map(
          "Last-Modified" -> List("2004-12-09T16:34:00Z"),
          "Creation-Date" -> List("2004-12-09T16:34:00Z"),
          "creator" -> List("PScript5.dll Version 5.2"),
          "Content-Type" -> List("application/pdf"),
          "Author" -> List("Administrator"),
          "title" -> List("Microsoft Word - Sprache als Magie komplett.doc"),
          "xmpTPg:NPages" -> List("16"),
          "producer" -> List("Acrobat Distiller 5.0 (Windows)"),
          "created" -> List("Thu Dec 09 17:34:00 CET 2004")
        )
    }
    "extract metaData also from images" in {
      val png = new TikaExtractor(getClass.getResourceAsStream("/apple-touch-icon.png"))
      png.getMetaData.keys shouldEqual Set("tiff:BitsPerSample", "Data PlanarConfiguration", "Data BitsPerSample", "Dimension PixelAspectRatio", "height", "tiff:ImageLength", "Chroma BlackIsZero", "tEXt tEXtEntry", "Compression CompressionTypeName", "Text TextEntry", "Content-Type", "Chroma ColorSpaceType", "Compression NumProgressiveScans", "Transparency Alpha", "Chroma NumChannels", "Compression Lossless", "width", "Data SampleFormat", "Dimension ImageOrientation", "IHDR", "PLTE PLTEEntry", "Chroma Palette PaletteEntry", "tiff:ImageWidth")
    }
  }
}
