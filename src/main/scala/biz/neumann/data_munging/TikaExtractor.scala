package biz.neumann.data_munging

import org.apache.tika.sax.BodyContentHandler
import org.apache.tika.metadata.Metadata
import java.io.{InputStream, File}


/**
 * AN-iT
 *
 * User: Andreas Neumann
 * Mail: andreas.neumann@an-it.com
 * URL: http://www.an-it.com
 * Date: 18.03.12
 * Time: 18:10
 * Package: biz.neumann.data_munging
 */

import org.apache.tika.parser.AutoDetectParser

class TikaExtractor(in : InputStream) {
  private val parser = new AutoDetectParser
  private val contentHandler = new BodyContentHandler
  private val metaData = new Metadata
  parser.parse(in, contentHandler, metaData)

  def getText : String = contentHandler toString
  
  def getMetaData = metaData.names map { metaDataFeature =>  Map(metaDataFeature -> metaData.getValues(metaDataFeature).toList)} reduce(_ ++ _)
}
