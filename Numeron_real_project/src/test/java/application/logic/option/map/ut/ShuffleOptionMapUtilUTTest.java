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

import application.component.consts.Numer0nShuffleEnum;
import application.component.consts.PriorityFlagConst;
import application.logic.option.map.ShuffleOptionMapUtil;

/**
 * <p>
 * ShuffleOptionMapUtilテストクラス
 * </p>
 * テストを始める前にMap内の値をクリアしておく
 * @author shiraishitoshio
 *
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Tag("UT")
public class ShuffleOptionMapUtilUTTest {

	@InjectMocks
	private ShuffleOptionMapUtil testSuite;

	@BeforeEach
	public void setUp() {
		this.testSuite.clearDoPriorityMap();
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * <p>
	 * 正常系01
	 * </p>
	 * [初期設定]<br>
	 * シャッフルする<br>
	 * 最優先フラグを設定<br>
	 * [期待値]<br>
	 * ・最優先フラグを設定した桁が取得できること<br>
	 * ・最優先フラグを設定した桁がMapに定義されていること
	 * @throws Exception
	 */
	@Test
	final void ShuffleOptionMapUtil01Test() throws Exception {
		// テスト
		this.testSuite.addDoPriorityMap(Numer0nShuffleEnum.SHUFFLE_GO.getOprionCd(), PriorityFlagConst.SAI_YUUSEN_FLAG);
		String value = this.testSuite.getDoPriorityMap(PriorityFlagConst.SAI_YUUSEN_FLAG);
		assertEquals(Numer0nShuffleEnum.SHUFFLE_GO.getOprionCd(), value);

		assertTrue(this.testSuite.containValueDoPriorityMap(PriorityFlagConst.SAI_YUUSEN_FLAG));
	}

	/**
	 * <p>
	 * 正常系02
	 * </p>
	 * [初期設定]<br>
	 * 無関係値<br>
	 * 最優先フラグを設定<br>
	 * [期待値]<br>
	 * ・最優先フラグを設定した桁が取得できないこと<br>
	 * ・最優先フラグを設定した桁がMapに定義されていないこと
	 * @throws Exception
	 */
	@Test
	final void ShuffleOptionMapUtil02Test() throws Exception {
		// テスト
		this.testSuite.addDoPriorityMap("-1", PriorityFlagConst.SAI_YUUSEN_FLAG);
		String value = this.testSuite.getDoPriorityMap(PriorityFlagConst.SAI_YUUSEN_FLAG);
		assertNull(value);

		assertFalse(this.testSuite.containValueDoPriorityMap(PriorityFlagConst.SAI_YUUSEN_FLAG));
	}

}
