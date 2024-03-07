package application.logic.option.map;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import application.component.consts.Const;

/**
 * ShuffleOptionにて使用する最優先フラグを管理するクラス
 * @author shiraishitoshio
 *
 */
@Service
public class ShuffleOptionMapUtil {

	/**
	 * <p>
	 * シャッフルするしないの優先度を表すMap
	 * </p>
	 */
	public static final Map<String, Integer> SHUFFLE_DO_PRIORITY_MAP;
	static {
		Map<String, Integer> shuffleFlagYuusenMap = new HashMap<String, Integer>();
		shuffleFlagYuusenMap.put(Const.SHUFFLE_GO, null);
		shuffleFlagYuusenMap.put(Const.SHUFFLE_REJECT, null);

		SHUFFLE_DO_PRIORITY_MAP = shuffleFlagYuusenMap;
	};

	/**
	 * <p>
	 * シャッフルするかしないかの優先度用Mapに優先度を上書きするメソッド
	 * </p>
	 * @param doKey シャッフルするかしないか
	 * @param doValue 優先度(数字が小さいほど優先度が高い)
	 */
	public synchronized void addDoPriorityMap(String doKey, Integer doValue) {
		if (SHUFFLE_DO_PRIORITY_MAP.containsKey(doKey)) {
			SHUFFLE_DO_PRIORITY_MAP.put(doKey, doValue);
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
		for(Map.Entry<String, Integer> entry: SHUFFLE_DO_PRIORITY_MAP.entrySet()) {
			if (doValue.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * <p>
	 * 交換するかしないかの優先度用Mapから指定した値が含まれているか
	 * </p>
	 * @param doValue 含まれるか確認する値
	 * @return 含まれる含まれない
	 */
	public boolean containValueDoPriorityMap(Integer doValue) {
		return SHUFFLE_DO_PRIORITY_MAP.containsValue(doValue);
	}

	/**
	 * <p>
	 * 交換する数字の優先度用Mapをクリアする
	 * </p>
	 */
	public void clearDoPriorityMap() {
		for (String key: SHUFFLE_DO_PRIORITY_MAP.keySet()) {
			SHUFFLE_DO_PRIORITY_MAP.put(key, null);
		}
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
