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
import application.logic.info.Numer0nInfo;
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
public class Numer0nSpecifyNumberMultipleConfirmation_setArrangeNumberMultipleInfoList_UTTest {

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
	 * info:NOPTION,148,2EAT0BITE<br>
	 * [期待値]<br>
	 * ・候補の数字リストと候補でない数字リストが一緒のリストに格納されること
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	final void Numer0nSpecifyNumberMultipleConfirmation_setArrangeNumberMultipleInfoList_normal01Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 情報設定
		Numer0nInfo info = spy(new Numer0nInfo());
		info.addPlayerInfoList("DOUBLE,1,4");
		info.addPlayerInfoList("CHANGE,1,LOW");
		info.addPlayerInfoList("HIGH&LOW,LOW,LOW,HIGH");
		info.addPlayerInfoList("NOPTION,148,2EAT0BITE");
		ReflectionTestUtils.setField(this.testSuite, "info", info);
		// 検証
		ArrayList<String> expectList = new ArrayList<String>();
		expectList.add("DOUBLE,1,4");
		expectList.add("CHANGE,1,LOW");
		expectList.add("HIGH&LOW,LOW,LOW,HIGH");
		expectList.add("NOPTION,148,2EAT0BITE");
		Method method = Numer0nSpecifyNumberMultipleConfirmationImpl.class.getDeclaredMethod(
				"setArrangeNumberMultipleInfoList");
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
	 * info:DOUBLE,037,1EAT2BITE<br>
	 * info:SLASH,6<br>
	 * info:TARGET,2,EXISTLISTOFNUMBER,7<br>
	 * info:SHUFFLE,--<br>
	 * [期待値]<br>
	 * ・候補の数字リストと候補でない数字リストが一緒のリストに格納されること
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	final void Numer0nSpecifyNumberMultipleConfirmation_setArrangeNumberMultipleInfoList_normal02Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 情報設定
		Numer0nInfo info = spy(new Numer0nInfo());
		info.addCpuInfoList("DOUBLE,037,1EAT2BITE");
		info.addCpuInfoList("SLASH,6");
		info.addCpuInfoList("TARGET,2,EXISTLISTOFNUMBER,7");
		info.addCpuInfoList("SHUFFLE,--");
		ReflectionTestUtils.setField(this.testSuite, "info", info);
		// 検証
		ArrayList<String> expectList = new ArrayList<String>();
		expectList.add("DOUBLE,037,1EAT2BITE");
		expectList.add("SLASH,6");
		expectList.add("TARGET,2,EXISTLISTOFNUMBER,7");
		expectList.add("SHUFFLE,--");
		Method method = Numer0nSpecifyNumberMultipleConfirmationImpl.class.getDeclaredMethod(
				"setArrangeNumberMultipleInfoList");
		method.setAccessible(true);
		ArrayList<String> resultList = (ArrayList<String>) method
				.invoke(this.testSuite);
		assertEquals(expectList, resultList);
	}

}
