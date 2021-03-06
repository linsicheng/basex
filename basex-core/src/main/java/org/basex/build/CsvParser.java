package org.basex.build;

import java.io.*;

import org.basex.build.xml.*;
import org.basex.core.*;
import org.basex.io.*;
import org.basex.io.parse.csv.*;
import org.basex.query.value.*;

/**
 * This class parses files in the CSV format
 * and converts them to XML.
 *
 * <p>The parser provides some options, which can be specified via the
 * {@link MainOptions#CSVPARSER} option.</p>
 *
 * @author BaseX Team 2005-13, BSD License
 * @author Christian Gruen
 */
public final class CsvParser extends XMLParser {
  /**
   * Constructor.
   * @param source document source
   * @param opts database options
   * @throws IOException I/O exception
   */
  public CsvParser(final IO source, final MainOptions opts) throws IOException {
    super(toXML(source, opts.get(MainOptions.CSVPARSER)), opts);
  }

  /**
   * Converts CSV data to XML.
   * @param io input
   * @param copts parsing options
   * @return parser
   * @throws IOException I/O exception
   */
  public static IO toXML(final IO io, final CsvParserOptions copts) throws IOException {
    final Value node = CsvConverter.convert(io, copts);
    final IOContent xml = new IOContent(node.serialize().toArray());
    xml.name(io.name());
    return xml;
  }
}
