package application.logic.info.ut;

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
import application.logic.info.Numer0nSpecifyNumber;
import application.logic.info.Numer0nSpecifyNumberImpl;
import application.logic.info.Numer0nSpecifyNumberMultipleConfirmationImpl;

/**
 * 数々の情報をもとに、どの数値を候補として残すか思考するテストクラス<br>
 * <p>
 * setArrangeNumberMultipleListのテスト
 * </p>
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Tag("UT")
public class Numer0nSpecifyNumberMultipleConfirmation_arrangeCandidateNumberMultipleConfirmation_UTTest {

	/**
	 * PLAYER
	 */
	private static final String PLAYER = "PLAYER";

	/**
	 * GameMaster mock
	 */
	@Mock
	private GameMaster gameMaster;

	/**
	 * Computer mock
	 */
	@Mock
	private Computer computer;

	/**
	 * Player mock
	 */
	@Mock
	private Player player;

	/**
	 * Numer0nInfo mock
	 */
	@Mock
	private Numer0nInfo info;

	/**
	 * Numer0nSpecifyNumber mock
	 */
	@Mock
	private Numer0nSpecifyNumber aiSpecifyNumber;

	/**
	 * GameComponentUtil
	 */
	@Mock
	private GameComponentUtil util;

	@InjectMocks
	private Numer0nSpecifyNumberMultipleConfirmationImpl testSuite;

	/**
	 * <p>
	 * 正常系01
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:DOUBLE,1,4<br>
	 * info:CHANGE,1,LOW<br>
	 * info:HIGH&LOW,LOW,LOW,HIGH<br>
	 * info:NOOPTION,148,2EAT0BITE<br>
	 * 候補の数字:138,347<br>
	 * 候補でない数字:619,745,149<br>
	 * [期待値]<br>
	 * ・候補の数字リストに149のみ格納されること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumberMultipleConfirmation_setArrangeNumberMultipleInfoList_normal01Test()
			throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);

		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		player.addCandidateNumberList("138");
		player.addCandidateNumberList("347");
		player.addNotCandidateNumberList("619");
		player.addNotCandidateNumberList("745");
		player.addNotCandidateNumberList("149");
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// Numer0nSpecifyNumber
		Numer0nSpecifyNumberImpl aiSpecifyNumber = spy(new Numer0nSpecifyNumberImpl(player, computer, gameMaster));
		ReflectionTestUtils.setField(this.testSuite, "aiSpecifyNumber", aiSpecifyNumber);
		// 情報設定
		Numer0nInfo info = spy(new Numer0nInfo());
		info.addPlayerInfoList("DOUBLE,1,4");
		info.addPlayerInfoList("CHANGE,1,LOW");
		info.addPlayerInfoList("HIGH&LOW,LOW,LOW,HIGH");
		info.addPlayerInfoList("NOOPTION,148,2EAT0BITE");
		ReflectionTestUtils.setField(this.testSuite, "info", info);
		// 期待値
		ArrayList<String> candidateExpectList = new ArrayList<String>();
		candidateExpectList.add("149");
		ArrayList<String> candidateNotExpectList = new ArrayList<String>();
		candidateNotExpectList.add("138");
		candidateNotExpectList.add("347");
		candidateNotExpectList.add("619");
		candidateNotExpectList.add("745");
		// 検証
		this.testSuite.arrangeCandidateNumberMultipleConfirmationLogic();

		player.getCandidatePlayerNumberList().remove("138");
		player.getCandidatePlayerNumberList().remove("347");
		player.getCandidatePlayerNumberList().remove("619");
		player.getCandidatePlayerNumberList().remove("745");
		player.getCandidatePlayerNumberList().remove("149");
		player.getNotCandidatePlayerNumberList().remove("149");
		player.getNotCandidatePlayerNumberList().remove("745");
		player.getNotCandidatePlayerNumberList().remove("619");

		assertEquals(candidateExpectList, player.getCandidatePlayerNumberList());
		assertEquals(candidateNotExpectList, player.getNotCandidatePlayerNumberList());
	}

	/**
	 * <p>
	 * 正常系02
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:DOUBLE,037,1EAT2BITE<br>
	 * info:CHANGE,--<br>
	 * info:SLASH,7<br>
	 * info:TARGET,2,EXISTLISTOFNUMBER,7<br>
	 * info:SHUFFLE,--<br>
	 * 候補の数字:512,498<br>
	 * 候補でない数字:703,307,204<br>
	 * [期待値]<br>
	 * ・候補の数字リストに307のみ格納されること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumberMultipleConfirmation_setArrangeNumberMultipleInfoList_normal02Test()
			throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		computer.addCandidateNumberList("512");
		computer.addCandidateNumberList("498");
		computer.addNotCandidateNumberList("703");
		computer.addNotCandidateNumberList("307");
		computer.addNotCandidateNumberList("204");
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// Numer0nSpecifyNumber
		Numer0nSpecifyNumberImpl aiSpecifyNumber = spy(new Numer0nSpecifyNumberImpl(player, computer, gameMaster));
		ReflectionTestUtils.setField(this.testSuite, "aiSpecifyNumber", aiSpecifyNumber);
		// 情報設定
		Numer0nInfo info = spy(new Numer0nInfo());
		info.addCpuInfoList("DOUBLE,037,1EAT2BITE");
		info.addCpuInfoList("CHANGE,--");
		info.addCpuInfoList("SLASH,7");
		info.addCpuInfoList("TARGET,2,EXISTLISTOFNUMBER,7");
		info.addCpuInfoList("SHUFFLE,--");
		ReflectionTestUtils.setField(this.testSuite, "info", info);
		// 期待値
		ArrayList<String> candidateExpectList = new ArrayList<String>();
		candidateExpectList.add("307");
		ArrayList<String> candidateNotExpectList = new ArrayList<String>();
		candidateNotExpectList.add("512");
		candidateNotExpectList.add("498");
		candidateNotExpectList.add("703");
		candidateNotExpectList.add("204");
		// 検証
		this.testSuite.arrangeCandidateNumberMultipleConfirmationLogic();

		computer.getCandidateCpuNumberList().remove("307");
		computer.getCandidateCpuNumberList().remove("512");
		computer.getCandidateCpuNumberList().remove("498");
		computer.getCandidateCpuNumberList().remove("703");
		computer.getCandidateCpuNumberList().remove("204");
		computer.getNotCandidateCpuNumberList().remove("703");
		computer.getNotCandidateCpuNumberList().remove("307");
		computer.getNotCandidateCpuNumberList().remove("204");

		assertEquals(candidateExpectList, computer.getCandidateCpuNumberList());
		assertEquals(candidateNotExpectList, computer.getNotCandidateCpuNumberList());
	}

	/**
	 * <p>
	 * 正常系03(カバレッジ網羅用)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:CHANGE,1,HIGH<br>
	 * info:SLASH,5<br>
	 * info:TARGET,2,NONEEXISTLISTOFNUMBER,4<br>
	 * info:TARGET,DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,7<br>
	 * 候補の数字:403,780<br>
	 * 候補でない数字:385,387<br>
	 * [期待値]<br>
	 * ・候補の数字リストに385のみ格納されること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumberMultipleConfirmation_setArrangeNumberMultipleInfoList_normal03Test()
			throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		computer.addCandidateNumberList("403");
		computer.addCandidateNumberList("780");
		computer.addNotCandidateNumberList("385");
		computer.addNotCandidateNumberList("387");
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// Numer0nSpecifyNumber
		Numer0nSpecifyNumberImpl aiSpecifyNumber = spy(new Numer0nSpecifyNumberImpl(player, computer, gameMaster));
		ReflectionTestUtils.setField(this.testSuite, "aiSpecifyNumber", aiSpecifyNumber);
		// 情報設定
		Numer0nInfo info = spy(new Numer0nInfo());
		info.addCpuInfoList("CHANGE,1,HIGH");
		info.addCpuInfoList("SLASH,5");
		info.addCpuInfoList("TARGET,2,NONEEXISTLISTOFNUMBER,4");
		info.addCpuInfoList("TARGET,DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,7");
		ReflectionTestUtils.setField(this.testSuite, "info", info);
		// 期待値
		ArrayList<String> candidateExpectList = new ArrayList<String>();
		candidateExpectList.add("385");
		ArrayList<String> candidateNotExpectList = new ArrayList<String>();
		candidateNotExpectList.add("403");
		candidateNotExpectList.add("780");
		candidateNotExpectList.add("387");
		// 検証
		this.testSuite.arrangeCandidateNumberMultipleConfirmationLogic();

		computer.getCandidateCpuNumberList().remove("403");
		computer.getCandidateCpuNumberList().remove("780");
		computer.getCandidateCpuNumberList().remove("385");
		computer.getCandidateCpuNumberList().remove("387");
		computer.getNotCandidateCpuNumberList().remove("385");
		computer.getNotCandidateCpuNumberList().remove("387");

		assertEquals(candidateExpectList, computer.getCandidateCpuNumberList());
		assertEquals(candidateNotExpectList, computer.getNotCandidateCpuNumberList());
	}

	/**
	 * <p>
	 * 正常系04(カバレッジ網羅用)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * info:TARGET,2,NONEEXISTLISTOFNUMBER,9<br>
	 * info:TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,1<br>
	 * info:TARGET,0,EXISTLISTOFNUMBER,8<br>
	 * 候補の数字:569,483<br>
	 * 候補でない数字:814,716<br>
	 * [期待値]<br>
	 * ・候補の数字リストに814のみ格納されること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumberMultipleConfirmation_setArrangeNumberMultipleInfoList_normal04Test()
			throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		computer.addCandidateNumberList("569");
		computer.addCandidateNumberList("483");
		computer.addNotCandidateNumberList("814");
		computer.addNotCandidateNumberList("716");
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// Numer0nSpecifyNumber
		Numer0nSpecifyNumberImpl aiSpecifyNumber = spy(new Numer0nSpecifyNumberImpl(player, computer, gameMaster));
		ReflectionTestUtils.setField(this.testSuite, "aiSpecifyNumber", aiSpecifyNumber);
		// 情報設定
		Numer0nInfo info = spy(new Numer0nInfo());
		info.addCpuInfoList("TARGET,2,NONEEXISTLISTOFNUMBER,9");
		info.addCpuInfoList("TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,1");
		info.addCpuInfoList("TARGET,0,EXISTLISTOFNUMBER,8");
		ReflectionTestUtils.setField(this.testSuite, "info", info);
		// 期待値
		ArrayList<String> candidateExpectList = new ArrayList<String>();
		candidateExpectList.add("814");
		ArrayList<String> candidateNotExpectList = new ArrayList<String>();
		candidateNotExpectList.add("569");
		candidateNotExpectList.add("483");
		candidateNotExpectList.add("716");
		// 検証
		this.testSuite.arrangeCandidateNumberMultipleConfirmationLogic();

		computer.getCandidateCpuNumberList().remove("569");
		computer.getCandidateCpuNumberList().remove("483");
		computer.getCandidateCpuNumberList().remove("814");
		computer.getCandidateCpuNumberList().remove("716");
		computer.getNotCandidateCpuNumberList().remove("814");
		computer.getNotCandidateCpuNumberList().remove("716");

		assertEquals(candidateExpectList, computer.getCandidateCpuNumberList());
		assertEquals(candidateNotExpectList, computer.getNotCandidateCpuNumberList());
	}

}
