package application.logic.option;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.stereotype.Service;

import application.component.anything.Anything;
import application.component.consts.Const;
import application.component.consts.DifficultyConst;
import application.logic.human.GameMaster;
import application.logic.option.map.TargetOptionMapUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Targetアイテム使用クラス
 * 10種類の番号のうち1つを指定して、相手にその数字を使用しているか否かを訊くことができる。<br>
 * その番号が相手の組み合わせに含まれている場合は、どの桁に入っているかも判明する。<br>
 * EASY,NORMAL,HARD... その番号が相手の組み合わせに含まれている場合は、どの桁に入っているかも判明する。<br>
 * EXHAUSTED,INSANE... 含まれているかいないかのみ判明する。
 * @author shiraishitoshio
 */
@Service
@RequiredArgsConstructor
public class TargetOption {

	/**
	 * 小機能ID
	 */
	private static final String S_FUNC = "OPTION006";

	/**
	 * クラス名
	 */
	private static final String CLASS_NAME = TargetOption.class.getSimpleName();

	/**
	 * 存在する
	 */
	public static final String EXIST_LIST_OF_NUMBER = "EXISTLISTOFNUMBER";

	/**
	 * 存在しない
	 */
	public static final String NONE_EXIST_LIST_OF_NUMBER = "NONEEXISTLISTOFNUMBER";

	/**
	 * GameMaster
	 */
	private final GameMaster gameMaster;

	/**
	 * TargetOptionMapUtil
	 */
	private final TargetOptionMapUtil mapUtil;

	/**
	 * HighLow確認名を一時保管
	 */
	@Getter
	private String chkMember;

	/**
	 * 交換する数値
	 */
	private String exNum;

	/**
	 * 存在した場合のindex
	 */
	private int existsInd;

	/**
	 * Targetクラス。指定した数字が存在するかどうか判定するクラス
	 * @param playerCPU プレーヤーorCPU名
	 * @param gameMaster GameMasterオブジェクト
	 */
	public String targetLogic() {
		// 名前設定
		this.chkMember = this.gameMaster.getName();

		// 存在した場合のindex
		int existsInd = -1;
		// 交換する数値
		String exNum = "-1";
		ArrayList<String> tarNumberList = null;
		if (Const.CPU.equals(this.getChkMember())) {
			tarNumberList = this.gameMaster.getCorrectPlayerNumberList();
			// 存在するかどうか判断するのに使用する数字
			// 最優先フラグがあれば
			if (!this.mapUtil.containValueSelectNumberPriorityMap(Const.SAI_YUUSEN_FLAG)) {
				exNum = Anything.convertIntegerToString(
						new Random().nextInt(
								TargetOptionMapUtil.TARGET_SELECT_NUMBER_PRIORITY_MAP.size()));
			} else {
				exNum = this.mapUtil.getSelectNumberPriorityMap(Const.SAI_YUUSEN_FLAG);
			}
		} else {
			tarNumberList = this.gameMaster.getCorrectCpuNumberList();
			// 数字を選択するメッセージを入力
			exNum = "5";
		}

		// 存在するか（indexが取得できるか）
		existsInd = tarNumberList.indexOf(exNum);

		// 存在した場合、その桁をセット
		String result = NONE_EXIST_LIST_OF_NUMBER;
		if (existsInd > -1) {
			result = EXIST_LIST_OF_NUMBER;

			// EXHAUSTED,INSANEは-1(初期化)に戻す
			switch (this.gameMaster.getDifficulty()) {
			case DifficultyConst.EXHAUSTED:
			case DifficultyConst.INSANE:
				existsInd = -1;
				break;
			}
			// 存在しない場合
		} else {
		}

		this.existsInd = existsInd;
		this.exNum = exNum;
		return result;
	}

	/**
	 * 存在するかどうか判断するのに使用する数字を返却する
	 * @return exNum 存在するかどうか判断するのに使用する数字
	 */
	public String getExNum() {
		return exNum;
	}

	/**
	 * 存在した場合の桁を返却する
	 * @return existsInd 存在した場合の桁
	 */
	public int getExistsInd() {
		return existsInd;
	}

}
