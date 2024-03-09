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
import application.logic.human.Computer;
import application.logic.human.GameMaster;
import application.logic.human.Player;
import application.logic.human.gameComponent.GameComponentUtil;
import application.logic.info.Numer0nInfo;
import application.logic.judge.Eatbite;
import application.logic.option.DoubleOption;
import application.logic.option.map.DoubleOptionMapUtil;

/**
 * ダブルオプションテストクラス
 * <p>
 * 本物の処理を確認し、info情報を確認する
 * </p>
 * @author shiraishitoshio
 *
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Tag("UT")
public class DoubleOption_realInfoMapUTTest {

	/**
	 * GameComponentUtil
	 */
	@Mock
	private GameComponentUtil util;

	@InjectMocks
	private DoubleOption testSuite;

	/**
	 * <p>
	 * PLAYER正常系01
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁数:4<br>
	 * CPUの正解の数字:1267<br>
	 * PLAYERの正解の数字:1267<br>
	 * 宣言する桁:任意の桁<br>
	 * [期待値]<br>
	 * ・コールナンバーが1749であること<br>
	 * ・TEACH_NUMBERが返却されること<br>
	 * ・PLAYERのターンで正解できなかった場合、PLAYERの正解数字の一部を教えること<br>
	 * ・宣言した桁:nullでないこと<br>
	 * ・宣言した桁に紐づく数字:-1でなく,nullでないこと<br>
	 * ・playerのinfo情報に"DOUBLE,1749,1EAT1BITE","DOUBLE,1749,1EAT1BITE"が格納されていること<br>
	 * ・cpuのinfo情報に"DOUBLE"が含まれた情報とDOUBLE,--,--が格納されていること<br>
	 * @throws Exception
	 */
	@Test
	final void doubleOption_realInfoMap_playerNormal01Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(4);
		// 事前提供対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("6");
		list.add("7");
		gameMaster.setCorrectPlayerNumberList(list);
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		DoubleOptionMapUtil mapUtil = spy(new DoubleOptionMapUtil());
		mapUtil.clearDigitPriorityMap();
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
		// 情報
		Numer0nInfo info = spy(new Numer0nInfo());
		ReflectionTestUtils.setField(this.testSuite, "info", info);
		// Player,Computer
		Player player = spy(new Player(gameMaster, info, util));
		ArrayList<String> calllist = new ArrayList<>();
		calllist.add("1");
		calllist.add("7");
		calllist.add("4");
		calllist.add("9");
		doNothing().when(player).doCallNumber();
		when(player.getCallNumber()).thenReturn(calllist);
		Eatbite eatBite = spy(new Eatbite(gameMaster));
		ReflectionTestUtils.setField(this.testSuite, "eatBite", eatBite);
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, info, util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		assertEquals(DoubleOption.TEACH_NUMBER, this.testSuite.doubleLogic());
		assertEquals(calllist, player.getCallNumber());
		assertNotNull(this.testSuite.getDoubleDigit());
		assertNotEquals("-1", this.testSuite.getDoubleNum());
		assertNotNull(this.testSuite.getDoubleNum());
		ArrayList<String> playerInfoList = new ArrayList<String>();
		playerInfoList.add("DOUBLE,1749,1EAT1BITE");
		playerInfoList.add("DOUBLE,1749,1EAT1BITE");
		assertEquals(playerInfoList, info.getPlayerInfoList());
		assertTrue(info.getCpuInfoList().get(0).contains("DOUBLE"));
		assertEquals("DOUBLE,--,--", info.getCpuInfoList().get(1));
	}

		/**
	 * <p>
	 * PLAYER正常系02
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁数:3<br>
	 * CPUの正解の数字:684<br>
	 * PLAYERの正解の数字:684<br>
	 * 宣言する桁:100の位:最優先フラグ付き<br>
	 * [期待値]<br>
	 * ・コールナンバーがnullでないこと<br>
	 * ・TEACH_NUMBERが返却されること<br>
	 * ・PLAYERのターンで正解できなかった場合、PLAYERの正解数字の一部を教えること<br>
	 * ・宣言した桁:0であること<br>
	 * ・宣言した桁に紐づく数字:6であること<br>
	 * ・playerのinfo情報に"DOUBLE,814,1EAT1BITE","DOUBLE,584,2EAT0BITE"が格納されていること<br>
	 * ・cpuのinfo情報に"DOUBLE"が含まれた情報とDOUBLE,--,--が格納されていること<br>
	 * @throws Exception
	 */
	@Test
	final void doubleOption_realInfoMap_playerNormal02Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		// 事前提供対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("6");
		list.add("8");
		list.add("4");
		gameMaster.setCorrectPlayerNumberList(list);
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		DoubleOptionMapUtil mapUtil = spy(new DoubleOptionMapUtil());
		mapUtil.clearDigitPriorityMap();
		mapUtil.addDigitPriorityMap(Const.ZERO_D, Const.SAI_YUUSEN_FLAG);
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
		// 情報
		Numer0nInfo info = spy(new Numer0nInfo());
		ReflectionTestUtils.setField(this.testSuite, "info", info);
		// Player,Computer
		Player player = spy(new Player(gameMaster, info, util));
		ArrayList<String> calllist1 = new ArrayList<>();
		calllist1.add("8");
		calllist1.add("1");
		calllist1.add("4");
		ArrayList<String> calllist2 = new ArrayList<>();
		calllist2.add("5");
		calllist2.add("8");
		calllist2.add("4");
		doNothing().when(player).doCallNumber();
		when(player.getCallNumber()).thenReturn(calllist1).thenReturn(calllist2);
		Eatbite eatBite = spy(new Eatbite(gameMaster));
		ReflectionTestUtils.setField(this.testSuite, "eatBite", eatBite);
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, info, util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		assertEquals(DoubleOption.TEACH_NUMBER, this.testSuite.doubleLogic());
		assertNotNull(player.getCallNumber());
		assertEquals(0, this.testSuite.getDoubleDigit());
		assertEquals("6", this.testSuite.getDoubleNum());
		ArrayList<String> playerInfoList = new ArrayList<String>();
		playerInfoList.add("DOUBLE,814,1EAT1BITE");
		playerInfoList.add("DOUBLE,584,2EAT0BITE");
		assertEquals(playerInfoList, info.getPlayerInfoList());
		assertTrue(info.getCpuInfoList().get(0).contains("DOUBLE"));
		assertEquals("DOUBLE,--,--", info.getCpuInfoList().get(1));
	}

	/**
	 * <p>
	 * PLAYER正常系03
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁数:3<br>
	 * CPUの正解の数字:684<br>
	 * PLAYERの正解の数字:684<br>
	 * 宣言する桁:100の位:最優先フラグ付き<br>
	 * 候補となる数字:814<br>
	 * [期待値]<br>
	 * ・コールナンバーがnullでないこと<br>
	 * ・TEACH_NUMBERが返却されること<br>
	 * ・PLAYERのターンで正解できなかった場合、PLAYERの正解数字の一部を教えること<br>
	 * ・宣言した桁:0であること<br>
	 * ・宣言した桁に紐づく数字:6であること<br>
	 * ・playerのinfo情報に"DOUBLE,814,1EAT1BITE","DOUBLE,684,3EAT0BITE"が格納されていること<br>
	 * ・cpuのinfo情報に何も格納されていないこと<br>
	 * ・候補となる数字リストに814が残っていないこと<br>
	 * @throws Exception
	 */
	@Test
	final void doubleOption_realInfoMap_playerNormal03Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		// 事前提供対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("6");
		list.add("8");
		list.add("4");
		gameMaster.setCorrectPlayerNumberList(list);
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		DoubleOptionMapUtil mapUtil = spy(new DoubleOptionMapUtil());
		mapUtil.clearDigitPriorityMap();
		mapUtil.addDigitPriorityMap(Const.ZERO_D, Const.SAI_YUUSEN_FLAG);
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
		// 情報
		Numer0nInfo info = spy(new Numer0nInfo());
		ReflectionTestUtils.setField(this.testSuite, "info", info);
		// Player,Computer
		Player player = spy(new Player(gameMaster, info, util));
		player.addCandidateNumberList("814");
		ArrayList<String> calllist1 = new ArrayList<>();
		calllist1.add("8");
		calllist1.add("1");
		calllist1.add("4");
		ArrayList<String> calllist2 = new ArrayList<>();
		calllist2.add("6");
		calllist2.add("8");
		calllist2.add("4");
		doNothing().when(player).doCallNumber();
		when(player.getCallNumber()).thenReturn(calllist1).thenReturn(calllist2);
		Eatbite eatBite = spy(new Eatbite(gameMaster));
		ReflectionTestUtils.setField(this.testSuite, "eatBite", eatBite);
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, info, util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		assertEquals(DoubleOption.ALL_EAT, this.testSuite.doubleLogic());
		assertNotNull(player.getCallNumber());
		assertEquals(0, this.testSuite.getDoubleDigit());
		assertEquals("6", this.testSuite.getDoubleNum());
		ArrayList<String> playerInfoList = new ArrayList<String>();
		playerInfoList.add("DOUBLE,814,1EAT1BITE");
		playerInfoList.add("DOUBLE,684,3EAT0BITE");
		assertEquals(playerInfoList, info.getPlayerInfoList());
		assertTrue(info.getCpuInfoList().size() == 0);
		assertFalse(player.getCandidatePlayerNumberList().size() == 0);
	}

	/**
	 * <p>
	 * PLAYER正常系04
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁数:3<br>
	 * CPUの正解の数字:684<br>
	 * PLAYERの正解の数字:684<br>
	 * 宣言する桁:100の位:最優先フラグ付き<br>
	 * 候補となる数字:814<br>
	 * [期待値]<br>
	 * ・コールナンバーがnullでないこと<br>
	 * ・TEACH_NUMBERが返却されること<br>
	 * ・PLAYERのターンで正解できなかった場合、PLAYERの正解数字の一部を教えること<br>
	 * ・宣言した桁:0であること<br>
	 * ・宣言した桁に紐づく数字:6であること<br>
	 * ・playerのinfo情報に"DOUBLE,814,1EAT1BITE","DOUBLE,684,3EAT0BITE"が格納されていること<br>
	 * ・cpuのinfo情報に何も格納されていないこと<br>
	 * ・候補となる数字リストに814が残っていること<br>
	 * @throws Exception
	 */
	@Test
	final void doubleOption_realInfoMap_playerNormal04Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		// 事前提供対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("6");
		list.add("8");
		list.add("4");
		gameMaster.setCorrectPlayerNumberList(list);
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		DoubleOptionMapUtil mapUtil = spy(new DoubleOptionMapUtil());
		mapUtil.clearDigitPriorityMap();
		mapUtil.addDigitPriorityMap(Const.ZERO_D, Const.SAI_YUUSEN_FLAG);
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
		// 情報
		Numer0nInfo info = spy(new Numer0nInfo());
		ReflectionTestUtils.setField(this.testSuite, "info", info);
		// Player,Computer
		Player player = spy(new Player(gameMaster, info, util));
		player.addCandidateNumberList("814");
		ArrayList<String> calllist1 = new ArrayList<>();
		calllist1.add("7");
		calllist1.add("5");
		calllist1.add("0");
		ArrayList<String> calllist2 = new ArrayList<>();
		calllist2.add("6");
		calllist2.add("8");
		calllist2.add("4");
		doNothing().when(player).doCallNumber();
		when(player.getCallNumber()).thenReturn(calllist1).thenReturn(calllist2);
		Eatbite eatBite = spy(new Eatbite(gameMaster));
		ReflectionTestUtils.setField(this.testSuite, "eatBite", eatBite);
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, info, util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		assertEquals(DoubleOption.ALL_EAT, this.testSuite.doubleLogic());
		assertNotNull(player.getCallNumber());
		assertEquals(0, this.testSuite.getDoubleDigit());
		assertEquals("6", this.testSuite.getDoubleNum());
		ArrayList<String> playerInfoList = new ArrayList<String>();
		playerInfoList.add("DOUBLE,750,0EAT0BITE");
		playerInfoList.add("DOUBLE,684,3EAT0BITE");
		assertEquals(playerInfoList, info.getPlayerInfoList());
		assertTrue(info.getCpuInfoList().size() == 0);
		assertTrue(player.getCandidatePlayerNumberList().size() == 1);
		assertEquals("814", player.getCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * CPU正常系01
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 桁数:4<br>
	 * PLAYERの正解の数字:4876<br>
	 * CPUの正解の数字:4876<br>
	 * 宣言する桁:任意の桁<br>
	 * [期待値]<br>
	 * ・コールナンバーが5678であること<br>
	 * ・TEACH_NUMBERが返却されること<br>
	 * ・PLAYERのターンで正解できなかった場合、PLAYERの正解数字の一部を教えること<br>
	 * ・宣言した桁:nullでないこと<br>
	 * ・宣言した桁に紐づく数字:-1でなく,nullでないこと<br>
	 * ・cpuのinfo情報に"DOUBLE,5678,1EAT2BITE","DOUBLE,5678,1EAT2BITE"が格納されていること<br>
	 * ・playerのinfo情報に"DOUBLE"が含まれた情報とDOUBLE,--,--が格納されていること<br>
	 * @throws Exception
	 */
	@Test
	final void doubleOption_realInfoMap_cpuNormal01Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(4);
		gameMaster.setName(Const.CPU);
		// 事前提供対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("4");
		list.add("8");
		list.add("7");
		list.add("6");
		gameMaster.setCorrectCpuNumberList(list);
		gameMaster.setCorrectPlayerNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		DoubleOptionMapUtil mapUtil = spy(new DoubleOptionMapUtil());
		mapUtil.clearDigitPriorityMap();
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
		// 情報
		Numer0nInfo info = spy(new Numer0nInfo());
		ReflectionTestUtils.setField(this.testSuite, "info", info);
		// Player,Computer
		Player player = spy(new Player(gameMaster, info, util));
		Eatbite eatBite = spy(new Eatbite(gameMaster));
		ReflectionTestUtils.setField(this.testSuite, "eatBite", eatBite);
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, info, util));
		ArrayList<String> calllist = new ArrayList<>();
		calllist.add("5");
		calllist.add("6");
		calllist.add("7");
		calllist.add("8");
		doNothing().when(computer).doCallNumber();
		when(computer.getCallNumber()).thenReturn(calllist);
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		assertEquals(DoubleOption.TEACH_NUMBER, this.testSuite.doubleLogic());
		assertEquals(calllist, computer.getCallNumber());
		assertNotNull(this.testSuite.getDoubleDigit());
		assertNotEquals("-1", this.testSuite.getDoubleNum());
		assertNotNull(this.testSuite.getDoubleNum());
		ArrayList<String> cpuInfoList = new ArrayList<String>();
		cpuInfoList.add("DOUBLE,5678,1EAT2BITE");
		cpuInfoList.add("DOUBLE,5678,1EAT2BITE");
		assertEquals(cpuInfoList, info.getCpuInfoList());
		assertTrue(info.getPlayerInfoList().get(0).contains("DOUBLE"));
		assertEquals("DOUBLE,--,--", info.getPlayerInfoList().get(1));
	}

	/**
	 * <p>
	 * CPU正常系02
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁数:3<br>
	 * CPUの正解の数字:723<br>
	 * PLAYERの正解の数字:723<br>
	 * 宣言する桁:1の位:最優先フラグ付き<br>
	 * 候補となる数字:953<br>
	 * [期待値]<br>
	 * ・コールナンバーがnullでないこと<br>
	 * ・TEACH_NUMBERが返却されること<br>
	 * ・PLAYERのターンで正解できなかった場合、PLAYERの正解数字の一部を教えること<br>
	 * ・宣言した桁:nullでないこと<br>
	 * ・宣言した桁に紐づく数字:nullでないこと<br>
	 * ・cpuのinfo情報に"DOUBLE,953,1EAT0BITE","DOUBLE,723,3EAT0BITE"が格納されていること<br>
	 * ・playerのinfo情報に何も格納されていないこと<br>
	 * ・候補となる数字リストに953が残っていないこと<br>
	 * @throws Exception
	 */
	@Test
	final void doubleOption_realInfoMap_cpuNormal02Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		// 事前提供対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("7");
		list.add("2");
		list.add("3");
		gameMaster.setCorrectPlayerNumberList(list);
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		DoubleOptionMapUtil mapUtil = spy(new DoubleOptionMapUtil());
		mapUtil.clearDigitPriorityMap();
		mapUtil.addDigitPriorityMap(Const.TWO_D, Const.SAI_YUUSEN_FLAG);
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
		// 情報
		Numer0nInfo info = spy(new Numer0nInfo());
		ReflectionTestUtils.setField(this.testSuite, "info", info);
		// Player,Computer
		Player player = spy(new Player(gameMaster, info, util));
		Eatbite eatBite = spy(new Eatbite(gameMaster));
		ReflectionTestUtils.setField(this.testSuite, "eatBite", eatBite);
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, info, util));
		computer.addCandidateNumberList("953");
		ArrayList<String> calllist1 = new ArrayList<>();
		calllist1.add("9");
		calllist1.add("5");
		calllist1.add("3");
		ArrayList<String> calllist2 = new ArrayList<>();
		calllist2.add("7");
		calllist2.add("2");
		calllist2.add("3");
		doNothing().when(computer).doCallNumber();
		when(computer.getCallNumber()).thenReturn(calllist1).thenReturn(calllist2);
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		assertEquals(DoubleOption.ALL_EAT, this.testSuite.doubleLogic());
		assertNotNull(player.getCallNumber());
		assertNotNull(this.testSuite.getDoubleDigit());
		assertNotNull(this.testSuite.getDoubleNum());
		ArrayList<String> cpuInfoList = new ArrayList<String>();
		cpuInfoList.add("DOUBLE,953,1EAT0BITE");
		cpuInfoList.add("DOUBLE,723,3EAT0BITE");
		assertEquals(cpuInfoList, info.getCpuInfoList());
		assertTrue(info.getPlayerInfoList().size() == 0);
		assertFalse(computer.getCandidateCpuNumberList().size() == 0);
	}

	/**
	 * <p>
	 * CPU正常系03
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁数:3<br>
	 * CPUの正解の数字:723<br>
	 * PLAYERの正解の数字:723<br>
	 * 宣言する桁:1の位:最優先フラグ付き<br>
	 * 候補となる数字:953<br>
	 * [期待値]<br>
	 * ・コールナンバーがnullでないこと<br>
	 * ・TEACH_NUMBERが返却されること<br>
	 * ・PLAYERのターンで正解できなかった場合、PLAYERの正解数字の一部を教えること<br>
	 * ・宣言した桁:nullでないこと<br>
	 * ・宣言した桁に紐づく数字:nullでないこと<br>
	 * ・cpuのinfo情報に"DOUBLE,126,1EAT0BITE","DOUBLE,723,3EAT0BITE"が格納されていること<br>
	 * ・playerのinfo情報に何も格納されていないこと<br>
	 * ・候補となる数字リストに953が残っていること<br>
	 * @throws Exception
	 */
	@Test
	final void doubleOption_realInfoMap_cpuNormal03Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		// 事前提供対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("7");
		list.add("2");
		list.add("3");
		gameMaster.setCorrectPlayerNumberList(list);
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		DoubleOptionMapUtil mapUtil = spy(new DoubleOptionMapUtil());
		mapUtil.clearDigitPriorityMap();
		mapUtil.addDigitPriorityMap(Const.TWO_D, Const.SAI_YUUSEN_FLAG);
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
		// 情報
		Numer0nInfo info = spy(new Numer0nInfo());
		ReflectionTestUtils.setField(this.testSuite, "info", info);
		// Player,Computer
		Player player = spy(new Player(gameMaster, info, util));
		Eatbite eatBite = spy(new Eatbite(gameMaster));
		ReflectionTestUtils.setField(this.testSuite, "eatBite", eatBite);
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, info, util));
		computer.addCandidateNumberList("953");
		ArrayList<String> calllist1 = new ArrayList<>();
		calllist1.add("1");
		calllist1.add("2");
		calllist1.add("6");
		ArrayList<String> calllist2 = new ArrayList<>();
		calllist2.add("7");
		calllist2.add("2");
		calllist2.add("3");
		doNothing().when(computer).doCallNumber();
		when(computer.getCallNumber()).thenReturn(calllist1).thenReturn(calllist2);
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		assertEquals(DoubleOption.ALL_EAT, this.testSuite.doubleLogic());
		assertNotNull(player.getCallNumber());
		assertNotNull(this.testSuite.getDoubleDigit());
		assertNotNull(this.testSuite.getDoubleNum());
		ArrayList<String> cpuInfoList = new ArrayList<String>();
		cpuInfoList.add("DOUBLE,126,1EAT0BITE");
		cpuInfoList.add("DOUBLE,723,3EAT0BITE");
		assertEquals(cpuInfoList, info.getCpuInfoList());
		assertTrue(info.getPlayerInfoList().size() == 0);
		assertEquals(cpuInfoList, info.getCpuInfoList());
		assertTrue(computer.getCandidateCpuNumberList().size() == 1);
		assertEquals("953", computer.getCandidateCpuNumberList().get(0));
	}

}
