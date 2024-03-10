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
 * changeオプション使用時の情報を得ている場合のテスト
 * </p>
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Tag("UT")
public class Numer0nSpecifyNumber_arrangeCandidateNumberLogic_changeUTTest extends Numer0nSpecifyNumberUtil {

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
	 * info:CHANGE,1,LOW<br>
	 * [期待値]<br>
	 * ・候補の数字リストに10の位がLOW以外が格納されていないこと<br>
	 * ・候補でない数字リストに10の位がLOWが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_change_normal01Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,1,LOW";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "1,LOW";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "CHANGE", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "CHANGE_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系02
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:NORMAL<br>
	 * 桁:3<br>
	 * info:CHANGE,1,HIGH<br>
	 * [期待値]<br>
	 * ・候補の数字リストに10の位がHIGH以外が格納されていないこと<br>
	 * ・候補でない数字リストに10の位がHIGHが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_change_normal02Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.NORMAL);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,1,HIGH";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "1,HIGH";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "CHANGE", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "CHANGE_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系03
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:HARD<br>
	 * 桁:3<br>
	 * info:CHANGE,DONTTEACHINDEX,LOW<br>
	 * [期待値]<br>
	 * ・候補の数字リストにどこかの位がLOW以外が格納されていないこと<br>
	 * ・候補でない数字リストにどこかの位がLOWが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_change_normal03Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.HARD);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,DONTTEACHINDEX,LOW";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "DONTTEACHINDEX,LOW";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "CHANGE", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "CHANGE_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系04
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:HARD<br>
	 * 桁:3<br>
	 * info:CHANGE,DONTTEACHINDEX,HIGH<br>
	 * [期待値]<br>
	 * ・候補の数字リストにどこかの位がHIGH以外が格納されていないこと<br>
	 * ・候補でない数字リストにどこかの位がHIGHが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_change_normal04Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.HARD);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,DONTTEACHINDEX,HIGH";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "DONTTEACHINDEX,HIGH";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "CHANGE", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "CHANGE_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系05
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EXHAUSTED<br>
	 * 桁:3<br>
	 * info:CHANGE,1,NOTCLEAR<br>
	 * [期待値]<br>
	 * ・候補の数字リスト,候補でない数字リストもどちらにも格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_change_normal05Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EXHAUSTED);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,1,NOTCLEAR";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "1,NOTCLEAR";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "CHANGE", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "CHANGE_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系06
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:INSANE<br>
	 * 桁:3<br>
	 * info:CHANGE,DONTTEACHINDEX,NOTCLEAR<br>
	 * [期待値]<br>
	 * ・候補の数字リスト,候補でない数字リストもどちらにも格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_change_normal06Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.INSANE);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,DONTTEACHINDEX,NOTCLEAR";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "DONTTEACHINDEX,NOTCLEAR";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "CHANGE", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "CHANGE_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系07
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:CHANGE,1,LOW<br>
	 * 候補の数字リスト既格納:567<br>
	 * [期待値]<br>
	 * ・候補の数字リストに10の位がLOW以外が格納されていないこと<br>
	 * ・候補でない数字リストに10の位がLOWが格納されていないこと<br>
	 * ・候補でない数字リストに567が格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_change_normal07Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,1,LOW";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		player.addCandidateNumberList("567");
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "1,LOW";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "CHANGE", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "CHANGE_OPPOSITE", false, null, null));
		assertFalse(player.getCandidatePlayerNumberList().contains("567"));
		assertTrue(player.getNotCandidatePlayerNumberList().contains("567"));
	}

	/**
	 * <p>
	 * 正常系08
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:CHANGE,0,HIGH<br>
	 * 候補でない数字リスト既格納:814<br>
	 * [期待値]<br>
	 * ・候補の数字リストに100の位がHIGH以外が格納されていないこと<br>
	 * ・候補でない数字リストに100の位がHIGHが格納されていないこと<br>
	 * ・候補の数字リストに814が格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_change_normal08Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,0,HIGH";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		player.addNotCandidateNumberList("814");
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "0,HIGH";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "CHANGE", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "CHANGE_OPPOSITE", false, null, null));
		assertTrue(player.getCandidatePlayerNumberList().contains("814"));
		assertFalse(player.getNotCandidatePlayerNumberList().contains("814"));
	}

	/**
	 * <p>
	 * 正常系08
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:CHANGE,--<br>
	 * 候補でない数字リスト既格納:814<br>
	 * [期待値]<br>
	 * ・特に情報がない場合はスキップし、候補の数字、候補でない数字に値は格納されないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_change_normal09Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,--";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertTrue(player.getCandidatePlayerNumberList().size() == 0);
		assertTrue(player.getNotCandidatePlayerNumberList().size() == 0);
	}

	/**
	 * <p>
	 * 正常系10
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:CHANGE,1,LOW<br>
	 * [期待値]<br>
	 * ・候補の数字リストに10の位がLOW以外が格納されていないこと<br>
	 * ・候補でない数字リストに10の位がLOWが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_change_normal10Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,1,LOW";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "1,LOW";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "CHANGE", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "CHANGE_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系11
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:NORMAL<br>
	 * 桁:3<br>
	 * info:CHANGE,1,HIGH<br>
	 * [期待値]<br>
	 * ・候補の数字リストに10の位がHIGH以外が格納されていないこと<br>
	 * ・候補でない数字リストに10の位がHIGHが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_change_normal11Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.NORMAL);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,1,HIGH";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "1,HIGH";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "CHANGE", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "CHANGE_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系12
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:HARD<br>
	 * 桁:3<br>
	 * info:CHANGE,DONTTEACHINDEX,LOW<br>
	 * [期待値]<br>
	 * ・候補の数字リストにどこかの位がLOW以外が格納されていないこと<br>
	 * ・候補でない数字リストにどこかの位がLOWが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_change_normal12Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.HARD);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,DONTTEACHINDEX,LOW";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "DONTTEACHINDEX,LOW";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "CHANGE", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "CHANGE_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系13
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:HARD<br>
	 * 桁:3<br>
	 * info:CHANGE,DONTTEACHINDEX,HIGH<br>
	 * [期待値]<br>
	 * ・候補の数字リストにどこかの位がHIGH以外が格納されていないこと<br>
	 * ・候補でない数字リストにどこかの位がHIGHが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_change_normal13Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.HARD);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,DONTTEACHINDEX,HIGH";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "DONTTEACHINDEX,HIGH";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "CHANGE", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "CHANGE_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系14
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EXHAUSTED<br>
	 * 桁:3<br>
	 * info:CHANGE,1,NOTCLEAR<br>
	 * [期待値]<br>
	 * ・候補の数字リスト,候補でない数字リストもどちらにも格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_change_normal14Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EXHAUSTED);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,1,NOTCLEAR";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "1,NOTCLEAR";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "CHANGE", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "CHANGE_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系15
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:INSANE<br>
	 * 桁:3<br>
	 * info:CHANGE,DONTTEACHINDEX,NOTCLEAR<br>
	 * [期待値]<br>
	 * ・候補の数字リスト,候補でない数字リストもどちらにも格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_change_normal15Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.INSANE);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,DONTTEACHINDEX,NOTCLEAR";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "DONTTEACHINDEX,NOTCLEAR";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "CHANGE", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "CHANGE_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系16
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:CHANGE,1,LOW<br>
	 * 候補の数字リスト既格納:567<br>
	 * [期待値]<br>
	 * ・候補の数字リストに10の位がLOW以外が格納されていないこと<br>
	 * ・候補でない数字リストに10の位がLOWが格納されていないこと<br>
	 * ・候補でない数字リストに567が格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_change_normal16Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,1,LOW";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		computer.addCandidateNumberList("567");
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "1,LOW";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "CHANGE", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "CHANGE_OPPOSITE", false, null, null));
		assertFalse(computer.getCandidateCpuNumberList().contains("567"));
		assertTrue(computer.getNotCandidateCpuNumberList().contains("567"));
	}

	/**
	 * <p>
	 * 正常系17
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:CHANGE,0,HIGH<br>
	 * 候補でない数字リスト既格納:814<br>
	 * [期待値]<br>
	 * ・候補の数字リストに100の位がHIGH以外が格納されていないこと<br>
	 * ・候補でない数字リストに100の位がHIGHが格納されていないこと<br>
	 * ・候補の数字リストに814が格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_change_normal17Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,0,HIGH";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		computer.addNotCandidateNumberList("814");
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "0,HIGH";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "CHANGE", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "CHANGE_OPPOSITE", false, null, null));
		assertTrue(computer.getCandidateCpuNumberList().contains("814"));
		assertFalse(computer.getNotCandidateCpuNumberList().contains("814"));
	}






}
