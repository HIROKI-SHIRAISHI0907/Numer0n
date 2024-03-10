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
import application.component.consts.Numer0nDoubleEnum;
import application.component.consts.PriorityFlagConst;
import application.component.message.MessageAccessor;
import application.logic.human.Computer;
import application.logic.human.GameMaster;
import application.logic.human.Player;
import application.logic.info.Numer0nInfo;
import application.logic.info.Numer0nSpecifyNumber;
import application.logic.judge.Eatbite;
import application.logic.option.DoubleOption;
import application.logic.option.map.DoubleOptionMapUtil;

/**
 * <p>
 * ダブルオプションテストクラス
 * </p>
 * @author shiraishitoshio
 *
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Tag("UT")
public class DoubleOptionUTTest {

	/**
	 * 小機能ID
	 */
	private static final String S_FUNC = "OPTION001";

	/**
	 * クラス名
	 */
	private static final String CLASS_NAME = DoubleOption.class.getSimpleName();

	/**
	 * gameMaster mock
	 */
	@Mock
	private GameMaster gameMaster;

	/**
	 * DoubleOptionMapUtil mock
	 */
	@Mock
	private DoubleOptionMapUtil mapUtil;

	/**
	 * Computer mock
	 */
	@Mock
	private Computer computer;

	/**
	 * Player mock
	 */
	@Mock
	private Player player;

	/**
	 * EatBite mock
	 */
	@Mock
	private Eatbite eatBite;

	/**
	 * Numer0nSpecifyNumber mock
	 */
	@Mock
	private Numer0nSpecifyNumber aiSpecifyNumber;

	/**
	 * Numer0nInfo mock
	 */
	@Mock
	private Numer0nInfo info;

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
	private DoubleOption testSuite;

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
	 * 桁数:4<br>
	 * ダブル対象数字:1267<br>
	 * 宣言する桁:1000の位<br>
	 * [期待値]<br>
	 * ・TEACH_NUMBERが返却されること<br>
	 * ・PLAYERのターンで正解できなかった場合、PLAYERの正解数字の一部を教えること<br>
	 * ・宣言した桁:0であること<br>
	 * ・宣言した桁に紐づく数字:1であること<br>
	 * @throws Exception
	 */
	@Test
	final void doubleOption_playerNormal01Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(4);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("6");
		list.add("7");
		gameMaster.setCorrectPlayerNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(0);
		// 検証
		Integer result = this.testSuite.doubleLogic();
		assertEquals(Numer0nDoubleEnum.TEACH_NUMBER.getOprionCd(), result);
		assertEquals(0, this.testSuite.getDoubleDigit());
		assertEquals("1", this.testSuite.getDoubleNum());
	}

	/**
	 * <p>
	 * PLAYER正常系02
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁数:3<br>
	 * ダブル対象数字:345<br>
	 * 宣言する桁:1の位<br>
	 * [期待値]<br>
	 * ・TEACH_NUMBERが返却されること<br>
	 * ・PLAYERのターンで正解できなかった場合、PLAYERの正解数字の一部を教えること<br>
	 * ・宣言した桁:2であること<br>
	 * ・宣言した桁に紐づく数字:5であること<br>
	 * @throws Exception
	 */
	@Test
	final void doubleOption_playerNormal02Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectPlayerNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(2);
		// 検証
		Integer result = this.testSuite.doubleLogic();
		assertEquals(Numer0nDoubleEnum.TEACH_NUMBER.getOprionCd(), result);
		assertEquals(2, this.testSuite.getDoubleDigit());
		assertEquals("5", this.testSuite.getDoubleNum());
	}

	/**
	 * <p>
	 * PLAYER正常系03
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁数:5<br>
	 * ダブル対象数字:34567<br>
	 * 宣言する桁:100の位<br>
	 * [期待値]<br>
	 * ・ALL_EATが返却されること<br>
	 * ・PLAYERのターンで正解できた場合、ALL_EATであること<br>
	 * ・宣言した桁:2であること<br>
	 * ・宣言した桁に紐づく数字:5であること<br>
	 * @throws Exception
	 */
	@Test
	final void doubleOption_playerNormal03Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(5);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("7");
		gameMaster.setCorrectPlayerNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(2);
		// eatBiteがALL_EAT
		when(this.eatBite.judgeEatBite(any(), any())).thenReturn(Numer0nDoubleEnum.ALLEAT.getOprionCd());
		// 検証
		Integer result = this.testSuite.doubleLogic();
		assertEquals(Numer0nDoubleEnum.ALLEAT.getOprionCd(), result);
		assertEquals(2, this.testSuite.getDoubleDigit());
		assertEquals("5", this.testSuite.getDoubleNum());
	}

//	/** mockにしておりMapUtilの正しい検証ができないためスキップ
//	 * <p>
//	 * PLAYER正常系04
//	 * </p>
//	 * [初期設定]<br>
//	 * 対象名:PLAYER<br>
//	 * 桁数:4<br>
//	 * ダブル対象数字:7024<br>
//	 * 宣言する桁:10の位<br>
//	 * [途中期待値]<br>
//	 * ランダムであること
//	 * [期待値]<br>
//	 * ・宣言した桁:nullでないこと<br>
//	 * ・宣言した桁に紐づく数字:nullでないこと<br>
//	 * @throws Exception
//	 */
//	@Test
//	final void doubleOption_playerNormal04Test() throws Exception {
//		// 対象名
//		ReflectionTestUtils.setField(this.testSuite, "chkMember", "PLAYER");
//		// 桁数
//		// 難易度
//		GameMaster gameMaster = spy(new GameMaster());
//		gameMaster.setDigit(4);
//		// シャッフル対象数字設定
//		ArrayList<String> list = new ArrayList<>();
//		list.add("7");
//		list.add("0");
//		list.add("2");
//		list.add("4");
//		gameMaster.setCorrectPlayerNumberList(list);
//		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
//		// 最優先フラグを設定
//		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
//		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(false);
//		// 検証
//		this.testSuite.doubleLogic();
//		assertNotNull(this.testSuite.getDoubleDigit());
//		assertNotNull(this.testSuite.getDoubleNum());
//	}

	/**
	 * <p>
	 * CPU正常系01
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 桁数:4<br>
	 * ダブル対象数字:1267<br>
	 * 宣言する桁:1000の位<br>
	 * [期待値]<br>
	 * ・TEACH_NUMBERが返却されること<br>
	 * ・CPUのターンで正解できなかった場合、CPUの正解数字の一部を教えること<br>
	 * ・宣言した桁:0であること<br>
	 * ・宣言した桁に紐づく数字:1であること<br>
	 * @throws Exception
	 */
	@Test
	final void doubleOption_cpuNormal01Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(4);
		gameMaster.setName(Const.CPU);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("6");
		list.add("7");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(0);
		// 検証
		Integer result = this.testSuite.doubleLogic();
		assertEquals(Numer0nDoubleEnum.TEACH_NUMBER.getOprionCd(), result);
		assertEquals(0, this.testSuite.getDoubleDigit());
		assertEquals("1", this.testSuite.getDoubleNum());
	}

	/**
	 * <p>
	 * CPU正常系02
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 桁数:3<br>
	 * ダブル対象数字:345<br>
	 * 宣言する桁:100の位<br>
	 * [期待値]<br>
	 * ・TEACH_NUMBERが返却されること<br>
	 * ・CPUのターンで正解できなかった場合、CPUの正解数字の一部を教えること<br>
	 * ・宣言した桁:0であること<br>
	 * ・宣言した桁に紐づく数字:3であること<br>
	 * @throws Exception
	 */
	@Test
	final void doubleOption_cpuNormal02Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(0);
		// 検証
		Integer result = this.testSuite.doubleLogic();
		assertEquals(Numer0nDoubleEnum.TEACH_NUMBER.getOprionCd(), result);
		assertEquals(0, this.testSuite.getDoubleDigit());
		assertEquals("3", this.testSuite.getDoubleNum());
	}

	/**
	 * <p>
	 * PLAYER正常系03
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁数:5<br>
	 * ダブル対象数字:34567<br>
	 * 宣言する桁:10000の位<br>
	 * [期待値]<br>
	 * ・ALL_EATが返却されること<br>
	 * ・CPUのターンで正解できた場合、ALL_EATであること<br>
	 * ・宣言した桁:2であること<br>
	 * ・宣言した桁に紐づく数字:5であること<br>
	 * @throws Exception
	 */
	@Test
	final void doubleOption_cpuNormal03Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(5);
		gameMaster.setName(Const.CPU);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("7");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(2);
		// eatBiteがALL_EAT
		when(this.eatBite.judgeEatBite(any(), any())).thenReturn(Numer0nDoubleEnum.ALLEAT.getOprionCd());
		// 検証
		Integer result = this.testSuite.doubleLogic();
		assertEquals(Numer0nDoubleEnum.ALLEAT.getOprionCd(), result);
		assertEquals(0, this.testSuite.getDoubleDigit());
		assertEquals("3", this.testSuite.getDoubleNum());
	}
}
