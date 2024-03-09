package application.logic.option.ut;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;

import application.component.consts.Const;
import application.component.consts.Numer0nSelectNumberEnum;
import application.component.error.CreateErrorExceptionComponentImpl;
import application.logic.human.GameMaster;
import application.logic.option.ChangeOption;
import application.logic.option.map.ChangeOptionMapUtil;

/**
 * チェンジオプションテストクラス
 * <p>
 * 本物の処理を確認し、info情報を確認する
 * </p>
 * @author shiraishitoshio
 *
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Tag("UT")
public class ChangeOption_realInfoMapUTTest {

	@InjectMocks
	private ChangeOption testSuite;

	/**
	 * <p>
	 * CPU正常系01
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 交換する数字:2:最優先フラグ付き
	 * 宣言する桁:100の位:最優先フラグ付き<br>
	 * [期待値]<br>
	 * ・交換した数字:2であること<br>
	 * ・交換した数字の情報:LOWであること<br>
	 * ・交換した桁:0であること<br>
	 * ・交換後の数字:245であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_realInfoMap_cpuNormal01Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.EASY);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		ChangeOptionMapUtil mapUtil = spy(new ChangeOptionMapUtil());
		mapUtil.clearSelectNumberPriorityMap();
		mapUtil.clearDigitPriorityMap();
		mapUtil.addSelectNumberPriorityMap(Numer0nSelectNumberEnum.TWO.getNum(), Const.SAI_YUUSEN_FLAG);
		mapUtil.addDigitPriorityMap(Const.ZERO_D, Const.SAI_YUUSEN_FLAG);
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
		// 例外
		CreateErrorExceptionComponentImpl exceptionComponent = spy(new CreateErrorExceptionComponentImpl());
		ReflectionTestUtils.setField(this.testSuite, "exceptionComponent", exceptionComponent);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("2", this.testSuite.getExNum());
		assertEquals("LOW", this.testSuite.getLh());
		assertEquals(0, this.testSuite.getDigitInd());
		assertEquals("245", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系02
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:819<br>
	 * 交換する数字:6:最優先フラグ付き
	 * 宣言する桁:1の位:最優先フラグ付き<br>
	 * [期待値]<br>
	 * ・交換した数字:6であること<br>
	 * ・交換した数字の情報:HIGHであること<br>
	 * ・交換した桁:2であること<br>
	 * ・交換後の数字:816であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_realInfoMap_cpuNormal02Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.EASY);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("8");
		list.add("1");
		list.add("9");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		ChangeOptionMapUtil mapUtil = spy(new ChangeOptionMapUtil());
		mapUtil.clearSelectNumberPriorityMap();
		mapUtil.clearDigitPriorityMap();
		mapUtil.addSelectNumberPriorityMap(Numer0nSelectNumberEnum.SIX.getNum(), Const.SAI_YUUSEN_FLAG);
		mapUtil.addDigitPriorityMap(Const.TWO_D, Const.SAI_YUUSEN_FLAG);
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
		// 例外
		CreateErrorExceptionComponentImpl exceptionComponent = spy(new CreateErrorExceptionComponentImpl());
		ReflectionTestUtils.setField(this.testSuite, "exceptionComponent", exceptionComponent);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("6", this.testSuite.getExNum());
		assertEquals("HIGH", this.testSuite.getLh());
		assertEquals(2, this.testSuite.getDigitInd());
		assertEquals("816", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系03
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:819<br>
	 * 交換する数字:ランダム<br>
	 * 宣言する桁:ランダム<br>
	 * [期待値]<br>
	 * ・交換した数字:nullでないこと<br>
	 * ・交換した数字の情報:nullでないこと<br>
	 * ・交換した桁:nullでないこと<br>
	 * ・交換後の数字:816とは異なること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_realInfoMap_cpuNormal03Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.EASY);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("8");
		list.add("1");
		list.add("9");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		ChangeOptionMapUtil mapUtil = spy(new ChangeOptionMapUtil());
		mapUtil.clearSelectNumberPriorityMap();
		mapUtil.clearDigitPriorityMap();
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
		// 例外
		CreateErrorExceptionComponentImpl exceptionComponent = spy(new CreateErrorExceptionComponentImpl());
		ReflectionTestUtils.setField(this.testSuite, "exceptionComponent", exceptionComponent);
		// 検証
		this.testSuite.changeLogic();
		assertNotNull(this.testSuite.getExNum());
		assertNotNull(this.testSuite.getLh());
		assertNotNull(this.testSuite.getDigitInd());
		assertNotEquals("816", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系04
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:NORMAL<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 交換する数字:2:最優先フラグ付き
	 * 宣言する桁:100の位:最優先フラグ付き<br>
	 * [期待値]<br>
	 * ・交換した数字:2であること<br>
	 * ・交換した数字の情報:LOWであること<br>
	 * ・交換した桁:0であること<br>
	 * ・交換後の数字:245であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_realInfoMap_cpuNormal04Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.NORMAL);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		ChangeOptionMapUtil mapUtil = spy(new ChangeOptionMapUtil());
		mapUtil.clearSelectNumberPriorityMap();
		mapUtil.clearDigitPriorityMap();
		mapUtil.addSelectNumberPriorityMap(Numer0nSelectNumberEnum.TWO.getNum(), Const.SAI_YUUSEN_FLAG);
		mapUtil.addDigitPriorityMap(Const.ZERO_D, Const.SAI_YUUSEN_FLAG);
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
		// 例外
		CreateErrorExceptionComponentImpl exceptionComponent = spy(new CreateErrorExceptionComponentImpl());
		ReflectionTestUtils.setField(this.testSuite, "exceptionComponent", exceptionComponent);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("2", this.testSuite.getExNum());
		assertEquals("LOW", this.testSuite.getLh());
		assertEquals(0, this.testSuite.getDigitInd());
		assertEquals("245", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系05
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:NORMAL<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:819<br>
	 * 交換する数字:6:最優先フラグ付き
	 * 宣言する桁:1の位:最優先フラグ付き<br>
	 * [期待値]<br>
	 * ・交換した数字:6であること<br>
	 * ・交換した数字の情報:HIGHであること<br>
	 * ・交換した桁:2であること<br>
	 * ・交換後の数字:816であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_realInfoMap_cpuNormal05Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.NORMAL);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("8");
		list.add("1");
		list.add("9");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		ChangeOptionMapUtil mapUtil = spy(new ChangeOptionMapUtil());
		mapUtil.clearSelectNumberPriorityMap();
		mapUtil.clearDigitPriorityMap();
		mapUtil.addSelectNumberPriorityMap(Numer0nSelectNumberEnum.SIX.getNum(), Const.SAI_YUUSEN_FLAG);
		mapUtil.addDigitPriorityMap(Const.TWO_D, Const.SAI_YUUSEN_FLAG);
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
		// 例外
		CreateErrorExceptionComponentImpl exceptionComponent = spy(new CreateErrorExceptionComponentImpl());
		ReflectionTestUtils.setField(this.testSuite, "exceptionComponent", exceptionComponent);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("6", this.testSuite.getExNum());
		assertEquals("HIGH", this.testSuite.getLh());
		assertEquals(2, this.testSuite.getDigitInd());
		assertEquals("816", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系06
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:NORMAL<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:819<br>
	 * 交換する数字:ランダム<br>
	 * 宣言する桁:ランダム<br>
	 * [期待値]<br>
	 * ・交換した数字:nullでないこと<br>
	 * ・交換した数字の情報:nullでないこと<br>
	 * ・交換した桁:nullでないこと<br>
	 * ・交換後の数字:816とは異なること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_realInfoMap_cpuNormal06Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.NORMAL);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("8");
		list.add("1");
		list.add("9");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		ChangeOptionMapUtil mapUtil = spy(new ChangeOptionMapUtil());
		mapUtil.clearSelectNumberPriorityMap();
		mapUtil.clearDigitPriorityMap();
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
		// 例外
		CreateErrorExceptionComponentImpl exceptionComponent = spy(new CreateErrorExceptionComponentImpl());
		ReflectionTestUtils.setField(this.testSuite, "exceptionComponent", exceptionComponent);
		// 検証
		this.testSuite.changeLogic();
		assertNotNull(this.testSuite.getExNum());
		assertNotNull(this.testSuite.getLh());
		assertNotNull(this.testSuite.getDigitInd());
		assertNotNull(convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系07
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:HARD<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 交換する数字:2:最優先フラグ付き
	 * 宣言する桁:100の位:最優先フラグ付き<br>
	 * [期待値]<br>
	 * ・交換した数字:2であること<br>
	 * ・交換した数字の情報:LOWであること<br>
	 * ・交換した桁:-1であること<br>
	 * ・交換後の数字:245であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_realInfoMap_cpuNormal07Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.HARD);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		ChangeOptionMapUtil mapUtil = spy(new ChangeOptionMapUtil());
		mapUtil.clearSelectNumberPriorityMap();
		mapUtil.clearDigitPriorityMap();
		mapUtil.addSelectNumberPriorityMap(Numer0nSelectNumberEnum.TWO.getNum(), Const.SAI_YUUSEN_FLAG);
		mapUtil.addDigitPriorityMap(Const.ZERO_D, Const.SAI_YUUSEN_FLAG);
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
		// 例外
		CreateErrorExceptionComponentImpl exceptionComponent = spy(new CreateErrorExceptionComponentImpl());
		ReflectionTestUtils.setField(this.testSuite, "exceptionComponent", exceptionComponent);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("2", this.testSuite.getExNum());
		assertEquals("LOW", this.testSuite.getLh());
		assertEquals(-1, this.testSuite.getDigitInd());
		assertEquals("245", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系08
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:HARD<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:819<br>
	 * 交換する数字:6:最優先フラグ付き
	 * 宣言する桁:1の位:最優先フラグ付き<br>
	 * [期待値]<br>
	 * ・交換した数字:6であること<br>
	 * ・交換した数字の情報:HIGHであること<br>
	 * ・交換した桁:-1であること<br>
	 * ・交換後の数字:816であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_realInfoMap_cpuNormal08Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.HARD);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("8");
		list.add("1");
		list.add("9");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		ChangeOptionMapUtil mapUtil = spy(new ChangeOptionMapUtil());
		mapUtil.clearSelectNumberPriorityMap();
		mapUtil.clearDigitPriorityMap();
		mapUtil.addSelectNumberPriorityMap(Numer0nSelectNumberEnum.SIX.getNum(), Const.SAI_YUUSEN_FLAG);
		mapUtil.addDigitPriorityMap(Const.TWO_D, Const.SAI_YUUSEN_FLAG);
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
		// 例外
		CreateErrorExceptionComponentImpl exceptionComponent = spy(new CreateErrorExceptionComponentImpl());
		ReflectionTestUtils.setField(this.testSuite, "exceptionComponent", exceptionComponent);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("6", this.testSuite.getExNum());
		assertEquals("HIGH", this.testSuite.getLh());
		assertEquals(-1, this.testSuite.getDigitInd());
		assertEquals("816", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系09
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:HARD<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:819<br>
	 * 交換する数字:ランダム<br>
	 * 宣言する桁:ランダム<br>
	 * [期待値]<br>
	 * ・交換した数字:nullでないこと<br>
	 * ・交換した数字の情報:nullでないこと<br>
	 * ・交換した桁:nullでないこと<br>
	 * ・交換後の数字:816とは異なること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_realInfoMap_cpuNormal09Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.HARD);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("8");
		list.add("1");
		list.add("9");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		ChangeOptionMapUtil mapUtil = spy(new ChangeOptionMapUtil());
		mapUtil.clearSelectNumberPriorityMap();
		mapUtil.clearDigitPriorityMap();
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
		// 例外
		CreateErrorExceptionComponentImpl exceptionComponent = spy(new CreateErrorExceptionComponentImpl());
		ReflectionTestUtils.setField(this.testSuite, "exceptionComponent", exceptionComponent);
		// 検証
		this.testSuite.changeLogic();
		assertNotNull(this.testSuite.getExNum());
		assertNotNull(this.testSuite.getLh());
		assertNotNull(this.testSuite.getDigitInd());
		assertNotEquals("816", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系10
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EXHAUSTED<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:819<br>
	 * 交換する数字:6,8:最優先フラグ付き
	 * 宣言する桁:1の位:最優先フラグ付き<br>
	 * [期待値]<br>
	 * ・交換した数字:-1であること<br>
	 * ・交換した数字の情報:NOTCLEARであること<br>
	 * ・交換した桁:2であること<br>
	 * ・交換後の数字:816であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_realInfoMap_cpuNormal10Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.EXHAUSTED);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("8");
		list.add("1");
		list.add("9");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		ChangeOptionMapUtil mapUtil = spy(new ChangeOptionMapUtil());
		mapUtil.clearSelectNumberPriorityMap();
		mapUtil.clearDigitPriorityMap();
		mapUtil.addSelectNumberPriorityMap(Numer0nSelectNumberEnum.SIX.getNum(), Const.SAI_YUUSEN_FLAG);
		mapUtil.addSelectNumberPriorityMap(Numer0nSelectNumberEnum.EIGHT.getNum(), Const.SAI_YUUSEN_FLAG);
		mapUtil.addDigitPriorityMap(Const.TWO_D, Const.SAI_YUUSEN_FLAG);
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
		// 例外
		CreateErrorExceptionComponentImpl exceptionComponent = spy(new CreateErrorExceptionComponentImpl());
		ReflectionTestUtils.setField(this.testSuite, "exceptionComponent", exceptionComponent);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("6", this.testSuite.getExNum());
		assertEquals("NOTCLEAR", this.testSuite.getLh());
		assertEquals(2, this.testSuite.getDigitInd());
		assertEquals("816", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系11
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EXHAUSTED<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:819<br>
	 * 交換する数字:ランダム<br>
	 * 宣言する桁:ランダム<br>
	 * [期待値]<br>
	 * ・交換した数字:nullでないこと<br>
	 * ・交換した数字の情報:NOTCLEARであること<br>
	 * ・交換した桁:nullでないこと<br>
	 * ・交換後の数字:816とは異なること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_realInfoMap_cpuNormal11Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.EXHAUSTED);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("8");
		list.add("1");
		list.add("9");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		ChangeOptionMapUtil mapUtil = spy(new ChangeOptionMapUtil());
		mapUtil.clearSelectNumberPriorityMap();
		mapUtil.clearDigitPriorityMap();
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
		// 例外
		CreateErrorExceptionComponentImpl exceptionComponent = spy(new CreateErrorExceptionComponentImpl());
		ReflectionTestUtils.setField(this.testSuite, "exceptionComponent", exceptionComponent);
		// 検証
		this.testSuite.changeLogic();
		assertNotNull(this.testSuite.getExNum());
		assertEquals("NOTCLEAR", this.testSuite.getLh());
		assertNotNull(this.testSuite.getDigitInd());
		assertNotEquals("816", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系12
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:INSANE<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 交換するか:false:最優先フラグ付き<br>
	 * [期待値]<br>
	 * ・交換した数字:nullであること<br>
	 * ・交換した数字の情報:NOTCLEARであること<br>
	 * ・交換した桁:-1であること<br>
	 * ・交換後の数字:345であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_realInfoMap_cpuNormal12Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.INSANE);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		ChangeOptionMapUtil mapUtil = spy(new ChangeOptionMapUtil());
		mapUtil.clearDoPriorityMap();
		mapUtil.clearSelectNumberPriorityMap();
		mapUtil.clearDigitPriorityMap();
		mapUtil.addDoPriorityMap(Const.CHANGE_REJECT, Const.SAI_YUUSEN_FLAG);
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
		// 例外
		CreateErrorExceptionComponentImpl exceptionComponent = spy(new CreateErrorExceptionComponentImpl());
		ReflectionTestUtils.setField(this.testSuite, "exceptionComponent", exceptionComponent);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("0", this.testSuite.getExNum());
		assertEquals("NOTCLEAR", this.testSuite.getLh());
		assertEquals(-1, this.testSuite.getDigitInd());
		assertEquals("345", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系13
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:INSANE<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 交換するか:true:最優先フラグ付き<br>
	 * 交換する数字:2:最優先フラグ付き<br>
	 * 宣言する桁:100の位:最優先フラグ付き<br>
	 * [期待値]<br>
	 * ・交換した数字:2であること<br>
	 * ・交換した数字の情報:NOTCLEARであること<br>
	 * ・交換した桁:-1であること<br>
	 * ・交換後の数字:245であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_realInfoMap_cpuNormal13Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.INSANE);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		ChangeOptionMapUtil mapUtil = spy(new ChangeOptionMapUtil());
		mapUtil.clearDoPriorityMap();
		mapUtil.clearSelectNumberPriorityMap();
		mapUtil.clearDigitPriorityMap();
		mapUtil.addDoPriorityMap(Const.CHANGE_GO, Const.SAI_YUUSEN_FLAG);
		mapUtil.addSelectNumberPriorityMap(Numer0nSelectNumberEnum.TWO.getNum(), Const.SAI_YUUSEN_FLAG);
		mapUtil.addDigitPriorityMap(Const.ZERO_D, Const.SAI_YUUSEN_FLAG);
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
		// 例外
		CreateErrorExceptionComponentImpl exceptionComponent = spy(new CreateErrorExceptionComponentImpl());
		ReflectionTestUtils.setField(this.testSuite, "exceptionComponent", exceptionComponent);
		// 検証
		this.testSuite.changeLogic();
		assertEquals("2", this.testSuite.getExNum());
		assertEquals("NOTCLEAR", this.testSuite.getLh());
		assertEquals(-1, this.testSuite.getDigitInd());
		assertEquals("245", convertListToString(this.testSuite.getChangedNumberList(), null));
	}

	/**
	 * <p>
	 * CPU正常系14
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:INSANE<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:819<br>
	 * 交換する数字:ランダム<br>
	 * 宣言する桁:ランダム<br>
	 * [期待値]<br>
	 * ・交換した数字:nullでないこと<br>
	 * ・交換した数字の情報:NOTCLEARであること<br>
	 * ・交換した桁:-1であること<br>
	 * ・交換後の数字:nullでないこと<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_realInfoMap_cpuNormal14Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.INSANE);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("8");
		list.add("1");
		list.add("9");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		ChangeOptionMapUtil mapUtil = spy(new ChangeOptionMapUtil());
		mapUtil.clearDoPriorityMap();
		mapUtil.clearSelectNumberPriorityMap();
		mapUtil.clearDigitPriorityMap();
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
		// 例外
		CreateErrorExceptionComponentImpl exceptionComponent = spy(new CreateErrorExceptionComponentImpl());
		ReflectionTestUtils.setField(this.testSuite, "exceptionComponent", exceptionComponent);
		// 検証
		this.testSuite.changeLogic();
		assertNotNull(this.testSuite.getExNum());
		assertEquals("NOTCLEAR", this.testSuite.getLh());
		assertEquals(-1, this.testSuite.getDigitInd());
		assertNotNull(convertListToString(this.testSuite.getChangedNumberList(), null));
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

}
