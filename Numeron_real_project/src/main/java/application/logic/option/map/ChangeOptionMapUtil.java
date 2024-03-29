package application.logic.option.map;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import application.component.consts.Numer0nChangeEnum;
import application.component.consts.Numer0nDigitEnum;
import application.component.consts.Numer0nSelectNumberEnum;

/**
 * ChangeOptionにて使用する最優先フラグを管理するクラス
 * @author shiraishitoshio
 *
 */
@Service
public class ChangeOptionMapUtil {

	/**
	 * <p>
	 * どの数字をどのくらい優先度高く変えるかを表すフラグを用意したMap
	 * </p>
	 * String... 数値
	 * Integer... 優先度の高さ（数字が低いほどその数字優先に変える）
	 */
	public static final Map<String, Integer> CHANGE_SELECT_NUMBER_PRIORITY_MAP;
	static {
		Map<String, Integer> changeSelectNumberYuusenMap = new HashMap<String, Integer>();
		changeSelectNumberYuusenMap.put(Numer0nSelectNumberEnum.ZERO.getNum(), null);
		changeSelectNumberYuusenMap.put(Numer0nSelectNumberEnum.ONE.getNum(), null);
		changeSelectNumberYuusenMap.put(Numer0nSelectNumberEnum.TWO.getNum(), null);
		changeSelectNumberYuusenMap.put(Numer0nSelectNumberEnum.THREE.getNum(), null);
		changeSelectNumberYuusenMap.put(Numer0nSelectNumberEnum.FOUR.getNum(), null);
		changeSelectNumberYuusenMap.put(Numer0nSelectNumberEnum.FIVE.getNum(), null);
		changeSelectNumberYuusenMap.put(Numer0nSelectNumberEnum.SIX.getNum(), null);
		changeSelectNumberYuusenMap.put(Numer0nSelectNumberEnum.SEVEN.getNum(), null);
		changeSelectNumberYuusenMap.put(Numer0nSelectNumberEnum.EIGHT.getNum(), null);
		changeSelectNumberYuusenMap.put(Numer0nSelectNumberEnum.NINE.getNum(), null);

		CHANGE_SELECT_NUMBER_PRIORITY_MAP = changeSelectNumberYuusenMap;
	};

	/**
	 * <p>
	 * どの桁をどのくらい優先度高く変えるかを表すフラグを用意したMap
	 * </p>
	 * Integer... 桁
	 * Integer... 優先度の高さ（数字が低いほどその桁優先に変える）
	 */
	public static final Map<Integer, Integer> CHANGE_DIGIT_PRIORITY_MAP;
	static {
		Map<Integer, Integer> changeDigitYuusenMap = new HashMap<Integer, Integer>();
		changeDigitYuusenMap.put(Numer0nDigitEnum.ZEROD.getDigit(), null);
		changeDigitYuusenMap.put(Numer0nDigitEnum.ONED.getDigit(), null);
		changeDigitYuusenMap.put(Numer0nDigitEnum.TWOD.getDigit(), null);
		changeDigitYuusenMap.put(Numer0nDigitEnum.THREED.getDigit(), null);
		changeDigitYuusenMap.put(Numer0nDigitEnum.FOURD.getDigit(), null);

		CHANGE_DIGIT_PRIORITY_MAP = changeDigitYuusenMap;
	};

	/**
	 * <p>
	 * 交換するしないの優先度を表すMap
	 * </p>
	 */
	public static final Map<String, Integer> CHANGE_DO_PRIORITY_MAP;
	static {
		Map<String, Integer> changeDoYuusenMap = new HashMap<String, Integer>();
		changeDoYuusenMap.put(Numer0nChangeEnum.CHANGE_GO.getOprionCd(), null);
		changeDoYuusenMap.put(Numer0nChangeEnum.CHANGE_REJECT.getOprionCd(), null);

		CHANGE_DO_PRIORITY_MAP = changeDoYuusenMap;
	};

	/**
	 * <p>
	 * 交換する数字の優先度用Mapに優先度を上書きするメソッド
	 * </p>
	 * @param selectNumberKey 交換する数字
	 * @param selectNumberValue 優先度(数字が小さいほど優先度が高い)
	 */
	public synchronized void addSelectNumberPriorityMap(String selectNumberKey, Integer selectNumberValue) {
		if (CHANGE_SELECT_NUMBER_PRIORITY_MAP.containsKey(selectNumberKey)) {
			CHANGE_SELECT_NUMBER_PRIORITY_MAP.put(selectNumberKey, selectNumberValue);
		}
	}

	/**
	 * <p>
	 * 交換する数字の優先度用Mapから優先度を取得するメソッド
	 * </p>
	 * @param selectNumberValue 優先度(数字が小さいほど優先度が高い)
	 * @return 該当するkey
	 */
	public String getSelectNumberPriorityMap(Integer selectNumberValue) {
		for(Map.Entry<String, Integer> entry: CHANGE_SELECT_NUMBER_PRIORITY_MAP.entrySet()) {
			if (selectNumberValue.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * <p>
	 * 交換するかしないかの優先度用Mapから指定した値が含まれているか
	 * </p>
	 * @param selectNumberValue 含まれるか確認する値
	 * @return 含まれる含まれない
	 */
	public boolean containValueSelectNumberPriorityMap(Integer selectNumberValue) {
		return CHANGE_SELECT_NUMBER_PRIORITY_MAP.containsValue(selectNumberValue);
	}

	/**
	 * <p>
	 * 交換する数字の優先度用Mapをクリアする
	 * </p>
	 */
	public void clearSelectNumberPriorityMap() {
		for (String key: CHANGE_SELECT_NUMBER_PRIORITY_MAP.keySet()) {
			CHANGE_SELECT_NUMBER_PRIORITY_MAP.put(key, null);
		}
	}

	/**
	 * <p>
	 * 交換する桁の優先度用Mapに優先度を上書きするメソッド
	 * </p>
	 * @param digitKey 交換する桁
	 * @param digitValue 優先度(数字が小さいほど優先度が高い)
	 */
	public synchronized void addDigitPriorityMap(Integer digitkey, Integer digitValue) {
		if (CHANGE_DIGIT_PRIORITY_MAP.containsKey(digitkey)) {
			CHANGE_DIGIT_PRIORITY_MAP.put(digitkey, digitValue);
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
		for(Map.Entry<Integer, Integer> entry: CHANGE_DIGIT_PRIORITY_MAP.entrySet()) {
			if (digitValue.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * <p>
	 * 交換する桁の優先度用Mapから指定した値が含まれているか
	 * </p>
	 * @param digitValue 含まれるか確認する値
	 * @return 含まれる含まれない
	 */
	public boolean containValueDigitPriorityMap(Integer digitValue) {
		return CHANGE_DIGIT_PRIORITY_MAP.containsValue(digitValue);
	}

	/**
	 * <p>
	 * 交換する数字の優先度用Mapをクリアする
	 * </p>
	 */
	public void clearDigitPriorityMap() {
		for (Integer key: CHANGE_DIGIT_PRIORITY_MAP.keySet()) {
			CHANGE_DIGIT_PRIORITY_MAP.put(key, null);
		}
	}

	/**
	 * <p>
	 * 交換するかしないかの優先度用Mapに優先度を上書きするメソッド
	 * </p>
	 * @param doKey 交換するかしないか
	 * @param doValue 優先度(数字が小さいほど優先度が高い)
	 */
	public synchronized void addDoPriorityMap(String doKey, Integer doValue) {
		if (CHANGE_DO_PRIORITY_MAP.containsKey(doKey)) {
			CHANGE_DO_PRIORITY_MAP.put(doKey, doValue);
		}
	}

	/**
	 * <p>
	 * 交換するかしないかの優先度用Mapから優先度を取得するメソッド
	 * </p>
	 * @param doValue 優先度(数字が小さいほど優先度が高い)
	 * @return 該当するkey
	 */
	public String getDoPriorityMap(Integer doValue) {
		for(Map.Entry<String, Integer> entry: CHANGE_DO_PRIORITY_MAP.entrySet()) {
			if (doValue.equals(entry.getValue())) {
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
	public boolean containValueDoPriorityMap(Integer doValue) {
		return CHANGE_DO_PRIORITY_MAP.containsValue(doValue);
	}

	/**
	 * <p>
	 * 交換する数字の優先度用Mapをクリアする
	 * </p>
	 */
	public void clearDoPriorityMap() {
		for (String key: CHANGE_DO_PRIORITY_MAP.keySet()) {
			CHANGE_DO_PRIORITY_MAP.put(key, null);
		}
	}

}
