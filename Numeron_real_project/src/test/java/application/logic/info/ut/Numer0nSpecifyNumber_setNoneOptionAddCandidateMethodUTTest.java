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
import application.component.consts.Numer0nNextActionFlagEnum;
import application.logic.human.Computer;
import application.logic.human.GameMaster;
import application.logic.human.Player;
import application.logic.human.gameComponent.GameComponentUtil;
import application.logic.info.Numer0nInfo;
import application.logic.info.Numer0nSpecifyNumberImpl;

/**
 * 得られたEatBite情報を元に候補の数値リストを登録する用のメソッド（オプションなし）テストクラス
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Tag("UT")
public class Numer0nSpecifyNumber_setNoneOptionAddCandidateMethodUTTest {

	/**
	 * PLAYER
	 */
	private static final String PLAYER = "PLAYER";

	/**
	 * Numer0n続行
	 */
	private static final Integer Numer0n_CONTINUE = 0;

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
	 * 桁:3
	 * info:NONEOPTION,348,0EAT1BITE<br>
	 * 候補数字:123<br>
	 * [期待値]<br>
	 * ・CONTINUEであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setNoneOptionAddCandidateMethod_normal01Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,348,0EAT1BITE";
		// chkNumber
		String chkNumber = "123";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		Integer result = this.testSuite.setNoneOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(Numer0n_CONTINUE, result);
		assertEquals("123", player.getCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系02
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁:3
	 * info:NONEOPTION,756,0EAT1BITE<br>
	 * 候補数字:123<br>
	 * [期待値]<br>
	 * ・CONTINUEであること<br>
	 * ・infoに合う候補でない場合、候補の数字リストから外されていること
	 * ・候補でない数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setNoneOptionAddCandidateMethod_normal02Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,756,2EAT1BITE";
		// chkNumber
		String chkNumber = "123";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		player.addCandidateNumberList(chkNumber);
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		Integer result = this.testSuite.setNoneOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(Numer0n_CONTINUE, result);
		assertTrue(player.getCandidatePlayerNumberList().size() == 0);
		assertEquals("123", player.getNotCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系03
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 桁:3
	 * info:NONEOPTION,657,1EAT2BITE<br>
	 * 候補数字:675<br>
	 * [期待値]<br>
	 * ・CONTINUEであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setNoneOptionAddCandidateMethod_normal03Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,657,1EAT2BITE";
		// chkNumber
		String chkNumber = "675";
		// 候補数字設定
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		Integer result = this.testSuite.setNoneOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(Numer0n_CONTINUE, result);
		assertEquals("675", computer.getCandidateCpuNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系04
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 桁:3
	 * info:NONEOPTION,906,0EAT0BITE<br>
	 * 候補数字:123<br>
	 * [期待値]<br>
	 * ・CONTINUEであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setNoneOptionAddCandidateMethod_normal04Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,906,0EAT0BITE";
		// chkNumber
		String chkNumber = "123";
		// 候補数字設定
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		Integer result = this.testSuite.setNoneOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(Numer0n_CONTINUE, result);
		assertEquals("123", computer.getCandidateCpuNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系05
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 桁:3
	 * info:NONEOPTION,427,1EAT0BITE<br>
	 * 候補数字:834<br>
	 * [期待値]<br>
	 * ・CONTINUEであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setNoneOptionAddCandidateMethod_normal05Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "NOOPTION,427,1EAT0BITE";
		// chkNumber
		String chkNumber = "834";
		// 候補数字設定
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		Integer result = this.testSuite.setNoneOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(Numer0n_CONTINUE, result);
		assertTrue(computer.getCandidateCpuNumberList().size() == 0);
		assertEquals("834", computer.getNotCandidateCpuNumberList().get(0));
	}

}
