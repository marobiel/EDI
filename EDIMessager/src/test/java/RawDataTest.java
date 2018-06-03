

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.markdev.apps.tool.RawData;

public class RawDataTest {

	private RawData table;
	
	@Before
	public void setup(){
		table = new RawData("Typ komunikatu","Numer IDoc","Status","DataBłStat","GodzBłStat","TekstStat","Parametr 1");
		String[] linia = {"ORDERS","0000000076435622","51","12.09.2014","20:40:00","Kod ISO jednostki miary & nie jest przyporządkowany: pozycja &","PCE"};
		
		table.addData(linia);
		
		String[] linia1 = {"ORDERS","0000000076875533","51","18.09.2014","08:11:29","Kod ISO jednostki miary & nie jest przyporządkowany: pozycja &","PCE"};
		table.addData(linia1);
		
		String[] linia2 = {"ORDERS","0000000076362396","51","11.09.2014","03:39:43","Nie można ustalić numeru klienta w IDOC &","0000000076252088"};
		table.addData(linia2);
		
		String[] linia3 = {"ORDERS","0000000076362397","51","11.09.2014","03:40:43","Nie można ustalić numeru klienta w IDOC &","0000000076362397"};
		table.addData(linia3);
		
		String[] linia4 = {"ORDERS","0000000076362398","51","11.09.2014","03:39:43","Nie można ustalić numeru klienta w IDOC &","0000000076362398"};
		table.addData(linia4);
		
		String[] linia5 = {"ORDERS","0000000076362399","51","11.09.2014","03:39:43","Nie można ustalić numeru klienta w IDOC &","0000000076362399"};
		table.addData(linia5);
	}

	@Test
	public void testAddData() {
		
		
		assertTrue(table.linesNum()==6);
		
		
	}

	@Test
	public void testGetLine() {
		table.getLine(0);
		table.getLine(1);
		table.getLine(2);
	
	}
	
	@Test
	public void testColumnsValue(){
		
		table.getColumnValues(0);
		
	}
   
    @Test
	public void testSomethingWrong(){
		fail("Zamierzona akcja");
	}

}
