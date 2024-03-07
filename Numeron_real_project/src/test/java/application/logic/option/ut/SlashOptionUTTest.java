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

import application.component.error.CreateErrorExceptionComponentImpl;
import application.component.error.Numer0nUncontinuableException;
import application.component.message.MessageAccessor;
import application.logic.human.GameMaster;
import application.logic.option.SlashOption;

/**
 * <p>
 * スラッシュオプションテストクラス
 * </p>
 * @author shiraishitoshio
 *
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Tag("UT")
public class SlashOptionUTTest {

	/**
	 * 小機能ID
	 */
	private static final String S_FUNC = "OPTION005";

	/**
	 * クラス名
	 */
	private static final String CLASS_NAME = SlashOption.class.getSimpleName();

	/**
	 * gameMaster mock
	 */
	@Mock
	private GameMaster gameMaster;

	/**
	 * Message mock
	 */
	MockedStatic<MessageAccessor> message;

	@InjectMocks
	@Spy
	private SlashOption testSuite;

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
	 * スラッシュ対象数字:CPU,012<br>
	 * [期待値]<br>
	 * ・2であること
	 * @throws Exception
	 */
	@Test
	final void slashOption_normal01Test() throws Exception {
		// 対象名
		ReflectionTestUtils.setField(this.testSuite, "chkMember", "PLAYER");
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("0");
		list.add("1");
		list.add("2");
		when(this.gameMaster.getCorrectCpuNumberList()).thenReturn(list);
		// 検証
		this.testSuite.slashLogic();
		assertEquals("2", this.testSuite.getSlashNum());
	}

	/**
	 * <p>
	 * 正常系02
	 * </p>
	 * [初期設定]<br>
	 * 対象名:PLAYER<br>
	 * スラッシュ対象数字:CPU,649<br>
	 * [期待値]<br>
	 * ・5であること
	 * @throws Exception
	 */
	@Test
	final void slashOption_normal02Test() throws Exception {
		// 対象名
		ReflectionTestUtils.setField(this.testSuite, "chkMember", "PLAYER");
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("6");
		list.add("4");
		list.add("9");
		when(this.gameMaster.getCorrectCpuNumberList()).thenReturn(list);
		// 検証
		this.testSuite.slashLogic();
		assertEquals("5", this.testSuite.getSlashNum());
	}

	/**
	 * <p>
	 * 異常系01
	 * </p>
	 * [初期設定]<br>
	 * 対象名:CPU<br>
	 * スラッシュ対象数字:PLAYER,6[*]9<br>
	 * [期待値]<br>
	 * ・5であること
	 * @throws Exception
	 */
	@Test
	final void slashOption_abNormal01Test() throws Exception {
		// spy設定
		CreateErrorExceptionComponentImpl exceptionComponent = spy(new CreateErrorExceptionComponentImpl());
		ReflectionTestUtils.setField(this.testSuite, "exceptionComponent", exceptionComponent);
		// メッセージmock設定
		ArgumentCaptor<String> messageCd = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String[]> fillChar = ArgumentCaptor.forClass(String[].class);
		message.when(() -> MessageAccessor.getMessage(messageCd.capture(), fillChar.capture())).thenReturn("message");
		// 対象名
		ReflectionTestUtils.setField(this.testSuite, "chkMember", "CPU");
		// シャッフル対象数字設定
		ArrayList<String> list = new ArrayList<>();
		list.add("6");
		list.add("*");
		list.add("9");
		when(this.gameMaster.getCorrectPlayerNumberList()).thenReturn(list);
		// 検証
		Numer0nUncontinuableException e = assertThrows(Numer0nUncontinuableException.class, () -> this.testSuite.slashLogic());
		assertEquals(S_FUNC, e.getNumer0nErrDTO().getSFuncId());
		assertEquals(CLASS_NAME, e.getNumer0nErrDTO().getClassName());
		assertEquals("slashLogic", e.getNumer0nErrDTO().getMethodName());
		assertEquals(null, e.getNumer0nErrDTO().getExpErrorCd());
		assertEquals("ERR_OPTION_05", e.getNumer0nErrDTO().getMessageCd());
		assertEquals(null, e.getNumer0nErrDTO().getJobId());
		assertNotNull(e.getNumer0nErrDTO().getErrorClass());
		assertEquals("message", e.getNumer0nErrDTO().getOutMessage());
		assertEquals(null, e.getNumer0nErrDTO().getOutErrorDetailMessage());
		// 引数確認
		assertEquals("ERR_OPTION_05", messageCd.getValue());
	}



}
