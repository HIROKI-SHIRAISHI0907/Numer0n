package application.logic.human.gameComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import application.component.consts.Numer0nOptionEnum;

/**
 * Numer0nを開始する際に必要な部品
 * @author shiraishitoshio
 *
 */
@Component
public class GameComponentMapUtil {

	/**
	 * オプションリスト
	 */
	public static final List<String> OPTION_LIST;
	static {
		ArrayList<String> optionList = new ArrayList<String>();
		optionList.add(Numer0nOptionEnum.DOUBLE.getOprionName());
		optionList.add(Numer0nOptionEnum.SLASH.getOprionName());
		optionList.add(Numer0nOptionEnum.TARGET.getOprionName());
		optionList.add(Numer0nOptionEnum.HIGHLOW.getOprionName());
		optionList.add(Numer0nOptionEnum.CHANGE.getOprionName());
		optionList.add(Numer0nOptionEnum.SHUFFLE.getOprionName());

		OPTION_LIST = Collections.unmodifiableList(optionList);
	}

	/**
	 * 選択した攻撃オプション用のリスト(PLAYER)
	 */
	public static final ArrayList<String> PLAYER_SELECT_OFFENSE_OPTION_LIST;
	static {
		ArrayList<String> offenseOptionList = new ArrayList<String>();
		PLAYER_SELECT_OFFENSE_OPTION_LIST = offenseOptionList;
	}

	/**
	 * 選択した防御オプション用のリスト(PLAYER)
	 */
	public static final ArrayList<String> PLAYER_SELECT_DIFFENSE_OPTION_LIST;
	static {
		ArrayList<String> diffenseOptionList = new ArrayList<String>();
		PLAYER_SELECT_DIFFENSE_OPTION_LIST = diffenseOptionList;
	}

	/**
	 * 選択した攻撃オプション用のMap(CPU)
	 */
	public static final Map<String, Integer> CPU_SELECT_OFFENSE_OPTION_PRIORITY_MAP;
	static {
		Map<String, Integer> optionOffenseYuusenMap = new HashMap<String, Integer>();
		CPU_SELECT_OFFENSE_OPTION_PRIORITY_MAP = optionOffenseYuusenMap;
	}

	/**
	 * 選択した防御オプション用のMap(CPU)
	 */
	public static final Map<String, Integer> CPU_SELECT_DIFFENSE_OPTION_PRIORITY_MAP;
	static {
		Map<String, Integer> optionDiffenseYuusenMap = new HashMap<String, Integer>();
		CPU_SELECT_DIFFENSE_OPTION_PRIORITY_MAP = optionDiffenseYuusenMap;
	}

	/**
	 * 選択した攻撃オプション用のリスト(PLAYER)を取得する
	 * @param index オプション番号(index)
	 */
	public String getPlayerOffense(Integer index) {
		return PLAYER_SELECT_OFFENSE_OPTION_LIST.get(index);
	}

	/**
	 * 選択した防御オプション用のリスト(PLAYER)を取得する
	 * @param index オプション番号(index)
	 */
	public String getPlayerDiffense(Integer index) {
		return PLAYER_SELECT_DIFFENSE_OPTION_LIST.get(index);
	}

	/**
	 * 選択した攻撃オプション用のリスト(CPU)を取得する
	 * @param index オプション番号(index)
	 */
	public String getCpuOffense(Integer index) {
		ArrayList<String> list = new ArrayList<String>();
		for (Map.Entry<String, Integer> entry : CPU_SELECT_OFFENSE_OPTION_PRIORITY_MAP.entrySet()) {
			list.add(entry.getKey());
		}
		return list.get(index);
	}

	/**
	 * 選択した防御オプション用のリスト(CPU)を取得する
	 * @param index オプション番号(index)
	 */
	public String getCpuDiffense(Integer index) {
		ArrayList<String> list = new ArrayList<String>();
		for (Map.Entry<String, Integer> entry : CPU_SELECT_DIFFENSE_OPTION_PRIORITY_MAP.entrySet()) {
			list.add(entry.getKey());
		}
		return list.get(index);
	}

	/**
	 * オプションリストのサイズを取得する
	 * @return リストのサイズ
	 */
	public Integer getOptionListSize() {
		return OPTION_LIST.size();
	}

	/**
	 * オプションリストからオプションを取得する
	 * @param listNumber リスト番号
	 * @return オプション名
	 */
	public String getOption(int listNumber) {
		return OPTION_LIST.get(listNumber);
	}

	/**
	 * オプションリストからオプションを取得する
	 * @param optionName オプション名
	 */
	public String getOption(String optionName) {
		if (OPTION_LIST.contains(optionName)) {
			return getOption(OPTION_LIST.indexOf(optionName));
		}
		return null;
	}

	/**
	 * 選択した攻撃オプション用のリスト(PLAYER)のサイズを取得する
	 * @return リストのサイズ
	 */
	public Integer getOffenseListSize() {
		return PLAYER_SELECT_OFFENSE_OPTION_LIST.size();
	}

	/**
	 * 選択した防御オプション用のリスト(PLAYER)のサイズを取得する
	 * @return リストのサイズ
	 */
	public Integer getDiffenseListSize() {
		return PLAYER_SELECT_DIFFENSE_OPTION_LIST.size();
	}

	/**
	 * 選択した攻撃オプション用のリスト(PLAYER)に指定したオプションが含まれるか
	 * @param optionName オプション名
	 */
	public boolean containOffenseOptionList(String optionName) {
		return PLAYER_SELECT_OFFENSE_OPTION_LIST.contains(optionName);
	}

	/**
	 * 選択した防御オプション用のリスト(PLAYER)に指定したオプションが含まれるか
	 * @param optionName オプション名
	 */
	public boolean containDiffenseOptionList(String optionName) {
		return PLAYER_SELECT_DIFFENSE_OPTION_LIST.contains(optionName);
	}

	/**
	 * 選択した攻撃オプション用のリスト(PLAYER)にオプションを追加する
	 * @param optionName オプション名
	 */
	public void addOffenseOptionList(String optionName) {
		PLAYER_SELECT_OFFENSE_OPTION_LIST.add(optionName);
	}

	/**
	 * 選択した防御オプション用のリスト(PLAYER)にオプションを追加する
	 * @param optionName オプション名
	 */
	public void addDiffenseOptionList(String optionName) {
		PLAYER_SELECT_DIFFENSE_OPTION_LIST.add(optionName);
	}

	/**
	 * 選択した攻撃オプション用のリスト(PLAYER)からオプションを削除する
	 * @param optionName オプション名
	 */
	public void removeOffenseOptionList(String optionName) {
		if (containOffenseOptionList(optionName)) {
			PLAYER_SELECT_OFFENSE_OPTION_LIST.remove(optionName);
		}
	}

	/**
	 * 選択した攻撃オプション用のリスト(PLAYER)からオプションを削除する
	 * @param index オプション名に紐づくindex
	 */
	public void removeOffenseOptionList(Integer index) {
		String item = PLAYER_SELECT_OFFENSE_OPTION_LIST.get(index);
		removeOffenseOptionList(item);
	}

	/**
	 * 選択した防御オプション用のリスト(PLAYER)からオプションを削除する
	 * @param optionName オプション名
	 */
	public void removeDiffenseOptionList(String optionName) {
		if (containDiffenseOptionList(optionName)) {
			PLAYER_SELECT_DIFFENSE_OPTION_LIST.remove(optionName);
		}
	}

	/**
	 * 選択した防御オプション用のリスト(PLAYER)からオプションを削除する
	 * param index オプション名に紐づくindex
	 */
	public void removeDiffenseOptionList(Integer index) {
		String item = PLAYER_SELECT_DIFFENSE_OPTION_LIST.get(index);
		removeDiffenseOptionList(item);
	}

	/**
	 * 選択した攻撃オプション用のMap(CPU)のサイズを取得する
	 * @return Mapのサイズ
	 */
	public Integer getOffenseMapSize() {
		return CPU_SELECT_OFFENSE_OPTION_PRIORITY_MAP.size();
	}

	/**
	 * 選択した防御オプション用のMap(CPU)のサイズを取得する
	 * @return Mapのサイズ
	 */
	public Integer getDiffenseMapSize() {
		return CPU_SELECT_DIFFENSE_OPTION_PRIORITY_MAP.size();
	}

	/**
	 * 選択した攻撃オプション用のMap(CPU)に指定したオプションが含まれるか
	 * @param optionName オプション名
	 */
	public boolean containsKeyOffenseOptionMap(String optionName) {
		return CPU_SELECT_OFFENSE_OPTION_PRIORITY_MAP.containsKey(optionName);
	}

	/**
	 * 選択した防御オプション用のMap(CPU)に指定したオプションが含まれるか
	 * @param optionName オプション名
	 */
	public boolean containsKeyDiffenseOptionMap(String optionName) {
		return CPU_SELECT_DIFFENSE_OPTION_PRIORITY_MAP.containsKey(optionName);
	}

	/**
	 * 選択した攻撃オプション用のMap(CPU)に指定したフラグが含まれるか
	 * @param flagNumber フラグ
	 */
	public boolean containsValueOffenseOptionMap(Integer flagNumber) {
		return CPU_SELECT_OFFENSE_OPTION_PRIORITY_MAP.containsValue(flagNumber);
	}

	/**
	 * 選択した防御オプション用のMap(CPU)に指定したフラグが含まれるか
	 * @param flagNumber フラグ
	 */
	public boolean containsValueDiffenseOptionMap(Integer flagNumber) {
		return CPU_SELECT_DIFFENSE_OPTION_PRIORITY_MAP.containsValue(flagNumber);
	}

	/**
	 * 選択した攻撃オプション用のMap(CPU)にオプションを追加する
	 * @param optionName オプション名
	 */
	public void addOffenseOptionMap(String optionName) {
		CPU_SELECT_OFFENSE_OPTION_PRIORITY_MAP.put(optionName, null);
	}

	/**
	 * 選択した防御オプション用のMap(CPU)にオプションを追加する
	 * @param optionName オプション名
	 */
	public void addDiffenseOptionMap(String optionName) {
		CPU_SELECT_DIFFENSE_OPTION_PRIORITY_MAP.put(optionName, null);
	}

	/**
	 * 登録した攻撃オプション用のMap(CPU)のvalueを更新する
	 * @param optionName オプション名
	 * @param value 更新したい優先度フラグ
	 */
	public void alterOffenseOptionMap(String optionName, Integer value) {
		if (containsKeyOffenseOptionMap(optionName)) {
			CPU_SELECT_OFFENSE_OPTION_PRIORITY_MAP.put(optionName, value);
		}
	}

	/**
	 * 登録した防御オプション用のMap(CPU)にvalueを更新する
	 * @param optionName オプション名
	 * @param value 更新したい優先度フラグ
	 */
	public void alterDiffenseOptionMap(String optionName, Integer value) {
		if (containsKeyDiffenseOptionMap(optionName)) {
			CPU_SELECT_DIFFENSE_OPTION_PRIORITY_MAP.put(optionName, value);
		}
	}

	/**
	 * 選択した攻撃オプション用のMap(CPU)からオプションを削除する
	 * @param optionName オプション名
	 */
	public void removeOffenseOptionMap(String optionName) {
		if (containsKeyOffenseOptionMap(optionName)) {
			CPU_SELECT_OFFENSE_OPTION_PRIORITY_MAP.remove(optionName);
		}
	}

	/**
	 * 選択した防御オプション用のMap(CPU)からオプションを削除する
	 * @param optionName オプション名
	 */
	public void removeDiffenseOptionMap(String optionName) {
		if (containsKeyDiffenseOptionMap(optionName)) {
			CPU_SELECT_DIFFENSE_OPTION_PRIORITY_MAP.remove(optionName);
		}
	}

	/**
	 * <p>
	 * 選択した攻撃オプション用のMap(CPU)をクリアする
	 * </p>
	 */
	public void clearOffenseOptionMap() {
		for (String key : CPU_SELECT_OFFENSE_OPTION_PRIORITY_MAP.keySet()) {
			CPU_SELECT_OFFENSE_OPTION_PRIORITY_MAP.put(key, null);
		}
	}

	/**
	 * <p>
	 * 選択した攻撃オプション用のMap(CPU)をクリアする
	 * </p>
	 */
	public void clearDiffenseOptionMap() {
		for (String key : CPU_SELECT_DIFFENSE_OPTION_PRIORITY_MAP.keySet()) {
			CPU_SELECT_DIFFENSE_OPTION_PRIORITY_MAP.put(key, null);
		}
	}

}
