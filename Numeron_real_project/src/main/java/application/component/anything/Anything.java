package application.component.anything;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Anything {

	// 「,」
	public static final String COMMA = ",";

	/**
	 * リストの文字連結をおこなう。
	 * @param array 文字列リスト
	 * @return returnNum 連結後の文字列
	 */
	public static String convertListToString(ArrayList<String> array) {
		String returnNum = "";
		for (String str: array) {
			returnNum += str;
		}
		return returnNum;
	}

	/**
	 * リストをコンマで連結する
	 * @param list 連結対象リスト(CHANGEの場合、0：桁の位置、1：プレーヤーがCHANGEしたのがHighかLowかを表す)
	 * @return str 連結文字
	 */
	public static String concatListToComma(ArrayList<String> list) {
		StringBuilder strBuild = new StringBuilder();
		String str = "";
		for (int i = 0;i < list.size();i++) {
			if (strBuild.length() > 0) {
				strBuild.append(COMMA);
			}
			strBuild.append(list.get(i));
		}
		str = strBuild.toString();
		return str;
	}

	/**
	 * 文字列をコンマで連結する
	 * @param str 連結対象の文字（いくつでも引数指定可）
	 * @return aStr 連結文字
	 */
	public static String concatStringToComma(String... strs) {
		String aStr = "";
		for (String str: strs) {
			if (aStr.length() > 0) {
				aStr += COMMA;
			}
			aStr += str;
		}
		return aStr;
	}

	/**
	 * コンマで分割を行う
	 * @param str 連結文字（TARGETの場合、3,EXISTのように連結されている）
	 * @return afterStrList 分割したリスト
	 */
	public static ArrayList<String> splitComma(String str) {
		ArrayList<String> afterStrList;
		afterStrList =  new ArrayList<String>(Arrays.asList(str.split(COMMA)));
		return afterStrList;
	}

	/**
	 * 数字をリストに変換する
	 * @param num 文字列の数字
	 * @return numberList 数字のリスト
	 */
	public static ArrayList<String> convertNumberToArrayList(String num) {
		ArrayList<String> numberList = new ArrayList<>();
		for (int i = 0;i < num.length();i++) {
			numberList.add(num.substring(i,i+1));
		}
		return numberList;
	}

	/**
	 * 数字のリストから文字列を作成する
	 * @param numList リスト
	 * @return str 文字列
	 */
	public static String convertNumberToString(ArrayList<String> numList) {
		Iterator<String> ite = numList.iterator();
		String str = "";
		while (ite.hasNext()) {
			str += ite.next();
		}
		return str;
	}

	/**
	 * IntegerからStringに型を変換する
	 * @param ints Integer型
	 * @return str String型
	 */
	public static String convertIntegerToString(Integer ints) {
		String str = String.valueOf(ints);
		return str;
	}

	/**
	 * StringからIntegerに型を変換する
	 * @param ints Integer型
	 * @return str String型
	 */
	public static Integer convertStringToInteger(String str) {
		Integer num = Integer.parseInt(str);
		return num;
	}

}
