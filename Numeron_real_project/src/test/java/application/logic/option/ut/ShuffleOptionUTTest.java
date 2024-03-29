package application.logic.option.ut;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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
import application.component.consts.DifficultyConst;
import application.component.consts.PriorityFlagConst;
import application.logic.human.GameMaster;
import application.logic.option.ShuffleOption;
import application.logic.option.map.ShuffleOptionMapUtil;

/**
 * <p>
 * シャッフルオプションテストクラス
 * </p>
 * @author shiraishitoshio
 *
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Tag("UT")
public class ShuffleOptionUTTest {

	/**
	 * ShuffleOptionMapUtil mock
	 */
	@Mock
	private ShuffleOptionMapUtil mapUtil;

	/**
	 * gameMaster mock
	 */
	@Mock
	private GameMaster gameMaster;

	@InjectMocks
	private ShuffleOption testSuite;

	@BeforeEach
	public void setUp() {
		this.mapUtil.clearDoPriorityMap();
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * <p>
	 * 正常系01
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * シャッフル対象数字:123<br>
	 * [期待値]<br>
	 * ・123であること
	 * @throws Exception
	 */
	@Test
	final void shuffleOption_normal01Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		gameMaster.setCorrectPlayerNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		this.testSuite.shuffleLogic();
		assertEquals("123", convertListToString(this.testSuite.getShuffleNumberList()));
	}

	/**
	 * <p>
	 * 正常系02
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY
	 * シャッフル対象数字:123<br>
	 * [期待値]<br>
	 * ・必ずシャッフルされること
	 * ・123とは異なること
	 * @throws Exception
	 */
	@Test
	final void shuffleOption_normal02Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		this.testSuite.shuffleLogic();
		assertNotEquals("123", convertListToString(this.testSuite.getShuffleNumberList()));
	}

	/**
	 * <p>
	 * 正常系03
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:NORMAL
	 * シャッフル対象数字:123<br>
	 * [期待値]<br>
	 * ・必ずシャッフルされること
	 * ・123とは異なること
	 * @throws Exception
	 */
	@Test
	final void shuffleOption_normal03Test() throws Exception {
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(DifficultyConst.NORMAL);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		this.testSuite.shuffleLogic();
		assertNotNull(convertListToString(this.testSuite.getShuffleNumberList()));
	}

	/**
	 * <p>
	 * 正常系04
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:INSANE<br>
	 * シャッフルフラグがfalseであること<br>
	 * シャッフル対象数字:123<br>
	 * [期待値]<br>
	 * ・123であること
	 * @throws Exception
	 */
	@Test
	final void shuffleOption_normal04Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(DifficultyConst.INSANE);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("2");
		// 検証
		this.testSuite.shuffleLogic();
		assertEquals("123", convertListToString(this.testSuite.getShuffleNumberList()));
	}

	/**
	 * <p>
	 * 正常系05
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:INSANE<br>
	 * 優先フラグが含まれていること<br>
	 * シャッフルフラグがtrueであること<br>
	 * シャッフル対象数字:123<br>
	 * [期待値]<br>
	 * ・123とは異なること
	 * @throws Exception
	 */
	@Test
	final void shuffleOption_normal05Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(DifficultyConst.INSANE);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		when(this.mapUtil.containValueDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG))).thenReturn(true);
		when(this.mapUtil.getDoPriorityMap(eq(PriorityFlagConst.SAI_YUUSEN_FLAG)))
				.thenReturn("1");
		// 検証
		this.testSuite.shuffleLogic();
		assertNotEquals("123", convertListToString(this.testSuite.getShuffleNumberList()));
	}

	/**
	 * <p>
	 * 正常系06
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:INSANE<br>
	 * 優先フラグが含まれていること<br>
	 * シャッフルフラグがfalseであること<br>
	 * シャッフル対象数字:123<br>
	 * [期待値]<br>
	 * ・123であること
	 * @throws Exception
	 */
	@Test
	final void shuffleOption_normal06Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(DifficultyConst.INSANE);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 最優先フラグを設定
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", this.mapUtil);
		// 検証
		this.testSuite.shuffleLogic();
		ArrayList<String> resultList = this.testSuite.getShuffleNumberList();
		assertTrue("123".equals(convertListToString(resultList)) ||
				!"123".equals(convertListToString(resultList)));
	}

	/**
	 * <p>
	 * 正常系07
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:HARD<br>
	 * シャッフル対象数字:123<br>
	 * [期待値]<br>
	 * ・nullでないこと
	 * @throws Exception
	 */
	@Test
	final void shuffleOption_normal07Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(DifficultyConst.HARD);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		this.testSuite.shuffleLogic();
		assertNotNull(convertListToString(this.testSuite.getShuffleNumberList()));
	}

	/**
	 * <p>
	 * 正常系08
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EXHAUSTED<br>
	 * シャッフル対象数字:123<br>
	 * [期待値]<br>
	 * ・nullでないこと
	 * @throws Exception
	 */
	@Test
	final void shuffleOption_normal08Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(DifficultyConst.EXHAUSTED);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		this.testSuite.shuffleLogic();
		assertNotNull(convertListToString(this.testSuite.getShuffleNumberList()));
	}

	/**
	 * 文字連結をおこなう。
	 * @param array 文字列リスト
	 * @return returnNum 連結後の文字列
	 */
	private String convertListToString(ArrayList<String> array) {
		String returnNum = "";
		for (String str : array) {
			returnNum += str;
		}
		return returnNum;
	}
}
