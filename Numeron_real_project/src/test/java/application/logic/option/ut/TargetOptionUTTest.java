package application.logic.option.ut;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;

import application.component.anything.Anything;
import application.component.consts.Const;
import application.component.message.MessageAccessor;
import application.logic.human.GameMaster;
import application.logic.option.TargetOption;
import application.logic.option.map.TargetOptionMapUtil;

/**
 * <p>
 * ターゲットオプションテストクラス
 * </p>
 * @author shiraishitoshio
 *
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Tag("UT")
public class TargetOptionUTTest {

	/**
	 * 小機能ID
	 */
	private static final String S_FUNC = "OPTION006";

	/**
	 * クラス名
	 */
	private static final String CLASS_NAME = TargetOption.class.getSimpleName();

	/**
	 * gameMaster mock
	 */
	@Mock
	private GameMaster gameMaster;

	/**
	 * TargetOptionMapUtil mock
	 */
	@Mock
	private TargetOptionMapUtil mapUtil;

	/**
	 * Message mock
	 */
	MockedStatic<MessageAccessor> message;

	/**
	 * Anything mock
	 */
	MockedStatic<Anything> anything;

	@InjectMocks
	@Spy
	private TargetOption testSuite;

	@BeforeEach
	public void setUp() {
		anything = mockStatic(Anything.class);
		message = mockStatic(MessageAccessor.class);
	}

	@AfterEach
	public void tearDown() {
		anything.close();
		message.close();
	}

	/**
	 * <p>
	 * PLAYER正常系01
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 指定する数字:5<br>
	 * [期待値]<br>
	 * ・EXISTLISTOFNUMBERが返却されること<br>
	 * ・指定した数字が存在すること<br>
	 * ・指定した数字が存在する桁:2であること<br>
	 * @throws Exception
	 */
	@Test
	final void targetOption_playerNormal01Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		assertEquals("EXISTLISTOFNUMBER", this.testSuite.targetLogic());
		assertEquals("5", this.testSuite.getExNum());
		assertEquals(2, this.testSuite.getExistsInd());
	}

	/**
	 * <p>
	 * PLAYER正常系02
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:348<br>
	 * 指定する数字:5<br>
	 * [期待値]<br>
	 * ・NONEEXISTLISTOFNUMBERが返却されること<br>
	 * ・指定した数字が存在しないこと<br>
	 * ・指定した数字が存在する桁:-1であること<br>
	 * @throws Exception
	 */
	@Test
	final void targetOption_playerNormal02Test() throws Exception {
		// 対象名
		ReflectionTestUtils.setField(this.testSuite, "chkMember", "PLAYER");
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("8");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		assertEquals("NONEEXISTLISTOFNUMBER", this.testSuite.targetLogic());
		assertEquals("5", this.testSuite.getExNum());
		assertEquals(-1, this.testSuite.getExistsInd());
	}

	/**
	 * <p>
	 * PLAYER正常系03
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:NORMAL<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:351<br>
	 * 指定する数字:5<br>
	 * [期待値]<br>
	 * ・EXISTLISTOFNUMBERが返却されること<br>
	 * ・指定した数字が存在すること<br>
	 * ・指定した数字が存在する桁:1であること<br>
	 * @throws Exception
	 */
	@Test
	final void targetOption_playerNormal03Test() throws Exception {
		// 対象名
		ReflectionTestUtils.setField(this.testSuite, "chkMember", "PLAYER");
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.NORMAL);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("5");
		list.add("1");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		assertEquals("EXISTLISTOFNUMBER", this.testSuite.targetLogic());
		assertEquals("5", this.testSuite.getExNum());
		assertEquals(1, this.testSuite.getExistsInd());
	}

	/**
	 * <p>
	 * PLAYER正常系04
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:HARD<br>
	 * 桁数:4<br>
	 * チェンジ対象数字:2351<br>
	 * 指定する数字:5<br>
	 * [期待値]<br>
	 * ・EXISTLISTOFNUMBERが返却されること<br>
	 * ・指定した数字が存在すること<br>
	 * ・指定した数字が存在する桁:2であること<br>
	 * @throws Exception
	 */
	@Test
	final void targetOption_playerNormal04Test() throws Exception {
		// 対象名
		ReflectionTestUtils.setField(this.testSuite, "chkMember", "PLAYER");
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(4);
		gameMaster.setDifficulty(Const.HARD);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("2");
		list.add("3");
		list.add("5");
		list.add("1");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		assertEquals("EXISTLISTOFNUMBER", this.testSuite.targetLogic());
		assertEquals("5", this.testSuite.getExNum());
		assertEquals(2, this.testSuite.getExistsInd());
	}

	/**
	 * <p>
	 * PLAYER正常系05
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EXHAUSTED<br>
	 * 桁数:5<br>
	 * チェンジ対象数字:23751<br>
	 * 指定する数字:5<br>
	 * [期待値]<br>
	 * ・EXISTLISTOFNUMBERが返却されること<br>
	 * ・指定した数字が存在すること<br>
	 * ・指定した数字が存在する桁:-1(初期化)であること<br>
	 * @throws Exception
	 */
	@Test
	final void targetOption_playerNormal05Test() throws Exception {
		// 対象名
		ReflectionTestUtils.setField(this.testSuite, "chkMember", "PLAYER");
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(Const.EXHAUSTED);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("2");
		list.add("3");
		list.add("7");
		list.add("5");
		list.add("1");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		assertEquals("EXISTLISTOFNUMBER", this.testSuite.targetLogic());
		assertEquals("5", this.testSuite.getExNum());
		assertEquals(-1, this.testSuite.getExistsInd());
	}

	/**
	 * <p>
	 * PLAYER正常系06
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:INSANE<br>
	 * 桁数:5<br>
	 * チェンジ対象数字:23751<br>
	 * 指定する数字:5<br>
	 * [期待値]<br>
	 * ・EXISTLISTOFNUMBERが返却されること<br>
	 * ・指定した数字が存在すること<br>
	 * ・指定した数字が存在する桁:-1(初期化)であること<br>
	 * @throws Exception
	 */
	@Test
	final void targetOption_playerNormal06Test() throws Exception {
		// 対象名
		ReflectionTestUtils.setField(this.testSuite, "chkMember", "PLAYER");
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(Const.INSANE);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("2");
		list.add("3");
		list.add("7");
		list.add("5");
		list.add("1");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		assertEquals("EXISTLISTOFNUMBER", this.testSuite.targetLogic());
		assertEquals("5", this.testSuite.getExNum());
		assertEquals(-1, this.testSuite.getExistsInd());
	}

	/**
	 * <p>
	 * CPU正常系01
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 指定する数字:4<br>
	 * [期待値]<br>
	 * ・EXISTLISTOFNUMBERが返却されること<br>
	 * ・指定した数字が存在すること<br>
	 * ・指定した数字が存在する桁:1であること<br>
	 * @throws Exception
	 */
	@Test
	final void targetOption_cpuNormal01Test() throws Exception {
		// 対象名
		ReflectionTestUtils.setField(this.testSuite, "chkMember", "CPU");
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectPlayerNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(Const.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getSelectNumberPriorityMap(eq(Const.SAI_YUUSEN_FLAG)))
				.thenReturn("4");
		// 検証
		assertEquals("EXISTLISTOFNUMBER", this.testSuite.targetLogic());
		assertEquals("4", this.testSuite.getExNum());
		assertEquals(1, this.testSuite.getExistsInd());
	}

	/**
	 * <p>
	 * CPU正常系02
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EXHAUSTED<br>
	 * 桁数:4<br>
	 * チェンジ対象数字:3450<br>
	 * 指定する数字:0<br>
	 * [期待値]<br>
	 * ・EXISTLISTOFNUMBERが返却されること<br>
	 * ・指定した数字が存在すること<br>
	 * ・指定した数字が存在する桁:-1(初期化)であること<br>
	 * @throws Exception
	 */
	@Test
	final void targetOption_cpuNormal02Test() throws Exception {
		// 対象名
		ReflectionTestUtils.setField(this.testSuite, "chkMember", "CPU");
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(4);
		gameMaster.setDifficulty(Const.EXHAUSTED);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("0");
		gameMaster.setCorrectPlayerNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(Const.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getSelectNumberPriorityMap(eq(Const.SAI_YUUSEN_FLAG)))
				.thenReturn("0");
		// 検証
		assertEquals("EXISTLISTOFNUMBER", this.testSuite.targetLogic());
		assertEquals("0", this.testSuite.getExNum());
		assertEquals(-1, this.testSuite.getExistsInd());
	}

	/**
	 * <p>
	 * CPU正常系03
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:INSANE<br>
	 * 桁数:5<br>
	 * チェンジ対象数字:39450<br>
	 * 指定する数字:4<br>
	 * [期待値]<br>
	 * ・EXISTLISTOFNUMBERが返却されること<br>
	 * ・指定した数字が存在すること<br>
	 * ・指定した数字が存在する桁:-1(初期化)であること<br>
	 * @throws Exception
	 */
	@Test
	final void targetOption_cpuNormal03Test() throws Exception {
		// 対象名
		ReflectionTestUtils.setField(this.testSuite, "chkMember", "CPU");
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(Const.INSANE);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("9");
		list.add("4");
		list.add("5");
		list.add("0");
		gameMaster.setCorrectPlayerNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(Const.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getSelectNumberPriorityMap(eq(Const.SAI_YUUSEN_FLAG)))
				.thenReturn("4");
		// 検証
		assertEquals("EXISTLISTOFNUMBER", this.testSuite.targetLogic());
		assertEquals("4", this.testSuite.getExNum());
		assertEquals(-1, this.testSuite.getExistsInd());
	}

	/**
	 * <p>
	 * CPU正常系04
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:INSANE<br>
	 * 桁数:5<br>
	 * チェンジ対象数字:39450<br>
	 * 指定する数字:ランダム<br>
	 * [期待値]<br>
	 * ・指定した数字が存在する桁:-1であること<br>
	 * @throws Exception
	 */
	@Test
	final void targetOption_cpuNormal04Test() throws Exception {
		// 対象名
		ReflectionTestUtils.setField(this.testSuite, "chkMember", "CPU");
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(Const.INSANE);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("9");
		list.add("4");
		list.add("5");
		list.add("0");
		gameMaster.setCorrectPlayerNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		ReflectionTestUtils.setField(this.testSuite, "exNum", "5");
		// 検証
		this.testSuite.targetLogic();
		assertEquals(-1, this.testSuite.getExistsInd());
	}

}
