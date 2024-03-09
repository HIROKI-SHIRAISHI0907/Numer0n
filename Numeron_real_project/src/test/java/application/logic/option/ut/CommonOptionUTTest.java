package application.logic.option.ut;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;

import application.component.consts.Const;
import application.component.consts.Numer0nOptionEnum;
import application.logic.human.Computer;
import application.logic.human.GameMaster;
import application.logic.human.Player;
import application.logic.info.Numer0nInfo;
import application.logic.info.Numer0nSpecifyNumber;
import application.logic.judge.Eatbite;
import application.logic.option.ChangeOption;
import application.logic.option.CommonOptionImpl;
import application.logic.option.DoubleOption;
import application.logic.option.HighlowOption;
import application.logic.option.ShuffleOption;
import application.logic.option.SlashOption;
import application.logic.option.TargetOption;
import application.logic.option.map.DoubleOptionMapUtil;

/**
 * <p>
 * オプション選択テストクラス
 * </p>
 * @author shiraishitoshio
 *
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Tag("UT")
public class CommonOptionUTTest {

	/**
	 * PLAYER名
	 */
	private static final String PLAYER = "PLAYER";

	/**
	 * Numer0n続行
	 */
	private static final Integer Numer0n_CONTINUE = 0;

	/**
	 * Numer0n終了
	 */
	private static final Integer Numer0n_GAMEOVER = 1;

	/**
	 * gameMaster mock
	 */
	@Mock
	private GameMaster gameMaster;

	/**
	 * ChangeOption mock
	 */
	@Mock
	private ChangeOption changeOption;

	/**
	 * DoubleOption mock
	 */
	@Mock
	private DoubleOption doubleOption;

	/**
	 * HighlowOption mock
	 */
	@Mock
	private HighlowOption highLowOption;

	/**
	 * ShuffleOption mock
	 */
	@Mock
	private ShuffleOption shuffleOption;

	/**
	 * SlashOption mock
	 */
	@Mock
	private SlashOption slashOption;

	/**
	 * TargetOption mock
	 */
	@Mock
	private TargetOption targetOption;

	/**
	 * Computer mock
	 */
	@Mock
	private Computer computer;

	/**
	 * Computer mock
	 */
	@Mock
	private Player player;

	/**
	 * Eatbite mock
	 */
	@Mock
	private Eatbite eatBite;

	/**
	 * Numer0nSpecifyNumber mock
	 */
	@Mock
	private Numer0nSpecifyNumber aiSpecifyNumber;

	/**
	 * Numer0nInfo mock
	 */
	@Mock
	private Numer0nInfo info;

	/**
	 * DoubleOptionMapUtil mock
	 */
	@Mock
	private DoubleOptionMapUtil mapUtil;

	/**
	 * CommonOptionImpl
	 */
	@InjectMocks
	private CommonOptionImpl testSuite;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * <p>
	 * PLAYER正常系01
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * オプション:DOUBLE<br>
	 * 返却値:TEACH_NUMBER<br>
	 * [期待値]<br>
	 * ・0(Numer0n_CONTINUE)であること
	 * @throws Exception
	 */
	@Test
	final void commonOption_playerNormal01Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// mock
		when(this.doubleOption.doubleLogic()).thenReturn(DoubleOption.TEACH_NUMBER);
		// 検証
		Integer value = this.testSuite.summarizeOption(Numer0nOptionEnum.DOUBLE.getOprionName());
		assertEquals(Numer0n_CONTINUE, value);
	}

	/**
	 * <p>
	 * PLAYER正常系02
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * オプション:DOUBLE<br>
	 * 返却値:ALL_EAT<br>
	 * [期待値]<br>
	 * ・1(Numer0n_GAMEOVER)であること
	 * @throws Exception
	 */
	@Test
	final void commonOption_playerNormal02Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// mock
		when(this.doubleOption.doubleLogic()).thenReturn(DoubleOption.ALL_EAT);
		// 検証
		Integer value = this.testSuite.summarizeOption(Numer0nOptionEnum.DOUBLE.getOprionName());
		assertEquals(Numer0n_GAMEOVER, value);
	}

	/**
	 * <p>
	 * PLAYER正常系03
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * オプション:HIGH&LOW<br>
	 * [期待値]<br>
	 * ・0(Numer0n_CONTINUE)であること
	 * @throws Exception
	 */
	@Test
	final void commonOption_playerNormal03Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		Integer value = this.testSuite.summarizeOption(Numer0nOptionEnum.HIGHLOW.getOprionName());
		assertEquals(Numer0n_CONTINUE, value);
	}

	/**
	 * <p>
	 * PLAYER正常系04
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * オプション:SLASH<br>
	 * [期待値]<br>
	 * ・0(Numer0n_CONTINUE)であること
	 * @throws Exception
	 */
	@Test
	final void commonOption_playerNormal04Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		Integer value = this.testSuite.summarizeOption(Numer0nOptionEnum.SLASH.getOprionName());
		assertEquals(Numer0n_CONTINUE, value);
	}

	/**
	 * <p>
	 * PLAYER正常系05
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * オプション:TARGET<br>
	 * [期待値]<br>
	 * ・0(Numer0n_CONTINUE)であること
	 * @throws Exception
	 */
	@Test
	final void commonOption_playerNormal05Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		Integer value = this.testSuite.summarizeOption(Numer0nOptionEnum.TARGET.getOprionName());
		assertEquals(Numer0n_CONTINUE, value);
	}

	/**
	 * <p>
	 * PLAYER正常系06
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * オプション:CHANGE<br>
	 * [期待値]<br>
	 * ・0(Numer0n_CONTINUE)であること
	 * @throws Exception
	 */
	@Test
	final void commonOption_playerNormal06Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		Integer value = this.testSuite.summarizeOption(Numer0nOptionEnum.CHANGE.getOprionName());
		assertEquals(Numer0n_CONTINUE, value);
	}

	/**
	 * <p>
	 * PLAYER正常系07
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * オプション:SLASH<br>
	 * [期待値]<br>
	 * ・0(Numer0n_CONTINUE)であること
	 * @throws Exception
	 */
	@Test
	final void commonOption_playerNormal07Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		Integer value = this.testSuite.summarizeOption(Numer0nOptionEnum.SLASH.getOprionName());
		assertEquals(Numer0n_CONTINUE, value);
	}

	/**
	 * <p>
	 * PLAYER正常系08
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * オプション:SHUFFLE<br>
	 * [期待値]<br>
	 * ・0(Numer0n_CONTINUE)であること
	 * @throws Exception
	 */
	@Test
	final void commonOption_playerNormal08Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		Integer value = this.testSuite.summarizeOption(Numer0nOptionEnum.SHUFFLE.getOprionName());
		assertEquals(Numer0n_CONTINUE, value);
	}

	/**
	 * <p>
	 * CPU正常系01
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * オプション:DOUBLE<br>
	 * 返却値:TEACH_NUMBER<br>
	 * [期待値]<br>
	 * ・0(Numer0n_CONTINUE)であること
	 * @throws Exception
	 */
	@Test
	final void commonOption_cpuNormal01Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// mock
		when(this.doubleOption.doubleLogic()).thenReturn(DoubleOption.TEACH_NUMBER);
		// 検証
		Integer value = this.testSuite.summarizeOption(Numer0nOptionEnum.DOUBLE.getOprionName());
		assertEquals(Numer0n_CONTINUE, value);
	}

	/**
	 * <p>
	 * CPU正常系02
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * オプション:DOUBLE<br>
	 * 返却値:ALL_EAT<br>
	 * [期待値]<br>
	 * ・1(Numer0n_GAMEOVER)であること
	 * @throws Exception
	 */
	@Test
	final void commonOption_cpuNormal02Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// mock
		when(this.doubleOption.doubleLogic()).thenReturn(DoubleOption.ALL_EAT);
		// 検証
		Integer value = this.testSuite.summarizeOption(Numer0nOptionEnum.DOUBLE.getOprionName());
		assertEquals(Numer0n_GAMEOVER, value);
	}

	/**
	 * <p>
	 * CPU正常系03
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * オプション:HIGH&LOW<br>
	 * [期待値]<br>
	 * ・0(Numer0n_CONTINUE)であること
	 * @throws Exception
	 */
	@Test
	final void commonOption_cpuNormal03Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		Integer value = this.testSuite.summarizeOption(Numer0nOptionEnum.HIGHLOW.getOprionName());
		assertEquals(Numer0n_CONTINUE, value);
	}

	/**
	 * <p>
	 * CPU正常系04
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * オプション:SLASH<br>
	 * [期待値]<br>
	 * ・0(Numer0n_CONTINUE)であること
	 * @throws Exception
	 */
	@Test
	final void commonOption_cpuNormal04Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		Integer value = this.testSuite.summarizeOption(Numer0nOptionEnum.SLASH.getOprionName());
		assertEquals(Numer0n_CONTINUE, value);
	}

	/**
	 * <p>
	 * CPU正常系05
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * オプション:TARGET<br>
	 * [期待値]<br>
	 * ・0(Numer0n_CONTINUE)であること
	 * @throws Exception
	 */
	@Test
	final void commonOption_cpuNormal05Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// mock
		when(this.targetOption.getExistsInd()).thenReturn(2);
		// 検証
		Integer value = this.testSuite.summarizeOption(Numer0nOptionEnum.TARGET.getOprionName());
		assertEquals(Numer0n_CONTINUE, value);
	}

	/**
	 * <p>
	 * CPU正常系06
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * オプション:CHANGE<br>
	 * [期待値]<br>
	 * ・0(Numer0n_CONTINUE)であること
	 * @throws Exception
	 */
	@Test
	final void commonOption_cpuNormal06Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// mock
		when(this.changeOption.getDigitInd()).thenReturn(1);
		// 検証
		Integer value = this.testSuite.summarizeOption(Numer0nOptionEnum.CHANGE.getOprionName());
		assertEquals(Numer0n_CONTINUE, value);
	}

	/**
	 * <p>
	 * CPU正常系07
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * オプション:SLASH<br>
	 * [期待値]<br>
	 * ・0(Numer0n_CONTINUE)であること
	 * @throws Exception
	 */
	@Test
	final void commonOption_cpuNormal07Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		Integer value = this.testSuite.summarizeOption(Numer0nOptionEnum.SLASH.getOprionName());
		assertEquals(Numer0n_CONTINUE, value);
	}

	/**
	 * <p>
	 * CPU正常系08
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * オプション:SHUFFLE<br>
	 * [期待値]<br>
	 * ・0(Numer0n_CONTINUE)であること
	 * @throws Exception
	 */
	@Test
	final void commonOption_cpuNormal08Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		Integer value = this.testSuite.summarizeOption(Numer0nOptionEnum.SHUFFLE.getOprionName());
		assertEquals(Numer0n_CONTINUE, value);
	}

	/**
	 * <p>
	 * CPU正常系09
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * オプション:TARGET<br>
	 * [期待値]<br>
	 * ・0(Numer0n_CONTINUE)であること
	 * @throws Exception
	 */
	@Test
	final void commonOption_cpuNormal09Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// mock
		when(this.targetOption.getExistsInd()).thenReturn(-1);
		// 検証
		Integer value = this.testSuite.summarizeOption(Numer0nOptionEnum.TARGET.getOprionName());
		assertEquals(Numer0n_CONTINUE, value);
	}

	/**
	 * <p>
	 * CPU正常系10
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * オプション:CHANGE<br>
	 * [期待値]<br>
	 * ・0(Numer0n_CONTINUE)であること
	 * @throws Exception
	 */
	@Test
	final void commonOption_cpuNormal10Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.EASY);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// mock
		when(this.changeOption.getDigitInd()).thenReturn(-1);
		// 検証
		Integer value = this.testSuite.summarizeOption(Numer0nOptionEnum.CHANGE.getOprionName());
		assertEquals(Numer0n_CONTINUE, value);
	}

	/**
	 * <p>
	 * CPU正常系11
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EXHAUSTED<br>
	 * オプション:任意<br>
	 * [期待値]<br>
	 * ・0(Numer0n_CONTINUE)であること
	 * @throws Exception
	 */
	@Test
	final void commonOption_cpuNormal11Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.EXHAUSTED);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		Integer value = this.testSuite.summarizeOption(Numer0nOptionEnum.SHUFFLE.getOprionName());
		assertEquals(Numer0n_CONTINUE, value);
	}

	/**
	 * <p>
	 * CPU正常系11
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:INSANE<br>
	 * オプション:任意<br>
	 * [期待値]<br>
	 * ・0(Numer0n_CONTINUE)であること
	 * @throws Exception
	 */
	@Test
	final void commonOption_cpuNormal12Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.INSANE);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);

		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		Integer value = this.testSuite.summarizeOption(Numer0nOptionEnum.SHUFFLE.getOprionName());
		assertEquals(Numer0n_CONTINUE, value);
	}

}
