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
import application.logic.info.Numer0nSpecifyNumber;

/**
 * 得られた情報を元に整理する数字を格納するテストクラス<br>
 * arrangeCandidateNumberLogicメソッド経由
 * <p>
 * slashオプション使用時の情報を得ている場合のテスト
 * </p>
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Tag("UT")
public class Numer0nSpecifyNumber_arrangeCandidateNumberLogic_slashUTTest extends Numer0nSpecifyNumberUtil {

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
	 * info:SLASH,1<br>
	 * [期待値]<br>
	 * ・候補の数字リストにスラッシュナンバーが1以外が格納されていないこと<br>
	 * ・候補でない数字リストにスラッシュナンバーが1が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_slash_normal01Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "SLASH,1";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "1";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "SLASH", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "SLASH_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系02
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:4<br>
	 * info:SLASH,8<br>
	 * [期待値]<br>
	 * ・候補の数字リストにスラッシュナンバーが8以外が格納されていないこと<br>
	 * ・候補でない数字リストにスラッシュナンバーが8が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_slash_normal02Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(4);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "SLASH,8";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "8";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "SLASH", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "SLASH_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系03
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:5<br>
	 * info:SLASH,5<br>
	 * [期待値]<br>
	 * ・候補の数字リストにスラッシュナンバーが5以外が格納されていないこと<br>
	 * ・候補でない数字リストにスラッシュナンバーが5が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_slash_normal03Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "SLASH,5";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "5";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "SLASH", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "SLASH_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系04
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:SLASH,--<br>
	 * [期待値]<br>
	 * ・特に情報がない場合はスキップし、候補の数字、候補でない数字に値は格納されないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_slash_normal04Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "SLASH,--";
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
	 * 正常系05
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:SLASH,1<br>
	 * [期待値]<br>
	 * ・候補の数字リストにスラッシュナンバーが1以外が格納されていないこと<br>
	 * ・候補でない数字リストにスラッシュナンバーが1が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_slash_normal05Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "SLASH,1";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "1";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "SLASH", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "SLASH_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系06
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:4<br>
	 * info:SLASH,8<br>
	 * [期待値]<br>
	 * ・候補の数字リストにスラッシュナンバーが8以外が格納されていないこと<br>
	 * ・候補でない数字リストにスラッシュナンバーが8が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_slash_normal06Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(4);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "SLASH,8";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "8";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "SLASH", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "SLASH_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系07
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:5<br>
	 * info:SLASH,5<br>
	 * [期待値]<br>
	 * ・候補の数字リストにスラッシュナンバーが5以外が格納されていないこと<br>
	 * ・候補でない数字リストにスラッシュナンバーが5が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_slash_normal07Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "SLASH,5";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "5";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "SLASH", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "SLASH_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系08
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:SLASH,--<br>
	 * [期待値]<br>
	 * ・特に情報がない場合はスキップし、候補の数字、候補でない数字に値は格納されないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_slash_normal08Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "SLASH,--";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertTrue(computer.getCandidateCpuNumberList().size() == 0);
		assertTrue(computer.getNotCandidateCpuNumberList().size() == 0);
	}

	/**
	 * <p>
	 * 正常系09
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:SLASH,4<br>
	 * 候補でない数字リスト既格納:589<br>
	 * [期待値]<br>
	 * ・候補でない数字リストに"589"が格納されていないこと<br>
	 * ・候補の数字リストに"589"が格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_slash_normal09Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "SLASH,4";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		player.addNotCandidateNumberList("589");
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "4";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "SLASH", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "SLASH_OPPOSITE", false, null, null));
		assertTrue(player.getCandidatePlayerNumberList().contains("589"));
		assertFalse(player.getNotCandidatePlayerNumberList().contains("589"));
	}

	/**
	 * <p>
	 * 正常系10
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:SLASH,4<br>
	 * 候補でない数字リスト既格納:589<br>
	 * [期待値]<br>
	 * ・候補でない数字リストに"589"が格納されていないこと<br>
	 * ・候補の数字リストに"589"が格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_slash_normal10Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "SLASH,4";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		computer.addNotCandidateNumberList("589");
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "4";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "SLASH", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "SLASH_OPPOSITE", false, null, null));
		assertTrue(computer.getCandidateCpuNumberList().contains("589"));
		assertFalse(computer.getNotCandidateCpuNumberList().contains("589"));
	}

	/**
	 * <p>
	 * 正常系11
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:SLASH,6<br>
	 * 候補の数字リスト既格納:480<br>
	 * [期待値]<br>
	 * ・候補の数字リストに"480"が格納されていないこと<br>
	 * ・候補でない数字リストに"480"が格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_slash_normal11Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "SLASH,6";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		player.addCandidateNumberList("480");
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "6";
		assertEquals("OK", chkContainsNumber(chk,
				player.getCandidatePlayerNumberList(), "SLASH", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				player.getNotCandidatePlayerNumberList(), "SLASH_OPPOSITE", false, null, null));
		assertFalse(player.getCandidatePlayerNumberList().contains("480"));
		assertTrue(player.getNotCandidatePlayerNumberList().contains("480"));
	}

	/**
	 * <p>
	 * 正常系12
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:SLASH,6<br>
	 * 候補の数字リスト既格納:480<br>
	 * [期待値]<br>
	 * ・候補の数字リストに"480"が格納されていないこと<br>
	 * ・候補でない数字リストに"480"が格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_slash_normal12Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "SLASH,6";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		computer.addCandidateNumberList("480");
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chk = "6";
		assertEquals("OK", chkContainsNumber(chk,
				computer.getCandidateCpuNumberList(), "SLASH", false, null, null));
		assertEquals("OK", chkContainsNumber(chk,
				computer.getNotCandidateCpuNumberList(), "SLASH_OPPOSITE", false, null, null));
		assertFalse(computer.getCandidateCpuNumberList().contains("480"));
		assertTrue(computer.getNotCandidateCpuNumberList().contains("480"));
	}



}
