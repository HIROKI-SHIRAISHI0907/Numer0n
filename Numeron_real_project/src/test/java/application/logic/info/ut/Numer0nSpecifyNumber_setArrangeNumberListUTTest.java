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

/**
 * 得られた情報を元に整理する数字を格納するテストクラス
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Tag("UT")
public class Numer0nSpecifyNumber_setArrangeNumberListUTTest {

	/**
	 * PLAYER
	 */
	private static final String PLAYER = "PLAYER";

	/**
	 * Player
	 */
	@Mock
	private Player player;

	/**
	 * Computer
	 */
	@Mock
	private Computer computer;

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
	 * 候補数字:825<br>
	 * [期待値]<br>
	 * ・825であること
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	final void Numer0nSpecifyNumber_setArrangeNumberList_normal01Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		// 候補数字設定
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		computer.addCandidateNumberList("825");
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		Method method = Numer0nSpecifyNumber.class.getDeclaredMethod(
				"setArrangeNumberList");
		method.setAccessible(true);
		ArrayList<String> resultList = (ArrayList<String>) method
				.invoke(this.testSuite);
		assertEquals("825", resultList.get(resultList.size() - 1));
	}

	/**
	 * <p>
	 * 正常系02
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 候補数字:空<br>
	 * [期待値]<br>
	 * ・空であること
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	final void Numer0nSpecifyNumber_setArrangeNumberList_normal02Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		// 候補数字設定
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		Method method = Numer0nSpecifyNumber.class.getDeclaredMethod(
				"setArrangeNumberList");
		method.setAccessible(true);
		ArrayList<String> resultList = (ArrayList<String>) method
				.invoke(this.testSuite);
		assertTrue(resultList.size() == 0);
	}

	/**
	 * <p>
	 * 正常系03
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 候補数字:825<br>
	 * [期待値]<br>
	 * ・825であること
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	final void Numer0nSpecifyNumber_setArrangeNumberList_normal03Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		player.addCandidateNumberList("825");
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		Method method = Numer0nSpecifyNumber.class.getDeclaredMethod(
				"setArrangeNumberList");
		method.setAccessible(true);
		ArrayList<String> resultList = (ArrayList<String>) method
				.invoke(this.testSuite);
		assertEquals("825", resultList.get(resultList.size() - 1));
	}

	/**
	 * <p>
	 * 正常系04
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 候補数字:空<br>
	 * [期待値]<br>
	 * ・空であること
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	final void Numer0nSpecifyNumber_setArrangeNumberList_normal04Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		Method method = Numer0nSpecifyNumber.class.getDeclaredMethod(
				"setArrangeNumberList");
		method.setAccessible(true);
		ArrayList<String> resultList = (ArrayList<String>) method
				.invoke(this.testSuite);
		assertTrue(resultList.size() == 0);
	}

}
