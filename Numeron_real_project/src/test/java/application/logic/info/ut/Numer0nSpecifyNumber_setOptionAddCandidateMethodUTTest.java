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
import application.component.consts.OtherMatchFlagConst;
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
public class Numer0nSpecifyNumber_setOptionAddCandidateMethodUTTest {

	/**
	 * PLAYER
	 */
	private static final String PLAYER = "PLAYER";

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
	 * 正常系01(CPUが2回連続でコール失敗時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁:3
	 * info:DOUBLE,0,1<br>
	 * 候補数字:123<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal01Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "DOUBLE,0,1";
		// chkNumber
		String chkNumber = "123";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("123", player.getCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系02(CPUが2回連続でコール失敗時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁:3
	 * info:DOUBLE,1,6<br>
	 * 候補数字:123<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補でない場合、候補でない数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal02Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "DOUBLE,1,6";
		// chkNumber
		String chkNumber = "123";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("123", player.getNotCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系03(PLAYERが2回連続でコール失敗時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 桁:3
	 * info:DOUBLE,0,1<br>
	 * 候補数字:123<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal03Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "DOUBLE,0,1";
		// chkNumber
		String chkNumber = "123";
		// 候補数字設定
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("123", computer.getCandidateCpuNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系04(PLAYERが2回連続でコール失敗時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 桁:3
	 * info:DOUBLE,1,6<br>
	 * 候補数字:123<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補でない場合、候補でない数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal04Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "DOUBLE,1,6";
		// chkNumber
		String chkNumber = "123";
		// 候補数字設定
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("123", computer.getNotCandidateCpuNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系05(PLAYERがDOUBLEでコール時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁:3
	 * info:DOUBLE,349,1EAT0BITE<br>
	 * 候補数字:357<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal05Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "DOUBLE,349,1EAT0BITE";
		// chkNumber
		String chkNumber = "357";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("357", player.getCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系06(CPUがDOUBLEでコール時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 桁:3
	 * info:DOUBLE,265,0EAT3BITE<br>
	 * 候補数字:483<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal06Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "DOUBLE,265,0EAT3BITE";
		// chkNumber
		String chkNumber = "483";
		// 候補数字設定
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("483", computer.getNotCandidateCpuNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系07(任意のプレーヤーがCHANGEを使用した後に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:任意<br>
	 * 桁:3
	 * info:CHANGE,--<br>
	 * 候補数字:357<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・余計な情報が候補,候補でない数字リストに格納されていないこと
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal07Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,--";
		// chkNumber
		String chkNumber = "357";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertTrue(player.getCandidatePlayerNumberList().size() == 0);
		assertTrue(computer.getCandidateCpuNumberList().size() == 0);
	}

	/**
	 * <p>
	 * 正常系08(PLAYERがCHANGEで難易度:EASY,NORMALの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁:3
	 * info:CHANGE,2,HIGH<br>
	 * 候補数字:357<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal08Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,2,HIGH";
		// chkNumber
		String chkNumber = "357";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("357", player.getCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系09(PLAYERがCHANGEで難易度:EASY,NORMALの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁:3
	 * info:CHANGE,1,LOW<br>
	 * 候補数字:207<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal09Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,1,LOW";
		// chkNumber
		String chkNumber = "207";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("207", player.getCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系10(PLAYERがCHANGEで難易度:EASY,NORMALの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁:3
	 * info:CHANGE,0,HIGH<br>
	 * 候補数字:482<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補でない場合、候補でない数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal10Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,0,HIGH";
		// chkNumber
		String chkNumber = "482";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("482", player.getNotCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系11(PLAYERがCHANGEで難易度:HARDの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁:3
	 * info:CHANGE,DONTTEACHINDEX,HIGH<br>
	 * 候補数字:357<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal11Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,DONTTEACHINDEX,HIGH";
		// chkNumber
		String chkNumber = "357";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("357", player.getCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系12(PLAYERがCHANGEで難易度:HARDの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁:3
	 * info:CHANGE,DONTTEACHINDEX,LOW<br>
	 * 候補数字:357<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal12Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,DONTTEACHINDEX,LOW";
		// chkNumber
		String chkNumber = "357";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("357", player.getCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系13(PLAYERがCHANGEで難易度:HARDの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁:3
	 * info:CHANGE,DONTTEACHINDEX,LOW<br>
	 * 候補数字:879<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補でない場合、候補でない数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal13Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,DONTTEACHINDEX,LOW";
		// chkNumber
		String chkNumber = "879";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("879", player.getNotCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系14(CPUがCHANGEで難易度:HARDの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 桁:3
	 * info:CHANGE,DONTTEACHINDEX,HIGH<br>
	 * 候補数字:357<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal14Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,DONTTEACHINDEX,HIGH";
		// chkNumber
		String chkNumber = "357";
		// 候補数字設定
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("357", computer.getCandidateCpuNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系15(CPUがCHANGEで難易度:HARDの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 桁:3
	 * info:CHANGE,DONTTEACHINDEX,LOW<br>
	 * 候補数字:357<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal15Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,DONTTEACHINDEX,LOW";
		// chkNumber
		String chkNumber = "357";
		// 候補数字設定
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("357", computer.getCandidateCpuNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系16(CPUがCHANGEで難易度:HARDの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 桁:3
	 * info:CHANGE,DONTTEACHINDEX,LOW<br>
	 * 候補数字:879<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補でない場合、候補でない数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal16Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,DONTTEACHINDEX,LOW";
		// chkNumber
		String chkNumber = "879";
		// 候補数字設定
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("879", computer.getNotCandidateCpuNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系17(PLAYERがCHANGEで難易度:EXHAUSTEDの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁:3
	 * info:CHANGE,0,NOTCLEAR<br>
	 * 候補数字:436<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・候補、候補でない数字リスト両方に格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal17Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,0,NOTCLEAR";
		// chkNumber
		String chkNumber = "436";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("436", player.getCandidatePlayerNumberList().get(0));
		assertEquals("436", player.getNotCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系18(CPUがCHANGEで難易度:EXHAUSTEDの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 桁:3
	 * info:CHANGE,0,NOTCLEAR<br>
	 * 候補数字:436<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・候補、候補でない数字リスト両方に格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal18Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,0,NOTCLEAR";
		// chkNumber
		String chkNumber = "436";
		// 候補数字設定
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("436", computer.getCandidateCpuNumberList().get(0));
		assertEquals("436", computer.getNotCandidateCpuNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系19(PLAYERがCHANGEで難易度:INSANEの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁:3
	 * info:CHANGE,DONTTEACHINDEX,NOTCLEAR<br>
	 * 候補数字:188<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・候補、候補でない数字リスト両方に格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal19Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,DONTTEACHINDEX,NOTCLEAR";
		// chkNumber
		String chkNumber = "188";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("188", player.getCandidatePlayerNumberList().get(0));
		assertEquals("188", player.getNotCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系20(CPUがCHANGEで難易度:INSANEの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 桁:3
	 * info:CHANGE,DONTTEACHINDEX,NOTCLEAR<br>
	 * 候補数字:188<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・候補、候補でない数字リスト両方に格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal20Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "CHANGE,DONTTEACHINDEX,NOTCLEAR";
		// chkNumber
		String chkNumber = "188";
		// 候補数字設定
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("188", computer.getCandidateCpuNumberList().get(0));
		assertEquals("188", computer.getNotCandidateCpuNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系21(PLAYERがHIGH&LOWで得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁:3
	 * info:HIGH&LOW,LOW,LOW,HIGH<br>
	 * 候補数字:217<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal21Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "HIGH&LOW,LOW,LOW,HIGH";
		// chkNumber
		String chkNumber = "217";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("217", player.getCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系22(PLAYERがHIGH&LOWで得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁:3
	 * info:HIGH&LOW,HIGH,LOW,HIGH<br>
	 * 候補数字:512<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補でない場合、候補でない数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal22Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "HIGH&LOW,HIGH,LOW,HIGH";
		// chkNumber
		String chkNumber = "512";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("512", player.getNotCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系23(PLAYERがHIGH&LOWで得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁:3
	 * info:HIGH&LOW,LOW,HIGH,HIGH<br>
	 * 候補数字:976<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補でない場合、候補でない数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal23Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "HIGH&LOW,LOW,HIGH,HIGH";
		// chkNumber
		String chkNumber = "976";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("976", player.getNotCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系24(CPUがHIGH&LOWで得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 桁:3
	 * info:HIGH&LOW,LOW,LOW,HIGH<br>
	 * 候補数字:217<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal24Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "HIGH&LOW,LOW,LOW,HIGH";
		// chkNumber
		String chkNumber = "217";
		// 候補数字設定
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("217", computer.getCandidateCpuNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系25(CPUがHIGH&LOWで得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 桁:3
	 * info:HIGH&LOW,HIGH,LOW,HIGH<br>
	 * 候補数字:512<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補でない場合、候補でない数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal25Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "HIGH&LOW,HIGH,LOW,HIGH";
		// chkNumber
		String chkNumber = "512";
		// 候補数字設定
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("512", computer.getNotCandidateCpuNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系26(CPUがHIGH&LOWで得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁:3
	 * info:HIGH&LOW,LOW,HIGH,HIGH<br>
	 * 候補数字:976<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補でない場合、候補でない数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal26Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "HIGH&LOW,LOW,HIGH,HIGH";
		// chkNumber
		String chkNumber = "976";
		// 候補数字設定
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("976", computer.getNotCandidateCpuNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系27(PLAYERがSLASHで得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁:3
	 * info:SLASH,3<br>
	 * 候補数字:754<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal27Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "SLASH,3";
		// chkNumber
		String chkNumber = "754";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("754", player.getCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系28(PLAYERがSLASHで得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁:3
	 * info:SLASH,6<br>
	 * 候補数字:467<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補でない場合、候補でない数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal28Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "SLASH,6";
		// chkNumber
		String chkNumber = "467";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("467", player.getNotCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系29(任意のプレーヤーがSLASHで得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:任意<br>
	 * 桁:3
	 * info:SLASH,--<br>
	 * 候補数字:901<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・候補、候補でない数字リスト両方に格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal29Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "SLASH,--";
		// chkNumber
		String chkNumber = "901";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertTrue(player.getCandidatePlayerNumberList().size() == 0);
		assertTrue(player.getNotCandidatePlayerNumberList().size() == 0);
	}

	/**
	 * <p>
	 * 正常系30(CPUがSLASHで得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 桁:3
	 * info:SLASH,3<br>
	 * 候補数字:754<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal30Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "SLASH,3";
		// chkNumber
		String chkNumber = "754";
		// 候補数字設定
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("754", computer.getCandidateCpuNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系31(CPUがSLASHで得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 桁:3
	 * info:SLASH,6<br>
	 * 候補数字:467<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補でない場合、候補でない数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal31Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "SLASH,6";
		// chkNumber
		String chkNumber = "467";
		// 候補数字設定
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("467", computer.getNotCandidateCpuNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系32(PLAYERがTARGETで難易度:EASY,NORMAL,HARDの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁:3
	 * info:TARGET,0,EXISTLISTOFNUMBER,3<br>
	 * 候補数字:467<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal32Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,0,EXISTLISTOFNUMBER,3";
		// chkNumber
		String chkNumber = "317";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("317", player.getCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系33(PLAYERがTARGETで難易度:EASY,NORMAL,HARDの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁:3
	 * info:TARGET,1,EXISTLISTOFNUMBER,5<br>
	 * 候補数字:865<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補でない場合、候補でない数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal33Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,1,EXISTLISTOFNUMBER,5";
		// chkNumber
		String chkNumber = "865";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("865", player.getNotCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系34(PLAYERがTARGETで難易度:EASY,NORMAL,HARDの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁:3
	 * info:TARGET,0,NONEEXISTLISTOFNUMBER,9<br>
	 * 候補数字:548<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal34Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,0,NONEEXISTLISTOFNUMBER,9";
		// chkNumber
		String chkNumber = "548";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("548", player.getCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系35(PLAYERがTARGETで難易度:EASY,NORMAL,HARDの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁:3
	 * info:TARGET,2,NONEEXISTLISTOFNUMBER,4<br>
	 * 候補数字:814<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補でない場合、候補でない数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal35Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,2,NONEEXISTLISTOFNUMBER,4";
		// chkNumber
		String chkNumber = "814";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("814", player.getNotCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系36(PLAYERがTARGETで難易度:EXHAUSTED,INSANEの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁:3
	 * info:TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,8<br>
	 * 候補数字:418<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal36Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,8";
		// chkNumber
		String chkNumber = "418";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("418", player.getCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系37(PLAYERがTARGETで難易度:EXHAUSTED,INSANEの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁:3
	 * info:TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,8<br>
	 * 候補数字:785<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal37Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,8";
		// chkNumber
		String chkNumber = "785";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("785", player.getCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系38(PLAYERがTARGETで難易度:EXHAUSTED,INSANEの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁:3
	 * info:TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,8<br>
	 * 候補数字:785<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal38Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,8";
		// chkNumber
		String chkNumber = "785";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("785", player.getCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系39(PLAYERがTARGETで難易度:EXHAUSTED,INSANEの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁:3
	 * info:TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,8<br>
	 * 候補数字:848<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal39Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,8";
		// chkNumber
		String chkNumber = "848";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("848", player.getCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系40(PLAYERがTARGETで難易度:EXHAUSTED,INSANEの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁:3
	 * info:TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,8<br>
	 * 候補数字:427<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補でない場合、候補でない数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal40Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,8";
		// chkNumber
		String chkNumber = "427";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("427", player.getNotCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系41(PLAYERがTARGETで難易度:EXHAUSTED,INSANEの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁:3
	 * info:TARGET,DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,1<br>
	 * 候補数字:359<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal41Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,1";
		// chkNumber
		String chkNumber = "359";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("359", player.getCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系42(PLAYERがTARGETで難易度:EXHAUSTED,INSANEの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * 桁:3
	 * info:TARGET,DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,9<br>
	 * 候補数字:902<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補でない場合、候補でない数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal42Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(PLAYER);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,9";
		// chkNumber
		String chkNumber = "902";
		// 候補数字設定
		Player player = spy(new Player(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "player", player);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("902", player.getNotCandidatePlayerNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系43(CPUがTARGETで難易度:EASY,NORMAL,HARDの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 桁:3
	 * info:TARGET,0,EXISTLISTOFNUMBER,3<br>
	 * 候補数字:467<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal43Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,0,EXISTLISTOFNUMBER,3";
		// chkNumber
		String chkNumber = "317";
		// 候補数字設定
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("317", computer.getCandidateCpuNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系44(CPUがTARGETで難易度:EASY,NORMAL,HARDの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 桁:3
	 * info:TARGET,1,EXISTLISTOFNUMBER,5<br>
	 * 候補数字:865<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補でない場合、候補でない数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal44Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,1,EXISTLISTOFNUMBER,5";
		// chkNumber
		String chkNumber = "865";
		// 候補数字設定
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("865", computer.getNotCandidateCpuNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系45(CPUがTARGETで難易度:EASY,NORMAL,HARDの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 桁:3
	 * info:TARGET,0,NONEEXISTLISTOFNUMBER,9<br>
	 * 候補数字:548<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal45Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,0,NONEEXISTLISTOFNUMBER,9";
		// chkNumber
		String chkNumber = "548";
		// 候補数字設定
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("548", computer.getCandidateCpuNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系46(CPUがTARGETで難易度:EASY,NORMAL,HARDの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 桁:3
	 * info:TARGET,2,NONEEXISTLISTOFNUMBER,4<br>
	 * 候補数字:814<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補でない場合、候補でない数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal46Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,2,NONEEXISTLISTOFNUMBER,4";
		// chkNumber
		String chkNumber = "814";
		// 候補数字設定
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("814", computer.getNotCandidateCpuNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系47(CPUがTARGETで難易度:EXHAUSTED,INSANEの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 桁:3
	 * info:TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,8<br>
	 * 候補数字:418<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal47Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,8";
		// chkNumber
		String chkNumber = "418";
		// 候補数字設定
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("418", computer.getCandidateCpuNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系48(CPUがTARGETで難易度:EXHAUSTED,INSANEの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 桁:3
	 * info:TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,8<br>
	 * 候補数字:785<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal48Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,8";
		// chkNumber
		String chkNumber = "785";
		// 候補数字設定
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("785", computer.getCandidateCpuNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系49(CPUがTARGETで難易度:EXHAUSTED,INSANEの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 桁:3
	 * info:TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,8<br>
	 * 候補数字:785<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal49Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,8";
		// chkNumber
		String chkNumber = "785";
		// 候補数字設定
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("785", computer.getCandidateCpuNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系50(CPUがTARGETで難易度:EXHAUSTED,INSANEの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 桁:3
	 * info:TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,8<br>
	 * 候補数字:848<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal50Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,8";
		// chkNumber
		String chkNumber = "848";
		// 候補数字設定
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("848", computer.getCandidateCpuNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系51(CPUがTARGETで難易度:EXHAUSTED,INSANEの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 桁:3
	 * info:TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,8<br>
	 * 候補数字:427<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補でない場合、候補でない数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal51Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,DONTTEACHINDEX,EXISTLISTOFNUMBER,8";
		// chkNumber
		String chkNumber = "427";
		// 候補数字設定
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("427", computer.getNotCandidateCpuNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系52(CPUがTARGETで難易度:EXHAUSTED,INSANEの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 桁:3
	 * info:TARGET,DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,1<br>
	 * 候補数字:359<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補である場合、候補の数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal52Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,1";
		// chkNumber
		String chkNumber = "359";
		// 候補数字設定
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("359", computer.getCandidateCpuNumberList().get(0));
	}

	/**
	 * <p>
	 * 正常系53(CPUがTARGETで難易度:EXHAUSTED,INSANEの時に得られた情報)
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * 桁:3
	 * info:TARGET,DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,9<br>
	 * 候補数字:902<br>
	 * [期待値]<br>
	 * ・MATCHであること<br>
	 * ・infoに合う候補でない場合、候補でない数字リストに格納されていること
	 * @throws Exception
	 */
	@Test
	final void Numer0nSpecifyNumber_setOptionAddCandidateMethod_normal53Test() throws Exception {
		// 桁数
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setName(Const.CPU);
		gameMaster.setDigit(3);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// info
		String info = "TARGET,DONTTEACHINDEX,NONEEXISTLISTOFNUMBER,9";
		// chkNumber
		String chkNumber = "902";
		// 候補数字設定
		Computer computer = spy(new Computer(gameMaster, this.info, this.util));
		ReflectionTestUtils.setField(this.testSuite, "computer", computer);
		// 検証
		int result = this.testSuite.setOptionAddCandidateMethod(
				info, chkNumber, Numer0nNextActionFlagEnum.LAST_INFO_FLAG.getFlagCd());
		assertEquals(OtherMatchFlagConst.MATCH, result);
		assertEquals("902", computer.getNotCandidateCpuNumberList().get(0));
	}

}
