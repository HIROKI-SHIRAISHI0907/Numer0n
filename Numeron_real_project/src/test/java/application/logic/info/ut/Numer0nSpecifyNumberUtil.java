package application.logic.info.ut;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;

import application.component.anything.Anything;
import application.component.consts.Const;
import application.component.consts.Numer0nChangeEnum;
import application.component.consts.Numer0nSelectNumberEnum;
import application.component.consts.Numer0nTargetEnum;
import application.logic.human.GameMaster;
import application.logic.judge.Eatbite;

/**
 * テストに使用するUtilクラス
 * @author shiraishitoshio
 *
 */
public class Numer0nSpecifyNumberUtil {

	/**
	 * 格納された情報リストに数値が含まれているかいないか確認する
	 * @param chkNumber その数字が含まれるか含まれないか確認するための数字
	 * @param resultNumberList 確認したい数値候補群
	 * @param howToChk 確認方法
	 * @param onlyContainFlg 含まれるかどうかのみ確認したいか、否の場合,equalsも確認する
	 * @param callNumber howToChk=TWO_PART_NUMBER_CONTAINのみinfo内の数値情報を格納
	 * @param callInfo howToChk=TWO_PART_NUMBER_CONTAINのみEATBITE情報を格納
	 * （一致を考慮しない場合のみ）
	 * @return <code>true:</code>含まれている,<code>false:</code>含まれていない
	 */
	protected String chkContainsNumber(String chkNumber,
			ArrayList<String> resultNumberList, String howToChk, boolean onlyContainFlg, String callNumber,
			String callInfo) {
		switch (howToChk) {
		case "PART_NUMBER_CONTAIN":
			if (chkNumber.length() != 1) {
				fail("PART_NUMBER_CONTAINは、chkNumberを1桁だけ入力してください");
			}
			int trueContainCount = 0;
			int falseContainCount = 0;
			boolean chkContain = false;
			ArrayList<String> callList = Anything.convertNumberToArrayList(chkNumber);
			for (int i = 0; i < resultNumberList.size(); i++) {
				int chk = 0;
				if (onlyContainFlg) {
					for (int j = 0; j < resultNumberList.get(i).length(); j++) {
						if (resultNumberList.get(i).substring(j, j + 1).equals(callList.get(j))) {
							chk++;
						}
					}
					if (chk == 0) {
						chkContain = true;
					}
				} else {
					chkContain = true;
				}

				if (Anything.convertNumberToArrayList(resultNumberList.get(i))
						.contains(chkNumber) && chkContain) {
					trueContainCount++;
				} else if (!Anything.convertNumberToArrayList(resultNumberList.get(i))
						.contains(chkNumber)) {
					falseContainCount++;
				}
			}
			if (trueContainCount == resultNumberList.size()) {
				return "ALL_CONTAINMENT";
			}
			if (falseContainCount == resultNumberList.size()) {
				return "ALL_NOT_CONTAINMENT";
			}
			return chkContainsNumber(chkNumber, resultNumberList, "TRUE_COUNT", false, null, null);
		case "ALL_NUMBER_CONTAIN":
			if (chkNumber == null) {
				fail("ALL_NUMBER_CONTAINは、chkNumberを何桁か入力してください");
			}
			int trueAllCount = 0;
			int falseAllCount = 0;
			chkContain = false;
			callList = Anything.convertNumberToArrayList(callNumber);
			for (int i = 0; i < resultNumberList.size(); i++) {
				int chk = 0;
				if (onlyContainFlg) {
					for (int j = 0; j < resultNumberList.get(i).length(); j++) {
						if (resultNumberList.get(i).substring(j, j + 1).equals(callList.get(j))) {
							chk++;
						}
					}
					if (chk == 0) {
						chkContain = true;
					}
				} else {
					chkContain = true;
				}

				if (resultNumberList.get(i).contains(
						chkNumber) && chkContain) {
					trueAllCount++;
				} else if (!resultNumberList.get(i).contains(
						chkNumber)) {
					falseAllCount++;
				}
			}
			if (trueAllCount == resultNumberList.size()) {
				return "ALL_CONTAINMENT";
			}
			if (falseAllCount == resultNumberList.size()) {
				return "ALL_NOT_CONTAINMENT";
			}
			return chkContainsNumber(chkNumber, resultNumberList, "TRUE_COUNT", false, null, null);
		case "TWO_PART_NUMBER_CONTAIN":
			if (chkNumber != null) {
				fail("TWO_PART_NUMBER_CONTAINは、nullにしてください");
			}
			callList = Anything.convertNumberToArrayList(callNumber);
			boolean tfChk = true;
			GameMaster gm = spy(new GameMaster());
			Eatbite eb = new Eatbite(gm);
			for (int i = 0; i < resultNumberList.size(); i++) {
				eb.judgeEatBite(
						Anything.convertNumberToArrayList(resultNumberList.get(i)),
						callList);
				Integer coEats = Integer.parseInt(callInfo.substring(0, 1));
				Integer coBites = Integer.parseInt(callInfo.substring(4, 5));
				Integer eats = Integer.parseInt(eb.getEatBiteResult().substring(0, 1));
				Integer bites = Integer.parseInt(eb.getEatBiteResult().substring(4, 5));
				if (!(coEats == eats && coBites == bites)) {
					tfChk = false;
					return "FAIL(数字:" + resultNumberList.get(i)
							+ ",eatbite:" + eats + "EAT" + bites + "BITE)";
				}
			}

			if (tfChk) {
				return "EAT&BITE_OK";
			}
		case "TWO_PART_NUMBER_CONTAIN_OPPOSITE":
			if (chkNumber != null) {
				fail("TWO_PART_NUMBER_CONTAIN_OPPOSITEは、nullにしてください");
			}
			callList = Anything.convertNumberToArrayList(callNumber);
			tfChk = true;
			gm = spy(new GameMaster());
			eb = new Eatbite(gm);
			for (int i = 0; i < resultNumberList.size(); i++) {
				eb.judgeEatBite(
						Anything.convertNumberToArrayList(resultNumberList.get(i)),
						callList);
				Integer coEats = Integer.parseInt(callInfo.substring(0, 1));
				Integer coBites = Integer.parseInt(callInfo.substring(4, 5));
				Integer eats = Integer.parseInt(eb.getEatBiteResult().substring(0, 1));
				Integer bites = Integer.parseInt(eb.getEatBiteResult().substring(4, 5));
				if (coEats == eats && coBites == bites) {
					tfChk = false;
					return "FAIL(数字:" + resultNumberList.get(i)
							+ ",eatbite:" + eats + "EAT" + bites + "BITE)";
				}
			}

			if (tfChk) {
				return "EAT&BITE_OK";
			}
		case "SAME_NOTSAME_LIST":
			if (chkNumber == null) {
				fail("SAME_NOTSAME_LISTは、chkNumberを何か入力してください");
			}
			int ind = resultNumberList.indexOf(chkNumber);
			if (ind != -1) {
				return "EXIST";
			} else {
				return "NOT_EXIST";
			}
		case "TRUE_COUNT_CONTAIN":
			if (chkNumber == null) {
				fail("COUNTは、chkNumberを何か入力してください");
			}
			Integer trueCount = 0;
			for (int i = 0; i < resultNumberList.size(); i++) {
				if (resultNumberList.get(i).contains(
						chkNumber)) {
					trueCount++;
				}
			}
			return Anything.convertIntegerToString(trueCount);
		case "FALSE_COUNT_CONTAIN":
			if (chkNumber == null) {
				fail("COUNTは、chkNumberを何か入力してください");
			}
			Integer falseCount = 0;
			for (int i = 0; i < resultNumberList.size(); i++) {
				if (!resultNumberList.get(i).contains(
						chkNumber)) {
					falseCount++;
				}
			}
			return Anything.convertIntegerToString(falseCount);
		case "TRUE_COUNT_EQUAL":
			if (chkNumber == null) {
				fail("COUNTは、chkNumberを何か入力してください");
			}
			Integer trueEqualCount = 0;
			for (int i = 0; i < resultNumberList.size(); i++) {
				for (int j = 0; j < resultNumberList.get(i).length(); j++) {
					if (resultNumberList.get(i).substring(j, j + 1).equals(chkNumber)) {
						trueEqualCount++;
					}
				}
			}
			return Anything.convertIntegerToString(trueEqualCount);
		case "DOUBLE_DIGIT_POINT":
			if (chkNumber == null) {
				fail("DOUBLE_DIGIT_POINTは、chkNumberに桁,指定の数字で順に入力してください");
			}
			ArrayList<String> infoList = Anything.splitComma(chkNumber);
			boolean doubleDigitPointTf = true;
			for (int i = 0; i < resultNumberList.size(); i++) {
				if (!resultNumberList.get(i).substring(
						Anything.convertStringToInteger(infoList.get(0)),
						Anything.convertStringToInteger(infoList.get(0)) + 1)
						.equals(infoList.get(1))) {
					doubleDigitPointTf = false;
					break;
				}
			}
			if (doubleDigitPointTf) {
				return "OK";
			} else {
				return "NG";
			}
		case "DOUBLE_DIGIT_POINT_OPPOSITE":
			if (chkNumber == null) {
				fail("DOUBLE_DIGIT_POINT_OPPOSITEは、chkNumberに桁,指定の数字で順に入力してください");
			}
			infoList = Anything.splitComma(chkNumber);
			doubleDigitPointTf = true;
			for (int i = 0; i < resultNumberList.size(); i++) {
				if (resultNumberList.get(i).substring(
						Anything.convertStringToInteger(infoList.get(0)),
						Anything.convertStringToInteger(infoList.get(0)) + 1)
						.equals(infoList.get(1))) {
					doubleDigitPointTf = false;
					break;
				}
			}
			if (doubleDigitPointTf) {
				return "OK";
			} else {
				return "NG";
			}
		case "SLASH":
			if (chkNumber == null) {
				fail("SLASHは、chkNumberにスラッシュナンバーを入力してください");
			}
			boolean slashTf = true;
			for (int i = 0; i < resultNumberList.size(); i++) {
				if (!(Integer.valueOf(Integer.parseInt(Collections.max(
						Anything.convertNumberToArrayList(resultNumberList.get(i)))) -
						Integer.parseInt(Collections.min(
								Anything.convertNumberToArrayList(resultNumberList.get(i))))) == Anything
										.convertStringToInteger(chkNumber))) {
					slashTf = false;
					break;
				}
			}
			if (slashTf) {
				return "OK";
			} else {
				return "NG";
			}
		case "SLASH_OPPOSITE":
			if (chkNumber == null) {
				fail("SLASHは、chkNumberにスラッシュナンバーを入力してください");
			}
			slashTf = false;
			for (int i = 0; i < resultNumberList.size(); i++) {
				if (Integer.valueOf(Integer.parseInt(Collections.max(
						Anything.convertNumberToArrayList(resultNumberList.get(i)))) -
						Integer.parseInt(Collections.min(
								Anything.convertNumberToArrayList(resultNumberList.get(i))))) == Anything
										.convertStringToInteger(chkNumber)) {
					slashTf = true;
					break;
				}
			}
			if (!slashTf) {
				return "OK";
			} else {
				return "NG";
			}
		case "HIGH&LOW":
			if (chkNumber == null) {
				fail("HIGH&LOWは、chkNumberにHIGHorLOWの文字列をカンマ区切りで入力してください");
			}
			ArrayList<String> highLowList = Anything.splitComma(chkNumber);

			boolean highLowTf = true;
			for (int i = 0; i < resultNumberList.size(); i++) {
				Integer zero = Anything.convertStringToInteger(Numer0nSelectNumberEnum.ZERO.getNum());
				Integer four = Anything.convertStringToInteger(Numer0nSelectNumberEnum.FOUR.getNum());
				Integer five = Anything.convertStringToInteger(Numer0nSelectNumberEnum.FIVE.getNum());
				Integer nine = Anything.convertStringToInteger(Numer0nSelectNumberEnum.NINE.getNum());

				for (int j = 0; j < resultNumberList.get(i).length(); j++) {
					Integer num = Anything.convertStringToInteger(resultNumberList.get(i)
							.substring(j, j + 1));
					if (Const.HIGH.equals(highLowList.get(j))) {
						if (0 >= zero.compareTo(num) &&
								0 <= four.compareTo(num)) {
							highLowTf = false;
							break;
						}
					} else {
						if (0 >= five.compareTo(num) &&
								0 <= nine.compareTo(num)) {
							highLowTf = false;
							break;
						}
					}
				}
			}

			if (highLowTf) {
				return "OK";
			} else {
				return "NG";
			}
		case "HIGH&LOW_OPPOSITE":
			if (chkNumber == null) {
				fail("HIGH&LOWは、chkNumberにHIGHorLOWの文字列をカンマ区切りで入力してください");
			}
			highLowList = Anything.splitComma(chkNumber);

			highLowTf = false;
			int allCount = 0;
			for (int i = 0; i < resultNumberList.size(); i++) {
				Integer zero = Anything.convertStringToInteger(Numer0nSelectNumberEnum.ZERO.getNum());
				Integer four = Anything.convertStringToInteger(Numer0nSelectNumberEnum.FOUR.getNum());
				Integer five = Anything.convertStringToInteger(Numer0nSelectNumberEnum.FIVE.getNum());
				Integer nine = Anything.convertStringToInteger(Numer0nSelectNumberEnum.NINE.getNum());

				int count = 0;
				for (int j = 0; j < resultNumberList.get(i).length(); j++) {
					Integer num = Anything.convertStringToInteger(resultNumberList.get(i)
							.substring(j, j + 1));
					if (Const.HIGH.equals(highLowList.get(j))) {
						if (0 >= five.compareTo(num) &&
								0 <= nine.compareTo(num)) {
							count++;
						}
					} else {
						if (0 >= zero.compareTo(num) &&
								0 <= four.compareTo(num)) {
							count++;
						}
					}

				}

				if (count != resultNumberList.get(i).length()) {
					allCount++;
				}

			}

			if (allCount == resultNumberList.size()) {
				highLowTf = true;
			}

			if (highLowTf) {
				return "OK";
			} else {
				return "NG(全体:" + resultNumberList.size() + ", 確認OK:" + allCount + ")";
			}
		case "CHANGE":
			if (chkNumber == null) {
				fail("CHANGEは、chkNumberにnull以外を入力してください");
			}
			boolean changeTf = false;

			int hCount = 0;
			int lCount = 0;
			ArrayList<String> chklist = Anything.splitComma(chkNumber);
			for (int i = 0; i < resultNumberList.size(); i++) {
				ArrayList<String> list = Anything.convertNumberToArrayList(resultNumberList.get(i));
				Integer zero = Anything.convertStringToInteger(Numer0nSelectNumberEnum.ZERO.getNum());
				Integer four = Anything.convertStringToInteger(Numer0nSelectNumberEnum.FOUR.getNum());
				Integer five = Anything.convertStringToInteger(Numer0nSelectNumberEnum.FIVE.getNum());
				Integer nine = Anything.convertStringToInteger(Numer0nSelectNumberEnum.NINE.getNum());
				String hl = chklist.get(1);
				if (!Numer0nChangeEnum.DONT_TEACH_INDEX.getAbb().equals(chklist.get(0))) {
					String chk = list.get(Anything.convertStringToInteger(chklist.get(0)));
					Integer chkInt = Anything.convertStringToInteger(chk);
					if (Numer0nChangeEnum.NOT_CLEAR.getAbb().equals(chklist.get(1))) {
						hCount++;
						lCount++;
					} else if (Const.HIGH.equals(hl)) {
						if (0 >= five.compareTo(chkInt) &&
								0 <= nine.compareTo(chkInt)) {
							hCount++;
						}
					} else {
						if (0 >= zero.compareTo(chkInt) &&
								0 <= four.compareTo(chkInt)) {
							lCount++;
						}
					}
				} else {
					if (!Numer0nChangeEnum.NOT_CLEAR.getAbb().equals(chklist.get(1))) {
						if ((list.contains("5") ||
								list.contains("6") ||
								list.contains("7") ||
								list.contains("8") ||
								list.contains("9")) &&
								Const.HIGH.equals(hl)) {
							hCount++;
						} else if ((list.contains("0") ||
								list.contains("1") ||
								list.contains("2") ||
								list.contains("3") ||
								list.contains("4")) &&
								Const.LOW.equals(hl)) {
							lCount++;
						}
					} else {
						hCount++;
						lCount++;
					}
				}
			}
			if (hCount == resultNumberList.size() ||
					lCount == resultNumberList.size()) {
				changeTf = true;
			}

			if (changeTf) {
				return "OK";
			} else {
				return "NG";
			}
		case "CHANGE_OPPOSITE":
			if (chkNumber == null) {
				fail("CHANGE_OPPOSITEは、chkNumberにnull以外を入力してください");
			}
			changeTf = false;

			hCount = 0;
			lCount = 0;
			chklist = Anything.splitComma(chkNumber);
			for (int i = 0; i < resultNumberList.size(); i++) {
				ArrayList<String> list = Anything.convertNumberToArrayList(resultNumberList.get(i));
				Integer zero = Anything.convertStringToInteger(Numer0nSelectNumberEnum.ZERO.getNum());
				Integer four = Anything.convertStringToInteger(Numer0nSelectNumberEnum.FOUR.getNum());
				Integer five = Anything.convertStringToInteger(Numer0nSelectNumberEnum.FIVE.getNum());
				Integer nine = Anything.convertStringToInteger(Numer0nSelectNumberEnum.NINE.getNum());
				String hl = chklist.get(1);
				if (!Numer0nChangeEnum.DONT_TEACH_INDEX.getAbb().equals(chklist.get(0))) {
					String chk = list.get(Anything.convertStringToInteger(chklist.get(0)));
					Integer chkInt = Anything.convertStringToInteger(chk);
					if (Numer0nChangeEnum.NOT_CLEAR.getAbb().equals(chklist.get(1))) {
						hCount++;
						lCount++;
					} else if (Const.HIGH.equals(hl)) {
						if (0 >= zero.compareTo(chkInt) &&
								0 <= four.compareTo(chkInt)) {
							hCount++;
						}
					} else {
						if (0 >= five.compareTo(chkInt) &&
								0 <= nine.compareTo(chkInt)) {
							lCount++;
						}
					}
				} else {
					if (!Numer0nChangeEnum.NOT_CLEAR.getAbb().equals(chklist.get(1))) {
						if ((list.contains("0") ||
								list.contains("1") ||
								list.contains("2") ||
								list.contains("3") ||
								list.contains("4")) &&
								Const.HIGH.equals(hl)) {
							hCount++;
						} else if ((list.contains("5") ||
								list.contains("6") ||
								list.contains("7") ||
								list.contains("8") ||
								list.contains("9")) &&
								Const.LOW.equals(hl)) {
							lCount++;
						}
					} else {
						hCount++;
						lCount++;
					}
				}
			}

			if (hCount == resultNumberList.size() ||
					lCount == resultNumberList.size()) {
				changeTf = true;
			}

			if (changeTf) {
				return "OK";
			} else {
				return "NG";
			}
		case "TARGET":
			if (chkNumber == null) {
				fail("CHANGEは、chkNumberにnull以外を入力してください");
			}

			int one = 0;
			int two = 0;
			int three = 0;
			int four = 0;
			int five = 0;
			int six = 0;
			chklist = Anything.splitComma(chkNumber);
			String digitChk = chklist.get(0);
			String existChk = chklist.get(1);
			String numberChk = chklist.get(2);
			for (int i = 0; i < resultNumberList.size(); i++) {
				ArrayList<String> list = Anything.convertNumberToArrayList(resultNumberList.get(i));
				// 存在するか
				if (!"NONEEXISTLISTOFNUMBER".equals(existChk)) {
					// 桁の位置がわかるか
					if (!Numer0nTargetEnum.DONT_TEACH_INDEX.getAbb().equals(digitChk)) {
						String chk = list.get(Anything.convertStringToInteger(digitChk));
						// その数字がわかって等しいか
						if (!Numer0nTargetEnum.NOT_CLEAR.getAbb().equals(numberChk)) {
							if (chk.equals(numberChk)) {
								one++;
							}
						}
					// 桁の位置が不明か
					} else {
						// 存在する数字がわかるか
						if (!Numer0nTargetEnum.NOT_CLEAR.getAbb().equals(numberChk)) {
							if (list.contains(numberChk)) {
								two++;
							}
						// 存在する数字がわからないか
						} else {
							three++;
						}
					}
				// 存在しないか
				} else {
					// 桁の位置がわかるか
					if (!Numer0nTargetEnum.DONT_TEACH_INDEX.getAbb().equals(digitChk)) {
						String chk = list.get(Anything.convertStringToInteger(digitChk));
						// その数字がわかって等しくないか
						if (!Numer0nTargetEnum.NOT_CLEAR.getAbb().equals(numberChk)) {
							if (!chk.equals(numberChk)) {
								four++;
							}
						}
					// 桁の位置が不明か
					} else {
						// 存在しない数字がわかるか
						if (!Numer0nTargetEnum.NOT_CLEAR.getAbb().equals(numberChk)) {
							if (!list.contains(numberChk)) {
								five++;
							}
						// 存在する数字がわからないか
						} else {
							six++;
						}
					}
				}
			}
			if (one == resultNumberList.size() ||
				two == resultNumberList.size() ||
				three == resultNumberList.size() ||
				four == resultNumberList.size() ||
				five == resultNumberList.size() ||
				six == resultNumberList.size()) {
				return "OK";
			} else {
				StringBuilder sb = new StringBuilder();
				sb.append("存在する:桁の位置わかる:その数字等しい:").append(one)
				.append("存在する:桁の位置わからない:存在する数字がわかる:").append(two)
				.append("存在する:桁の位置わからない:存在する数字がわからない:").append(three)
				.append("存在しない:桁の位置わかる:その数字等しくない:").append(four)
				.append("存在しない:桁の位置わからない:存在しない数字がわかる:").append(five)
				.append("存在しない:桁の位置わからない:存在しない数字がわからない:").append(six);
				return "NG(" + sb.toString() + ")";
			}
		case "TARGET_OPPOSITE":
			if (chkNumber == null) {
				fail("CHANGEは、chkNumberにnull以外を入力してください");
			}

			one = 0;
			two = 0;
			three = 0;
			four = 0;
			five = 0;
			six = 0;
			chklist = Anything.splitComma(chkNumber);
			digitChk = chklist.get(0);
			existChk = chklist.get(1);
			numberChk = chklist.get(2);
			for (int i = 0; i < resultNumberList.size(); i++) {
				ArrayList<String> list = Anything.convertNumberToArrayList(resultNumberList.get(i));
				// 存在するか
				if (!"NONEEXISTLISTOFNUMBER".equals(existChk)) {
					// 桁の位置がわかるか
					if (!Numer0nTargetEnum.DONT_TEACH_INDEX.getAbb().equals(digitChk)) {
						String chk = list.get(Anything.convertStringToInteger(digitChk));
						// その数字がわかって等しいか
						if (!Numer0nTargetEnum.NOT_CLEAR.getAbb().equals(numberChk)) {
							if (!chk.equals(numberChk)) {
								one++;
							}
						}
					// 桁の位置が不明か
					} else {
						// 存在する数字がわかるか
						if (!Numer0nTargetEnum.NOT_CLEAR.getAbb().equals(numberChk)) {
							if (!list.contains(numberChk)) {
								two++;
							}
						// 存在する数字がわからないか
						} else {
							three++;
						}
					}
				// 存在しないか
				} else {
					// 桁の位置がわかるか
					if (!Numer0nTargetEnum.DONT_TEACH_INDEX.getAbb().equals(digitChk)) {
						String chk = list.get(Anything.convertStringToInteger(digitChk));
						// その数字がわかって等しくないか
						if (!Numer0nTargetEnum.NOT_CLEAR.getAbb().equals(numberChk)) {
							if (chk.equals(numberChk)) {
								four++;
							}
						}
					// 桁の位置が不明か
					} else {
						// 存在しない数字がわかるか
						if (!Numer0nTargetEnum.NOT_CLEAR.getAbb().equals(numberChk)) {
							if (list.contains(numberChk)) {
								five++;
							}
						// 存在する数字がわからないか
						} else {
							six++;
						}
					}
				}
			}
			if (one == resultNumberList.size() ||
				two == resultNumberList.size() ||
				three == resultNumberList.size() ||
				four == resultNumberList.size() ||
				five == resultNumberList.size() ||
				six == resultNumberList.size()) {
				return "OK";
			} else {
				StringBuilder sb = new StringBuilder();
				sb.append("!存在する:桁の位置わかる:その数字等しい:").append(one)
				.append("!存在する:桁の位置わからない:存在する数字がわかる:").append(two)
				.append("!存在する:桁の位置わからない:存在する数字がわからない:").append(three)
				.append("!存在しない:桁の位置わかる:その数字等しくない:").append(four)
				.append("!存在しない:桁の位置わからない:存在しない数字がわかる:").append(five)
				.append("!存在しない:桁の位置わからない:存在しない数字がわからない:").append(six);
				return "NG(" + sb.toString() + ")";
			}
		}
		fail("どれにも当てはまりません。");
		return "FAIL";
	}

}
