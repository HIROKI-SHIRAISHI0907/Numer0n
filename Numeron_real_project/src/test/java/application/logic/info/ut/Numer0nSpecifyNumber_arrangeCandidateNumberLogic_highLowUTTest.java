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
import application.logic.human.Computer;
import application.logic.human.GameMaster;
import application.logic.human.Player;
import application.logic.human.gameComponent.GameComponentUtil;
import application.logic.info.Numer0nInfo;
import application.logic.info.Numer0nSpecifyNumber;

/**
 * 得られた情報を元に整理する数字を格納するテストクラス<br>
 * arrangeCandidateNumberLogicメソッド経由
 * <p>
 * HIGH&LOWオプション使用時の情報を得ている場合のテスト
 * </p>
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Tag("UT")
public class Numer0nSpecifyNumber_arrangeCandidateNumberLogic_highLowUTTest extends Numer0nSpecifyNumberUtil {

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
	private Numer0nSpecifyNumber testSuite;

	/**
	 * <p>
	 * 正常系01
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:HIGH&LOW,LOW,LOW,HIGH<br>
	 * [期待値]<br>
	 * ・候補の数字リストにLOW,LOW,HIGH以外が格納されていないこと<br>
	 * ・候補でない数字リストにLOW,LOW,HIGHが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_highLow_normal01Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "HIGH&LOW,LOW,LOW,HIGH";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "LOW,LOW,HIGH";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "HIGH&LOW", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "HIGH&LOW_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系02
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:HIGH&LOW,HIGH,HIGH,LOW<br>
	 * [期待値]<br>
	 * ・候補の数字リストにHIGH,HIGH,LOW以外が格納されていないこと<br>
	 * ・候補でない数字リストにHIGH,HIGH,LOWが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_highLow_normal02Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "HIGH&LOW,HIGH,HIGH,LOW";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "HIGH,HIGH,LOW";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "HIGH&LOW", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "HIGH&LOW_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系03
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:HIGH&LOW,LOW,LOW,HIGH<br>
	 * [期待値]<br>
	 * ・候補の数字リストにLOW,LOW,HIGH以外が格納されていないこと<br>
	 * ・候補でない数字リストにLOW,LOW,HIGHが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_highLow_normal03Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "HIGH&LOW,LOW,LOW,HIGH";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "LOW,LOW,HIGH";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "HIGH&LOW", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "HIGH&LOW_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系04
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:HIGH&LOW,HIGH,HIGH,LOW<br>
	 * [期待値]<br>
	 * ・候補の数字リストにHIGH,HIGH,LOW以外が格納されていないこと<br>
	 * ・候補でない数字リストにHIGH,HIGH,LOWが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_highLow_normal04Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "HIGH&LOW,HIGH,HIGH,LOW";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "HIGH,HIGH,LOW";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "HIGH&LOW", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "HIGH&LOW_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系05
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:4<br>
	 * info:HIGH&LOW,LOW,LOW,HIGH,LOW<br>
	 * [期待値]<br>
	 * ・候補の数字リストにLOW,LOW,HIGH,LOW以外が格納されていないこと<br>
	 * ・候補でない数字リストにLOW,LOW,HIGH,LOWが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_highLow_normal05Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(4);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "HIGH&LOW,LOW,LOW,HIGH,LOW";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "LOW,LOW,HIGH,LOW";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "HIGH&LOW", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "HIGH&LOW_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系06
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:4<br>
	 * info:HIGH&LOW,HIGH,HIGH,HIGH,HIGH<br>
	 * [期待値]<br>
	 * ・候補の数字リストにHIGH,HIGH,HIGH,HIGH以外が格納されていないこと<br>
	 * ・候補でない数字リストにHIGH,HIGH,HIGH,HIGHが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_highLow_normal06Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(4);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "HIGH&LOW,HIGH,HIGH,HIGH,HIGH";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "HIGH,HIGH,HIGH,HIGH";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "HIGH&LOW", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "HIGH&LOW_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系07
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:4<br>
	 * info:HIGH&LOW,LOW,LOW,HIGH,LOW<br>
	 * [期待値]<br>
	 * ・候補の数字リストにLOW,LOW,HIGH,LOW以外が格納されていないこと<br>
	 * ・候補でない数字リストにLOW,LOW,HIGH,LOWが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_highLow_normal07Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(4);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "HIGH&LOW,LOW,LOW,HIGH,LOW";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "LOW,LOW,HIGH,LOW";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "HIGH&LOW", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "HIGH&LOW_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系08
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:4<br>
	 * info:HIGH&LOW,HIGH,HIGH,HIGH,HIGH<br>
	 * [期待値]<br>
	 * ・候補の数字リストにHIGH,HIGH,HIGH,HIGH以外が格納されていないこと<br>
	 * ・候補でない数字リストにHIGH,HIGH,HIGH,HIGHが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_highLow_normal08Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "HIGH&LOW,HIGH,HIGH,HIGH,HIGH";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "HIGH,HIGH,HIGH,HIGH";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "HIGH&LOW", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "HIGH&LOW_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系09
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:5<br>
	 * info:HIGH&LOW,LOW,HIGH,LOW,HIGH,LOW<br>
	 * [期待値]<br>
	 * ・候補の数字リストにLOW,HIGH,LOW,HIGH,LOW以外が格納されていないこと<br>
	 * ・候補でない数字リストにLOW,HIGH,LOW,HIGH,LOWが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_highLow_normal09Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "HIGH&LOW,LOW,HIGH,LOW,HIGH,LOW";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "LOW,HIGH,LOW,HIGH,LOW";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "HIGH&LOW", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "HIGH&LOW_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系10
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:5<br>
	 * info:HIGH&LOW,LOW,LOW,LOW,LOW,LOW<br>
	 * [期待値]<br>
	 * ・候補の数字リストにLOW,LOW,LOW,LOW,LO以外が格納されていないこと<br>
	 * ・候補でない数字リストにLOW,LOW,LOW,LOW,LOが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_highLow_normal10Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "HIGH&LOW,LOW,LOW,LOW,LOW,LO";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "LOW,LOW,LOW,LOW,LO";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "HIGH&LOW", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "HIGH&LOW_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系11
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:5<br>
	 * info:HIGH&LOW,LOW,HIGH,LOW,HIGH,LOW<br>
	 * [期待値]<br>
	 * ・候補の数字リストにLOW,HIGH,LOW,HIGH,LOW以外が格納されていないこと<br>
	 * ・候補でない数字リストにLOW,HIGH,LOW,HIGH,LOWが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_highLow_normal11Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "HIGH&LOW,LOW,HIGH,LOW,HIGH,LOW";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "LOW,HIGH,LOW,HIGH,LOW";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "HIGH&LOW", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "HIGH&LOW_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系12
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:5<br>
	 * info:HIGH&LOW,LOW,LOW,LOW,LOW,LOW<br>
	 * [期待値]<br>
	 * ・候補の数字リストにLOW,LOW,LOW,LOW,LOW以外が格納されていないこと<br>
	 * ・候補でない数字リストにLOW,LOW,LOW,LOW,LOWが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_highLow_normal12Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "HIGH&LOW,LOW,LOW,LOW,LOW,LOW";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "LOW,LOW,LOW,LOW,LOW";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "HIGH&LOW", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "HIGH&LOW_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系13
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:HIGH&LOW,LOW,HIGH,LOW<br>
	 * 候補の数字リスト既格納:589<br>
	 * [期待値]<br>
	 * ・候補の数字リストにLOW,HIGH,LOW以外が格納されていないこと<br>
	 * ・候補でない数字リストにLOW,HIGH,LOWが格納されていないこと<br>
	 * ・候補でない数字リストに589が格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_highLow_normal13Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "HIGH&LOW,LOW,HIGH,LOW";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		player.addCandidateNumberList("589");
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "LOW,HIGH,LOW";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "HIGH&LOW", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "HIGH&LOW_OPPOSITE", false, null, null));
		assertFalse(player.getCandidatePlayerNumberList().contains("589"));
		assertTrue(player.getNotCandidatePlayerNumberList().contains("589"));
	}

	/**
	 * <p>
	 * 正常系14
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:HIGH&LOW,LOW,HIGH,LOW<br>
	 * 候補の数字リスト既格納:589<br>
	 * [期待値]<br>
	 * ・候補の数字リストにLOW,HIGH,LOW以外が格納されていないこと<br>
	 * ・候補でない数字リストにLOW,HIGH,LOWが格納されていないこと<br>
	 * ・候補でない数字リストに589が格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_highLow_normal14Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "HIGH&LOW,LOW,HIGH,LOW";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		computer.addCandidateNumberList("589");
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "LOW,HIGH,LOW";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "HIGH&LOW", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "HIGH&LOW_OPPOSITE", false, null, null));
		assertFalse(computer.getCandidateCpuNumberList().contains("589"));
		assertTrue(computer.getNotCandidateCpuNumberList().contains("589"));
	}

	/**
	 * <p>
	 * 正常系15
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:HIGH&LOW,HIGH,HIGH,LOW<br>
	 * 候補でない数字リスト既格納:761<br>
	 * [期待値]<br>
	 * ・候補の数字リストにHIGH,HIGH,LOW以外が格納されていないこと<br>
	 * ・候補でない数字リストにHIGH,HIGH,LOWが格納されていないこと<br>
	 * ・候補の数字リストに761が格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_highLow_normal15Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "HIGH&LOW,HIGH,HIGH,LOW";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		player.addNotCandidateNumberList("761");
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "HIGH,HIGH,LOW";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "HIGH&LOW", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "HIGH&LOW_OPPOSITE", false, null, null));
		assertTrue(player.getCandidatePlayerNumberList().contains("761"));
		assertFalse(player.getNotCandidatePlayerNumberList().contains("761"));
	}

	/**
	 * <p>
	 * 正常系16
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:HIGH&LOW,HIGH,HIGH,LOW<br>
	 * 候補でない数字リスト既格納:761<br>
	 * [期待値]<br>
	 * ・候補の数字リストにHIGH,HIGH,LOW以外が格納されていないこと<br>
	 * ・候補でない数字リストにHIGH,HIGH,LOWが格納されていないこと<br>
	 * ・候補の数字リストに761が格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_highLow_normal16Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "HIGH&LOW,HIGH,HIGH,LOW";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		computer.addNotCandidateNumberList("761");
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "HIGH,HIGH,LOW";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "HIGH&LOW", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "HIGH&LOW_OPPOSITE", false, null, null));
		assertTrue(computer.getCandidateCpuNumberList().contains("761"));
		assertFalse(computer.getNotCandidateCpuNumberList().contains("761"));
	}

}
