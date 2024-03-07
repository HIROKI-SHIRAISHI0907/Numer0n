package application.logic.info.ut;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Method;
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
public class Numer0nSpecifyNumberMultipleConfirmation_setArrangeNumberMultipleList_UTTest {

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
	 * 候補の数字リスト:123,456,789<br>
	 * 候補でない数字リスト:105,248<br>
	 * [期待値]<br>
	 * ・候補の数字リストと候補でない数字リストが一緒のリストに格納されること
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	final void Numer0nSpecifyNumberMultipleConfirmation_setArrangeNumberMultipleList_normal01Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		player.addCandidateNumberList("123");
		player.addCandidateNumberList("456");
		player.addCandidateNumberList("789");
		player.addNotCandidateNumberList("105");
		player.addNotCandidateNumberList("248");
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		ArrayList<String> expectList = new ArrayList<String>();
		expectList.add("123");
		expectList.add("456");
		expectList.add("789");
		expectList.add("105");
		expectList.add("248");
		Method method = Numer0nSpecifyNumberMultipleConfirmationImpl.class.getDeclaredMethod(
				"setArrangeNumberMultipleList");
		method.setAccessible(true);
		ArrayList<String> resultList = (ArrayList<String>) method
				.invoke(this.testSuite);
		assertEquals(expectList, resultList);
	}

	/**
	 * <p>
	 * 正常系02
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁:3<br>
	 * 候補の数字リスト:123,456,789<br>
	 * 候補でない数字リスト:105,248<br>
	 * [期待値]<br>
	 * ・候補の数字リストと候補でない数字リストが一緒のリストに格納されること
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	final void Numer0nSpecifyNumberMultipleConfirmation_setArrangeNumberMultipleList_normal02Test() throws Exception {
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
		computer.addCandidateNumberList("123");
		computer.addCandidateNumberList("456");
		computer.addCandidateNumberList("789");
		computer.addNotCandidateNumberList("105");
		computer.addNotCandidateNumberList("248");
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		ArrayList<String> expectList = new ArrayList<String>();
		expectList.add("123");
		expectList.add("456");
		expectList.add("789");
		expectList.add("105");
		expectList.add("248");
		Method method = Numer0nSpecifyNumberMultipleConfirmationImpl.class.getDeclaredMethod(
				"setArrangeNumberMultipleList");
		method.setAccessible(true);
		ArrayList<String> resultList = (ArrayList<String>) method
				.invoke(this.testSuite);
		assertEquals(expectList, resultList);
	}

}
