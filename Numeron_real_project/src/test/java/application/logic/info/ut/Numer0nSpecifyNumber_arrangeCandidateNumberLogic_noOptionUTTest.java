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

import application.component.anything.Anything;
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
 * オプション未使用時の情報を得ている場合のテスト
 * </p>
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Tag("UT")
public class Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOptionUTTest extends Numer0nSpecifyNumberUtil {

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
	 * info:NOOPTION,348,0EAT0BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに"3,4,8"が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal01Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,348,0EAT0BITE";
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
	 * 正常系02
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:NOOPTION,194,1EAT2BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに"149","491","914"のみ格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal02Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,194,1EAT2BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String[] chklist = { "149", "491", "914" };

		for (String list : chklist) {
			assertEquals("EXIST", chkContainsNumber(list,
					player.getCandidatePlayerNumberList(), "SAME_NOTSAME_LIST", false, null, null));
			player.getCandidatePlayerNumberList().remove(list);
		}
		assertTrue(player.getCandidatePlayerNumberList().size() == 0);
	}

	/**
	 * <p>
	 * 正常系03
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:NOOPTION,532,0EAT1BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに"5","3","2"のいずれかのみが含まれた数字が同じ数格納されていること<br>
	 * ・"5","3","2"のいずれかのみが含まれた数字以外が存在しないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal03Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,532,0EAT1BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);

		String[] chklist = { "5", "3", "2" };
		Integer allCount = player.getCandidatePlayerNumberList().size();
		for (String list : chklist) {
			assertEquals(Anything.convertIntegerToString(allCount / chklist.length), chkContainsNumber(
					list, player.getCandidatePlayerNumberList(), "TRUE_COUNT_CONTAIN", false, null, null));
			// 各数字が含まれていたらその都度削除(indexのズレを防ぐため後ろから参照)
			for (int i = player.getCandidatePlayerNumberList().size() - 1; i >= 0; i--) {
				if (Anything.convertNumberToArrayList(
						player.getCandidatePlayerNumberList().get(i)).contains(list)) {
					player.getCandidatePlayerNumberList().remove(
							player.getCandidatePlayerNumberList().get(i));
				}
			}
		}
		assertTrue(player.getCandidatePlayerNumberList().size() == 0);
	}

	/**
	 * <p>
	 * 正常系04
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:NOOPTION,895,0EAT2BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに0EAT2BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに0EAT2BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal04Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,895,0EAT2BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "895", "0EAT2BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "895",
				"0EAT2BITE"));
	}

	/**
	 * <p>
	 * 正常系05
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:NOOPTION,632,1EAT1BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに1EAT1BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに1EAT1BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal05Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,632,1EAT1BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "632", "1EAT1BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "632",
				"1EAT1BITE"));
	}

	/**
	 * <p>
	 * 正常系06
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:NOOPTION,739,1EAT0BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに"7","3","9"のいずれかの位置の桁が一致している数字が同じ数格納されていること<br>
	 * ・"7","3","9"のいずれかの位置の桁が一致している数字以外が存在しないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal06Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,739,1EAT0BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);

		String[] chklist = { "7", "3", "9" };
		Integer allCount = player.getCandidatePlayerNumberList().size();
		for (String list : chklist) {
			assertEquals(Anything.convertIntegerToString(allCount / chklist.length), chkContainsNumber(
					list, player.getCandidatePlayerNumberList(), "TRUE_COUNT_EQUAL", false, null, null));
			// 各数字が含まれていたらその都度削除(indexのズレを防ぐため後ろから参照)
			for (int i = player.getCandidatePlayerNumberList().size() - 1; i >= 0; i--) {
				if (Anything.convertNumberToArrayList(
						player.getCandidatePlayerNumberList().get(i)).contains(list)) {
					player.getCandidatePlayerNumberList().remove(
							player.getCandidatePlayerNumberList().get(i));
				}
			}
		}
		assertTrue(player.getCandidatePlayerNumberList().size() == 0);
	}

	/**
	 * <p>
	 * 正常系07
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:4<br>
	 * info:NOOPTION,3176,0EAT0BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに"3,1,7,6"が格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal07Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(4);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,3176,0EAT0BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String[] chklist = { "3", "1", "7", "6" };
		for (String list : chklist) {
			assertEquals("ALL_NOT_CONTAINMENT", chkContainsNumber(list,
					player.getCandidatePlayerNumberList(), "PART_NUMBER_CONTAIN", false, null, null));
		}
	}

	/**
	 * <p>
	 * 正常系08
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:4<br>
	 * info:NOOPTION,2831,1EAT3BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに
	 * "2318","2183",
	 * "3812","1823",
	 * "1238","8132",
	 * "8321","3281"
	 * のみ格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal08Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(4);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,2831,1EAT3BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		String[] chklist = {
				"2318", "2183",
				"3812", "1823",
				"1238", "8132",
				"8321", "3281"
		};

		for (String list : chklist) {
			assertEquals("EXIST", chkContainsNumber(list,
					player.getCandidatePlayerNumberList(), "SAME_NOTSAME_LIST", false, null, null));
			player.getCandidatePlayerNumberList().remove(list);
		}
		assertTrue(player.getCandidatePlayerNumberList().size() == 0);
	}

	/**
	 * <p>
	 * 正常系09
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:4<br>
	 * info:NOOPTION,0386,0EAT1BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに"0","3","8","6"のいずれかのみが含まれた数字が同じ数格納されていること<br>
	 * ・"0","3","8","6"のいずれかのみが含まれた数字以外が存在しないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal09Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(4);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,0386,0EAT1BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);

		String[] chklist = { "0", "3", "8", "6" };
		Integer allCount = player.getCandidatePlayerNumberList().size();
		for (String list : chklist) {
			assertEquals(Anything.convertIntegerToString(allCount / chklist.length), chkContainsNumber(
					list, player.getCandidatePlayerNumberList(), "TRUE_COUNT_CONTAIN", false, null, null));
			// 各数字が含まれていたらその都度削除(indexのズレを防ぐため後ろから参照)
			for (int i = player.getCandidatePlayerNumberList().size() - 1; i >= 0; i--) {
				if (Anything.convertNumberToArrayList(
						player.getCandidatePlayerNumberList().get(i)).contains(list)) {
					player.getCandidatePlayerNumberList().remove(
							player.getCandidatePlayerNumberList().get(i));
				}
			}
		}
		assertTrue(player.getCandidatePlayerNumberList().size() == 0);
	}

	/**
	 * <p>
	 * 正常系10
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:4<br>
	 * info:NOOPTION,4067,0EAT3BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに0EAT3BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに0EAT3BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal10Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(4);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,4067,0EAT3BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "4067", "0EAT3BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "4067",
				"0EAT3BITE"));
	}

	/**
	 * <p>
	 * 正常系11
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:4<br>
	 * info:NOOPTION,9478,2EAT1BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに2EAT1BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに2EAT1BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal11Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(4);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,9478,2EAT1BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "9478", "2EAT1BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "9478",
				"2EAT1BITE"));
	}

	/**
	 * <p>
	 * 正常系12
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:4<br>
	 * info:NOOPTION,8127,2EAT0BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに2EAT0BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに2EAT0BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal12Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(4);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,8127,2EAT0BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "8127", "2EAT0BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "8127",
				"2EAT0BITE"));
	}

	/**
	 * <p>
	 * 正常系13
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:4<br>
	 * info:NOOPTION,9481,1EAT2BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに1EAT2BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに1EAT2BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal13Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(4);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,9481,1EAT2BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "9481", "1EAT2BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "9481",
				"1EAT2BITE"));
	}

	/**
	 * <p>
	 * 正常系14
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:4<br>
	 * info:NOOPTION,6048,1EAT1BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに1EAT1BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに1EAT1BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal14Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(4);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,6048,1EAT1BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "6048", "1EAT1BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "6048",
				"1EAT1BITE"));
	}

	/**
	 * <p>
	 * 正常系15
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:4<br>
	 * info:NOOPTION,7390,0EAT2BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに0EAT2BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに0EAT2BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal15Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(4);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,7390,0EAT2BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "7390", "0EAT2BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "7390",
				"0EAT2BITE"));
	}

	/**
	 * <p>
	 * 正常系16
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:4<br>
	 * info:NOOPTION,0812,1EAT0BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに1EAT0BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに1EAT0BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal16Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(4);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,0812,1EAT0BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "0812", "1EAT0BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "0812",
				"1EAT0BITE"));
	}

	/**
	 * <p>
	 * 正常系17
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:4<br>
	 * info:NOOPTION,6749,2EAT2BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに2EAT2BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに2EAT2BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal17Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(4);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,6749,2EAT2BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "6749", "2EAT2BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "6749",
				"2EAT2BITE"));
	}

	/**
	 * <p>
	 * 正常系18
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:4<br>
	 * info:NOOPTION,6389,0EAT4BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに0EAT4BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに0EAT4BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal18Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(4);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,6389,0EAT4BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "6389", "0EAT4BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "6389",
				"0EAT4BITE"));
	}

	/**
	 * <p>
	 * 正常系19
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:5<br>
	 * info:NOOPTION,14257,4EAT0BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに4EAT0BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに4EAT0BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal19Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,14257,4EAT0BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "14257", "4EAT0BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "14257",
				"4EAT0BITE"));
	}

	/**
	 * <p>
	 * 正常系20
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:5<br>
	 * info:NOOPTION,38916,3EAT0BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに3EAT0BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに3EAT0BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal20Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,38916,3EAT0BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "38916", "3EAT0BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "38916",
				"3EAT0BITE"));
	}

	/**
	 * <p>
	 * 正常系21
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:4<br>
	 * info:NOOPTION,57821,3EAT1BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに3EAT1BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに3EAT1BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal21Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,57821,3EAT1BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "57821", "3EAT1BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "57821",
				"3EAT1BITE"));
	}

	/**
	 * <p>
	 * 正常系22
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:5<br>
	 * info:NOOPTION,90124,3EAT2BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに3EAT2BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに3EAT2BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal22Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,90124,3EAT2BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "90124", "3EAT2BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "90124",
				"3EAT2BITE"));
	}

	/**
	 * <p>
	 * 正常系23
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:5<br>
	 * info:NOOPTION,67124,2EAT0BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに2EAT0BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに2EAT0BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal23Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,67124,2EAT0BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "67124", "2EAT0BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "67124",
				"2EAT0BITE"));
	}

	/**
	 * <p>
	 * 正常系24
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:5<br>
	 * info:NOOPTION,91276,2EAT1BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに2EAT1BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに2EAT1BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal24Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(4);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,91276,2EAT1BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "91276", "2EAT1BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "91276",
				"2EAT1BITE"));
	}

	/**
	 * <p>
	 * 正常系26
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:5<br>
	 * info:NOOPTION,90347,2EAT2BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに2EAT2BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに2EAT2BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal26Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,90347,2EAT2BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "90347", "2EAT2BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "90347",
				"2EAT2BITE"));
	}

	/**
	 * <p>
	 * 正常系27
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:5<br>
	 * info:NOOPTION,10547,2EAT3BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに0EAT4BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに0EAT4BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal27Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,10547,2EAT3BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "10547", "2EAT3BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "10547",
				"2EAT3BITE"));
	}

	/**
	 * <p>
	 * 正常系28
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:5<br>
	 * info:NOOPTION,84609,1EAT0BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに1EAT0BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに1EAT0BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal28Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,84609,1EAT0BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "84609", "1EAT0BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "84609",
				"1EAT0BITE"));
	}

	/**
	 * <p>
	 * 正常系29
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:5<br>
	 * info:NOOPTION,27845,1EAT1BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに1EAT1BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに1EAT1BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal29Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,27845,1EAT1BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "27845", "1EAT1BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "27845",
				"1EAT1BITE"));
	}

	/**
	 * <p>
	 * 正常系30
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:5<br>
	 * info:NOOPTION,67245,1EAT2BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに1EAT2BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに1EAT2BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal30Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,67245,1EAT2BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "67245", "1EAT2BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "67245",
				"1EAT2BITE"));
	}

	/**
	 * <p>
	 * 正常系31
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:5<br>
	 * info:NOOPTION,89012,1EAT3BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに1EAT3BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに1EAT3BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal31Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,89012,1EAT3BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "89012", "1EAT3BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "89012",
				"1EAT3BITE"));
	}

	/**
	 * <p>
	 * 正常系32
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:5<br>
	 * info:NOOPTION,71293,1EAT4BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに1EAT4BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに1EAT4BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal32Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,71293,1EAT4BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "71293", "1EAT4BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "71293",
				"1EAT4BITE"));
	}

	/**
	 * <p>
	 * 正常系33
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:5<br>
	 * info:NOOPTION,01378,0EAT0BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに0EAT0BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに0EAT0BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal33Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,01378,0EAT0BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "01378", "0EAT0BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "01378",
				"0EAT0BITE"));
	}

	/**
	 * <p>
	 * 正常系34
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:5<br>
	 * info:NOOPTION,82467,0EAT1BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに0EAT1BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに0EAT1BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal34Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,82467,0EAT1BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "82467", "0EAT1BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "82467",
				"0EAT1BITE"));
	}

	/**
	 * <p>
	 * 正常系35
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:5<br>
	 * info:NOOPTION,75901,0EAT2BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに0EAT2BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに0EAT2BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal35Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,75901,0EAT2BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "75901", "0EAT2BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "75901",
				"0EAT2BITE"));
	}

	/**
	 * <p>
	 * 正常系36
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:5<br>
	 * info:NOOPTION,19034,0EAT3BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに0EAT2BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに0EAT2BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal36Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,19034,0EAT3BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "19034", "0EAT3BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "19034",
				"0EAT3BITE"));
	}

	/**
	 * <p>
	 * 正常系37
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:5<br>
	 * info:NOOPTION,98532,0EAT4BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに0EAT4BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに0EAT4BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal37Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,98532,0EAT4BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "98532", "0EAT4BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "98532",
				"0EAT4BITE"));
	}

	/**
	 * <p>
	 * 正常系38
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:5<br>
	 * info:NOOPTION,14782,0EAT5BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストに0EAT5BITE以外が格納されていないこと<br>
	 * ・候補でない数字リストに0EAT5BITEが格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_arrangeCandidateNumberLogic_noOption_normal38Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(5);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,14782,0EAT5BITE";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		this.testSuite.arrangeCandidateNumberLogic(info);
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN", true, "14782", "0EAT5BITE"));
		assertEquals("EAT&BITE_OK", chkContainsNumber(null,
				player.getNotCandidatePlayerNumberList(), "TWO_PART_NUMBER_CONTAIN_OPPOSITE", true, "14782",
				"0EAT5BITE"));
	}

}
