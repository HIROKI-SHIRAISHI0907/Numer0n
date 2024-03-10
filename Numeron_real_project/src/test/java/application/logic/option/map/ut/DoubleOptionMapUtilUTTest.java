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

import application.component.consts.Numer0nDigitEnum;
import application.component.consts.PriorityFlagConst;
import application.logic.option.map.DoubleOptionMapUtil;

/**
 * <p>
 * DoubleOptionMapUtilテストクラス
 * </p>
 * テストを始める前にMap内の値をクリアしておく
 * @author shiraishitoshio
 *
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Tag("UT")
public class DoubleOptionMapUtilUTTest {

	@InjectMocks
	private DoubleOptionMapUtil testSuite;

	@BeforeEach
	public void setUp() {
		this.testSuite.clearDigitPriorityMap();
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * <p>
	 * 正常系01
	 * </p>
	 * [初期設定]<br>
	 * 桁:0
	 * 最優先フラグを設定<br>
	 * [期待値]<br>
	 * ・最優先フラグを設定した桁が取得できること<br>
	 * ・最優先フラグを設定した桁がMapに定義されていること
	 * @throws Exception
	 */
	@Test
	final void DoubleOptionMapUtil01Test() throws Exception {
		// テスト
		this.testSuite.addDigitPriorityMap(Numer0nDigitEnum.ZEROD.getDigit(), PriorityFlagConst.SAI_YUUSEN_FLAG);
		Integer value = this.testSuite.getDigitPriorityMap(PriorityFlagConst.SAI_YUUSEN_FLAG);
		assertEquals(Numer0nDigitEnum.ZEROD.getDigit(), value);

		assertTrue(this.testSuite.containValueDigitPriorityMap(PriorityFlagConst.SAI_YUUSEN_FLAG));
	}

	/**
	 * <p>
	 * 正常系02
	 * </p>
	 * [初期設定]<br>
	 * 桁:-1
	 * 最優先フラグを設定<br>
	 * [期待値]<br>
	 * ・最優先フラグを設定した桁が取得できないこと<br>
	 * ・最優先フラグを設定した桁がMapに定義されていないこと
	 * @throws Exception
	 */
	@Test
	final void DoubleOptionMapUtil02Test() throws Exception {
		// テスト
		this.testSuite.addDigitPriorityMap(-1, PriorityFlagConst.SAI_YUUSEN_FLAG);
		Integer value = this.testSuite.getDigitPriorityMap(PriorityFlagConst.SAI_YUUSEN_FLAG);
		assertNull(value);

		assertFalse(this.testSuite.containValueDigitPriorityMap(PriorityFlagConst.SAI_YUUSEN_FLAG));
	}

}
