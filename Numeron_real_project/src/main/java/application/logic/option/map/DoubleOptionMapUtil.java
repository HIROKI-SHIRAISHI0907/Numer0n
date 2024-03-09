package application.logic.option.map;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import application.component.consts.Numer0nDigitEnum;

/**
 * TargetOptionにて使用する最優先フラグを管理するクラス
 * @author shiraishitoshio
 *
 */
@Service
public class DoubleOptionMapUtil {

	/**
	 * <p>
	 * 2回コールで開けられなかった場合どの桁をどのくらい優先度高く開けるかを表すフラグを用意したMap
	 * </p>
	 * Integer... 桁
	 * Integer... 優先度の高さ（数字が低いほどその桁優先に変える）
	 */
	public static final Map<Integer, Integer> DOUBLE_DIGIT_PRIORITY_MAP;
	static {
		Map<Integer, Integer> doubleDigitYuusenMap = new HashMap<Integer, Integer>();
		doubleDigitYuusenMap.put(Numer0nDigitEnum.ZEROD.getDigit(), null);
		doubleDigitYuusenMap.put(Numer0nDigitEnum.ONED.getDigit(), null);
		doubleDigitYuusenMap.put(Numer0nDigitEnum.TWOD.getDigit(), null);
		doubleDigitYuusenMap.put(Numer0nDigitEnum.THREED.getDigit(), null);
		doubleDigitYuusenMap.put(Numer0nDigitEnum.FOURD.getDigit(), null);

		DOUBLE_DIGIT_PRIORITY_MAP = doubleDigitYuusenMap;
	};

	/**
	 * <p>
	 * 交換する桁の優先度用Mapに優先度を上書きするメソッド
	 * </p>
	 * @param digitKey 交換する桁
	 * @param digitValue 優先度(数字が小さいほど優先度が高い)
	 */
	public synchronized void addDigitPriorityMap(Integer digitkey, Integer digitValue) {
		if (DOUBLE_DIGIT_PRIORITY_MAP.containsKey(digitkey)) {
			DOUBLE_DIGIT_PRIORITY_MAP.put(digitkey, digitValue);
		}
	}

	/**
	 * <p>
	 * 交換する桁の優先度用Mapから優先度を取得するメソッド
	 * </p>
	 * @param digitValue 優先度(数字が小さいほど優先度が高い)
	 * @return 該当するkey
	 */
	public Integer getDigitPriorityMap(Integer digitValue) {
		for(Map.Entry<Integer, Integer> entry: DOUBLE_DIGIT_PRIORITY_MAP.entrySet()) {
			if (digitValue.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * <p>
	 * 交換する数字の優先度用Mapから指定した値が含まれているか
	 * </p>
	 * @param doValue 含まれるか確認する値
	 * @return 含まれる含まれない
	 */
	public boolean containValueDigitPriorityMap(Integer doValue) {
		return DOUBLE_DIGIT_PRIORITY_MAP.containsValue(doValue);
	}

	/**
	 * <p>
	 * 交換する数字の優先度用Mapをクリアする
	 * </p>
	 */
	public void clearDigitPriorityMap() {
		for (Integer key : DOUBLE_DIGIT_PRIORITY_MAP.keySet()) {
			DOUBLE_DIGIT_PRIORITY_MAP.put(key, null);
		}
	}

}
