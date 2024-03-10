package application.logic.option.ut;

import static org.junit.Assert.*;
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
import application.component.consts.DifficultyConst;
import application.component.consts.Numer0nSelectNumberEnum;
import application.logic.human.GameMaster;
import application.logic.option.TargetOption;
import application.logic.option.map.TargetOptionMapUtil;

/**
 * ターゲットオプションテストクラス
 * <p>
 * 本物の処理を確認し、info情報を確認する
 * </p>
 * @author shiraishitoshio
 *
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Tag("UT")
public class TargetOption_realInfoMapUTTest {

	@InjectMocks
	private TargetOption testSuite;

	/**
	 * <p>
	 * CPU正常系01
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 指定する数字:4<br>
	 * [期待値]<br>
	 * ・EXISTLISTOFNUMBERのNONEEXISTLISTOFNUMBERのいずれがが返却されること<br>
	 * ・指定した数字:nullでないこと<br>
	 * ・指定した数字が存在する桁:nullでないこと<br>
	 * @throws Exception
	 */
	@Test
	final void targetOption_realInfoMap_cpuNormal01Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectPlayerNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		TargetOptionMapUtil mapUtil = spy(new TargetOptionMapUtil());
		mapUtil.clearSelectNumberPriorityMap();
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
		// 検証
		String result = this.testSuite.targetLogic();
		assertTrue("EXISTLISTOFNUMBER".equals(result)
				|| "NONEEXISTLISTOFNUMBER".equals(result));
		assertNotNull(this.testSuite.getExNum());
		assertNotNull(this.testSuite.getExistsInd());
	}

	/**
	 * <p>
	 * CPU正常系02
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EXHAUSTED<br>
	 * 桁数:4<br>
	 * チェンジ対象数字:3450<br>
	 * 指定する数字:0<br>
	 * [期待値]<br>
	 * ・EXISTLISTOFNUMBERのNONEEXISTLISTOFNUMBERのいずれがが返却されること<br>
	 * ・指定した数字:nullでないこと<br>
	 * ・指定した数字が存在する桁:-1(初期化)であること<br>
	 * @throws Exception
	 */
	@Test
	final void targetOption_realInfoMap_cpuNormal02Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(4);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(DifficultyConst.EXHAUSTED);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("0");
		gameMaster.setCorrectPlayerNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		TargetOptionMapUtil mapUtil = spy(new TargetOptionMapUtil());
		mapUtil.clearSelectNumberPriorityMap();
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
		// 検証
		String result = this.testSuite.targetLogic();
		assertTrue("EXISTLISTOFNUMBER".equals(result)
				|| "NONEEXISTLISTOFNUMBER".equals(result));
		assertNotNull(this.testSuite.getExNum());
		assertEquals(-1, this.testSuite.getExistsInd());
	}

	/**
	 * <p>
	 * CPU正常系03
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:INSANE<br>
	 * 桁数:5<br>
	 * チェンジ対象数字:39450<br>
	 * 指定する数字:4<br>
	 * [期待値]<br>
	 * ・EXISTLISTOFNUMBERのNONEEXISTLISTOFNUMBERのいずれがが返却されること<br>
	 * ・指定した数字:nullでないこと<br>
	 * ・指定した数字が存在する桁:-1(初期化)であること<br>
	 * @throws Exception
	 */
	@Test
	final void targetOption_realInfoMap_cpuNormal03Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(5);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(DifficultyConst.INSANE);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("9");
		list.add("4");
		list.add("5");
		list.add("0");
		gameMaster.setCorrectPlayerNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		TargetOptionMapUtil mapUtil = spy(new TargetOptionMapUtil());
		mapUtil.clearSelectNumberPriorityMap();
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
		// 検証
		String result = this.testSuite.targetLogic();
		assertTrue("EXISTLISTOFNUMBER".equals(result)
				|| "NONEEXISTLISTOFNUMBER".equals(result));
		assertNotNull(this.testSuite.getExNum());
		assertEquals(-1, this.testSuite.getExistsInd());
	}

	/**
	 * <p>
	 * CPU正常系04
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EASY<br>
	 * 桁数:3<br>
	 * チェンジ対象数字:345<br>
	 * 指定する数字:4<br>
	 * 最優先フラグ:4<br>
	 * [期待値]<br>
	 * ・EXISTLISTOFNUMBERが返却されること<br>
	 * ・指定した数字:4であること<br>
	 * ・指定した数字が存在する桁:1であること<br>
	 * @throws Exception
	 */
	@Test
	final void targetOption_realInfoMap_cpuNormal04Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(DifficultyConst.EASY);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		gameMaster.setCorrectPlayerNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		TargetOptionMapUtil mapUtil = spy(new TargetOptionMapUtil());
		mapUtil.clearSelectNumberPriorityMap();
		mapUtil.addSelectNumberPriorityMap(Numer0nSelectNumberEnum.FOUR.getNum(), Const.SAI_YUUSEN_FLAG);
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
		// 検証
		String result = this.testSuite.targetLogic();
		assertEquals("EXISTLISTOFNUMBER", result);
		assertEquals("4", this.testSuite.getExNum());
		assertEquals(1, this.testSuite.getExistsInd());
	}

	/**
	 * <p>
	 * CPU正常系05
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:EXHAUSTED<br>
	 * 桁数:4<br>
	 * チェンジ対象数字:3450<br>
	 * 指定する数字:0<br>
	 * 最優先フラグ:9<br>
	 * [期待値]<br>
	 * ・EXISTLISTOFNUMBERが返却されること<br>
	 * ・指定した数字:9であること<br>
	 * ・指定した数字が存在する桁:-1(初期化)であること<br>
	 * @throws Exception
	 */
	@Test
	final void targetOption_realInfoMap_cpuNormal05Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(4);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(DifficultyConst.EXHAUSTED);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("0");
		gameMaster.setCorrectPlayerNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		TargetOptionMapUtil mapUtil = spy(new TargetOptionMapUtil());
		mapUtil.clearSelectNumberPriorityMap();
		mapUtil.addSelectNumberPriorityMap(Numer0nSelectNumberEnum.NINE.getNum(), Const.SAI_YUUSEN_FLAG);
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
		// 検証
		String result = this.testSuite.targetLogic();
		assertEquals("NONEEXISTLISTOFNUMBER", result);
		assertEquals("9", this.testSuite.getExNum());
		assertEquals(-1, this.testSuite.getExistsInd());
	}

	/**
	 * <p>
	 * CPU正常系06
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 難易度:INSANE<br>
	 * 桁数:5<br>
	 * チェンジ対象数字:39450<br>
	 * 指定する数字:4<br>
	 * 最優先フラグ:5<br>
	 * [期待値]<br>
	 * ・EXISTLISTOFNUMBERが返却されること<br>
	 * ・指定した数字:5であること<br>
	 * ・指定した数字が存在する桁:-1(初期化)であること<br>
	 * @throws Exception
	 */
	@Test
	final void targetOption_realInfoMap_cpuNormal06Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(5);
		gameMaster.setName(Const.CPU);
		gameMaster.setDifficulty(DifficultyConst.INSANE);
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("3");
		list.add("9");
		list.add("4");
		list.add("5");
		list.add("0");
		gameMaster.setCorrectPlayerNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// MapUtil
		TargetOptionMapUtil mapUtil = spy(new TargetOptionMapUtil());
		mapUtil.clearSelectNumberPriorityMap();
		mapUtil.addSelectNumberPriorityMap(Numer0nSelectNumberEnum.FIVE.getNum(), Const.SAI_YUUSEN_FLAG);
		ReflectionTestUtils.setField(this.testSuite, "mapUtil", mapUtil);
		// 検証
		String result = this.testSuite.targetLogic();
		assertEquals("EXISTLISTOFNUMBER", result);
		assertEquals("5", this.testSuite.getExNum());
		assertEquals(-1, this.testSuite.getExistsInd());
	}

}
