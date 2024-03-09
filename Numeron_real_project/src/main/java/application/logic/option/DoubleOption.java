package application.logic.option;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.stereotype.Service;

import application.component.anything.Anything;
import application.component.consts.Const;
import application.logic.human.Computer;
import application.logic.human.GameMaster;
import application.logic.human.Player;
import application.logic.info.Numer0nInfo;
import application.logic.judge.Eatbite;
import application.logic.option.map.DoubleOptionMapUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Doubleアイテム使用クラス
 * 自分のターンに2連続で相手の番号をコールできる。
 * ただし、DOUBLEの前に代償として自分の番号を1つ開示しなければならず、しかもどの桁を開示するかは相手に指定される。
 * また、使用した回を含めた2回のコールで1ターンとみなすため､2回目のコール時は双方ともアイテムを使用できない｡
 * @author shiraishitoshio
 */
@Service
@RequiredArgsConstructor
public class DoubleOption {

	/**
	 * 小機能ID
	 */
	private static final String S_FUNC = "OPTION002";

	/**
	 * クラス名
	 */
	private static final String CLASS_NAME = DoubleOption.class.getSimpleName();

	/**
	 * 3EAT（桁が増える場合ことを想定してALL_EATとする）の場合
	 */
	public static final int ALL_EAT = 0;

	/**
	 * 連続コールが外れた場合
	 */
	public static final int TEACH_NUMBER = 1;

	/**
	 * GameMaster
	 */
	private final GameMaster gameMaster;

	/**
	 * DoubleOptionMapUtil
	 */
	private final DoubleOptionMapUtil mapUtil;

	/**
	 * Computer
	 */
	private final Computer computer;

	/**
	 * Player
	 */
	private final Player player;

	/**
	 * EatBite
	 */
	private final Eatbite eatBite;

	/**
	 * Info
	 */
	private final Numer0nInfo info;

	/**
	 * Double確認名を一時保管
	 */
	@Getter
	private String chkMember;

	/**
	 * 連続コールが外れた時のインデックス
	 */
	private int doubleDigit;

	/**
	 * 連続コールが外れた時の数字
	 */
	private String doubleNum;

	/**
	 * Doubleコールクラス
	 */
	public int doubleLogic() {
		// 名前設定
		this.chkMember = this.gameMaster.getName();

		// 現在勝負している桁数に応じて初期化（YUUSEN_MINUS→対象外の桁）
		if (this.gameMaster.getDigit() < 5) {
			this.mapUtil.addDigitPriorityMap(Const.FOUR_D, Const.YUUSEN_MINUS);
			if (this.gameMaster.getDigit() == 3) {
				this.mapUtil.addDigitPriorityMap(Const.THREE_D, Const.YUUSEN_MINUS);
			}
		}

		// 連続コールが外れた時のインデックス
		int doubleDigit = 0;
		// 連続コールが外れた時の数字
		String doubleNum = "-1";

		// 先に任意の位の数字を宣言。教える桁と数字は相手に指定される
		if (Const.CPU.equals(this.getChkMember())) {
			// 教える桁メッセージ（0or1or2(or3or4)）
			doubleDigit = 0;
			// 教える数字
			doubleNum = this.gameMaster.getCorrectCpuNumberList().get(this.doubleDigit);
		} else {
			// 教える桁（最優先フラグが含まれているか）
			if (!this.mapUtil.containValueDigitPriorityMap(Const.SAI_YUUSEN_FLAG)) {
				doubleDigit = new Random().nextInt(
						this.gameMaster.getDigit());
			} else {
				doubleDigit = this.mapUtil.getDigitPriorityMap(Const.SAI_YUUSEN_FLAG);
			}
			// 教える数字
			doubleNum = this.gameMaster.getCorrectPlayerNumberList().get(doubleDigit);
		}

		this.doubleDigit = doubleDigit;
		this.doubleNum = doubleNum;

		// 2連続で数字を宣言する
		int loop = 1;
		do {
			ArrayList<String> callNumberList;
			ArrayList<String> correctNumberList;
			// CPUの場合、任意の数字をコール
			if (Const.CPU.equals(this.getChkMember())) {
				this.computer.doCallNumber();
				callNumberList = this.computer.getCallNumber();
				correctNumberList = this.gameMaster.getCorrectPlayerNumberList();
			} else {
				this.player.doCallNumber();
				callNumberList = this.player.getCallNumber();
				correctNumberList = this.gameMaster.getCorrectCpuNumberList();
			}

			// EatBite判定
			String eatBiteResult = this.eatBite.judgeEatBite(callNumberList, correctNumberList);

			// CPUかplayerによって得られる情報を格納
			if (Const.CPU.equals(this.getChkMember())) {
				this.info.addCpuInfoList(Anything.concatStringToComma(
						Const.DOUBLE, Anything.convertListToString(callNumberList),
						this.eatBite.getEatBiteResult()));
			} else {
				this.info.addPlayerInfoList(Anything.concatStringToComma(
						Const.DOUBLE, Anything.convertListToString(callNumberList),
						this.eatBite.getEatBiteResult()));
			}

			// 2回のコールのうちに正解したらbreak
			if (Eatbite.ALL_EAT.equals(eatBiteResult)) {
				return ALL_EAT;
			}

			// 候補の数字から宣言した時、重複して宣言しないように、1度宣言した数字は候補リストから削除。
			if (Const.CPU.equals(this.getChkMember()) &&
					!this.computer.getCandidateCpuNumberList().isEmpty() &&
					this.computer.getCandidateCpuNumberList().contains(
							Anything.concatListToComma(callNumberList))) {
				this.computer.getCandidateCpuNumberList().remove(
						Anything.concatListToComma(this.computer.getCallNumber()));
			} else if (!this.player.getCandidatePlayerNumberList().isEmpty() &&
					this.player.getCandidatePlayerNumberList().contains(
							Anything.concatListToComma(callNumberList))) {
				this.player.getCandidatePlayerNumberList().remove(
						Anything.concatListToComma(this.player.getCallNumber()));
			}

			// 2回のコールで当てられなかった場合、コールされた側に事前に教えてもらった桁and数値情報を格納し、宣言した数値にも格納
			if (loop == 2) {
				if (Const.CPU.equals(this.getChkMember())) {
					this.info.addPlayerInfoList(Anything.concatStringToComma(
							Const.DOUBLE, Anything.convertIntegerToString(getDoubleDigit()),
							getDoubleNum()));
					// 情報の大きさを合わせるためハイフンを格納
					this.info.addPlayerInfoList(Anything.concatStringToComma(
							Const.DOUBLE, Const.HYPHEN, Const.HYPHEN));
				} else {
					this.info.addCpuInfoList(Anything.concatStringToComma(
							Const.DOUBLE, Anything.convertIntegerToString(getDoubleDigit()),
							getDoubleNum()));
					// 情報の大きさを合わせるためハイフンを格納
					this.info.addCpuInfoList(Anything.concatStringToComma(
							Const.DOUBLE, Const.HYPHEN, Const.HYPHEN));
				}
			}

			// 2回目コールのためのindex
			loop += 1;
		} while (loop < 3);

		return TEACH_NUMBER;
	}

	/**
	 * 連続コールが外れた時のインデックスを返却する
	 * @return doubleDigit 連続コールが外れた時のインデックス
	 */
	public int getDoubleDigit() {
		return doubleDigit;
	}

	/**
	 * 連続コールが外れた時の数字を返却する
	 * @return doubleNum 連続コールが外れた時の数字
	 */
	public String getDoubleNum() {
		return doubleNum;
	}

}
