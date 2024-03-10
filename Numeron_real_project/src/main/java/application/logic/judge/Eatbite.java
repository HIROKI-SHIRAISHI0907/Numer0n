package application.logic.judge;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import application.component.anything.Anything;
import application.component.consts.Const;
import application.component.consts.Numer0nDoubleEnum;
import application.logic.human.GameMaster;
import lombok.RequiredArgsConstructor;

/**
 * EAT,BITEを判断するクラス
 */
@Service
@RequiredArgsConstructor
public class Eatbite {

	/**
	 * プレーヤークラス
	 */
	private final GameMaster gameMaster;

	/**
	 * ?EAT?BITEを格納する文字列
	 */
	private String eatBiteResult;

	/**
	 * EAT,BITEを判断する
	 * @param callNumber コールする側の数字
	 * @param correctNumber 正しい数字(判断される側の数字)
	 */
	public Integer judgeEatBite(ArrayList<String> callNumber, ArrayList<String> correctNumber) {
		int eat = 0;
		int bite = 0;
		for (int i = 0;i < callNumber.size();i++) {
			for (int j = 0;j < correctNumber.size();j++) {
				if (callNumber.get(i).equals(correctNumber.get(j))) {
					if (i == j) {
						eat += 1;
					} else {
						bite += 1;
					}
				}
			}
		}

		// eat,biteをセット
		StringBuilder strBuild = new StringBuilder();
		String eatInts = Anything.convertIntegerToString(eat);
		String eatBites = Anything.convertIntegerToString(bite);
		this.eatBiteResult = strBuild.append(eatInts)
				.append(Const.EAT).append(eatBites).append(Const.BITE).toString();

		if (eat == this.gameMaster.getDigit()) {
			return Numer0nDoubleEnum.ALLEAT.getOprionCd();
		} else {
			return Numer0nDoubleEnum.NONE_ALLEAT.getOprionCd();
		}
	}

	/**
	 * eatBiteの結果を返却
	 * @return eatBite値
	 */
	public String getEatBiteResult() {
		return eatBiteResult;
	}

}
