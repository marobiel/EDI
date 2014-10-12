

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.markdev.apps.tool.impl.FileDescRepository;

public class FileDescRepositoryTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		
		String path="/home/markus/git/EDI/EDIMessager/src/main/resources/opis.txt";
		
		FileDescRepository repo = new FileDescRepository(new File(path));
		System.out.println(repo);
		
	}

}
