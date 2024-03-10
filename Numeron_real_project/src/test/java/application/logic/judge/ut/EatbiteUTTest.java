package application.logic.judge.ut;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;

import application.component.consts.Numer0nDoubleEnum;
import application.logic.human.GameMaster;
import application.logic.judge.Eatbite;

/**
 * <p>
 * Eatbiteテストクラス
 * </p>
 * @author shiraishitoshio
 *
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Tag("UT")
public class EatbiteUTTest {

	@Mock
	private GameMaster gameMaster;

	@InjectMocks
	private Eatbite testSuite;

	/**
	 * <p>
	 * 正常系01
	 * </p>
	 * [初期設定]<br>
	 * 桁数:3<br>
	 * コール対象数字:012<br>
	 * コールされる対象数字:012<br>
	 * [期待値]<br>
	 * ・3EATであること
	 * @throws Exception
	 */
	@Test
	final void eatbite_normal01Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// コール対象数字設定
		ArrayList<String> callNumber = new ArrayList<String>();
		callNumber.add("0");
		callNumber.add("1");
		callNumber.add("2");
		// コールされる対象数字設定
		ArrayList<String> correctNumber = new ArrayList<String>();
		correctNumber.add("0");
		correctNumber.add("1");
		correctNumber.add("2");
		// 検証
		assertEquals(Numer0nDoubleEnum.ALLEAT.getOprionCd(), this.testSuite.judgeEatBite(callNumber, correctNumber));
		assertEquals("3EAT0BITE", this.testSuite.getEatBiteResult());
	}

	/**
	 * <p>
	 * 正常系02
	 * </p>
	 * [初期設定]<br>
	 * 桁数:3<br>
	 * コール対象数字:847<br>
	 * コールされる対象数字:849<br>
	 * [期待値]<br>
	 * ・2EAT0BITEであること
	 * @throws Exception
	 */
	@Test
	final void eatbite_normal02Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// コール対象数字設定
		ArrayList<String> callNumber = new ArrayList<String>();
		callNumber.add("8");
		callNumber.add("4");
		callNumber.add("7");
		// コールされる対象数字設定
		ArrayList<String> correctNumber = new ArrayList<String>();
		correctNumber.add("8");
		correctNumber.add("4");
		correctNumber.add("9");
		// 検証
		assertEquals(Numer0nDoubleEnum.NONE_ALLEAT.getOprionCd(), this.testSuite.judgeEatBite(callNumber, correctNumber));
		assertEquals("2EAT0BITE", this.testSuite.getEatBiteResult());
	}

	/**
	 * <p>
	 * 正常系03
	 * </p>
	 * [初期設定]<br>
	 * 桁数:3<br>
	 * コール対象数字:154<br>
	 * コールされる対象数字:145<br>
	 * [期待値]<br>
	 * ・1EAT2BITEであること
	 * @throws Exception
	 */
	@Test
	final void eatbite_normal03Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// コール対象数字設定
		ArrayList<String> callNumber = new ArrayList<String>();
		callNumber.add("1");
		callNumber.add("5");
		callNumber.add("4");
		// コールされる対象数字設定
		ArrayList<String> correctNumber = new ArrayList<String>();
		correctNumber.add("1");
		correctNumber.add("4");
		correctNumber.add("5");
		// 検証
		assertEquals(Numer0nDoubleEnum.NONE_ALLEAT.getOprionCd(), this.testSuite.judgeEatBite(callNumber, correctNumber));
		assertEquals("1EAT2BITE", this.testSuite.getEatBiteResult());
	}

	/**
	 * <p>
	 * 正常系04
	 * </p>
	 * [初期設定]<br>
	 * 桁数:3<br>
	 * コール対象数字:893<br>
	 * コールされる対象数字:849<br>
	 * [期待値]<br>
	 * ・1EAT1BITEであること
	 * @throws Exception
	 */
	@Test
	final void eatbite_normal04Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// コール対象数字設定
		ArrayList<String> callNumber = new ArrayList<String>();
		callNumber.add("8");
		callNumber.add("9");
		callNumber.add("3");
		// コールされる対象数字設定
		ArrayList<String> correctNumber = new ArrayList<String>();
		correctNumber.add("8");
		correctNumber.add("4");
		correctNumber.add("9");
		// 検証
		assertEquals(Numer0nDoubleEnum.NONE_ALLEAT.getOprionCd(), this.testSuite.judgeEatBite(callNumber, correctNumber));
		assertEquals("1EAT1BITE", this.testSuite.getEatBiteResult());
	}

	/**
	 * <p>
	 * 正常系05
	 * </p>
	 * [初期設定]<br>
	 * 桁数:3<br>
	 * コール対象数字:602<br>
	 * コールされる対象数字:501<br>
	 * [期待値]<br>
	 * ・1EAT0BITEであること
	 * @throws Exception
	 */
	@Test
	final void eatbite_normal05Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// コール対象数字設定
		ArrayList<String> callNumber = new ArrayList<String>();
		callNumber.add("6");
		callNumber.add("0");
		callNumber.add("2");
		// コールされる対象数字設定
		ArrayList<String> correctNumber = new ArrayList<String>();
		correctNumber.add("5");
		correctNumber.add("0");
		correctNumber.add("1");
		// 検証
		assertEquals(Numer0nDoubleEnum.NONE_ALLEAT.getOprionCd(), this.testSuite.judgeEatBite(callNumber, correctNumber));
		assertEquals("1EAT0BITE", this.testSuite.getEatBiteResult());
	}

	/**
	 * <p>
	 * 正常系06
	 * </p>
	 * [初期設定]<br>
	 * 桁数:3<br>
	 * コール対象数字:491<br>
	 * コールされる対象数字:914<br>
	 * [期待値]<br>
	 * ・0EAT3BITEであること
	 * @throws Exception
	 */
	@Test
	final void eatbite_normal06Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// コール対象数字設定
		ArrayList<String> callNumber = new ArrayList<String>();
		callNumber.add("4");
		callNumber.add("9");
		callNumber.add("1");
		// コールされる対象数字設定
		ArrayList<String> correctNumber = new ArrayList<String>();
		correctNumber.add("9");
		correctNumber.add("1");
		correctNumber.add("4");
		// 検証
		assertEquals(Numer0nDoubleEnum.NONE_ALLEAT.getOprionCd(), this.testSuite.judgeEatBite(callNumber, correctNumber));
		assertEquals("0EAT3BITE", this.testSuite.getEatBiteResult());
	}

	/**
	 * <p>
	 * 正常系07
	 * </p>
	 * [初期設定]<br>
	 * 桁数:3<br>
	 * コール対象数字:518<br>
	 * コールされる対象数字:351<br>
	 * [期待値]<br>
	 * ・0EAT2BITEであること
	 * @throws Exception
	 */
	@Test
	final void eatbite_normal07Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// コール対象数字設定
		ArrayList<String> callNumber = new ArrayList<String>();
		callNumber.add("5");
		callNumber.add("1");
		callNumber.add("8");
		// コールされる対象数字設定
		ArrayList<String> correctNumber = new ArrayList<String>();
		correctNumber.add("3");
		correctNumber.add("5");
		correctNumber.add("1");
		// 検証
		assertEquals(Numer0nDoubleEnum.NONE_ALLEAT.getOprionCd(), this.testSuite.judgeEatBite(callNumber, correctNumber));
		assertEquals("0EAT2BITE", this.testSuite.getEatBiteResult());
	}

	/**
	 * <p>
	 * 正常系08
	 * </p>
	 * [初期設定]<br>
	 * 桁数:3<br>
	 * コール対象数字:382<br>
	 * コールされる対象数字:937<br>
	 * [期待値]<br>
	 * ・0EAT1BITEであること
	 * @throws Exception
	 */
	@Test
	final void eatbite_normal08Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// コール対象数字設定
		ArrayList<String> callNumber = new ArrayList<String>();
		callNumber.add("3");
		callNumber.add("8");
		callNumber.add("2");
		// コールされる対象数字設定
		ArrayList<String> correctNumber = new ArrayList<String>();
		correctNumber.add("9");
		correctNumber.add("3");
		correctNumber.add("7");
		// 検証
		assertEquals(Numer0nDoubleEnum.NONE_ALLEAT.getOprionCd(), this.testSuite.judgeEatBite(callNumber, correctNumber));
		assertEquals("0EAT1BITE", this.testSuite.getEatBiteResult());
	}

	/**
	 * <p>
	 * 正常系09
	 * </p>
	 * [初期設定]<br>
	 * 桁数:3<br>
	 * コール対象数字:673<br>
	 * コールされる対象数字:481<br>
	 * [期待値]<br>
	 * ・0EAT0BITEであること
	 * @throws Exception
	 */
	@Test
	final void eatbite_normal09Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// コール対象数字設定
		ArrayList<String> callNumber = new ArrayList<String>();
		callNumber.add("6");
		callNumber.add("7");
		callNumber.add("3");
		// コールされる対象数字設定
		ArrayList<String> correctNumber = new ArrayList<String>();
		correctNumber.add("4");
		correctNumber.add("8");
		correctNumber.add("1");
		// 検証
		assertEquals(Numer0nDoubleEnum.NONE_ALLEAT.getOprionCd(), this.testSuite.judgeEatBite(callNumber, correctNumber));
		assertEquals("0EAT0BITE", this.testSuite.getEatBiteResult());
	}

}
