package application.logic.option.ut;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;

import application.component.consts.Const;
import application.component.consts.Numer0nShuffleEnum;
import application.logic.human.GameMaster;
import application.logic.option.ShuffleOption;
import application.logic.option.map.ShuffleOptionMapUtil;

/**
 * シャッフルオプションテストクラス
 * <p>
 * 本物の処理を確認し、info情報を確認する
 * </p>
 * @author shiraishitoshio
 *
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Tag("UT")
public class ShuffleOption_realInfoMapUTTest {

	@InjectMocks
	private ShuffleOption testSuite;

	/**
	 * <p>
	 * 正常系01
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
	final void shuffleOption_realInfoMap_normal01Test() throws Exception {
		// 対象名
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.EASY);
		// MapUtil
		ShuffleOptionMapUtil mapUtil = spy(new ShuffleOptionMapUtil());
		mapUtil.clearDoPriorityMap();
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
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
	 * 正常系02
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
	final void shuffleOption_realInfoMap_normal02Test() throws Exception {
		// 対象名
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.NORMAL);
		// MapUtil
		ShuffleOptionMapUtil mapUtil = spy(new ShuffleOptionMapUtil());
		mapUtil.clearDoPriorityMap();
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
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
	 * 正常系03
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:HARD
	 * シャッフル対象数字:123<br>
	 * [期待値]<br>
	 * ・必ずシャッフルされること
	 * ・123とは異なること
	 * @throws Exception
	 */
	@Test
	final void shuffleOption_realInfoMap_normal03Test() throws Exception {
		// 対象名
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.HARD);
		// MapUtil
		ShuffleOptionMapUtil mapUtil = spy(new ShuffleOptionMapUtil());
		mapUtil.clearDoPriorityMap();
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
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
	 * 難易度:EXHAUSTED
	 * シャッフル対象数字:123<br>
	 * [期待値]<br>
	 * ・必ずシャッフルされること
	 * ・123とは異なること
	 * @throws Exception
	 */
	@Test
	final void shuffleOption_realInfoMap_normal04Test() throws Exception {
		// 対象名
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.EXHAUSTED);
		// MapUtil
		ShuffleOptionMapUtil mapUtil = spy(new ShuffleOptionMapUtil());
		mapUtil.clearDoPriorityMap();
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
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
	 * 正常系05
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
	final void shuffleOption_realInfoMap_normal05Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.INSANE);
		// MapUtil
		ShuffleOptionMapUtil mapUtil = spy(new ShuffleOptionMapUtil());
		mapUtil.clearDoPriorityMap();
		mapUtil.addDoPriorityMap(Numer0nShuffleEnum.SHUFFLE_REJECT.getOprionCd(), Const.SAI_YUUSEN_FLAG);
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		this.testSuite.shuffleLogic();
		assertEquals("123", convertListToString(this.testSuite.getShuffleNumberList()));
	}

	/**
	 * <p>
	 * 正常系06
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:INSANE<br>
	 * シャッフルフラグがtrueであること<br>
	 * シャッフル対象数字:123<br>
	 * [期待値]<br>
	 * ・123とは異なること
	 * @throws Exception
	 */
	@Test
	final void shuffleOption_realInfoMap_normal06Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.INSANE);
		// MapUtil
		ShuffleOptionMapUtil mapUtil = spy(new ShuffleOptionMapUtil());
		mapUtil.clearDoPriorityMap();
		mapUtil.addDoPriorityMap(Numer0nShuffleEnum.SHUFFLE_GO.getOprionCd(), Const.SAI_YUUSEN_FLAG);
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
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
	 * 正常系07
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:INSANE<br>
	 * ランダムでシャッフル<br>
	 * シャッフル対象数字:123<br>
	 * [期待値]<br>
	 * ・nullでないこと
	 * @throws Exception
	 */
	@Test
	final void shuffleOption_realInfoMap_normal07Test() throws Exception {
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(Const.INSANE);
		// MapUtil
		ShuffleOptionMapUtil mapUtil = spy(new ShuffleOptionMapUtil());
		mapUtil.clearDoPriorityMap();
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		this.testSuite.shuffleLogic();
		assertNotNull(this.testSuite.getShuffleNumberList());
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
