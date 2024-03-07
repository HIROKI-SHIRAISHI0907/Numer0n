package application.logic.info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import application.component.anything.Anything;
import application.component.consts.Const;
import application.logic.human.Computer;
import application.logic.human.GameMaster;
import application.logic.human.Player;
import application.logic.option.ChangeOption;
import application.logic.option.ShuffleOption;
import application.logic.option.TargetOption;

/**
 * 相手（プレーヤー）が得ている情報を元に、オプションを使用するかしないかを判断するクラス
 * 当クラスはHARD以上のレベルのみ使用する
 * オプションを使用するようにした場合、以下の優先順位をつける。
 * 1,SLASH
 * 2,HIGHLOW
 * 3,TARGET
 * 4,SHUFFLE（数字が当てられそうになった場合の使用1。）
 * 5,CHANGE（数字が当てられそうになった場合の使用2。SHUFFLEに比べて数字そのものを変えられるため、SHUFFLEよりも使用優先度は低い）
 * 6,DOUBLE（ある程度の相手の数字が絞られた場合の最終手段として使用。）
 * @author shiraishitoshio
 */
public class AiSpecifyOptionAndNextAction {

	AiSpecifyNumber aiSpecifyNumber;
	// info（プレーヤーの情報）
	private String playerInfoList;
	// プレーヤーの思い浮かべている数字
	private ArrayList<String> playerCandidateNumberList;
	// 数字
	public ArrayList<String> conditionMakeNumber = new ArrayList<String>();
	// 何がいくつがあるかをカウントするMAP
	private Map<String, Integer> countMap = new HashMap<String, Integer>() {
		{
			put(Const.ZERO, 0);
			put(Const.ONE, 0);
			put(Const.TWO, 0);
			put(Const.THREE, 0);
			put(Const.FOUR, 0);
			put(Const.FIVE, 0);
			put(Const.SIX, 0);
			put(Const.SEVEN, 0);
			put(Const.EIGHT, 0);
			put(Const.NINE, 0);
		}
	};
	// 攻撃オプション
	private String[] offenseList = {
		Const.DOUBLE,
		Const.HIGH_LOW,
		Const.TARGET,
		Const.SLASH
	};
	// 防御オプション
	private String[] diffenseList = {
		Const.SHUFFLE,
		Const.CHANGE
	};

	// シャッフルする
	private static final String SHUFFLE_GO = "1";

	// 交換する
	private static final String CHANGE_GO = "1";

	/**
	 * 情報セット用初期化クラス
	 * AiSpecifyNumber コンピュータ特定No.設定クラス
	 * info プレーヤーの得ている情報を取得するオブジェクト
	 * player プレーヤークラス
	 * info 情報クラス
	 * gameMaster ゲームマスタークラス
	 */
	public AiSpecifyOptionAndNextAction(Player player, Computer computer, Info info, GameMaster gameMaster) {
		aiSpecifyNumber = new AiSpecifyNumber(gameMaster, player, computer, null);
		this.playerInfoList = info.getPlayerInfoList().get(info.getPlayerInfoList().size()-1);
		this.playerCandidateNumberList = player.getCandidatePlayerNumberList();
	}

	/**
	 * プレーヤーの数字の理解度によってCPUが次ターンに行う行動について、そのオプションが選択されるようにフラグを設定する
	 * 設定事項
	 * 1, どのオプションを使うのか、使わないのか（itemAttackYuusenMap,itemDiffenseYuusenMap）<br>
	 * 2, TARGETを使用した時、何を指定するのか<br>
	 * 3, CHANGEを使用した時、どの桁を何に変えるのか（changeSelectNumberYuusenMap,changeDigitYuusenMap,changeDoYuusenMap, EXHAUSTED,INSANE用）<br>
	 * 4, SHUFFLEを使用した時、SHUFFLEするのかしないのか（shuffleFlagYuusenMap, EXHAUSTED,INSANE用）<br>
	 * 5, 数字をコールするとき、ランダムではなく、論理的に設定できるか<br>
	 * computer コンピュータクラス
	 */
	public void setOptionYuusenFlagLogic(GameMaster gameMaster, Computer computer, TargetOption targetOption, ShuffleOption shuffleOption, ChangeOption changeOption) {
		// 0EATnBITE(n>=2)の時、TARGETを使う（n個の数字が入っていることが確定しているため、場所を確定させる）
		if (Objects.nonNull(this.playerInfoList)) {
			ArrayList<String> infoList = Anything.splitComma(this.playerInfoList);
			if ((Const.DOUBLE.equals(infoList.get(0)) ||
				Const.NO_OPTION.equals(infoList.get(0))) &&
					Anything.convertIntegerToString(gameMaster.getDigit()-1).equals(
							infoList.get(infoList.size()-1).substring(Const.BITE_START_STRING, Const.BITE_START_STRING+1))) {
				computer.itemOffenseYuusenMap.replace(Const.TARGET, Const.SAI_YUUSEN_FLAG);
				// TARGETで指定する数字はplayerInfoListにある宣言したn桁の数字から選択
				for (int i = 1;i <= gameMaster.getDigit();i++) {
					targetOption.targetSelectNumberYuusenMap.replace(
							infoList.get(i), Const.SAI_YUUSEN_FLAG);
				}
			}
		}

		// プレーヤーの思い浮かべている数字が5個以下になってしまった時、SHUFFLE or CHANGEを使用する。（どういう候補が残っているかによる）
		// プレーヤーの思い浮かべている数字が空の場合も当分岐を満たすため、それは除外
		if (this.playerCandidateNumberList.size() != 0
				&& Const.Numer0n_PINCH_NUMBER >= this.playerCandidateNumberList.size()) {
			// 防御オプションに最優先フラグを設定
			if (computer.itemDiffenseYuusenMap.size() >= 2) {
				for (String diff: diffenseList) {
					computer.itemDiffenseYuusenMap.replace(diff, Const.SAI_YUUSEN_FLAG);
				}
				shuffleOption.shuffleFlagYuusenMap.replace(SHUFFLE_GO, Const.SAI_YUUSEN_FLAG);
				changeOption.changeDoYuusenMap.replace(CHANGE_GO, Const.SAI_YUUSEN_FLAG);
			}

			// 防御オプションは、攻撃オプションと並行して使用できないため、攻撃オプションの優先度を-1にして選ばれないようにする。
			if (computer.itemOffenseYuusenMap.size() != 0) {
				for (String off: offenseList) {
					computer.itemOffenseYuusenMap.replace(off, Const.YUUSEN_MINUS);
				}
			}
		}

		// 特定の2つの数字と桁がわかっているが、n20のようにnが複数考えられる場合、そのn-1個が入っていない数字をわざとコールする。
		// 4桁の場合、n230, 5桁の場合、n2450とし、複数とは4以上とする
		if (Objects.nonNull(this.playerCandidateNumberList)) {
			for (String num: this.playerCandidateNumberList) {
				this.countEachNumber(num);
			}

			// mapに入った値を調べ、サイズと同じ（全てがn20のような形）ものが桁数-1個ある場合
			int counts = 0;
			ArrayList<String> conList = new ArrayList<String>();
			for (String key: this.countMap.keySet()) {
				if (this.countMap.get(key) == this.playerCandidateNumberList.size()) {
					counts++;
					conList.add(key);
				}
			}

			String conStr = null;
			if (conList.size() > 0) {
				conList.add(Const.MAKE_OTHER_THESE_NUMBER);
				conStr = Anything.concatListToComma(conList);

				// n-1個が入っていない数字のリストを作成
				if (counts == gameMaster.getDigit()-1) {
					this.aiSpecifyNumber.arrangeCandidateNumberLogic(
						Const.MAKE_NUMBER_FLAG, conStr, gameMaster, null, null);
					computer.nextActionNumberList = this.aiSpecifyNumber.getConditionNumberList();
				}
			}
		}

	}

	/**
	 * 期待値の計算をする
	 */
	private void expectedValueCalc() {

	}

	/**
	 * 該当数字を渡し、何がいくつ使用されているかをカウントする。
	 * @param num 数字（文字列）
	 */
	private void countEachNumber(String num) {
		ArrayList<String> numList = Anything.convertNumberToArrayList(num);
		for (String nums: numList) {
			int counts = this.countMap.get(nums);
			this.countMap.replace(nums, counts+1);
		}
	}

	/**
	 * info（プレーヤーの情報）を返却する
	 * @return playerInfoList info（プレーヤーの情報）
	 */
	public String getPlayerInfoList() {
		return playerInfoList;
	}

	/**
	 * player（プレーヤーの思い浮かべている候補）を返却する
	 * @return playerCandidateNumberList（プレーヤーの情報）
	 */
	public ArrayList<String> getPlayerCandidateNumberList() {
		return playerCandidateNumberList;
	}

}
