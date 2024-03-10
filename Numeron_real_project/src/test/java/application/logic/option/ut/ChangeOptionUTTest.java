package application.logic.option.ut;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;

import application.component.consts.Const;
import application.component.consts.DifficultyConst;
import application.component.consts.Numer0nSelectNumberEnum;
import application.component.consts.PriorityFlagConst;
import application.component.message.MessageAccessor;
import application.logic.human.GameMaster;
import application.logic.option.ChangeOption;
import application.logic.option.SlashOption;
import application.logic.option.map.ChangeOptionMapUtil;

/**
 * <p>
 * チェンジオプションテストクラス
 * </p>
 * @author shiraishitoshio
 *
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Tag("UT")
public class ChangeOptionUTTest {

	/**
	 * 小機能ID
	 */
	private static final String S_FUNC = "OPTION001";

	/**
	 * クラス名
	 */
	private static final String CLASS_NAME = SlashOption.class.getSimpleName();

	/**
	 * gameMaster mock
	 */
	@Mock
	private GameMaster gameMaster;

	/**
	 * ChangeOptionMapUtil mock
	 */
	@Mock
	private ChangeOptionMapUtil mapUtil;

	/**
	 * Message mock
	 */
	MockedStatic<MessageAccessor> message;

	@InjectMocks
	@Spy
	private ChangeOption testSuite;

	@BeforeEach
	public void setUp() {
		message = mockStatic(MessageAccessor.class);
		MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	public void tearDown() {
		message.close();
	}

	/**
	 * <p>
	 * PLAYER正常系01
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁数:3
	 * チェンジ対象数字:825<br>
	 * 交換する数字:-<br>
	 * 交換する桁:-<br>
	 * [期待値]<br>
	 * ・825であること
	 * @throws Exception
	 */
	@Test
	final void changeOption_playerNormal01Test() throws Exception {
		// 対象名
		ReflectionTestUtils.setField(this.testSuite, "chkMember", "PLAYER");
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("8");
		list.add("2");
		list.add("5");
		gameMaster.setCorrectPlayerNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("825", convertListToString(this.testSuite.getChangedNumberList(), null));
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
	 * 交換する数字:7<br>
	 * 交換する桁:1の位<br>
	 * [期待値]<br>
	 * ・交換する番号はLOWナンバー同士・HIGHナンバー同士であること<br>
	 * ・交換した数字:7であること<br>
	 * ・交換した数字の情報:HIGHであること<br>
	 * ・交換した桁:2であること<br>
	 * ・交換後の数字:347であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal01Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("7");
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(2);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("LOW,LOW,HIGH", convertListToString(highLow(this.testSuite.getChangedNumberList())));
		assertEquals("7", this.testSuite.getExNum());
		assertEquals("HIGH", this.testSuite.getLh());
		assertEquals(2, this.testSuite.getDigitInd());
		assertEquals("347", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系02
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 交換する数字:1<br>
	 * 交換する桁:100の位<br>
	 * [期待値]<br>
	 * ・交換する番号はLOWナンバー同士・HIGHナンバー同士であること<br>
	 * ・交換した数字:1であること<br>
	 * ・交換した数字の情報:LOWであること<br>
	 * ・交換した桁:0であること<br>
	 * ・交換後の数字:145であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal02Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("1");
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(0);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("LOW,LOW,HIGH", convertListToString(highLow(this.testSuite.getChangedNumberList())));
		assertEquals("1", this.testSuite.getExNum());
		assertEquals("LOW", this.testSuite.getLh());
		assertEquals(0, this.testSuite.getDigitInd());
		assertEquals("145", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系03
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 1週目の交換する数字:3<br>
	 * 1週目の交換する桁:1の位<br>
	 * 2週目の交換する数字:7<br>
	 * 2週目の交換する桁:1の位<br>
	 * [途中期待値]<br>
	 * ・交換する番号はLOWナンバー同士・HIGHナンバー同士でなく無限ループをの2週目に入ること<br>
	 * [期待値]<br>
	 * ・交換する番号はLOWナンバー同士・HIGHナンバー同士であること<br>
	 * ・交換した数字:7であること<br>
	 * ・交換した数字の情報:HIGHであること<br>
	 * ・交換した桁:2であること<br>
	 * ・交換後の数字:347であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal03Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("3").thenReturn("7");
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(2).thenReturn(2);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("LOW,LOW,HIGH", convertListToString(highLow(this.testSuite.getChangedNumberList())));
		assertEquals("7", this.testSuite.getExNum());
		assertEquals("HIGH", this.testSuite.getLh());
		assertEquals(2, this.testSuite.getDigitInd());
		assertEquals("347", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系04
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 1週目の交換する数字:4<br>
	 * 1週目の交換する桁:10の位<br>
	 * 2週目の交換する数字:1<br>
	 * 2週目の交換する桁:100の位<br>
	 * [途中期待値]<br>
	 * ・交換した後の数字が交換前と同じだった場合、無限ループをの2週目に入ること<br>
	 * [期待値]<br>
	 * ・交換する番号はLOWナンバー同士・HIGHナンバー同士であること<br>
	 * ・交換した数字:1であること<br>
	 * ・交換した数字の情報:LOWであること<br>
	 * ・交換した桁:0であること<br>
	 * ・交換後の数字:145であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal04Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("4").thenReturn("1");
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(1).thenReturn(0);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("LOW,LOW,HIGH", convertListToString(highLow(this.testSuite.getChangedNumberList())));
		assertEquals("1", this.testSuite.getExNum());
		assertEquals("LOW", this.testSuite.getLh());
		assertEquals(0, this.testSuite.getDigitInd());
		assertEquals("145", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系05
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 1週目の交換する数字:8<br>
	 * 1週目の交換する桁:100の位<br>
	 * 2週目の交換する数字:2<br>
	 * 2週目の交換する桁:100の位<br>
	 * [途中期待値]<br>
	 * ・交換する番号はLOWナンバー同士・HIGHナンバー同士でなく無限ループをの2週目に入ること<br>
	 * [期待値]<br>
	 * ・交換する番号はLOWナンバー同士・HIGHナンバー同士であること<br>
	 * ・交換した数字:2であること<br>
	 * ・交換した数字の情報:LOWであること<br>
	 * ・交換した桁:0であること<br>
	 * ・交換後の数字:245であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal05Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("8").thenReturn("2");
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(0).thenReturn(0);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("LOW,LOW,HIGH", convertListToString(highLow(this.testSuite.getChangedNumberList())));
		assertEquals("2", this.testSuite.getExNum());
		assertEquals("LOW", this.testSuite.getLh());
		assertEquals(0, this.testSuite.getDigitInd());
		assertEquals("245", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系06
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * ランダムで交換する数字,桁を選択
	 * [期待値]<br>
	 * ・交換する番号はLOWナンバー同士・HIGHナンバー同士であること<br>
	 * ・交換した数字:nullでないこと<br>
	 * ・交換した桁:nullでないこと<br>
	 * ・交換後の数字:345でないこと<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal06Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(false);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(false);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("LOW,LOW,HIGH", convertListToString(highLow(this.testSuite.getChangedNumberList())));
		assertNotNull(this.testSuite.getExNum());
		assertNotNull(this.testSuite.getDigitInd());
		assertNotEquals("345", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系07
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:NORMAL<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 交換する数字:7<br>
	 * 交換する桁:1の位<br>
	 * [期待値]<br>
	 * ・交換する番号はLOWナンバー同士・HIGHナンバー同士であること<br>
	 * ・交換した数字:7であること<br>
	 * ・交換した数字の情報:HIGHであること<br>
	 * ・交換した桁:2であること<br>
	 * ・交換後の数字:347であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal07Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.NORMAL);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("7");
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(2);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("LOW,LOW,HIGH", convertListToString(highLow(this.testSuite.getChangedNumberList())));
		assertEquals("7", this.testSuite.getExNum());
		assertEquals("HIGH", this.testSuite.getLh());
		assertEquals(2, this.testSuite.getDigitInd());
		assertEquals("347", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系08
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:NORMAL<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 交換する数字:1<br>
	 * 交換する桁:100の位<br>
	 * [期待値]<br>
	 * ・交換する番号はLOWナンバー同士・HIGHナンバー同士であること<br>
	 * ・交換した数字:1であること<br>
	 * ・交換した数字の情報:LOWであること<br>
	 * ・交換した桁:0であること<br>
	 * ・交換後の数字:145であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal08Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.NORMAL);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("1");
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(0);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("LOW,LOW,HIGH", convertListToString(highLow(this.testSuite.getChangedNumberList())));
		assertEquals("1", this.testSuite.getExNum());
		assertEquals("LOW", this.testSuite.getLh());
		assertEquals(0, this.testSuite.getDigitInd());
		assertEquals("145", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系09
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:NORMAL<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 1週目の交換する数字:3<br>
	 * 1週目の交換する桁:1の位<br>
	 * 2週目の交換する数字:7<br>
	 * 2週目の交換する桁:1の位<br>
	 * [途中期待値]<br>
	 * ・交換する番号はLOWナンバー同士・HIGHナンバー同士でなく無限ループをの2週目に入ること<br>
	 * [期待値]<br>
	 * ・交換する番号はLOWナンバー同士・HIGHナンバー同士であること<br>
	 * ・交換した数字:7であること<br>
	 * ・交換した数字の情報:HIGHであること<br>
	 * ・交換した桁:2であること<br>
	 * ・交換後の数字:347であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal09Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.NORMAL);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("3").thenReturn("7");
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(2).thenReturn(2);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("LOW,LOW,HIGH", convertListToString(highLow(this.testSuite.getChangedNumberList())));
		assertEquals("7", this.testSuite.getExNum());
		assertEquals("HIGH", this.testSuite.getLh());
		assertEquals(2, this.testSuite.getDigitInd());
		assertEquals("347", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系10
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:NORMAL<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 1週目の交換する数字:4<br>
	 * 1週目の交換する桁:10の位<br>
	 * 2週目の交換する数字:1<br>
	 * 2週目の交換する桁:100の位<br>
	 * [途中期待値]<br>
	 * ・交換した後の数字が交換前と同じだった場合、無限ループをの2週目に入ること<br>
	 * [期待値]<br>
	 * ・交換する番号はLOWナンバー同士・HIGHナンバー同士であること<br>
	 * ・交換した数字:1であること<br>
	 * ・交換した数字の情報:LOWであること<br>
	 * ・交換した桁:0であること<br>
	 * ・交換後の数字:145であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal10Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.NORMAL);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("4").thenReturn("1");
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(1).thenReturn(0);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("LOW,LOW,HIGH", convertListToString(highLow(this.testSuite.getChangedNumberList())));
		assertEquals("1", this.testSuite.getExNum());
		assertEquals("LOW", this.testSuite.getLh());
		assertEquals(0, this.testSuite.getDigitInd());
		assertEquals("145", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系11
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:NORMAL<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 1週目の交換する数字:8<br>
	 * 1週目の交換する桁:100の位<br>
	 * 2週目の交換する数字:2<br>
	 * 2週目の交換する桁:100の位<br>
	 * [途中期待値]<br>
	 * ・交換する番号はLOWナンバー同士・HIGHナンバー同士でなく無限ループをの2週目に入ること<br>
	 * [期待値]<br>
	 * ・交換する番号はLOWナンバー同士・HIGHナンバー同士であること<br>
	 * ・交換した数字:2であること<br>
	 * ・交換した数字の情報:LOWであること<br>
	 * ・交換した桁:0であること<br>
	 * ・交換後の数字:245であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal11Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.NORMAL);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("8").thenReturn("2");
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(0).thenReturn(0);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("LOW,LOW,HIGH", convertListToString(highLow(this.testSuite.getChangedNumberList())));
		assertEquals("2", this.testSuite.getExNum());
		assertEquals("LOW", this.testSuite.getLh());
		assertEquals(0, this.testSuite.getDigitInd());
		assertEquals("245", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系12
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:NORMAL<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * ランダムで交換する数字,桁を選択
	 * [期待値]<br>
	 * ・交換する番号はLOWナンバー同士・HIGHナンバー同士であること<br>
	 * ・交換した数字:nullでないこと<br>
	 * ・交換した桁:nullでないこと<br>
	 * ・交換後の数字:345でないこと<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal12Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.NORMAL);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(false);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(false);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(false);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("LOW,LOW,HIGH", convertListToString(highLow(this.testSuite.getChangedNumberList())));
		assertNotNull(this.testSuite.getExNum());
		assertNotNull(this.testSuite.getDigitInd());
		assertNotEquals("345", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系13
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:HARD<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 交換する数字:7<br>
	 * 交換する桁:1の位<br>
	 * [期待値]<br>
	 * ・交換する番号はLOWナンバー同士・HIGHナンバー同士であること<br>
	 * ・交換した数字:7であること<br>
	 * ・交換した数字の情報:HIGHであること<br>
	 * ・交換した桁:-1であること(初期化)<br>
	 * ・交換後の数字:347であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal13Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.HARD);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("7");
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(2);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("LOW,LOW,HIGH", convertListToString(highLow(this.testSuite.getChangedNumberList())));
		assertEquals("7", this.testSuite.getExNum());
		assertEquals("HIGH", this.testSuite.getLh());
		assertEquals(-1, this.testSuite.getDigitInd());
		assertEquals("347", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系14
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:HARD<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 交換する数字:1<br>
	 * 交換する桁:100の位<br>
	 * [期待値]<br>
	 * ・交換する番号はLOWナンバー同士・HIGHナンバー同士であること<br>
	 * ・交換した数字:1であること<br>
	 * ・交換した数字の情報:LOWであること<br>
	 * ・交換した桁:-1であること(初期化)<br>
	 * ・交換後の数字:145であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal14Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.HARD);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("1");
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(0);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("LOW,LOW,HIGH", convertListToString(highLow(this.testSuite.getChangedNumberList())));
		assertEquals("1", this.testSuite.getExNum());
		assertEquals("LOW", this.testSuite.getLh());
		assertEquals(-1, this.testSuite.getDigitInd());
		assertEquals("145", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系15
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:HARD<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 1週目の交換する数字:3<br>
	 * 1週目の交換する桁:1の位<br>
	 * 2週目の交換する数字:7<br>
	 * 2週目の交換する桁:1の位<br>
	 * [途中期待値]<br>
	 * ・交換する番号はLOWナンバー同士・HIGHナンバー同士でなく無限ループをの2週目に入ること<br>
	 * [期待値]<br>
	 * ・交換する番号はLOWナンバー同士・HIGHナンバー同士であること<br>
	 * ・交換した数字:7であること<br>
	 * ・交換した数字の情報:HIGHであること<br>
	 * ・交換した桁:-1であること(初期化)<br>
	 * ・交換後の数字:347であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal15Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.HARD);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("3").thenReturn("7");
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(2).thenReturn(2);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("LOW,LOW,HIGH", convertListToString(highLow(this.testSuite.getChangedNumberList())));
		assertEquals("7", this.testSuite.getExNum());
		assertEquals("HIGH", this.testSuite.getLh());
		assertEquals(-1, this.testSuite.getDigitInd());
		assertEquals("347", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系16
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:HARD<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 1週目の交換する数字:4<br>
	 * 1週目の交換する桁:10の位<br>
	 * 2週目の交換する数字:1<br>
	 * 2週目の交換する桁:100の位<br>
	 * [途中期待値]<br>
	 * ・交換した後の数字が交換前と同じだった場合、無限ループをの2週目に入ること<br>
	 * [期待値]<br>
	 * ・交換する番号はLOWナンバー同士・HIGHナンバー同士であること<br>
	 * ・交換した数字:1であること<br>
	 * ・交換した数字の情報:LOWであること<br>
	 * ・交換した桁:-1であること(初期化)<br>
	 * ・交換後の数字:145であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal16Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.HARD);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("4").thenReturn("1");
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(1).thenReturn(0);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("LOW,LOW,HIGH", convertListToString(highLow(this.testSuite.getChangedNumberList())));
		assertEquals("1", this.testSuite.getExNum());
		assertEquals("LOW", this.testSuite.getLh());
		assertEquals(-1, this.testSuite.getDigitInd());
		assertEquals("145", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系17
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:HARD<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 1週目の交換する数字:8<br>
	 * 1週目の交換する桁:100の位<br>
	 * 2週目の交換する数字:2<br>
	 * 2週目の交換する桁:100の位<br>
	 * [途中期待値]<br>
	 * ・交換する番号はLOWナンバー同士・HIGHナンバー同士でなく無限ループをの2週目に入ること<br>
	 * [期待値]<br>
	 * ・交換する番号はLOWナンバー同士・HIGHナンバー同士であること<br>
	 * ・交換した数字:2であること<br>
	 * ・交換した数字の情報:LOWであること<br>
	 * ・交換した桁:-1であること(初期化)<br>
	 * ・交換後の数字:245であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal17Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.HARD);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("8").thenReturn("2");
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(0).thenReturn(0);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("LOW,LOW,HIGH", convertListToString(highLow(this.testSuite.getChangedNumberList())));
		assertEquals("2", this.testSuite.getExNum());
		assertEquals("LOW", this.testSuite.getLh());
		assertEquals(-1, this.testSuite.getDigitInd());
		assertEquals("245", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系18
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:HARD<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * ランダムで交換する数字,桁を選択
	 * [期待値]<br>
	 * ・交換する番号はLOWナンバー同士・HIGHナンバー同士であること<br>
	 * ・交換した数字:nullでないこと<br>
	 * ・交換した桁:-1であること(初期化)<br>
	 * ・交換後の数字:345でないこと<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal18Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.HARD);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(false);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(false);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(false);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("LOW,LOW,HIGH", convertListToString(highLow(this.testSuite.getChangedNumberList())));
		assertNotNull(this.testSuite.getExNum());
		assertEquals(-1, this.testSuite.getDigitInd());
		assertNotEquals("345", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系19
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EXHAUSTED<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 交換する数字:9<br>
	 * 交換する桁:1の位<br>
	 * [期待値]<br>
	 * ・交換した数字:9であること<br>
	 * ・交換した数字の情報:NOTCLEARであること(初期化)<br>
	 * ・交換した桁:2であること<br>
	 * ・交換後の数字:347であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal19Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EXHAUSTED);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("9");
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(2);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("9", this.testSuite.getExNum());
		assertEquals("NOTCLEAR", this.testSuite.getLh());
		assertEquals(2, this.testSuite.getDigitInd());
		assertEquals("349", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系20
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EXHAUSTED<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 交換する数字:2<br>
	 * 交換する桁:100の位<br>
	 * [期待値]<br>
	 * ・交換した数字:2であること<br>
	 * ・交換した数字の情報:NOTCLEARであること(初期化)<br>
	 * ・交換した桁:0であること<br>
	 * ・交換後の数字:245であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal20Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EXHAUSTED);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("2");
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(0);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("2", this.testSuite.getExNum());
		assertEquals("NOTCLEAR", this.testSuite.getLh());
		assertEquals(0, this.testSuite.getDigitInd());
		assertEquals("245", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系21
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EXHAUSTED<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 交換する数字:1<br>
	 * 交換する桁:1の位<br>
	 * [期待値]<br>
	 * ・交換した数字:2であること<br>
	 * ・交換した数字の情報:NOTCLEARであること(初期化)<br>
	 * ・交換した桁:2であること<br>
	 * ・交換後の数字:341であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal21Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EXHAUSTED);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("1");
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(2);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("1", this.testSuite.getExNum());
		assertEquals("NOTCLEAR", this.testSuite.getLh());
		assertEquals(2, this.testSuite.getDigitInd());
		assertEquals("341", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系22
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EXHAUSTED<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 交換する数字:9<br>
	 * 交換する桁:100の位<br>
	 * [期待値]<br>
	 * ・交換した数字:9であること<br>
	 * ・交換した数字の情報:NOTCLEARであること(初期化)<br>
	 * ・交換した桁:0であること<br>
	 * ・交換後の数字:945であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal22Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EXHAUSTED);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("9");
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(0);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("9", this.testSuite.getExNum());
		assertEquals("NOTCLEAR", this.testSuite.getLh());
		assertEquals(0, this.testSuite.getDigitInd());
		assertEquals("945", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系23
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EXHAUSTED<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 1週目の交換する数字:3<br>
	 * 1週目の交換する桁:1の位<br>
	 * 2週目の交換する数字:7<br>
	 * 2週目の交換する桁:1の位<br>
	 * [途中期待値]<br>
	 * ・交換した数字がすでに含まれているかつそれが同じ桁ではない場合、無限ループの2週目に入ること<br>
	 * [期待値]<br>
	 * ・交換した数字:7であること<br>
	 * ・交換した数字の情報:NOTCLEARであること(初期化)<br>
	 * ・交換した桁:2であること<br>
	 * ・交換後の数字:347であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal23Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EXHAUSTED);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("3").thenReturn("7");
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(2).thenReturn(2);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("7", this.testSuite.getExNum());
		assertEquals("NOTCLEAR", this.testSuite.getLh());
		assertEquals(2, this.testSuite.getDigitInd());
		assertEquals("347", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系24
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:INSANE<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 交換する数字:8<br>
	 * 交換する桁:100の位<br>
	 * [途中期待値]<br>
	 * ・交換するかしないかを判断するフラグ関数がtrueになる状態<br>
	 * [期待値]<br>
	 * ・交換した数字:8であること<br>
	 * ・交換した数字の情報:NOTCLEARであること(初期化)<br>
	 * ・交換した桁:-1であること(初期化)<br>
	 * ・交換後の数字:845であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal24Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.INSANE);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("8");
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(0);
		when(this.mapUtil.getDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("1");
		// 検証
		this.testSuite.changeLogic();
		assertEquals("8", this.testSuite.getExNum());
		assertEquals("NOTCLEAR", this.testSuite.getLh());
		assertEquals(-1, this.testSuite.getDigitInd());
		assertEquals("845", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系25
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:INSANE<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * [途中期待値]<br>
	 * ・交換するかしないかを判断するフラグ関数がfalseになる状態<br>
	 * [期待値]<br>
	 * ・交換した数字:0(初期化)であること<br>
	 * ・交換した数字の情報:NOTCLEARであること<br>
	 * ・交換した桁:-1であること<br>
	 * ・交換後の数字:345であること(交換されないこと)<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal25Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.INSANE);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("2");
		// 検証
		this.testSuite.changeLogic();
		assertEquals("0", this.testSuite.getExNum());
		assertEquals("NOTCLEAR", this.testSuite.getLh());
		assertEquals(-1, this.testSuite.getDigitInd());
		assertEquals("345", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系26
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:INSANE<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 交換する数字:2<br>
	 * 交換する桁:1の位<br>
	 * [途中期待値]<br>
	 * ・交換するかしないかを判断するフラグ関数がtrueになる状態<br>
	 * [期待値]<br>
	 * ・交換した数字:2であること<br>
	 * ・交換した数字の情報:NOTCLEARであること(初期化)<br>
	 * ・交換した桁:-1であること(初期化)<br>
	 * ・交換後の数字:342であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal26Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.INSANE);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("2");
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(2);
		when(this.mapUtil.getDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("1");
		// 検証
		this.testSuite.changeLogic();
		assertEquals("2", this.testSuite.getExNum());
		assertEquals("NOTCLEAR", this.testSuite.getLh());
		assertEquals(-1, this.testSuite.getDigitInd());
		assertEquals("342", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系27
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:INSANE<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 交換する数字:4<br>
	 * 交換する桁:10の位<br>
	 * [途中期待値]<br>
	 * ・交換するかしないかを判断するフラグ関数がtrueになる状態<br>
	 * [期待値]<br>
	 * ・交換した数字:4であること<br>
	 * ・交換した数字の情報:NOTCLEARであること(初期化)<br>
	 * ・交換した桁:-1であること(初期化)<br>
	 * ・交換後の数字:345であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal27Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.INSANE);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("4");
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(1);
		when(this.mapUtil.getDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("1");
		// 検証
		this.testSuite.changeLogic();
		assertEquals("4", this.testSuite.getExNum());
		assertEquals("NOTCLEAR", this.testSuite.getLh());
		assertEquals(-1, this.testSuite.getDigitInd());
		assertEquals("345", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系28
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁数:4<br>
	 * チェンジ対象数字:1267<br>
	 * 1週目の交換する数字:6<br>
	 * 1週目の交換する桁:1000の位<br>
	 * 2週目の交換する数字:7<br>
	 * 2週目の交換する桁:1の位<br>
	 * 3週目の交換する数字:1<br>
	 * 3週目の交換する桁:100の位<br>
	 * 4週目の交換する数字:9<br>
	 * 4週目の交換する桁:10の位<br>
	 * [途中期待値]<br>
	 * ・無限ループを4週すること<br>
	 * [期待値]<br>
	 * ・交換する番号はLOWナンバー同士・HIGHナンバー同士であること<br>
	 * ・交換した数字:9であること<br>
	 * ・交換した数字の情報:HIGHであること<br>
	 * ・交換した桁:2であること<br>
	 * ・交換後の数字:1297であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal28Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(4);
		gameMaster.setDifficulty(DifficultyConst.EASY);
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
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("1").thenReturn("6").thenReturn("7").thenReturn("1").thenReturn("9");
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(0).thenReturn(3).thenReturn(1).thenReturn(2);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("LOW,LOW,HIGH,HIGH", convertListToString(highLow(this.testSuite.getChangedNumberList())));
		assertEquals("9", this.testSuite.getExNum());
		assertEquals("HIGH", this.testSuite.getLh());
		assertEquals(2, this.testSuite.getDigitInd());
		assertEquals("1297", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系28
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:HARD<br>
	 * 桁数:5<br>
	 * チェンジ対象数字:12679<br>
	 * 交換する数字:8<br>
	 * 交換する桁:10の位<br>
	 * [途中期待値]<br>
	 * 各最優先フラグから取得するようにすること<br>
	 * [期待値]<br>
	 * ・交換する番号はLOWナンバー同士・HIGHナンバー同士であること<br>
	 * ・交換した数字:8であること<br>
	 * ・交換した数字の情報:HIGHであること<br>
	 * ・交換した桁:-1であること(初期化)<br>
	 * ・交換後の数字:12689であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal29Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(DifficultyConst.HARD);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("6");
		list.add("7");
		list.add("9");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.containValueDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getSelectNumberPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("8");
		when(this.mapUtil.getDigitPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn(3);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("LOW,LOW,HIGH,HIGH,HIGH", convertListToString(highLow(this.testSuite.getChangedNumberList())));
		assertEquals("8", this.testSuite.getExNum());
		assertEquals("HIGH", this.testSuite.getLh());
		assertEquals(-1, this.testSuite.getDigitInd());
		assertEquals("12689", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系30
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:INSANE<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * [途中期待値]<br>
	 * ・交換するかしないかを判断するフラグ関数がfalseになる状態<br>
	 * [期待値]<br>
	 * ・交換した数字:nullでないこと<br>
	 * ・交換した数字の情報:NOTCLEARであること<br>
	 * ・交換した桁:-1であること<br>
	 * ・交換後の数字:nullでないこと(交換したかわからないため)<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_cpuNormal30Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.INSANE);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(false);
		// 検証
		this.testSuite.changeLogic();
		assertNotNull(this.testSuite.getExNum());
		assertEquals("NOTCLEAR", this.testSuite.getLh());
		assertEquals(-1, this.testSuite.getDigitInd());
		assertNotNull(this.testSuite.getChangedNumberList());
	}

	/**
	 * 文字連結をおこなう。
	 * @param array 文字列リスト
	 * @param key カンマ付きか否か
	 * @return returnNum 連結後の文字列
	 */
	private String convertListToString(ArrayList<String> array, String key) {
		if (array == null) {
			return null;
		}

		String returnNum = "";
		for (String str : array) {
			if ("comma".equals(key) && returnNum.length() > 0) {
				returnNum += ",";
			}
			returnNum += str;
		}
		return returnNum;
	}

	/**
	 * カンマ付き文字連結をおこなう。
	 * @param array 文字列リスト
	 * @param key カンマ付きか否か
	 * @return returnNum 連結後の文字列
	 */
	private String convertListToString(ArrayList<String> array) {
		return convertListToString(array, "comma");
	}

	/**
	 * HighLowの関係を調べる
	 * @param array 文字列リスト
	 * @return returnNum 連結後の文字列
	 */
	private ArrayList<String> highLow(ArrayList<String> list) {
		ArrayList<String> hlList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			String hl = null;
			Integer num = Integer.parseInt(list.get(i));
			if (0 <= num.compareTo(Integer.parseInt(Numer0nSelectNumberEnum.ZERO.getNum())) &&
					0 >= num.compareTo(Integer.parseInt(Numer0nSelectNumberEnum.FOUR.getNum()))) {
				hl = Const.LOW;
			} else if (0 <= num.compareTo(Integer.parseInt(Numer0nSelectNumberEnum.FIVE.getNum()))
					&& 0 >= num.compareTo(Integer.parseInt(Numer0nSelectNumberEnum.NINE.getNum()))) {
				hl = Const.HIGH;
			}
			hlList.add(hl);
		}
		return hlList;
	}

}
