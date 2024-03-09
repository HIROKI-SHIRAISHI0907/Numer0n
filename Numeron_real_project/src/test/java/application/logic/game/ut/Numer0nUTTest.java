package application.logic.game.ut;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

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
import application.component.error.CreateErrorExceptionComponentImpl;
import application.logic.game.Numer0n;
import application.logic.human.Computer;
import application.logic.human.GameMaster;
import application.logic.human.Player;
import application.logic.human.gameComponent.GameComponentUtil;
import application.logic.info.Numer0nInfo;
import application.logic.info.Numer0nNextAction;
import application.logic.judge.Eatbite;
import application.logic.option.ChangeOption;
import application.logic.option.CommonOptionImpl;
import application.logic.option.DoubleOption;
import application.logic.option.HighlowOption;
import application.logic.option.ShuffleOption;
import application.logic.option.SlashOption;
import application.logic.option.TargetOption;
import application.logic.option.map.ChangeOptionMapUtil;
import application.logic.option.map.DoubleOptionMapUtil;
import application.logic.option.map.ShuffleOptionMapUtil;
import application.logic.option.map.TargetOptionMapUtil;

/**
 * Numer0nテストクラス<br>
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Tag("UT")
public class Numer0nUTTest {

	/**
	 * Numer0nNextAction
	 */
	@Mock
	private Numer0nNextAction action;

	@InjectMocks
	private Numer0n testSuite;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * <p>
	 * CPU正常系01
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * [期待値]
	 * @throws Exception
	 */
	@Test
	final void numer0n_playerNormal01Test() throws Exception {
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName("PLAYER");
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectPlayerNumberList(list);
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		Numer0nInfo info = spy(new Numer0nInfo());
		ReflectionTestUtils.setField(this.testSuite, "info", info);
		GameComponentUtil utilMap = spy(new GameComponentUtil());
		utilMap.addOffenseOptionList(Const.DOUBLE);
		ReflectionTestUtils.setField(this.testSuite, "utilMap", utilMap);
		Player player = spy(new Player(gameMaster, info, utilMap));
		ArrayList<String> calllist1 = new ArrayList<>();
		calllist1.add("3");
		calllist1.add("4");
		calllist1.add("5");
		doNothing().when(player).doCallNumber();
		when(player.getCallNumber()).thenReturn(calllist1);
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, info, utilMap));
		doNothing().when(computer).doCallNumber();
		when(computer.getCallNumber()).thenReturn(calllist1);
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		Eatbite eb = spy(new Eatbite(gameMaster));
		ReflectionTestUtils.setField(this.testSuite, "eb", eb);
		CreateErrorExceptionComponentImpl exceptionComponent = spy(new CreateErrorExceptionComponentImpl());
		ChangeOptionMapUtil changeMapUtil = spy(new ChangeOptionMapUtil());
		ChangeOption changeOption = spy(new ChangeOption(gameMaster, changeMapUtil, exceptionComponent));
		DoubleOptionMapUtil doubleMapUtil = spy(new DoubleOptionMapUtil());
		DoubleOption doubleOption = spy(new DoubleOption(gameMaster, doubleMapUtil, computer, player, eb, info));
		HighlowOption highlowOption = spy(new HighlowOption(gameMaster, exceptionComponent));
		ShuffleOptionMapUtil shuffleMapUtil = spy(new ShuffleOptionMapUtil());
		ShuffleOption shuffleOption = spy(new ShuffleOption(gameMaster, shuffleMapUtil, exceptionComponent));
		SlashOption slashOption = spy(new SlashOption(gameMaster, exceptionComponent));
		TargetOptionMapUtil targetMapUtil = spy(new TargetOptionMapUtil());
		TargetOption targetOption = spy(new TargetOption(gameMaster, targetMapUtil));
		CommonOptionImpl option = spy(new CommonOptionImpl(gameMaster, info,
				changeOption, doubleOption, highlowOption, shuffleOption, slashOption, targetOption));
		ReflectionTestUtils.setField(this.testSuite, "option", option);

		// 検証
		this.testSuite.startNumer0n();
	}


	/**
	 * <p>
	 * CPU正常系01
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 交換する数字:2:最優先フラグ付き
	 * 宣言する桁:100の位:最優先フラグ付き<br>
	 * [期待値]<br>
	 * ・交換した数字:2であること<br>
	 * ・交換した数字の情報:LOWであること<br>
	 * ・交換した桁:0であること<br>
	 * ・交換後の数字:245であること<br>
	 * @throws Exception
	 */
	@Test
	final void changeOption_realInfoMap_cpuNormal01Test() throws Exception {
		// 対象名
		ReflectionTestUtils.setField(this.testSuite, "chkMember", "CPU");
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setDifficulty(Const.EASY);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info,map
		Numer0nInfo info = spy(new Numer0nInfo());
		GameComponentUtil utilMap = spy(new GameComponentUtil());
		ReflectionTestUtils.setField(this.testSuite, "info", info);
		ReflectionTestUtils.setField(this.testSuite, "utilMap", utilMap);
		// 候補数字設定
		Player player = spy(new Player(gameMaster, info, utilMap));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, info, utilMap));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 例外
		CreateErrorExceptionComponentImpl exceptionComponent = spy(new CreateErrorExceptionComponentImpl());
		ReflectionTestUtils.setField(this.testSuite, "exceptionComponent", exceptionComponent);
		// 検証
	}

}
