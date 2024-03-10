package application.logic.info.ut;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
import application.component.consts.DifficultyConst;
import application.logic.human.Computer;
import application.logic.human.GameMaster;
import application.logic.human.Player;
import application.logic.human.gameComponent.GameComponentUtil;
import application.logic.info.Numer0nInfo;
import application.logic.info.Numer0nSpecifyNumberImpl;

/**
 * 得られた情報を元に整理する数字を格納するテストクラス<br>
 * arrangeCandidateNumberLogicメソッド経由
 * <p>
 * targetオプション使用時の情報を得ている場合のテスト
 * </p>
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Tag("UT")
public class Numer0nSpecifyNumber_arrangeCandidateNumberLogic_targetUTTest extends Numer0nSpecifyNumberUtil {

	/**
	 * PLAYER
	 */
	private static final String PLAYER = "PLAYER";

	/**
	 * Numer0nInfo
	 */
	@Mock
	private Numer0nInfo info;

	/**
	 * GameComponentUtil
	 */
	@Mock
	private GameComponentUtil util;

	@InjectMocks
	private Numer0nSpecifyNumberImpl testSuite;

	/**
	 * <p>
	 * 正常系01
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:TARGET,1,EXISTLISTOFNUMBER,2<br>
	 * [期待値]<br>
	 * ・候補の数字リストに10の位がLOW以外が格納されていないこと<br>
	 * ・候補でない数字リストに10の位がLOWが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_target_normal01Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,1,EXISTLISTOFNUMBER,2";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "1,EXISTLISTOFNUMBER,2";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "TARGET", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "TARGET_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系02
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:TARGET,1,NONEEXISTLISTOFNUMBER,9<br>
	 * [期待値]<br>
	 * ・候補の数字リストに10の位がLOW以外が格納されていないこと<br>
	 * ・候補でない数字リストに10の位がLOWが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_target_normal02Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,1,NONEEXISTLISTOFNUMBER,9";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "1,NONEEXISTLISTOFNUMBER,9";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "TARGET", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "TARGET_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系03
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:HARD<br>
	 * 桁:3<br>
	 * info:TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,5<br>
	 * [期待値]<br>
	 * ・候補の数字リストにどこかの位がLOW以外が格納されていないこと<br>
	 * ・候補でない数字リストにどこかの位がLOWが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_target_normal03Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.HARD);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,5";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "DONTTEACHINDEX,EXISTLISTOFNUMBER,5";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "TARGET", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "TARGET_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系04
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:HARD<br>
	 * 桁:3<br>
	 * info:TARGET,DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,7<br>
	 * [期待値]<br>
	 * ・候補の数字リストにどこかの位がLOW以外が格納されていないこと<br>
	 * ・候補でない数字リストにどこかの位がLOWが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_target_normal04Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.HARD);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,7";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,7";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "TARGET", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "TARGET_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系05
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:TARGET,1,EXISTLISTOFNUMBER,2<br>
	 * 候補でない数字リスト既格納:527<br>
	 * [期待値]<br>
	 * ・候補の数字リストに527が格納されていること<br>
	 * ・候補でない数字リストに527が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_target_normal05Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,1,EXISTLISTOFNUMBER,2";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		player.addNotCandidateNumberList("527");
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "1,EXISTLISTOFNUMBER,2";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "TARGET", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "TARGET_OPPOSITE", false, null, null));
		assertTrue(player.getCandidatePlayerNumberList().contains("527"));
		assertFalse(player.getNotCandidatePlayerNumberList().contains("527"));
	}

	/**
	 * <p>
	 * 正常系06
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:TARGET,1,NONEEXISTLISTOFNUMBER,9<br>
	 * 候補の数字リスト既格納:794<br>
	 * [期待値]<br>
	 * ・候補でない数字リストに794が格納されていること<br>
	 * ・候補の数字リストに794が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_target_normal06Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,1,NONEEXISTLISTOFNUMBER,9";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		player.addCandidateNumberList("794");
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "1,NONEEXISTLISTOFNUMBER,9";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "TARGET", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "TARGET_OPPOSITE", false, null, null));
		assertFalse(player.getCandidatePlayerNumberList().contains("794"));
		assertTrue(player.getNotCandidatePlayerNumberList().contains("794"));
	}

	/**
	 * <p>
	 * 正常系07
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:INSANE<br>
	 * 桁:3<br>
	 * info:TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,4<br>
	 * 候補でない数字リスト既格納:412<br>
	 * [期待値]<br>
	 * ・候補でない数字リストに412が格納されていないこと<br>
	 * ・候補の数字リストに412が格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_target_normal07Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.INSANE);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,4";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		player.addNotCandidateNumberList("412");
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "DONTTEACHINDEX,EXISTLISTOFNUMBER,4";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "TARGET", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "TARGET_OPPOSITE", false, null, null));
		assertTrue(player.getCandidatePlayerNumberList().contains("412"));
		assertFalse(player.getNotCandidatePlayerNumberList().contains("412"));
	}

	/**
	 * <p>
	 * 正常系08
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:INSANE<br>
	 * 桁:3<br>
	 * info:TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,4<br>
	 * 候補でない数字リスト既格納:384<br>
	 * [期待値]<br>
	 * ・候補でない数字リストに384が格納されていないこと<br>
	 * ・候補の数字リストに384が格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_target_normal08Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.INSANE);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,4";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		player.addNotCandidateNumberList("384");
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "DONTTEACHINDEX,EXISTLISTOFNUMBER,4";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "TARGET", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "TARGET_OPPOSITE", false, null, null));
		assertTrue(player.getCandidatePlayerNumberList().contains("384"));
		assertFalse(player.getNotCandidatePlayerNumberList().contains("384"));
	}

	/**
	 * <p>
	 * 正常系09
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:INSANE<br>
	 * 桁:3<br>
	 * info:TARGET,DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,3<br>
	 * 候補の数字リスト既格納:375<br>
	 * [期待値]<br>
	 * ・候補でない数字リストに375が格納されていること<br>
	 * ・候補の数字リストに375が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_target_normal09Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.INSANE);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,3";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		player.addCandidateNumberList("375");
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,3";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "TARGET", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "TARGET_OPPOSITE", false, null, null));
		assertFalse(player.getCandidatePlayerNumberList().contains("375"));
		assertTrue(player.getNotCandidatePlayerNumberList().contains("375"));
	}

	/**
	 * <p>
	 * 正常系10
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:TARGET,DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,9<br>
	 * 候補でない数字リスト既格納:174<br>
	 * [期待値]<br>
	 * ・候補でない数字リストに174が格納されていないこと<br>
	 * ・候補の数字リストに174が格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_target_normal10Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,9";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		player.addNotCandidateNumberList("174");
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,9";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "TARGET", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "TARGET_OPPOSITE", false, null, null));
		assertTrue(player.getCandidatePlayerNumberList().contains("174"));
		assertFalse(player.getNotCandidatePlayerNumberList().contains("174"));
	}

	/**
	 * <p>
	 * 正常系11
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:TARGET,2,NONEEXISTLISTOFNUMBER,9<br>
	 * 候補でない数字リスト既格納:395<br>
	 * [期待値]<br>
	 * ・候補の数字リストに395が格納されていること<br>
	 * ・候補でない数字リストに395が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_target_normal11Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,2,NONEEXISTLISTOFNUMBER,9";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		player.addNotCandidateNumberList("395");
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "2,NONEEXISTLISTOFNUMBER,9";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "TARGET", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "TARGET_OPPOSITE", false, null, null));
		assertTrue(player.getCandidatePlayerNumberList().contains("395"));
		assertFalse(player.getNotCandidatePlayerNumberList().contains("395"));
	}

	/**
	 * <p>
	 * 正常系12
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:INSANE<br>
	 * 桁:3<br>
	 * info:TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,5<br>
	 * 候補の数字リスト既格納:702<br>
	 * [期待値]<br>
	 * ・候補でない数字リストに702が格納されていること<br>
	 * ・候補の数字リストに702が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_target_normal12Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.INSANE);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,5";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		player.addCandidateNumberList("702");
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "DONTTEACHINDEX,EXISTLISTOFNUMBER,5";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "TARGET", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "TARGET_OPPOSITE", false, null, null));
		assertFalse(player.getCandidatePlayerNumberList().contains("702"));
		assertTrue(player.getNotCandidatePlayerNumberList().contains("702"));
	}

	/**
	 * <p>
	 * 正常系13
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:TARGET,1,EXISTLISTOFNUMBER,8<br>
	 * 候補の数字リスト既格納:402<br>
	 * [期待値]<br>
	 * ・候補でない数字リストに402が格納されていること<br>
	 * ・候補の数字リストに402が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_target_normal13Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,1,EXISTLISTOFNUMBER,8";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		player.addCandidateNumberList("402");
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "1,EXISTLISTOFNUMBER,8";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "TARGET", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "TARGET_OPPOSITE", false, null, null));
		assertFalse(player.getCandidatePlayerNumberList().contains("402"));
		assertTrue(player.getNotCandidatePlayerNumberList().contains("402"));
	}

	/**
	 * <p>
	 * 正常系14
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:TARGET,1,EXISTLISTOFNUMBER,2<br>
	 * [期待値]<br>
	 * ・候補の数字リストに10の位がLOW以外が格納されていないこと<br>
	 * ・候補でない数字リストに10の位がLOWが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_target_normal14Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,1,EXISTLISTOFNUMBER,2";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "1,EXISTLISTOFNUMBER,2";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "TARGET", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "TARGET_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系15
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:TARGET,1,NONEEXISTLISTOFNUMBER,9<br>
	 * [期待値]<br>
	 * ・候補の数字リストに10の位がLOW以外が格納されていないこと<br>
	 * ・候補でない数字リストに10の位がLOWが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_target_normal15Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,1,NONEEXISTLISTOFNUMBER,9";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "1,NONEEXISTLISTOFNUMBER,9";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "TARGET", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "TARGET_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系16
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:HARD<br>
	 * 桁:3<br>
	 * info:TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,5<br>
	 * [期待値]<br>
	 * ・候補の数字リストにどこかの位がLOW以外が格納されていないこと<br>
	 * ・候補でない数字リストにどこかの位がLOWが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_target_normal16Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.HARD);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,5";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "DONTTEACHINDEX,EXISTLISTOFNUMBER,5";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "TARGET", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "TARGET_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系17
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:HARD<br>
	 * 桁:3<br>
	 * info:TARGET,DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,7<br>
	 * [期待値]<br>
	 * ・候補の数字リストにどこかの位がLOW以外が格納されていないこと<br>
	 * ・候補でない数字リストにどこかの位がLOWが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_target_normal17Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.HARD);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,7";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,7";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "TARGET", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "TARGET_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系18
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:TARGET,1,EXISTLISTOFNUMBER,2<br>
	 * 候補でない数字リスト既格納:527<br>
	 * [期待値]<br>
	 * ・候補の数字リストに527が格納されていること<br>
	 * ・候補でない数字リストに527が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_target_normal18Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,1,EXISTLISTOFNUMBER,2";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		computer.addNotCandidateNumberList("527");
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "1,EXISTLISTOFNUMBER,2";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "TARGET", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "TARGET_OPPOSITE", false, null, null));
		assertTrue(computer.getCandidateCpuNumberList().contains("527"));
		assertFalse(computer.getNotCandidateCpuNumberList().contains("527"));
	}

	/**
	 * <p>
	 * 正常系19
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:TARGET,1,NONEEXISTLISTOFNUMBER,9<br>
	 * 候補の数字リスト既格納:794<br>
	 * [期待値]<br>
	 * ・候補でない数字リストに794が格納されていること<br>
	 * ・候補の数字リストに794が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_target_normal19Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,1,NONEEXISTLISTOFNUMBER,9";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		computer.addCandidateNumberList("794");
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "1,NONEEXISTLISTOFNUMBER,9";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "TARGET", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "TARGET_OPPOSITE", false, null, null));
		assertFalse(computer.getCandidateCpuNumberList().contains("794"));
		assertTrue(computer.getNotCandidateCpuNumberList().contains("794"));
	}

	/**
	 * <p>
	 * 正常系20
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:INSANE<br>
	 * 桁:3<br>
	 * info:TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,4<br>
	 * 候補でない数字リスト既格納:412<br>
	 * [期待値]<br>
	 * ・候補でない数字リストに412が格納されていないこと<br>
	 * ・候補の数字リストに412が格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_target_normal20Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.INSANE);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,4";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		computer.addNotCandidateNumberList("412");
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "DONTTEACHINDEX,EXISTLISTOFNUMBER,4";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "TARGET", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "TARGET_OPPOSITE", false, null, null));
		assertTrue(computer.getCandidateCpuNumberList().contains("412"));
		assertFalse(computer.getNotCandidateCpuNumberList().contains("412"));
	}

	/**
	 * <p>
	 * 正常系21
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:INSANE<br>
	 * 桁:3<br>
	 * info:TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,4<br>
	 * 候補でない数字リスト既格納:384<br>
	 * [期待値]<br>
	 * ・候補でない数字リストに384が格納されていないこと<br>
	 * ・候補の数字リストに384が格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_target_normal21Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.INSANE);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,4";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		computer.addNotCandidateNumberList("384");
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "DONTTEACHINDEX,EXISTLISTOFNUMBER,4";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "TARGET", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "TARGET_OPPOSITE", false, null, null));
		assertTrue(computer.getCandidateCpuNumberList().contains("384"));
		assertFalse(computer.getNotCandidateCpuNumberList().contains("384"));
	}

	/**
	 * <p>
	 * 正常系22
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:INSANE<br>
	 * 桁:3<br>
	 * info:TARGET,DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,3<br>
	 * 候補の数字リスト既格納:375<br>
	 * [期待値]<br>
	 * ・候補でない数字リストに375が格納されていること<br>
	 * ・候補の数字リストに375が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_target_normal22Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.INSANE);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,3";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		computer.addCandidateNumberList("375");
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,3";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "TARGET", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "TARGET_OPPOSITE", false, null, null));
		assertFalse(computer.getCandidateCpuNumberList().contains("375"));
		assertTrue(computer.getNotCandidateCpuNumberList().contains("375"));
	}

	/**
	 * <p>
	 * 正常系23
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:TARGET,DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,9<br>
	 * 候補でない数字リスト既格納:174<br>
	 * [期待値]<br>
	 * ・候補でない数字リストに174が格納されていないこと<br>
	 * ・候補の数字リストに174が格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_target_normal23Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,9";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		computer.addNotCandidateNumberList("174");
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,9";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "TARGET", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "TARGET_OPPOSITE", false, null, null));
		assertTrue(computer.getCandidateCpuNumberList().contains("174"));
		assertFalse(computer.getNotCandidateCpuNumberList().contains("174"));
	}

	/**
	 * <p>
	 * 正常系24
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:TARGET,2,NONEEXISTLISTOFNUMBER,9<br>
	 * 候補でない数字リスト既格納:395<br>
	 * [期待値]<br>
	 * ・候補の数字リストに395が格納されていること<br>
	 * ・候補でない数字リストに395が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_target_normal24Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,2,NONEEXISTLISTOFNUMBER,9";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		computer.addNotCandidateNumberList("395");
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "2,NONEEXISTLISTOFNUMBER,9";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "TARGET", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "TARGET_OPPOSITE", false, null, null));
		assertTrue(computer.getCandidateCpuNumberList().contains("395"));
		assertFalse(computer.getNotCandidateCpuNumberList().contains("395"));
	}

	/**
	 * <p>
	 * 正常系25
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:INSANE<br>
	 * 桁:3<br>
	 * info:TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,5<br>
	 * 候補の数字リスト既格納:702<br>
	 * [期待値]<br>
	 * ・候補でない数字リストに702が格納されていること<br>
	 * ・候補の数字リストに702が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_target_normal25Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.INSANE);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,5";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		computer.addCandidateNumberList("702");
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "DONTTEACHINDEX,EXISTLISTOFNUMBER,5";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "TARGET", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "TARGET_OPPOSITE", false, null, null));
		assertFalse(computer.getCandidateCpuNumberList().contains("702"));
		assertTrue(computer.getNotCandidateCpuNumberList().contains("702"));
	}

	/**
	 * <p>
	 * 正常系26
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:TARGET,1,EXISTLISTOFNUMBER,8<br>
	 * 候補の数字リスト既格納:402<br>
	 * [期待値]<br>
	 * ・候補でない数字リストに402が格納されていること<br>
	 * ・候補の数字リストに402が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_target_normal26Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,1,EXISTLISTOFNUMBER,8";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		computer.addCandidateNumberList("402");
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "1,EXISTLISTOFNUMBER,8";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "TARGET", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "TARGET_OPPOSITE", false, null, null));
		assertFalse(computer.getCandidateCpuNumberList().contains("402"));
		assertTrue(computer.getNotCandidateCpuNumberList().contains("402"));
	}


}
