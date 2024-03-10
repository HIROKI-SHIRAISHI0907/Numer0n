package application.logic.option;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

import org.springframework.stereotype.Service;

import application.component.anything.Anything;
import application.component.consts.Const;
import application.component.consts.DifficultyConst;
import application.component.consts.Numer0nShuffleEnum;
import application.component.error.CreateErrorExceptionComponent;
import application.component.error.Numer0nUncontinuableException;
import application.logic.human.GameMaster;
import application.logic.option.map.ShuffleOptionMapUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Shuffleアイテム使用クラス<br>
 * 自分が設定した番号カードを並べ替えて、新たな番号にすることができる。<br>
 * たとえ「3BITE」や「1EAT-2BITE」のようにピンチの状態でも相手を混乱させることができる。<br>
 * またHIGH&LOW、DOUBLE、TARGETおよびCHANGEで開示された自分の番号の情報を隠すこともできる。<br>
 * EASY,NORMAL... 必ずシャッフルする必要がある。<br>
 * HARD,EXHAUSTED,INSANE... 並べ替えたと見せかけてそのままにするフェイントも可能。
 * @author shiraishitoshio
 */
@Service
@RequiredArgsConstructor
public class ShuffleOption {

	/**
	 * 小機能ID
	 */
	private static final String S_FUNC = "OPTION004";

	/**
	 * クラス名
	 */
	private static final String CLASS_NAME = ShuffleOption.class.getSimpleName();

	/**
	 * GameMaster
	 */
	private final GameMaster gameMaster;

	/**
	 * ShuffleOptionMapUtil
	 */
	private final ShuffleOptionMapUtil mapUtil;

	/**
	 * CreateErrorExceptionComponent
	 */
	private final CreateErrorExceptionComponent exceptionComponent;

	/**
	 * シャッフル確認名を一時保管
	 */
	@Getter
	private String chkMember;

	/**
	 * シャッフル結果格納用
	 */
	private ArrayList<String> shuffleNumberList;

	/**
	 * シャッフルするフラグが立った場合（CPU）、シャッフルする。
	 */
	public void shuffleLogic() {
		// 名前設定
		this.chkMember = this.gameMaster.getName();

		ArrayList<String> shuffleNumberList = new ArrayList<String>();

		try {
			if (Const.CPU.equals(this.getChkMember())) {
				shuffleNumberList = this.gameMaster.getCorrectCpuNumberList();
				// 数字チェック
				chkListParameter(shuffleNumberList);

				switch (this.gameMaster.getDifficulty()) {
				case DifficultyConst.EASY:
				case DifficultyConst.NORMAL:
					shuffleNumberList = doShuffle(shuffleNumberList);
					break;
				case DifficultyConst.HARD:
				case DifficultyConst.EXHAUSTED:
					// 必ずシャッフルされるとは限らない
					Collections.shuffle(shuffleNumberList);
					break;
				default:
					// シャッフルするかどうか
					if (getDoShuffleFlag()) {
						shuffleNumberList = doShuffle(shuffleNumberList);
					}
					break;
				}
				this.gameMaster.setCorrectCpuNumberList(shuffleNumberList);
			} else {
				shuffleNumberList = this.gameMaster.getCorrectPlayerNumberList();
				// 数字チェック
				chkListParameter(shuffleNumberList);

				this.gameMaster.setCorrectPlayerNumberList(shuffleNumberList);
			}

			this.shuffleNumberList = shuffleNumberList;
		} catch (Numer0nUncontinuableException e) {
			throw this.exceptionComponent.createOnlyFuncIdAndFillCharOtherDTONumer0nUncontinuableException(
					S_FUNC, e.getNumer0nErrDTO(), "シャッフル後の値:" + shuffleNumberList);
		}

	}

	/**
	 * シャッフルするかしないかを判断するフラグ関数。基本的には、シャッフルするかしないかをランダムで選ぶが、
	 * CPUがプレーヤーにナンバーを当てられそうな場合は優先的にシャッフルする。
	 * @return <code>true,false</code> シャッフルする、しない
	 */
	private boolean getDoShuffleFlag() {
		// シャッフルする優先フラグ何も立っていない場合、ランダムでシャッフルするか選ぶ
		String ind;
		if (!this.mapUtil.containValueDoPriorityMap(Const.SAI_YUUSEN_FLAG)) {
			ind = Anything.convertIntegerToString(new Random().nextInt(
					ShuffleOptionMapUtil.SHUFFLE_DO_PRIORITY_MAP.size()) + 1);
		} else {
			ind = this.mapUtil.getDoPriorityMap(Const.SAI_YUUSEN_FLAG);
		}

		return (Numer0nShuffleEnum.SHUFFLE_GO.getOprionCd().equals(ind))
				? true
				: false;
	}

	/**
	 * シャッフルする<br>
	 * <p>
	 * シャッフル前と同一文字列になった場合やり直し
	 * </p>
	 * @param shuffleNumberList シャッフル前のリスト
	 * @return シャッフル前とは異なる順番のリスト
	 */
	private ArrayList<String> doShuffle(ArrayList<String> shuffleNumberList) {
		// return用
		ArrayList<String> returnList = new ArrayList<String>();

		while (true) {
			// ランダムな文字を選ぶ
			Integer number = new Random().nextInt(10);
			String numberStr = Anything.convertIntegerToString(number);
			// 数字が被らないように登録(ただしshuffleNumberListに入っている数字であること)
			boolean nextFlg = false;
			if (!returnList.contains(numberStr)
					&& shuffleNumberList.contains(numberStr)) {
				nextFlg = true;
			}

			if (nextFlg) {
				returnList.add(numberStr);

				boolean contiFlg = false;
				// 桁数分入れ終わったら比較
				if (returnList.size() == this.gameMaster.getDigit()) {
					if (!Objects.equals(shuffleNumberList, returnList)) {
						// ランダムになっていたらtrue
						contiFlg = true;
					} else {
						returnList.clear();
					}
				}

				if (contiFlg) {
					break;
				}
			}
		}

		return returnList;

	}

	/**
	 * 余計な数字が入っていないか確認する
	 * @param shuffleNumberList
	 */
	private boolean chkListParameter(ArrayList<String> shuffleNumberList) {
		final String METHOD_NAME = "chkListParameter";

		for (String num : shuffleNumberList) {
			if (Anything.convertStringToInteger(num) < 0 ||
					Anything.convertStringToInteger(num) >= 10) {
				throw this.exceptionComponent.createNumer0nUncontinuableException(null, CLASS_NAME, METHOD_NAME, null,
						"ERR_OPTION_04", null, null, null);
			}
		}
		return true;
	}

	/**
	 * シャッフル結果を返却する
	 * @return shuffleNumberList シャッフル結果
	 */
	public ArrayList<String> getShuffleNumberList() {
		return shuffleNumberList;
	}
}
