package application.logic.info;

import org.springframework.stereotype.Service;

import application.component.consts.Const;
import application.component.consts.Numer0nOptionEnum;
import application.component.consts.Numer0nShuffleEnum;
import application.component.consts.PriorityFlagConst;
import application.logic.human.Player;
import application.logic.human.gameComponent.GameComponentMapUtil;
import application.logic.option.map.ChangeOptionMapUtil;
import application.logic.option.map.DoubleOptionMapUtil;
import application.logic.option.map.ShuffleOptionMapUtil;
import application.logic.option.map.TargetOptionMapUtil;
import lombok.RequiredArgsConstructor;

/**
 * 相手（プレーヤー）が得ている情報を元に、優先フラグ等のフラグを設定するクラス<br>
 * 基本的にはランダムでオプションなどを選ぶが、優先フラグなどがついている場合はそこから選ぶようにする。<br>
 * よりゲームさを出すためのクラス<br>
 * [基本前提条件]<br>
 * 当クラスはHARD以上のレベルのみ使用する
 * <p>
 * オプションを使用するようにした場合、以下の優先順位をつける。
 * 1,SLASH
 * 2,HIGHLOW
 * 3,TARGET
 * 4,SHUFFLE（数字が当てられそうになった場合の使用1。）
 * 5,CHANGE（数字が当てられそうになった場合の使用2。SHUFFLEに比べて数字そのものを変えられるため、SHUFFLEよりも使用優先度は低い）
 * 6,DOUBLE（ある程度の相手の数字が絞られた場合の最終手段として使用。）
 * </p>
 * @author shiraishitoshio
 */
@Service
@RequiredArgsConstructor
public class Numer0nNextActionSetFlagOnMapImpl implements Numer0nNextActionSetFlagOnMap {

	/**
	 * Player
	 */
	private final Player player;

	/**
	 * GameComponentMapUtil
	 */
	private final GameComponentMapUtil gameMapUtil;

	/**
	 * DoubleOptionMapUtil
	 */
	private final DoubleOptionMapUtil doubleMapUtil;

	/**
	 * ChangeOptionMapUtil
	 */
	private final ChangeOptionMapUtil changeMapUtil;

	/**
	 * ShuffleOptionMapUtil
	 */
	private final ShuffleOptionMapUtil shuffleMapUtil;

	/**
	 * TargetOptionMapUtil
	 */
	private final TargetOptionMapUtil targetMapUtil;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setFlagLogic() {
		clearCpuOptionMap();
		setDoubleOptionMap();
		setChangeOptionMap();
		setShuffleOptionMap();
		setTargetOptionMap();
	}

	/**
	 * CPUが所有しているオプションをクリアする
	 */
	private void clearCpuOptionMap() {
		this.gameMapUtil.clearOffenseOptionMap();
		this.gameMapUtil.clearDiffenseOptionMap();
	}

	/**
	 * DOUBLEオプション内に所有しているMapにフラグを設定する
	 */
	private void setDoubleOptionMap() {
		// CPUのオプションにフラグ設定
		this.gameMapUtil.alterOffenseOptionMap(
				Numer0nOptionEnum.DOUBLE.getOprionName(), PriorityFlagConst.SAI_YUUSEN_FLAG);

		// 初期化
		this.doubleMapUtil.clearDigitPriorityMap();
	}

	/**
	 * CHANGEオプション内に所有しているMapにフラグを設定する
	 */
	private void setChangeOptionMap() {
		// CPUのオプションにフラグ設定
		this.gameMapUtil.alterDiffenseOptionMap(
				Numer0nOptionEnum.CHANGE.getOprionName(), PriorityFlagConst.SAI_YUUSEN_FLAG);

		// 初期化
		this.changeMapUtil.clearDoPriorityMap();
		this.changeMapUtil.clearDigitPriorityMap();
		this.changeMapUtil.clearSelectNumberPriorityMap();
	}

	/**
	 * SHUFFLEオプション内に所有しているMapにフラグを設定する
	 */
	private void setShuffleOptionMap() {
		// CPUのオプションにフラグ設定
		this.gameMapUtil.alterDiffenseOptionMap(
				Numer0nOptionEnum.SHUFFLE.getOprionName(), PriorityFlagConst.SAI_YUUSEN_FLAG);

		// 初期化
		this.shuffleMapUtil.clearDoPriorityMap();

		// プレーヤーの候補の数字が任意の個数個以下ならシャッフル
		if (Const.Numer0n_PINCH_NUMBER >= this.player.getCandidatePlayerNumberList().size()) {
			this.shuffleMapUtil.addDoPriorityMap(
					Numer0nShuffleEnum.SHUFFLE_GO.getOprionCd(),
					PriorityFlagConst.SAI_YUUSEN_FLAG);
		}
	}

	/**
	 * TARGETオプション内に所有しているMapにフラグを設定する
	 */
	private void setTargetOptionMap() {
		// CPUのオプションにフラグ設定
		this.gameMapUtil.alterOffenseOptionMap(
				Numer0nOptionEnum.TARGET.getOprionName(), PriorityFlagConst.SAI_YUUSEN_FLAG);

		// 初期化
		this.targetMapUtil.clearSelectNumberPriorityMap();
	}

}
