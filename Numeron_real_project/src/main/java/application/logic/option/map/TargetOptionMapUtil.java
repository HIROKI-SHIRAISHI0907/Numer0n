package application.logic.option.map;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import application.component.consts.Numer0nSelectNumberEnum;

/**
 * TargetOptionにて使用する最優先フラグを管理するクラス
 * @author shiraishitoshio
 *
 */
@Service
public class TargetOptionMapUtil {

	/**
	 * <p>
	 * どの数字をどのくらい優先度高く変えるかを表すフラグを用意したMap
	 * </p>
	 * String... 数値
	 * Integer... 優先度の高さ（数字が低いほどその数字優先に変える）
	 */
	public static final Map<String, Integer> TARGET_SELECT_NUMBER_PRIORITY_MAP;
	static {
		Map<String, Integer> targetSelectNumberYuusenMap = new HashMap<String, Integer>();
		targetSelectNumberYuusenMap.put(Numer0nSelectNumberEnum.ZERO.getNum(), null);
		targetSelectNumberYuusenMap.put(Numer0nSelectNumberEnum.ONE.getNum(), null);
		targetSelectNumberYuusenMap.put(Numer0nSelectNumberEnum.TWO.getNum(), null);
		targetSelectNumberYuusenMap.put(Numer0nSelectNumberEnum.THREE.getNum(), null);
		targetSelectNumberYuusenMap.put(Numer0nSelectNumberEnum.FOUR.getNum(), null);
		targetSelectNumberYuusenMap.put(Numer0nSelectNumberEnum.FIVE.getNum(), null);
		targetSelectNumberYuusenMap.put(Numer0nSelectNumberEnum.SIX.getNum(), null);
		targetSelectNumberYuusenMap.put(Numer0nSelectNumberEnum.SEVEN.getNum(), null);
		targetSelectNumberYuusenMap.put(Numer0nSelectNumberEnum.EIGHT.getNum(), null);
		targetSelectNumberYuusenMap.put(Numer0nSelectNumberEnum.NINE.getNum(), null);

		TARGET_SELECT_NUMBER_PRIORITY_MAP = targetSelectNumberYuusenMap;
	};

	/**
	 * <p>
	 * 交換する数字の優先度用Mapに優先度を上書きするメソッド
	 * </p>
	 * @param selectNumberKey 交換する数字
	 * @param selectNumberValue 優先度(数字が小さいほど優先度が高い)
	 */
	public synchronized void addSelectNumberPriorityMap(String selectNumberKey, Integer selectNumberValue) {
		if (TARGET_SELECT_NUMBER_PRIORITY_MAP.containsKey(selectNumberKey)) {
			TARGET_SELECT_NUMBER_PRIORITY_MAP.put(selectNumberKey, selectNumberValue);
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
		for(Map.Entry<String, Integer> entry: TARGET_SELECT_NUMBER_PRIORITY_MAP.entrySet()) {
			if (selectNumberValue.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * <p>
	 * 交換する数字の優先度用Mapをクリアする
	 * </p>
	 */
	public void clearSelectNumberPriorityMap() {
		for (String key: TARGET_SELECT_NUMBER_PRIORITY_MAP.keySet()) {
			TARGET_SELECT_NUMBER_PRIORITY_MAP.put(key, null);
		}
	}

	/**
	 * <p>
	 * 交換する数字の優先度用Mapから指定した値が含まれているか
	 * </p>
	 * @param doValue 含まれるか確認する値
	 * @return 含まれる含まれない
	 */
	public boolean containValueSelectNumberPriorityMap(Integer selectNumberValue) {
		return TARGET_SELECT_NUMBER_PRIORITY_MAP.containsValue(selectNumberValue);
	}

	/**
	 * Mapの値からkeyを取得するメソッド
	 * @param <K> key
	 * @param <V> value
	 * @param map map
	 * @param value value
	 * @return key
	 */
	public String getStringMapKey(Map<String, Integer> map, Integer value) {
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

}
