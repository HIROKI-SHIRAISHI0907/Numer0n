package application.logic.info;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import application.component.consts.Const;
import application.logic.human.Player;
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

		setDoubleOptionMap();
		setChangeOptionMap();
		setShuffleOptionMap();
		setTargetOptionMap();

	}

	/**
	 * DOUBLEオプション内に所有しているMapにフラグを設定する
	 */
	private void setDoubleOptionMap() {
		// 初期化
		this.doubleMapUtil.clearDigitPriorityMap();
	}

	/**
	 * CHANGEオプション内に所有しているMapにフラグを設定する
	 */
	private void setChangeOptionMap() {
		// 初期化
		this.changeMapUtil.clearDoPriorityMap();
		this.changeMapUtil.clearDigitPriorityMap();
		this.changeMapUtil.clearSelectNumberPriorityMap();
	}

	/**
	 * SHUFFLEオプション内に所有しているMapにフラグを設定する
	 */
	private void setShuffleOptionMap() {
		// 初期化
		this.shuffleMapUtil.clearDoPriorityMap();

		// プレーヤーの候補の数字が任意の個数個以下ならシャッフル
		if (Const.Numer0n_PINCH_NUMBER >=
				this.player.getCandidatePlayerNumberList().size()) {
			this.shuffleMapUtil.addDoPriorityMap(Const.SHUFFLE_GO, Const.SAI_YUUSEN_FLAG);
		}
	}

	/**
	 * TARGETオプション内に所有しているMapにフラグを設定する
	 */
	private void setTargetOptionMap() {
		// 初期化
		this.targetMapUtil.clearSelectNumberPriorityMap();
	}

}
