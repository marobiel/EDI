

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import com.markdev.apps.tool.Collector;
import com.markdev.apps.tool.RawData;

public class CollectorTest {

	
private RawData table;
	
	@Before
	public void setup(){
		table = new RawData("Typ komunikatu","Numer IDoc","Status","DataBłStat","GodzBłStat","TekstStat","Parametr 1");
		String[] linia = {"ORDERS","0000000076435622","51","12.09.2014","20:40:00","Kod ISO jednostki miary & nie jest przyporządkowany: pozycja &","PCE"};
		
		table.addData(linia);
		
		String[] linia1 = {"ORDERS","0000000076875533","51","18.09.2014","08:11:29","Kod ISO jednostki miary & nie jest przyporządkowany: pozycja &","PCE"};
		
		String[] linia2 = {"ORDERS","0000000076362396","51","11.09.2014","03:39:43","Nie można ustalić numeru klienta w IDOC &","0000000076252088"};
		
		String[] linia3 = {"ORDERS","0000000076362397","51","11.09.2014","03:40:43","Nie można ustalić numeru klienta w IDOC &","0000000076362397"};
		
		String[] linia4 = {"ORDERS","0000000076362398","51","11.09.2014","03:39:43","Nie można ustalić numeru klienta w IDOC &","0000000076362398"};
		
		String[] linia5 = {"ORDERS","0000000076362399","51","11.09.2014","03:39:43","Nie można ustalić numeru klienta w IDOC &","0000000076362399"};
		
		table.addData(linia5);
		table.addData(linia1);
		table.addData(linia3);
		table.addData(linia2);
		table.addData(linia4);
		
	}
	
	@Test
	public void test() {
		Collector collector = new Collector(table);
		collector.collect();
		
		System.out.println(collector.numLinesFor("Nie można ustalić numeru klienta w IDOC &"));
		System.out.println(collector.numLinesFor("Kod ISO jednostki miary & nie jest przyporządkowany: pozycja &"));
		System.out.println(collector.numLines());
		
		
		Iterator<String> it = collector.iterator();
		while (it.hasNext()){
			String key = it.next();
			System.out.println(collector.get(key));
			
		}
		
		
	}

}
