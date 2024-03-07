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
 * doubleオプション使用時の情報を得ている場合のテスト
 * </p>
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Tag("UT")
public class Numer0nSpecifyNumber_arrangeCandidateNumberLogic_doubleUTTest extends Numer0nSpecifyNumberUtil {

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
	 * 正常系01(2回連続でコール時に都度得られる情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:DOUBLE,348,0EAT0BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに"3,4,8"が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_double_normal01Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "DOUBLE,348,0EAT0BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String[] chklist = { "3", "4", "8" };
		for (String list : chklist) {
			assertEquals("ALL_NOT_CONTAINMENT", chkContainsNumber(list,
					player.getCandidatePlayerNumberList(), "PART_NUMBER_CONTAIN", false, null, null));
		}
	}

	/**
	 * <p>
	 * 正常系02(2回連続でコール時に都度得られる情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:4<br>
	 * info:DOUBLE,2487,1EAT2BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに1EAT2BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに1EAT2BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_double_normal02Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(4);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "DOUBLE,2487,1EAT2BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "2487", "1EAT2BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "2487",
				"1EAT2BITE"));
	}

	/**
	 * <p>
	 * 正常系03(2回連続でコール時に都度得られる情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:5<br>
	 * info:DOUBLE,81345,3EAT1BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに3EAT1BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに3EAT1BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_double_normal03Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "DOUBLE,81345,3EAT1BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "81345", "3EAT1BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "81345",
				"3EAT1BITE"));
	}

	/**
	 * <p>
	 * 正常系04(2回連続でコール時に当てられなかった場合の相手に渡してしまう情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:DOUBLE,0,1<br>
	 * [期待値]<br>
	 * ・候補の数字リストの各数字の100の位に1である数字が格納されていること<br>
	 * ・候補でない数字リストの各数字の100の位に1である数字が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_double_normal04Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "DOUBLE,0,1";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chklist = "0,1";
		assertEquals("OK", chkContainsNumber(chklist,
				player.getCandidatePlayerNumberList(), "DOUBLE_DIGIT_POINT", false, null, null));
		assertEquals("OK", chkContainsNumber(chklist,
				player.getNotCandidatePlayerNumberList(), "DOUBLE_DIGIT_POINT_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系05(2回連続でコール時に当てられなかった場合の相手に渡してしまう情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:4<br>
	 * info:DOUBLE,2,3<br>
	 * [期待値]<br>
	 * ・候補の数字リストの各数字の10の位に3である数字が格納されていること<br>
	 * ・候補でない数字リストの各数字の10の位に3である数字が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_double_normal05Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(4);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "DOUBLE,2,3";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chklist = "2,3";
		assertEquals("OK", chkContainsNumber(chklist,
				player.getCandidatePlayerNumberList(), "DOUBLE_DIGIT_POINT", false, null, null));
		assertEquals("OK", chkContainsNumber(chklist,
				player.getNotCandidatePlayerNumberList(), "DOUBLE_DIGIT_POINT_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系06(2回連続でコール時に当てられなかった場合の相手に渡してしまう情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:5<br>
	 * info:DOUBLE,4,1<br>
	 * [期待値]<br>
	 * ・候補の数字リストの各数字の1の位に1である数字が格納されていること<br>
	 * ・候補でない数字リストの各数字の1の位に1である数字が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_double_normal06Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "DOUBLE,4,1";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chklist = "4,1";
		assertEquals("OK", chkContainsNumber(chklist,
				player.getCandidatePlayerNumberList(), "DOUBLE_DIGIT_POINT", false, null, null));
		assertEquals("OK", chkContainsNumber(chklist,
				player.getNotCandidatePlayerNumberList(), "DOUBLE_DIGIT_POINT_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系07(2回連続でコール時に都度得られる情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:DOUBLE,348,0EAT0BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに"3,4,8"が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_double_normal07Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "DOUBLE,348,0EAT0BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String[] chklist = { "3", "4", "8" };
		for (String list : chklist) {
			assertEquals("ALL_NOT_CONTAINMENT", chkContainsNumber(list,
					computer.getCandidateCpuNumberList(), "PART_NUMBER_CONTAIN", false, null, null));
		}
	}

	/**
	 * <p>
	 * 正常系08(2回連続でコール時に都度得られる情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:4<br>
	 * info:DOUBLE,2487,1EAT2BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに1EAT2BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに1EAT2BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_double_normal08Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(4);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "DOUBLE,2487,1EAT2BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				computer.getCandidateCpuNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "2487", "1EAT2BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				computer.getNotCandidateCpuNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "2487",
				"1EAT2BITE"));
	}

	/**
	 * <p>
	 * 正常系09(2回連続でコール時に都度得られる情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:5<br>
	 * info:DOUBLE,81345,3EAT1BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに3EAT1BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに3EAT1BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_double_normal09Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "DOUBLE,81345,3EAT1BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				computer.getCandidateCpuNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "81345", "3EAT1BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				computer.getNotCandidateCpuNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "81345",
				"3EAT1BITE"));
	}

	/**
	 * <p>
	 * 正常系10(2回連続でコール時に当てられなかった場合の相手に渡してしまう情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:DOUBLE,0,1<br>
	 * [期待値]<br>
	 * ・候補の数字リストの各数字の100の位に1である数字が格納されていること<br>
	 * ・候補でない数字リストの各数字の100の位に1である数字が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_double_normal10Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "DOUBLE,0,1";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chklist = "0,1";
		assertEquals("OK", chkContainsNumber(chklist,
				computer.getCandidateCpuNumberList(), "DOUBLE_DIGIT_POINT", false, null, null));
		assertEquals("OK", chkContainsNumber(chklist,
				computer.getNotCandidateCpuNumberList(), "DOUBLE_DIGIT_POINT_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系11(2回連続でコール時に当てられなかった場合の相手に渡してしまう情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:4<br>
	 * info:DOUBLE,2,3<br>
	 * [期待値]<br>
	 * ・候補の数字リストの各数字の10の位に3である数字が格納されていること<br>
	 * ・候補でない数字リストの各数字の10の位に3である数字が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_double_normal11Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(4);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "DOUBLE,2,3";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chklist = "2,3";
		assertEquals("OK", chkContainsNumber(chklist,
				computer.getCandidateCpuNumberList(), "DOUBLE_DIGIT_POINT", false, null, null));
		assertEquals("OK", chkContainsNumber(chklist,
				computer.getNotCandidateCpuNumberList(), "DOUBLE_DIGIT_POINT_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系12(2回連続でコール時に当てられなかった場合の相手に渡してしまう情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:5<br>
	 * info:DOUBLE,4,1<br>
	 * [期待値]<br>
	 * ・候補の数字リストの各数字の1の位に1である数字が格納されていること<br>
	 * ・候補でない数字リストの各数字の1の位に1である数字が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_double_normal12Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "DOUBLE,4,1";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chklist = "4,1";
		assertEquals("OK", chkContainsNumber(chklist,
				computer.getCandidateCpuNumberList(), "DOUBLE_DIGIT_POINT", false, null, null));
		assertEquals("OK", chkContainsNumber(chklist,
				computer.getNotCandidateCpuNumberList(), "DOUBLE_DIGIT_POINT_OPPOSITE", false, null, null));
	}

	/**
	 * <p>
	 * 正常系13(2回連続でコール時に都度得られる情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:DOUBLE,348,0EAT0BITE<br>
	 * 候補の数字リスト既格納:358<br>
	 * [期待値]<br>
	 * ・候補の数字リストに"3,4,8"が格納されていないこと<br>
	 * ・候補でない数字リストに"358"が格納されていること<br>
	 * ・候補の数字リストに"358"が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_double_normal13Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "DOUBLE,348,0EAT0BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		computer.addCandidateNumberList("358");
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String[] chklist = { "3", "4", "8" };
		for (String list : chklist) {
			assertEquals("ALL_NOT_CONTAINMENT", chkContainsNumber(list,
					player.getCandidatePlayerNumberList(), "PART_NUMBER_CONTAIN", false, null, null));
		}
		assertTrue(player.getNotCandidatePlayerNumberList().contains("358"));
		assertFalse(player.getCandidatePlayerNumberList().contains("358"));
	}

	/**
	 * <p>
	 * 正常系14(2回連続でコール時に都度得られる情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:DOUBLE,348,0EAT0BITE<br>
	 * 候補の数字リスト既格納:358<br>
	 * [期待値]<br>
	 * ・候補の数字リストに"3,4,8"が格納されていないこと<br>
	 * ・候補でない数字リストに"358"が格納されていること<br>
	 * ・候補の数字リストに"358"が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_double_normal14Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "DOUBLE,348,0EAT0BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		player.addCandidateNumberList("358");
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String[] chklist = { "3", "4", "8" };
		for (String list : chklist) {
			assertEquals("ALL_NOT_CONTAINMENT", chkContainsNumber(list,
					computer.getCandidateCpuNumberList(), "PART_NUMBER_CONTAIN", false, null, null));
		}
		assertTrue(computer.getNotCandidateCpuNumberList().contains("358"));
		assertFalse(computer.getCandidateCpuNumberList().contains("358"));
	}

	/**
	 * <p>
	 * 正常系15(2回連続でコール時に都度得られる情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:DOUBLE,348,0EAT0BITE<br>
	 * 候補でない数字リスト既格納:912<br>
	 * [期待値]<br>
	 * ・候補の数字リストに"3,4,8"が格納されていないこと<br>
	 * ・候補でない数字リストに"912"が格納されていないこと<br>
	 * ・候補の数字リストに"912"が格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_double_normal15Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "DOUBLE,348,0EAT0BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		player.addNotCandidateNumberList("912");
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String[] chklist = { "3", "4", "8" };
		for (String list : chklist) {
			assertEquals("ALL_NOT_CONTAINMENT", chkContainsNumber(list,
					player.getCandidatePlayerNumberList(), "PART_NUMBER_CONTAIN", false, null, null));
		}
		assertFalse(player.getNotCandidatePlayerNumberList().contains("912"));
		assertTrue(player.getCandidatePlayerNumberList().contains("912"));
	}

	/**
	 * <p>
	 * 正常系16(2回連続でコール時に都度得られる情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:DOUBLE,348,0EAT0BITE<br>
	 * 候補でない数字リスト既格納:912<br>
	 * [期待値]<br>
	 * ・候補の数字リストに"3,4,8"が格納されていないこと<br>
	 * ・候補でない数字リストに"912"が格納されていないこと<br>
	 * ・候補の数字リストに"912"が格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_double_normal16Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "DOUBLE,348,0EAT0BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		computer.addNotCandidateNumberList("912");
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String[] chklist = { "3", "4", "8" };
		for (String list : chklist) {
			assertEquals("ALL_NOT_CONTAINMENT", chkContainsNumber(list,
					computer.getCandidateCpuNumberList(), "PART_NUMBER_CONTAIN", false, null, null));
		}
		assertFalse(computer.getNotCandidateCpuNumberList().contains("912"));
		assertTrue(computer.getCandidateCpuNumberList().contains("912"));
	}

	/**
	 * <p>
	 * 正常系17(2回連続でコール時に都度得られる情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:DOUBLE,348,1EAT2BITE<br>
	 * 候補の数字リスト既格納:540<br>
	 * [期待値]<br>
	 * ・候補の数字リストに"384", "843", "438"が格納されていること<br>
	 * ・候補の数字リストに"540"が格納されていないこと<br>
	 * ・候補でない数字リストに"540"が格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_double_normal17Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "DOUBLE,348,1EAT2BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		player.addCandidateNumberList("540");
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String[] chklist = { "384", "843", "438" };
		for (String list : chklist) {
			assertEquals("EXIST", chkContainsNumber(list,
					player.getCandidatePlayerNumberList(), "SAME_NOTSAME_LIST", false, null, null));
		}
		assertFalse(player.getCandidatePlayerNumberList().contains("540"));
		assertTrue(player.getNotCandidatePlayerNumberList().contains("540"));
	}

	/**
	 * <p>
	 * 正常系18(2回連続でコール時に都度得られる情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:DOUBLE,348,1EAT2BITE<br>
	 * 候補の数字リスト既格納:540<br>
	 * [期待値]<br>
	 * ・候補の数字リストに"384", "843", "438"が格納されていること<br>
	 * ・候補の数字リストに"540"が格納されていないこと<br>
	 * ・候補でない数字リストに"540"が格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_double_normal18Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "DOUBLE,348,1EAT2BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		computer.addCandidateNumberList("540");
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String[] chklist = { "384", "843", "438" };
		for (String list : chklist) {
			assertEquals("EXIST", chkContainsNumber(list,
					computer.getCandidateCpuNumberList(), "SAME_NOTSAME_LIST", false, null, null));
		}
		assertFalse(computer.getCandidateCpuNumberList().contains("540"));
		assertTrue(computer.getNotCandidateCpuNumberList().contains("540"));
	}

	/**
	 * <p>
	 * 正常系19(2回連続でコール時に都度得られる情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:DOUBLE,0,1<br>
	 * 候補の数字リスト既格納:358<br>
	 * [期待値]<br>
	 * ・候補の数字リストの各数字の100の位に1である数字が格納されていること<br>
	 * ・候補でない数字リストの各数字の100の位に1である数字が格納されていないこと<br>
	 * ・候補でない数字リストに"358"が格納されていること<br>
	 * ・候補の数字リストに"358"が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_double_normal19Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "DOUBLE,0,1";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		player.addCandidateNumberList("358");
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chklist = "0,1";
		assertEquals("OK", chkContainsNumber(chklist,
				player.getCandidatePlayerNumberList(), "DOUBLE_DIGIT_POINT", false, null, null));
		assertEquals("OK", chkContainsNumber(chklist,
				player.getNotCandidatePlayerNumberList(), "DOUBLE_DIGIT_POINT_OPPOSITE", false, null, null));
		assertTrue(player.getNotCandidatePlayerNumberList().contains("358"));
		assertFalse(player.getCandidatePlayerNumberList().contains("358"));
	}

	/**
	 * <p>
	 * 正常系20(2回連続でコール時に都度得られる情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:DOUBLE,0,1<br>
	 * 候補の数字リスト既格納:358<br>
	 * [期待値]<br>
	 * ・候補の数字リストの各数字の100の位に1である数字が格納されていること<br>
	 * ・候補でない数字リストの各数字の100の位に1である数字が格納されていないこと<br>
	 * ・候補でない数字リストに"358"が格納されていること<br>
	 * ・候補の数字リストに"358"が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_double_normal20Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "DOUBLE,0,1";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		computer.addCandidateNumberList("358");
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chklist = "0,1";
		assertEquals("OK", chkContainsNumber(chklist,
				computer.getCandidateCpuNumberList(), "DOUBLE_DIGIT_POINT", false, null, null));
		assertEquals("OK", chkContainsNumber(chklist,
				computer.getNotCandidateCpuNumberList(), "DOUBLE_DIGIT_POINT_OPPOSITE", false, null, null));
		assertTrue(computer.getNotCandidateCpuNumberList().contains("358"));
		assertFalse(computer.getCandidateCpuNumberList().contains("358"));
	}

	/**
	 * <p>
	 * 正常系21(2回連続でコール時に都度得られる情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:DOUBLE,2,2<br>
	 * 候補でない数字リスト既格納:912<br>
	 * [期待値]<br>
	 * ・候補の数字リストの各数字の1の位に2である数字が格納されていること<br>
	 * ・候補でない数字リストの各数字の1の位に2である数字が格納されていないこと<br>
	 * ・候補でない数字リストに"912"が格納されていないこと<br>
	 * ・候補の数字リストに"912"が格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_double_normal21Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "DOUBLE,2,2";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		player.addNotCandidateNumberList("912");
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chklist = "2,2";
		assertEquals("OK", chkContainsNumber(chklist,
				player.getCandidatePlayerNumberList(), "DOUBLE_DIGIT_POINT", false, null, null));
		assertEquals("OK", chkContainsNumber(chklist,
				player.getNotCandidatePlayerNumberList(), "DOUBLE_DIGIT_POINT_OPPOSITE", false, null, null));
		assertFalse(player.getNotCandidatePlayerNumberList().contains("912"));
		assertTrue(player.getCandidatePlayerNumberList().contains("912"));
	}

	/**
	 * <p>
	 * 正常系22(2回連続でコール時に都度得られる情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:DOUBLE,2,2<br>
	 * 候補でない数字リスト既格納:912<br>
	 * [期待値]<br>
	 * ・候補の数字リストの各数字の1の位に2である数字が格納されていること<br>
	 * ・候補でない数字リストの各数字の1の位に2である数字が格納されていないこと<br>
	 * ・候補でない数字リストに"912"が格納されていないこと<br>
	 * ・候補の数字リストに"912"が格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_double_normal22Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "DOUBLE,2,2";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		computer.addNotCandidateNumberList("912");
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String chklist = "2,2";
		assertEquals("OK", chkContainsNumber(chklist,
				computer.getCandidateCpuNumberList(), "DOUBLE_DIGIT_POINT", false, null, null));
		assertEquals("OK", chkContainsNumber(chklist,
				computer.getNotCandidateCpuNumberList(), "DOUBLE_DIGIT_POINT_OPPOSITE", false, null, null));
		assertFalse(computer.getNotCandidateCpuNumberList().contains("912"));
		assertTrue(computer.getCandidateCpuNumberList().contains("912"));
	}


}
