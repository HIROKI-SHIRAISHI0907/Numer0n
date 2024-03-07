package application.logic.game.ct;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import application.Numer0nApplication;
import application.logic.game.Numer0n;

@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(classes = {Numer0nApplication.class})
@Tag("CT")
public class Numer0nCTTest {

	@Autowired
	private Numer0n numeron;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void test1() {
		numeron.doStart();
		//System.out.println("結果:"+hl.getHighLowList());
	}

	@Test
	public void test2() {
		numeron.doStart();
		//System.out.println("結果:"+hl.getHighLowList());
	}

	@Test
	public void test3() {
		numeron.doStart();
		//System.out.println("コール結果:"+hl.getHighLowList());
	}

	@Test
	public void test4() {
		numeron.doStart();
		//System.out.println("結果:"+hl.getHighLowList());
	}

	@Test
	public void test5() {
		numeron.doStart();
		//System.out.println("結果:"+hl.getHighLowList());
	}




}
