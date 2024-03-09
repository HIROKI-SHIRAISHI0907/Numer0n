package application.logic.option.map.ut;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import application.component.consts.Const;
import application.component.consts.Numer0nSelectNumberEnum;
import application.logic.option.map.TargetOptionMapUtil;

/**
 * <p>
 * TargetOptionMapUtilテストクラス
 * </p>
 * テストを始める前にMap内の値をクリアしておく
 * @author shiraishitoshio
 *
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Tag("UT")
public class TargetOptionMapUtilUTTest {

	@InjectMocks
	private TargetOptionMapUtil testSuite;

	@BeforeEach
	public void setUp() {
		this.testSuite.clearSelectNumberPriorityMap();
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * <p>
	 * 正常系01
	 * </p>
	 * [初期設定]<br>
	 * 選択:0<br>
	 * 最優先フラグを設定<br>
	 * [期待値]<br>
	 * ・最優先フラグを設定した桁が取得できること<br>
	 * ・最優先フラグを設定した桁がMapに定義されていること
	 * @throws Exception
	 */
	@Test
	final void TargetOptionMapUtil01Test() throws Exception {
		// テスト
		this.testSuite.addSelectNumberPriorityMap(Numer0nSelectNumberEnum.ZERO.getNum(), Const.SAI_YUUSEN_FLAG);
		String value = this.testSuite.getSelectNumberPriorityMap(Const.SAI_YUUSEN_FLAG);
		assertEquals(Numer0nSelectNumberEnum.ZERO.getNum(), value);

		assertTrue(this.testSuite.containValueSelectNumberPriorityMap(Const.SAI_YUUSEN_FLAG));
	}

	/**
	 * <p>
	 * 正常系02
	 * </p>
	 * [初期設定]<br>
	 * 選択:-1<br>
	 * 最優先フラグを設定<br>
	 * [期待値]<br>
	 * ・最優先フラグを設定した桁が取得できないこと<br>
	 * ・最優先フラグを設定した桁がMapに定義されていないこと
	 * @throws Exception
	 */
	@Test
	final void targetOptionMapUtil02Test() throws Exception {
		// テスト
		this.testSuite.addSelectNumberPriorityMap("-1", Const.SAI_YUUSEN_FLAG);
		String value = this.testSuite.getSelectNumberPriorityMap(Const.SAI_YUUSEN_FLAG);
		assertNull(value);

		assertFalse(this.testSuite.containValueSelectNumberPriorityMap(Const.SAI_YUUSEN_FLAG));
	}

}
