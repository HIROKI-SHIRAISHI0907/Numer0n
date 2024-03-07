package application.logic.judge;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import application.component.anything.Anything;
import application.component.consts.Const;
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
	 * 3EAT（桁が増える場合ことを想定してALL_EATとする）の場合
	 */
	public static final String ALL_EAT = "ALLEAT";

	/**
	 * 3EAT（桁が増える場合ことを想定してALL_EATとする）以外の場合
	 */
	public static final String NONE_ALL_EAT = "NONEALLEAT";

	/**
	 * ?EAT?BITEを格納する文字列
	 */
	private String eatBiteResult;

//	/**
//	 * EatBiteクラス
//	 * @param judgeNumberList 宣言した数字
//	 * @param playerCPUName プレーヤーorCPU名
//	 * @param gameMaster ゲームマスタークラス
//	 */
//	public Eatbite(ArrayList<String> judgeNumberList, String playerCPUName, GameMaster gameMaster) {
//
//		// 宣言する数字を格納
//		this.judgeNumberList = judgeNumberList;
//
//		// CPU：宣言、Playerの数字と照合
//		if (Const.CPU.equals(playerCPUName)) {
//			this.isJudgedNumberList = gameMaster.getCorrectPlayerNumberList();
//		// Player：宣言、CPUの数字と照合
//		} else {
//			this.isJudgedNumberList = gameMaster.getCorrectCpuNumberList();
//		}
//	}

	/**
	 * EAT,BITEを判断する
	 * @param callNumber コールする側の数字
	 * @param correctNumber 正しい数字(判断される側の数字)
	 */
	public String judgeEatBite(ArrayList<String> callNumber, ArrayList<String> correctNumber) {
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
			return ALL_EAT;
		} else {
			return NONE_ALL_EAT;
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
