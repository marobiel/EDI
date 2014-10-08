

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.markdev.apps.tool.ColumnFilter;
import com.markdev.apps.tool.Parser;
import com.markdev.apps.tool.impl.CSVImporter;
import com.markdev.apps.tool.impl.ParserImpl;

public class CSVImporterTest {

	final static String PATH = "/home/hostuser/Documents/workspace-sts-3.5.1.RELEASE/EDIMessager/src/main/resources/EDI.txt";

	@Test
	public void testImportData() {

		ColumnFilter filter = new ColumnFilter() {

			public boolean apply(int i) {
				if (i == 0 || i == 1)
					return true;

				return false;
			}
		};

		ColumnFilter filter1 = new ColumnFilter() {

			public boolean apply(int i) {
				return true;
			}
		};

		Parser parser = new ParserImpl(filter1); 

		CSVImporter importer = new CSVImporter(new File(PATH),
				parser);
		importer.importData();
		System.out.println(parser.data());
	}

}
