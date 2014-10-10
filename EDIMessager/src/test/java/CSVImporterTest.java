

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.markdev.apps.tool.Filter;
import com.markdev.apps.tool.Parser;
import com.markdev.apps.tool.impl.CSVImporter;
import com.markdev.apps.tool.impl.ParserImpl;

public class CSVImporterTest {

	final static String PATH = "/home/hostuser/Documents/workspace-sts-3.5.1.RELEASE/EDIMessager/src/main/resources/EDI.txt";

	@Test
	public void testImportData() {

		Filter filter = new Filter() {

			public boolean apply(int i) {
				if (i == 0 || i == 1)
					return true;

				return false;
			}

			public boolean apply(String[] data) {
				throw new RuntimeException("No used in this object");
			}
		};

		Filter filter1 = new Filter() {

			public boolean apply(int i) {
				return true;
			}

			public boolean apply(String[] data) {
				throw new RuntimeException("No used in this object");
			}
		};

		Parser parser = new ParserImpl(filter1); 

		CSVImporter importer = new CSVImporter(new File(PATH),
				parser);
		importer.importData();
		System.out.println(parser.data());
	}

}
