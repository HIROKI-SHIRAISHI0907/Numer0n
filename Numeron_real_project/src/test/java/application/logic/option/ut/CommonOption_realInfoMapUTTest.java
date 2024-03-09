package application.logic.option.ut;

import static org.junit.Assert.*;
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

import application.component.consts.Const;
import application.component.consts.Numer0nDigitEnum;
import application.component.consts.Numer0nSelectNumberEnum;
import application.component.error.CreateErrorExceptionComponentImpl;
import application.logic.human.Computer;
import application.logic.human.GameMaster;
import application.logic.human.Player;
import application.logic.human.gameComponent.GameComponentUtil;
import application.logic.info.Numer0nInfo;
import application.logic.judge.Eatbite;
import application.logic.option.ChangeOption;
import application.logic.option.CommonOptionImpl;
import application.logic.option.DoubleOption;
import application.logic.option.HighlowOption;
import application.logic.option.ShuffleOption;
import application.logic.option.SlashOption;
import application.logic.option.TargetOption;
import application.logic.option.map.ChangeOptionMapUtil;
import application.logic.option.map.DoubleOptionMapUtil;
import application.logic.option.map.ShuffleOptionMapUtil;
import application.logic.option.map.TargetOptionMapUtil;

/**
 * オプション選択テストクラス
 * <p>
 * 本物の処理を確認し、info情報を確認する
 * </p>
 * @author shiraishitoshio
 *
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Tag("UT")
public class CommonOption_realInfoMapUTTest {

	/**
	 * PLAYER名
	 */
	private static final String PLAYER = "PLAYER";

	/**
	 * GameComponentUtil
	 */
	@Mock
	private GameComponentUtil util;

	/**
	 * CommonOptionImpl
	 */
	@InjectMocks
	private CommonOptionImpl testSuite;

	/**
	 * <p>
	 * PLAYER正常系01
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * オプション:DOUBLE<br>
	 * player,cpu正しい数字:126<br>
	 * コール数字:750,123
	 * [期待値]<br>
	 * ・0(Numer0n_CONTINUE)であること<br>
	 * ・playerのinfoにDOUBLE,750,0EAT0BITE,DOUBLE,123,2EAT0BITEが格納されていること<br>
	 * ・cpuのinfoにDOUBLE,1,6,DOUBLE,--,--が格納されていること
	 * @throws Exception
	 */
	@Test
	final void commonOption_playerNormal01Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		// 事前提供対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("6");
		gameMaster.setCorrectPlayerNumberList(list);
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		DoubleOptionMapUtil mapUtil = spy(new DoubleOptionMapUtil());
		mapUtil.clearDigitPriorityMap();
		mapUtil.addDigitPriorityMap(Numer0nDigitEnum.ONED.getDigit(), Const.SAI_YUUSEN_FLAG);
		// 情報
		Numer0nInfo info = spy(new Numer0nInfo());
		ReflectionTestUtils.setField(this.testSuite, "info", info);
		// Player,Computer
		Player player = spy(new Player(gameMaster, info, util));
		ArrayList<String> calllist1 = new ArrayList<>();
		calllist1.add("7");
		calllist1.add("5");
		calllist1.add("0");
		ArrayList<String> calllist2 = new ArrayList<>();
		calllist2.add("1");
		calllist2.add("2");
		calllist2.add("3");
		doNothing().when(player).doCallNumber();
		when(player.getCallNumber()).thenReturn(calllist1).thenReturn(calllist2);
		Eatbite eatBite = spy(new Eatbite(gameMaster));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, info, util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// オプション
		DoubleOption doubleOption = spy(new DoubleOption(gameMaster, mapUtil, computer, player, eatBite, info));
		ReflectionTestUtils.setField(this.testSuite, "doubleOption", doubleOption);
		// 検証
		int result = this.testSuite.summarizeOption(Const.DOUBLE);
		assertEquals(Const.Numer0n_CONTINUE, result);
		ArrayList<String> playerInfoList = new ArrayList<String>();
		playerInfoList.add("DOUBLE,750,0EAT0BITE");
		playerInfoList.add("DOUBLE,123,2EAT0BITE");
		assertEquals(playerInfoList, info.getPlayerInfoList());
		ArrayList<String> cpuInfoList = new ArrayList<String>();
		cpuInfoList.add("DOUBLE,1,2");
		cpuInfoList.add("DOUBLE,--,--");
		assertEquals(cpuInfoList, info.getCpuInfoList());
	}

	/**
	 * <p>
	 * PLAYER正常系02
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * オプション:DOUBLE<br>
	 * player,cpu正しい数字:123<br>
	 * コール数字:750,123
	 * [期待値]<br>
	 * ・1(Numer0n_GAMEOVER)であること<br>
	 * ・playerのinfoにDOUBLE,750,0EAT0BITE,DOUBLE,123,3EAT0BITEが格納されていること
	 * @throws Exception
	 */
	@Test
	final void commonOption_playerNormal02Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		// 事前提供対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		gameMaster.setCorrectPlayerNumberList(list);
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		DoubleOptionMapUtil mapUtil = spy(new DoubleOptionMapUtil());
		mapUtil.clearDigitPriorityMap();
		// 情報
		Numer0nInfo info = spy(new Numer0nInfo());
		ReflectionTestUtils.setField(this.testSuite, "info", info);
		// Player,Computer
		Player player = spy(new Player(gameMaster, info, util));
		ArrayList<String> calllist1 = new ArrayList<>();
		calllist1.add("7");
		calllist1.add("5");
		calllist1.add("0");
		ArrayList<String> calllist2 = new ArrayList<>();
		calllist2.add("1");
		calllist2.add("2");
		calllist2.add("3");
		doNothing().when(player).doCallNumber();
		when(player.getCallNumber()).thenReturn(calllist1).thenReturn(calllist2);
		Eatbite eatBite = spy(new Eatbite(gameMaster));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, info, util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// オプション
		DoubleOption doubleOption = spy(new DoubleOption(gameMaster, mapUtil, computer, player, eatBite, info));
		ReflectionTestUtils.setField(this.testSuite, "doubleOption", doubleOption);
		// 検証
		int result = this.testSuite.summarizeOption(Const.DOUBLE);
		assertEquals(Const.Numer0n_GAMEOVER, result);
		ArrayList<String> playerInfoList = new ArrayList<String>();
		playerInfoList.add("DOUBLE,750,0EAT0BITE");
		playerInfoList.add("DOUBLE,123,3EAT0BITE");
		assertEquals(playerInfoList, info.getPlayerInfoList());
		assertTrue(info.getCpuInfoList().size() == 0);
	}

	/**
	 * <p>
	 * PLAYER正常系03
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * オプション:HIGH&LOW<br>
	 * player,cpu正しい数字:912<br>
	 * [期待値]<br>
	 * ・0(Numer0n_CONTINUE)であること<br>
	 * ・playerのinfoにHIGH&LOW,HIGH,LOW,LOWが格納されていること<br>
	 * @throws Exception
	 */
	@Test
	final void commonOption_playerNormal03Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		// 事前提供対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("9");
		list.add("1");
		list.add("2");
		gameMaster.setCorrectPlayerNumberList(list);
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 情報
		Numer0nInfo info = spy(new Numer0nInfo());
		ReflectionTestUtils.setField(this.testSuite, "info", info);
		// Player,Computer
		Player player = spy(new Player(gameMaster, info, util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, info, util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// CreateErrorExceptionComponentImpl
		CreateErrorExceptionComponentImpl exceptionComponent = spy(new CreateErrorExceptionComponentImpl());
		// オプション
		HighlowOption highlowOption = spy(new HighlowOption(gameMaster, exceptionComponent));
		ReflectionTestUtils.setField(this.testSuite, "highlowOption", highlowOption);
		// 検証
		int result = this.testSuite.summarizeOption(Const.HIGH_LOW);
		assertEquals(Const.Numer0n_CONTINUE, result);
		ArrayList<String> playerInfoList = new ArrayList<String>();
		playerInfoList.add("HIGH&LOW,HIGH,LOW,LOW");
		assertEquals(playerInfoList, info.getPlayerInfoList());
		assertTrue(info.getCpuInfoList().size() == 0);
	}

	/**
	 * <p>
	 * PLAYER正常系04
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * オプション:SLASH<br>
	 * player,cpu正しい数字:745<br>
	 * [期待値]<br>
	 * ・0(Numer0n_CONTINUE)であること<br>
	 * ・playerのinfoにSLASH,3が格納されていること<br>
	 * @throws Exception
	 */
	@Test
	final void commonOption_playerNormal04Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		// 事前提供対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("7");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectPlayerNumberList(list);
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 情報
		Numer0nInfo info = spy(new Numer0nInfo());
		ReflectionTestUtils.setField(this.testSuite, "info", info);
		// Player,Computer
		Player player = spy(new Player(gameMaster, info, util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, info, util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// CreateErrorExceptionComponentImpl
		CreateErrorExceptionComponentImpl exceptionComponent = spy(new CreateErrorExceptionComponentImpl());
		// オプション
		SlashOption slashOption = spy(new SlashOption(gameMaster, exceptionComponent));
		ReflectionTestUtils.setField(this.testSuite, "slashOption", slashOption);
		// 検証
		int result = this.testSuite.summarizeOption(Const.SLASH);
		assertEquals(Const.Numer0n_CONTINUE, result);
		ArrayList<String> playerInfoList = new ArrayList<String>();
		playerInfoList.add("SLASH,3");
		assertEquals(playerInfoList, info.getPlayerInfoList());
		assertTrue(info.getCpuInfoList().size() == 0);
	}

	/**
	 * <p>
	 * PLAYER正常系05
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * オプション:TARGET<br>
	 * player,cpu正しい数字:819<br>
	 * [期待値]<br>
	 * ・0(Numer0n_CONTINUE)であること<br>
	 * ・playerのinfoにTARGET,DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,5が格納されていること
	 * @throws Exception
	 */
	@Test
	final void commonOption_playerNormal05Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		// 事前提供対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("8");
		list.add("1");
		list.add("9");
		gameMaster.setCorrectPlayerNumberList(list);
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		TargetOptionMapUtil mapUtil = spy(new TargetOptionMapUtil());
		mapUtil.clearSelectNumberPriorityMap();
		mapUtil.addSelectNumberPriorityMap(Numer0nSelectNumberEnum.NINE.getNum(), Const.SAI_YUUSEN_FLAG);
		// 情報
		Numer0nInfo info = spy(new Numer0nInfo());
		ReflectionTestUtils.setField(this.testSuite, "info", info);
		// Player,Computer
		Player player = spy(new Player(gameMaster, info, util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, info, util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// オプション
		TargetOption targetOption = spy(new TargetOption(gameMaster, mapUtil));
		ReflectionTestUtils.setField(this.testSuite, "targetOption", targetOption);
		// 検証
		int result = this.testSuite.summarizeOption(Const.TARGET);
		assertEquals(Const.Numer0n_CONTINUE, result);
		ArrayList<String> playerInfoList = new ArrayList<String>();
		playerInfoList.add("TARGET,DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,5");
		assertEquals(playerInfoList, info.getPlayerInfoList());
		assertTrue(info.getCpuInfoList().size() == 0);
	}

	/**
	 * <p>
	 * PLAYER正常系06
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * オプション:CHANGE<br>
	 * player,cpu正しい数字:528<br>
	 * [期待値]<br>
	 * ・0(Numer0n_CONTINUE)であること<br>
	 * ・cpuのinfoにCHANGEが含まれたものが格納されていること
	 * @throws Exception
	 */
	@Test
	final void commonOption_playerNormal06Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		// 事前提供対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("5");
		list.add("2");
		list.add("8");
		gameMaster.setCorrectPlayerNumberList(list);
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		ChangeOptionMapUtil mapUtil = spy(new ChangeOptionMapUtil());
		mapUtil.clearDoPriorityMap();
		mapUtil.clearSelectNumberPriorityMap();
		mapUtil.clearDigitPriorityMap();
		mapUtil.addDoPriorityMap(Const.CHANGE_GO, Const.SAI_YUUSEN_FLAG);
		mapUtil.addSelectNumberPriorityMap(Numer0nSelectNumberEnum.NINE.getNum(), Const.SAI_YUUSEN_FLAG);
		mapUtil.addDigitPriorityMap(Numer0nDigitEnum.ZEROD.getDigit(), Const.SAI_YUUSEN_FLAG);
		// 情報
		Numer0nInfo info = spy(new Numer0nInfo());
		ReflectionTestUtils.setField(this.testSuite, "info", info);
		// Player,Computer
		Player player = spy(new Player(gameMaster, info, util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, info, util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// CreateErrorExceptionComponentImpl
		CreateErrorExceptionComponentImpl exceptionComponent = spy(new CreateErrorExceptionComponentImpl());
		// オプション
		ChangeOption changeOption = spy(new ChangeOption(gameMaster, mapUtil, exceptionComponent));
		ReflectionTestUtils.setField(this.testSuite, "changeOption", changeOption);
		// 検証
		int result = this.testSuite.summarizeOption(Const.CHANGE);
		assertEquals(Const.Numer0n_CONTINUE, result);
		assertTrue(info.getCpuInfoList().get(0).contains("CHANGE"));
		assertTrue(info.getPlayerInfoList().size() == 0);
	}

	/**
	 * <p>
	 * PLAYER正常系07
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * オプション:SHUFFLE<br>
	 * player,cpu正しい数字:903<br>
	 * [期待値]<br>
	 * ・0(Numer0n_CONTINUE)であること<br>
	 * ・cpuのinfoに何も格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void commonOption_playerNormal07Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		// 事前提供対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("9");
		list.add("0");
		list.add("3");
		gameMaster.setCorrectPlayerNumberList(list);
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		ShuffleOptionMapUtil mapUtil = spy(new ShuffleOptionMapUtil());
		mapUtil.clearDoPriorityMap();
		mapUtil.addDoPriorityMap(Const.SHUFFLE_GO, Const.SAI_YUUSEN_FLAG);
		// 情報
		Numer0nInfo info = spy(new Numer0nInfo());
		ReflectionTestUtils.setField(this.testSuite, "info", info);
		// Player,Computer
		Player player = spy(new Player(gameMaster, info, util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, info, util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// CreateErrorExceptionComponentImpl
		CreateErrorExceptionComponentImpl exceptionComponent = spy(new CreateErrorExceptionComponentImpl());
		// オプション
		ShuffleOption shuffleOption = spy(new ShuffleOption(gameMaster, mapUtil, exceptionComponent));
		ReflectionTestUtils.setField(this.testSuite, "shuffleOption", shuffleOption);
		// 検証
		int result = this.testSuite.summarizeOption(Const.SHUFFLE);
		assertEquals(Const.Numer0n_CONTINUE, result);
		assertTrue(info.getPlayerInfoList().size() == 0);
	}

	/**
	 * <p>
	 * CPU正常系01
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * オプション:DOUBLE<br>
	 * player,cpu正しい数字:126<br>
	 * コール数字:750,123
	 * [期待値]<br>
	 * ・0(Numer0n_CONTINUE)であること<br>
	 * ・cpuのinfoにDOUBLE,750,0EAT0BITE,DOUBLE,123,2EAT0BITEが格納されていること<br>
	 * ・playerのinfoにDOUBLEを含む要素とDOUBLE,--,--が格納されていること
	 * @throws Exception
	 */
	@Test
	final void commonOption_cpuNormal01Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		// 事前提供対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("6");
		gameMaster.setCorrectPlayerNumberList(list);
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		DoubleOptionMapUtil mapUtil = spy(new DoubleOptionMapUtil());
		mapUtil.clearDigitPriorityMap();
		mapUtil.addDigitPriorityMap(Numer0nDigitEnum.ONED.getDigit(), Const.SAI_YUUSEN_FLAG);
		// 情報
		Numer0nInfo info = spy(new Numer0nInfo());
		ReflectionTestUtils.setField(this.testSuite, "info", info);
		// Player,Computer
		Player player = spy(new Player(gameMaster, info, util));
		Eatbite eatBite = spy(new Eatbite(gameMaster));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, info, util));
		ArrayList<String> calllist1 = new ArrayList<>();
		calllist1.add("7");
		calllist1.add("5");
		calllist1.add("0");
		ArrayList<String> calllist2 = new ArrayList<>();
		calllist2.add("1");
		calllist2.add("2");
		calllist2.add("3");
		doNothing().when(computer).doCallNumber();
		when(computer.getCallNumber()).thenReturn(calllist1).thenReturn(calllist2);
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// オプション
		DoubleOption doubleOption = spy(new DoubleOption(gameMaster, mapUtil, computer, player, eatBite, info));
		ReflectionTestUtils.setField(this.testSuite, "doubleOption", doubleOption);
		// 検証
		int result = this.testSuite.summarizeOption(Const.DOUBLE);
		assertEquals(Const.Numer0n_CONTINUE, result);
		ArrayList<String> cpuInfoList = new ArrayList<String>();
		cpuInfoList.add("DOUBLE,750,0EAT0BITE");
		cpuInfoList.add("DOUBLE,123,2EAT0BITE");
		assertEquals(cpuInfoList, info.getCpuInfoList());
		assertTrue(info.getPlayerInfoList().get(0).contains("DOUBLE"));
		assertEquals("DOUBLE,--,--", info.getPlayerInfoList().get(1));
	}

	/**
	 * <p>
	 * CPU正常系02
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * オプション:DOUBLE<br>
	 * player,cpu正しい数字:123<br>
	 * コール数字:750,123
	 * [期待値]<br>
	 * ・1(Numer0n_GAMEOVER)であること<br>
	 * ・cpuのinfoにDOUBLE,750,0EAT0BITE,DOUBLE,123,3EAT0BITEが格納されていること
	 * @throws Exception
	 */
	@Test
	final void commonOption_cpuNormal02Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		// 事前提供対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		gameMaster.setCorrectPlayerNumberList(list);
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		DoubleOptionMapUtil mapUtil = spy(new DoubleOptionMapUtil());
		mapUtil.clearDigitPriorityMap();
		// 情報
		Numer0nInfo info = spy(new Numer0nInfo());
		ReflectionTestUtils.setField(this.testSuite, "info", info);
		// Player,Computer
		Player player = spy(new Player(gameMaster, info, util));
		Eatbite eatBite = spy(new Eatbite(gameMaster));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, info, util));
		ArrayList<String> calllist1 = new ArrayList<>();
		calllist1.add("7");
		calllist1.add("5");
		calllist1.add("0");
		ArrayList<String> calllist2 = new ArrayList<>();
		calllist2.add("1");
		calllist2.add("2");
		calllist2.add("3");
		doNothing().when(computer).doCallNumber();
		when(computer.getCallNumber()).thenReturn(calllist1).thenReturn(calllist2);
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// オプション
		DoubleOption doubleOption = spy(new DoubleOption(gameMaster, mapUtil, computer, player, eatBite, info));
		ReflectionTestUtils.setField(this.testSuite, "doubleOption", doubleOption);
		// 検証
		int result = this.testSuite.summarizeOption(Const.DOUBLE);
		assertEquals(Const.Numer0n_GAMEOVER, result);
		ArrayList<String> cpuInfoList = new ArrayList<String>();
		cpuInfoList.add("DOUBLE,750,0EAT0BITE");
		cpuInfoList.add("DOUBLE,123,3EAT0BITE");
		assertEquals(cpuInfoList, info.getCpuInfoList());
		assertTrue(info.getPlayerInfoList().size() == 0);
	}

	/**
	 * <p>
	 * CPU正常系03
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * オプション:HIGH&LOW<br>
	 * player,cpu正しい数字:912<br>
	 * [期待値]<br>
	 * ・0(Numer0n_CONTINUE)であること<br>
	 * ・playerのinfoにHIGH&LOW,HIGH,LOW,LOWが格納されていること<br>
	 * @throws Exception
	 */
	@Test
	final void commonOption_cpuNormal03Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		// 事前提供対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("9");
		list.add("1");
		list.add("2");
		gameMaster.setCorrectPlayerNumberList(list);
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 情報
		Numer0nInfo info = spy(new Numer0nInfo());
		ReflectionTestUtils.setField(this.testSuite, "info", info);
		// Player,Computer
		Player player = spy(new Player(gameMaster, info, util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, info, util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// CreateErrorExceptionComponentImpl
		CreateErrorExceptionComponentImpl exceptionComponent = spy(new CreateErrorExceptionComponentImpl());
		// オプション
		HighlowOption highlowOption = spy(new HighlowOption(gameMaster, exceptionComponent));
		ReflectionTestUtils.setField(this.testSuite, "highlowOption", highlowOption);
		// 検証
		int result = this.testSuite.summarizeOption(Const.HIGH_LOW);
		assertEquals(Const.Numer0n_CONTINUE, result);
		ArrayList<String> cpuInfoList = new ArrayList<String>();
		cpuInfoList.add("HIGH&LOW,HIGH,LOW,LOW");
		assertEquals(cpuInfoList, info.getCpuInfoList());
		assertTrue(info.getPlayerInfoList().size() == 0);
	}

	/**
	 * <p>
	 * CPU正常系04
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * オプション:SLASH<br>
	 * player,cpu正しい数字:745<br>
	 * [期待値]<br>
	 * ・0(Numer0n_CONTINUE)であること<br>
	 * ・cpuのinfoにSLASH,3が格納されていること<br>
	 * @throws Exception
	 */
	@Test
	final void commonOption_cpuNormal04Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		// 事前提供対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("7");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectPlayerNumberList(list);
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 情報
		Numer0nInfo info = spy(new Numer0nInfo());
		ReflectionTestUtils.setField(this.testSuite, "info", info);
		// Player,Computer
		Player player = spy(new Player(gameMaster, info, util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, info, util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// CreateErrorExceptionComponentImpl
		CreateErrorExceptionComponentImpl exceptionComponent = spy(new CreateErrorExceptionComponentImpl());
		// オプション
		SlashOption slashOption = spy(new SlashOption(gameMaster, exceptionComponent));
		ReflectionTestUtils.setField(this.testSuite, "slashOption", slashOption);
		// 検証
		int result = this.testSuite.summarizeOption(Const.SLASH);
		assertEquals(Const.Numer0n_CONTINUE, result);
		ArrayList<String> cpuInfoList = new ArrayList<String>();
		cpuInfoList.add("SLASH,3");
		assertEquals(cpuInfoList, info.getCpuInfoList());
		assertTrue(info.getPlayerInfoList().size() == 0);
	}

	/**
	 * <p>
	 * CPU正常系05
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * オプション:TARGET<br>
	 * player,cpu正しい数字:819<br>
	 * [期待値]<br>
	 * ・0(Numer0n_CONTINUE)であること<br>
	 * ・playerのinfoにTARGET,DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,5が格納されていること
	 * @throws Exception
	 */
	@Test
	final void commonOption_cpuNormal05Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		// 事前提供対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("8");
		list.add("1");
		list.add("9");
		gameMaster.setCorrectPlayerNumberList(list);
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		TargetOptionMapUtil mapUtil = spy(new TargetOptionMapUtil());
		mapUtil.clearSelectNumberPriorityMap();
		mapUtil.addSelectNumberPriorityMap(Numer0nSelectNumberEnum.NINE.getNum(), Const.SAI_YUUSEN_FLAG);
		// 情報
		Numer0nInfo info = spy(new Numer0nInfo());
		ReflectionTestUtils.setField(this.testSuite, "info", info);
		// Player,Computer
		Player player = spy(new Player(gameMaster, info, util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, info, util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// オプション
		TargetOption targetOption = spy(new TargetOption(gameMaster, mapUtil));
		ReflectionTestUtils.setField(this.testSuite, "targetOption", targetOption);
		// 検証
		int result = this.testSuite.summarizeOption(Const.TARGET);
		assertEquals(Const.Numer0n_CONTINUE, result);
		ArrayList<String> cpuInfoList = new ArrayList<String>();
		cpuInfoList.add("TARGET,2,EXISTLISTOFNUMBER,9");
		assertEquals(cpuInfoList, info.getCpuInfoList());
		assertTrue(info.getPlayerInfoList().size() == 0);
	}

	/**
	 * <p>
	 * CPU正常系06
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * オプション:CHANGE<br>
	 * player,cpu正しい数字:528<br>
	 * [期待値]<br>
	 * ・0(Numer0n_CONTINUE)であること<br>
	 * ・playerのinfoにCHANGEが含まれたものが格納されていること
	 * @throws Exception
	 */
	@Test
	final void commonOption_cpuNormal06Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		// 事前提供対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("5");
		list.add("2");
		list.add("8");
		gameMaster.setCorrectPlayerNumberList(list);
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		ChangeOptionMapUtil mapUtil = spy(new ChangeOptionMapUtil());
		mapUtil.clearDoPriorityMap();
		mapUtil.clearSelectNumberPriorityMap();
		mapUtil.clearDigitPriorityMap();
		mapUtil.addDoPriorityMap(Const.CHANGE_GO, Const.SAI_YUUSEN_FLAG);
		mapUtil.addSelectNumberPriorityMap(Numer0nSelectNumberEnum.NINE.getNum(), Const.SAI_YUUSEN_FLAG);
		mapUtil.addDigitPriorityMap(Numer0nDigitEnum.ZEROD.getDigit(), Const.SAI_YUUSEN_FLAG);
		// 情報
		Numer0nInfo info = spy(new Numer0nInfo());
		ReflectionTestUtils.setField(this.testSuite, "info", info);
		// Player,Computer
		Player player = spy(new Player(gameMaster, info, util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, info, util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// CreateErrorExceptionComponentImpl
		CreateErrorExceptionComponentImpl exceptionComponent = spy(new CreateErrorExceptionComponentImpl());
		// オプション
		ChangeOption changeOption = spy(new ChangeOption(gameMaster, mapUtil, exceptionComponent));
		ReflectionTestUtils.setField(this.testSuite, "changeOption", changeOption);
		// 検証
		int result = this.testSuite.summarizeOption(Const.CHANGE);
		assertEquals(Const.Numer0n_CONTINUE, result);
		ArrayList<String> playerInfoList = new ArrayList<String>();
		playerInfoList.add("CHANGE,0,HIGH");
		assertEquals(playerInfoList, info.getPlayerInfoList());
		assertTrue(info.getCpuInfoList().size() == 0);
	}

}
