package application.logic.option.ut;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;

import application.component.consts.Const;
import application.component.error.CreateErrorExceptionComponent;
import application.component.error.CreateErrorExceptionComponentImpl;
import application.component.error.Numer0nUncontinuableException;
import application.component.message.MessageAccessor;
import application.logic.human.GameMaster;
import application.logic.option.HighlowOption;

/**
 * <p>
 * ハイローオプションテストクラス
 * </p>
 * @author shiraishitoshio
 *
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Tag("UT")
public class HighLowOptionUTTest {

	/**
	 * 小機能ID
	 */
	private static final String S_FUNC = "OPTION003";

	/**
	 * クラス名
	 */
	private static final String CLASS_NAME = HighlowOption.class.getSimpleName();

	/**
	 * gameMaster mock
	 */
	@Mock
	private GameMaster gameMaster;

	/**
	 * CreateErrorExceptionComponent mock
	 */
	@Mock
	private CreateErrorExceptionComponent exceptionComponent;

	/**
	 * Message mock
	 */
	MockedStatic<MessageAccessor> message;

	@InjectMocks
	@Spy
	private HighlowOption testSuite;

	@BeforeEach
	public void setUp() {
		message = mockStatic(MessageAccessor.class);
	}

	@AfterEach
	public void tearDown() {
		message.close();
	}

	/**
	 * <p>
	 * 正常系01
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * ハイロー対象数字:CPU,012<br>
	 * [期待値]<br>
	 * ・LOW,LOW,LOWであること
	 * @throws Exception
	 */
	@Test
	final void highLowOption_normal01Test() throws Exception {
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("0");
		list.add("1");
		list.add("2");
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setCorrectPlayerNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		this.testSuite.highLowLogic();
		assertEquals("LOW,LOW,LOW", convertListToString(this.testSuite.getHighLowList()));
	}

	/**
	 * <p>
	 * 正常系02
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * ハイロー対象数字:PLAYER,456<br>
	 * [期待値]<br>
	 * ・LOW,HIGH,HIGHであること
	 * @throws Exception
	 */
	@Test
	final void highLowOption_normal02Test() throws Exception {
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("4");
		list.add("5");
		list.add("6");
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setCorrectPlayerNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		this.testSuite.highLowLogic();
		assertEquals("LOW,HIGH,HIGH", convertListToString(this.testSuite.getHighLowList()));
	}

	/**
	 * <p>
	 * 正常系03
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * ハイロー対象数字:PLAYER,456<br>
	 * [期待値]<br>
	 * ・HIGH,HIGH,HIGHであること
	 * @throws Exception
	 */
	@Test
	final void highLowOption_normal03Test() throws Exception {
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("7");
		list.add("8");
		list.add("9");
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setCorrectPlayerNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		this.testSuite.highLowLogic();
		assertEquals("HIGH,HIGH,HIGH", convertListToString(this.testSuite.getHighLowList()));
	}

	/**
	 * <p>
	 * 異常系01
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * ハイロー対象数字:PLAYER,7-19<br>
	 * [期待値]<br>
	 * ・Numer0n実行不可能例外が発生すること
	 * @throws Exception
	 */
	@Test
	final void highLowOption_abNormal01Test() throws Exception {
		// spy設定
		CreateErrorExceptionComponentImpl exceptionComponent = spy(new CreateErrorExceptionComponentImpl());
		ReflectionTestUtils.setField(this.testSuite, "exceptionComponent", exceptionComponent);
		// メッセージmock設定
		ArgumentCaptor<String> messageCd = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String[]> fillChar = ArgumentCaptor.forClass(String[].class);
		message.when(() -> MessageAccessor.getMessage(messageCd.capture(), fillChar.capture())).thenReturn("message");
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("7");
		list.add("-1");
		list.add("9");
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setCorrectCpuNumberList(list);
		ReflectionTestUtils.setField(this.testSuite, "gameMaster", gameMaster);
		// 検証
		this.testSuite.highLowLogic();
		Numer0nUncontinuableException e = assertThrows(Numer0nUncontinuableException.class,
				() -> this.testSuite.highLowLogic());
		assertEquals(S_FUNC, e.getNumer0nErrDTO().getSFuncId());
		assertEquals(CLASS_NAME, e.getNumer0nErrDTO().getClassName());
		assertEquals("highLowLogic", e.getNumer0nErrDTO().getMethodName());
		assertEquals(null, e.getNumer0nErrDTO().getExpErrorCd());
		assertEquals("ERR_OPTION_04", e.getNumer0nErrDTO().getMessageCd());
		assertEquals(null, e.getNumer0nErrDTO().getJobId());
		assertEquals("message", e.getNumer0nErrDTO().getOutMessage());
		assertEquals(null, e.getNumer0nErrDTO().getOutErrorDetailMessage());
		// 引数確認
		assertEquals("ERR_OPTION_04", messageCd.getValue());
		assertEquals("-1", fillChar.getValue()[0]);
	}

	/**
	 * <p>
	 * 異常系02
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * ハイロー対象数字:PLAYER,7[10]9<br>
	 * [期待値]<br>
	 * ・Numer0n実行不可能例外が発生すること
	 * @throws Exception
	 */
	@Test
	final void highLowOption_abNormal02Test() throws Exception {
		// spy設定
		CreateErrorExceptionComponentImpl exceptionComponent = spy(new CreateErrorExceptionComponentImpl());
		ReflectionTestUtils.setField(this.testSuite, "exceptionComponent", exceptionComponent);
		// メッセージmock設定
		ArgumentCaptor<String> messageCd = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String[]> fillChar = ArgumentCaptor.forClass(String[].class);
		message.when(() -> MessageAccessor.getMessage(messageCd.capture(), fillChar.capture())).thenReturn("message");
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("7");
		list.add("10");
		list.add("9");
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setCorrectCpuNumberList(list);
		// 検証
		Numer0nUncontinuableException e = assertThrows(Numer0nUncontinuableException.class,
				() -> this.testSuite.highLowLogic());
		assertEquals(S_FUNC, e.getNumer0nErrDTO().getSFuncId());
		assertEquals(CLASS_NAME, e.getNumer0nErrDTO().getClassName());
		assertEquals("highLowLogic", e.getNumer0nErrDTO().getMethodName());
		assertEquals(null, e.getNumer0nErrDTO().getExpErrorCd());
		assertEquals("ERR_OPTION_04", e.getNumer0nErrDTO().getMessageCd());
		assertEquals(null, e.getNumer0nErrDTO().getJobId());
		assertEquals(null, e.getNumer0nErrDTO().getErrorClass());
		assertEquals("message", e.getNumer0nErrDTO().getOutMessage());
		assertEquals(null, e.getNumer0nErrDTO().getOutErrorDetailMessage());
		// 引数確認
		assertEquals("ERR_OPTION_04", messageCd.getValue());
		assertEquals("10", fillChar.getValue()[0]);
	}

	/**
	 * <p>
	 * 異常系03
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * ハイロー対象数字:PLAYER,7[*]9<br>
	 * [期待値]<br>
	 * ・Numer0n実行不可能例外が発生すること
	 * @throws Exception
	 */
	@Test
	final void highLowOption_abNormal03Test() throws Exception {
		// spy設定
		CreateErrorExceptionComponentImpl exceptionComponent = spy(new CreateErrorExceptionComponentImpl());
		ReflectionTestUtils.setField(this.testSuite, "exceptionComponent", exceptionComponent);
		// メッセージmock設定
		ArgumentCaptor<String> messageCd = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String[]> fillChar = ArgumentCaptor.forClass(String[].class);
		message.when(() -> MessageAccessor.getMessage(messageCd.capture(), fillChar.capture())).thenReturn("message");
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("7");
		list.add("*");
		list.add("9");
		// 難易度
		GameMaster gameMaster = spy(new GameMaster());
		gameMaster.setDigit(3);
		gameMaster.setName(Const.CPU);
		gameMaster.setCorrectCpuNumberList(list);
		// 検証
		Numer0nUncontinuableException e = assertThrows(Numer0nUncontinuableException.class,
				() -> this.testSuite.highLowLogic());
		assertEquals(S_FUNC, e.getNumer0nErrDTO().getSFuncId());
		assertEquals(CLASS_NAME, e.getNumer0nErrDTO().getClassName());
		assertEquals("highLowLogic", e.getNumer0nErrDTO().getMethodName());
		assertEquals(null, e.getNumer0nErrDTO().getExpErrorCd());
		assertEquals("ERR_OPTION_04", e.getNumer0nErrDTO().getMessageCd());
		assertEquals(null, e.getNumer0nErrDTO().getJobId());
		assertEquals(null, e.getNumer0nErrDTO().getErrorClass());
		assertEquals("message", e.getNumer0nErrDTO().getOutMessage());
		assertEquals(null, e.getNumer0nErrDTO().getOutErrorDetailMessage());
		// 引数確認
		assertEquals("ERR_OPTION_04", messageCd.getValue());
		assertEquals("*", fillChar.getValue()[0]);
	}

	/**
	 * 文字連結をおこなう。
	 * @param array 文字列リスト
	 * @return returnNum 連結後の文字列
	 */
	private String convertListToString(ArrayList<String> array) {
		String returnNum = "";
		for (String str : array) {
			if (returnNum.length() > 0) {
				returnNum += ",";
			}
			returnNum += str;
		}
		return returnNum;
	}

}
