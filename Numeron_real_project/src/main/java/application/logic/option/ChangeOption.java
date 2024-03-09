package application.logic.option;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.stereotype.Service;

import application.component.anything.Anything;
import application.component.consts.Const;
import application.component.consts.Numer0nSelectNumberEnum;
import application.component.error.CreateErrorExceptionComponent;
import application.component.error.Numer0nUncontinuableException;
import application.logic.human.GameMaster;
import application.logic.option.map.ChangeOptionMapUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Changeアイテム使用クラス
 * 自分のナンバーの中から1つを選択し、その番号を手持ちのカードの中から交換できる。<br>
 * ただし、交換する桁の位置と、それがHIGH・LOWのどちらなのかを宣言する必要がある。<br>
 * EASY,NORMAL... 交換する番号はLOWナンバー同士・HIGHナンバー同士に限定され、桁の位置も教える。<br>
 * HARD... 交換する番号はLOW,HIGHナンバー同士に限定される（HIGHかLOWかは教える）が、交換された桁の位置は不明。<br>
 * EXHAUSTED... 交換する桁の位置のみ相手に教える。交換された数値がHIGHかLOWかは不明。<br>
 * INSANE... 交換すると言いながら交換しなくても良い（ただし交換しなかった場合でもアイテムは使用したものとする）。<br>
 * 交換した場合は、何も情報を教えない。
 * @author shiraishitoshio
 */
@Service
@RequiredArgsConstructor
public class ChangeOption {

	/**
	 * 小機能ID
	 */
	private static final String S_FUNC = "OPTION001";

	/**
	 * クラス名
	 */
	private static final String CLASS_NAME = ChangeOption.class.getSimpleName();

	/**
	 * GameMaster
	 */
	private final GameMaster gameMaster;

	/**
	 * ChangeOptionMapUtil
	 */
	private final ChangeOptionMapUtil mapUtil;

	/**
	 * CreateErrorExceptionComponent
	 */
	private final CreateErrorExceptionComponent exceptionComponent;

	/**
	 * Change確認名を一時保管
	 */
	@Getter
	private String chkMember;

	/**
	 * Change後の数値リスト
	 */
	private ArrayList<String> changedNumberList;

	/**
	 * 交換した数値の情報
	 */
	private String lh;

	/**
	 * 交換する桁
	 */
	private int digitInd;

	/**
	 * 交換する数値
	 */
	private String exNum;

	//	/**
	//	 * 数値のリストを手持ちの数字に置き換える。
	//	 * @param playerCPU プレーヤーorCPUどちらのリストを変更するか
	//	 * @param difficulty 難易度
	//	 * @param gameMaster GameMasterオブジェクト
	//	 */
	//	public ChangeOption(String playerCPU ,GameMaster gameMaster) {
	//		// 名前を一時保管
	//		this.mem = playerCPU;
	//		this.loggerComponent = null;
	//		this.gameMaster = gameMaster;
	//	}

	/**
	 * 交換フラグが立っている場合、任意の数字に交換する。（同一桁限定）<br>
	 * 難易度によって数値を変えられる桁も検討<br>
	 * EASY,NORMAL... 交換する番号はLOWナンバー同士・HIGHナンバー同士に限定され、桁の位置も教える。<br>
	 * HARD... 交換する番号はLOW,HIGHナンバー同士に限定されるが、交換された桁の位置は不明。<br>
	 * EXHAUSTED... 交換する桁の位置のみ相手に教える。交換された数値はHIGH同士、LOW同士とは限らない。<br>
	 * INSANE... 交換すると言いながら交換しなくても良い。交換する場合相手に何も情報が渡らない（ただし交換しなかった場合でもアイテムは使用したものとする）。
	 */
	public void changeLogic() {
		// 名前設定
		this.chkMember = this.gameMaster.getName();

		// メッセージ
		//		switch (gameMaster.getDifficulty()) {
		//			case Const.EASY:
		//			case Const.NORMAL:
		//				// メッセージ（CHANGEによる数字の選択（交換したか不明））
		//				lp.setLogParam(Const.CHANGE, 5,
		//						new ArrayList<String>(Arrays.asList(Const.GAMEMASTER)));
		//				break;
		//			case Const.HARD:
		//				// メッセージ（CHANGEによる数字の選択（交換したか不明））
		//				lp.setLogParam(Const.CHANGE, 7,
		//						new ArrayList<String>(Arrays.asList(Const.GAMEMASTER)));
		//				break;
		//			case Const.EXHAUSTED:
		//				// メッセージ（CHANGEによる数字の選択（交換したか不明））
		//				lp.setLogParam(Const.CHANGE, 9,
		//						new ArrayList<String>(Arrays.asList(Const.GAMEMASTER)));
		//				break;
		//			case Const.INSANE:
		//				// メッセージ（CHANGEによる数字の選択（交換したか不明））
		//				lp.setLogParam(Const.CHANGE, 11,
		//						new ArrayList<String>(Arrays.asList(Const.GAMEMASTER)));
		//				break;
		//		}

		// メッセージ（CHANGEによる数字の選択）
		//		lp.setLogParam(Const.CHANGE, 2,
		//				new ArrayList<String>(Arrays.asList(Const.GAMEMASTER, this.getMem())));

		// 交換する桁
		int digitInd = -1;
		// 交換した数値の情報
		String lh = "0";
		// 交換する数値
		String exNum = "0";
		// 交換後の文字列リスト
		ArrayList<String> changedNumberList = new ArrayList<String>();
		try {
			if (Const.CPU.equals(this.getChkMember())) {
				boolean changeFlag = false;
				if (Const.INSANE.equals(this.gameMaster.getDifficulty())) {
					// 交換するかどうか
					if (getDoChangeFlag()) {
						changeFlag = true;
					}

					//				// メッセージ（CHANGEによる数字の選択（交換したか不明））
					//				lp.setLogParam(Const.CHANGE, 4,
					//						new ArrayList<String>(Arrays.asList(Const.GAMEMASTER, this.getMem())));
					// 他の難易度は交換する
				} else {
					changeFlag = true;

					//				// メッセージ（CHANGEによる数字の選択）
					//				lp.setLogParam(Const.CHANGE, 3,
					//						new ArrayList<String>(Arrays.asList(Const.GAMEMASTER, this.getMem())));
				}

				changedNumberList = this.gameMaster.getCorrectCpuNumberList();
				// 交換する場合
				if (changeFlag) {
					// 現在勝負している桁数に応じて初期化（YUUSEN_MINUS→対象外の桁）
					if (this.gameMaster.getDigit() < 5) {
						this.mapUtil.addDigitPriorityMap(Const.FOUR_D, Const.YUUSEN_MINUS);
						if (this.gameMaster.getDigit() == 3) {
							this.mapUtil.addDigitPriorityMap(Const.THREE_D, Const.YUUSEN_MINUS);
						}
					}

					while (true) {
						// 交換する数字がHIGH同士かもしくはLOW同士かを管理
						boolean hLTf = false;
						// 交換する数値が同じでない（交換したことになっていない）、
						// もしくは交換した数字がすでに使われているかを管理
						boolean notSameTf = false;

						// 交換する数値選択（優先度未決定時）
						if (!this.mapUtil.containValueSelectNumberPriorityMap(Const.SAI_YUUSEN_FLAG)) {
							exNum = Anything.convertIntegerToString(
									new Random().nextInt(
											ChangeOptionMapUtil.CHANGE_SELECT_NUMBER_PRIORITY_MAP.size()));
							// 最優先フラグがついている数字を選択
						} else {
							exNum = this.mapUtil.getSelectNumberPriorityMap(Const.SAI_YUUSEN_FLAG);
						}

						// 交換する桁選択（優先度未決定時）
						if (!this.mapUtil.containValueDigitPriorityMap(Const.SAI_YUUSEN_FLAG)) {
							digitInd = new Random().nextInt(this.gameMaster.getDigit());
							// 最優先フラグがついている桁を選択
						} else {
							digitInd = this.mapUtil.getDigitPriorityMap(Const.SAI_YUUSEN_FLAG);
						}

						// 難易度に応じて数値チェック
						// EASY,NORMAL,HARD
						switch (this.gameMaster.getDifficulty()) {
						case Const.EASY:
						case Const.NORMAL:
						case Const.HARD:
							// 交換する数値がLOWナンバー同士・HIGHナンバー同士か
							hLTf = judgeExchangeSameHighLowNumber(
									exNum, changedNumberList.get(digitInd));

							// 交換する数値がすでに使われていた場合もしくは交換後の数値が同じ（交換したことになっていない）場合やり直し
							if (!(changedNumberList.get(digitInd).equals(exNum)
									|| changedNumberList.contains(exNum))) {
								notSameTf = true;
							}
							break;

						// EXHAUSTED
						case Const.EXHAUSTED:
							// 交換する数値がLOWナンバー同士・HIGHナンバー同士でなくても良い
							hLTf = true;

							// 交換する数値がすでに使われていた場合はやり直し
							if (!changedNumberList.contains(exNum)) {
								notSameTf = true;
							}
							break;

						// INSANE
						default:
							// LOWナンバー同士・HIGHナンバー同士でなく、交換する数値が被っていても良いためtrueに設定
							hLTf = true;
							notSameTf = true;
							break;
						}

						// 両方trueならbreak
						if (hLTf && notSameTf) {
							break;
						}
					}

					// 更新
					changedNumberList.set(digitInd, exNum);
					this.gameMaster.setCorrectCpuNumberList(changedNumberList);
					// 交換した数値がLowかHighかを判断
					lh = judgeLowHighNumber(exNum);
				}
				// PLAYERの場合
			} else {
				changedNumberList = this.gameMaster.getCorrectPlayerNumberList();
				// 交換するかどうかのメッセージとif文
				//			switch (gameMaster.getDigit()) {
				//				case 3:
				//					// メッセージ（選択肢3桁）
				//					lp.setLogParam(Const.CHANGE, 13,
				//							new ArrayList<String>(Arrays.asList(Const.GAMEMASTER)));
				//					break;
				//				case 4:
				//					// メッセージ（選択肢3桁）
				//					lp.setLogParam(Const.CHANGE, 14,
				//							new ArrayList<String>(Arrays.asList(Const.GAMEMASTER)));
				//					break;
				//				case 5:
				//					// メッセージ（選択肢3桁）
				//					lp.setLogParam(Const.CHANGE, 15,
				//							new ArrayList<String>(Arrays.asList(Const.GAMEMASTER)));
				//					break;
				//			}

				this.gameMaster.setCorrectPlayerNumberList(changedNumberList);
			}

			// HARDは桁の位置を教えないため、初期化
			if (Const.HARD.equals(this.gameMaster.getDifficulty())) {
				digitInd = -1;
			}

			// EXHAUSTEDは交換した数値の情報を教えないため、初期化
			if (Const.EXHAUSTED.equals(this.gameMaster.getDifficulty())) {
				lh = Const.NOT_CLEAR;
			}

			// INSANEは何も教えないため、全て初期化
			if (Const.INSANE.equals(this.gameMaster.getDifficulty())) {
				digitInd = -1;
				lh = Const.NOT_CLEAR;
			}

			this.digitInd = digitInd;
			this.lh = lh;
			this.exNum = exNum;
			this.changedNumberList = changedNumberList;
		} catch (Numer0nUncontinuableException e) {
			String fillChar1 = "交換する桁:" + digitInd;
			String fillChar2 = "交換した数値の情報:" + lh;
			String fillChar3 = "交換する数値:" + exNum;
			String fillChar4 = "Change後の数値リスト:" + changedNumberList;
			throw this.exceptionComponent.createOnlyFuncIdAndFillCharOtherDTONumer0nUncontinuableException(
					S_FUNC, e.getNumer0nErrDTO(), fillChar1, fillChar2, fillChar3, fillChar4);
		}

		// メッセージ
		//		switch (gameMaster.getDifficulty()) {
		//			case Const.EASY:
		//			case Const.NORMAL:
		//				// メッセージ（桁の位置、HIGHLOW）
		//				lp.setLogParam(Const.CHANGE, 6,
		//					new ArrayList<String>(Arrays.asList(Const.GAMEMASTER, this.getMem(),
		//							gameMaster.getAite(), digitsList[gameMaster.getDigit()-this.getDigitInd()],
		//							this.getLh())));
		//				break;
		//			case Const.HARD:
		//				// メッセージ（HIGHLOW）
		//				lp.setLogParam(Const.CHANGE, 8,
		//					new ArrayList<String>(Arrays.asList(Const.GAMEMASTER, this.getMem(),
		//							gameMaster.getAite(), this.getLh())));
		//				break;
		//			case Const.EXHAUSTED:
		//				// メッセージ（桁の位置）
		//				lp.setLogParam(Const.CHANGE, 10,
		//					new ArrayList<String>(Arrays.asList(Const.GAMEMASTER, this.getMem(),
		//							gameMaster.getAite(), digitsList[gameMaster.getDigit()-this.getDigitInd()])));
		//				break;
		//			case Const.INSANE:
		//				// メッセージ（情報なし）
		//				lp.setLogParam(Const.CHANGE, 12,
		//					new ArrayList<String>(Arrays.asList(Const.GAMEMASTER, this.getMem())));
		//				break;
		//		}

	}

	/**
	 * 交換するかしないかを判断するフラグ関数。基本的には、交換するかしないかをランダムで選ぶが、
	 * CPUがプレーヤーにナンバーを当てられそうな場合は優先的に交換する。
	 * @return <code>true,false</code> 交換する、しない
	 */
	private boolean getDoChangeFlag() {
		String ind;
		// 最優先フラグが設定されていなければ
		if (!this.mapUtil.containValueDoPriorityMap(Const.SAI_YUUSEN_FLAG)) {
			ind = Anything.convertIntegerToString(new Random().nextInt(
					ChangeOptionMapUtil.CHANGE_DO_PRIORITY_MAP.size()) + 1);
			// ランダム選択
		} else {
			ind = this.mapUtil.getDoPriorityMap(Const.SAI_YUUSEN_FLAG);
		}

		return (Const.CHANGE_GO.equals(ind))
				? true
				: false;
	}

	/**
	 * 交換する桁がHigh同士、Low同士か判断するメソッド
	 * @param exchangeNum 交換後の数字
	 * @param isExchangeNum 交換される数字
	 * @return tf HighもしくはLowか
	 * @throws Numer0nUncontinuableException Numer0n実行不可能例外
	 */
	private boolean judgeExchangeSameHighLowNumber(String exchangeNum, String isExchangeNum)
			throws Numer0nUncontinuableException {
		final String METHOD_NAME = "judgeExchangeSameDigitNumber";

		boolean tf = false;
		// どちらも0~4の間か
		Integer zero = Anything.convertStringToInteger(Numer0nSelectNumberEnum.ZERO.getNum());
		Integer four = Anything.convertStringToInteger(Numer0nSelectNumberEnum.FOUR.getNum());
		Integer five = Anything.convertStringToInteger(Numer0nSelectNumberEnum.FIVE.getNum());
		Integer nine = Anything.convertStringToInteger(Numer0nSelectNumberEnum.NINE.getNum());
		Integer exchangeNums = Integer.parseInt(exchangeNum);
		Integer isExchangeNums = Integer.parseInt(isExchangeNum);
		if ((0 <= exchangeNums.compareTo(zero) && 0 >= exchangeNums.compareTo(four)) &&
				(0 <= isExchangeNums.compareTo(zero) && 0 >= isExchangeNums.compareTo(four))) {
			tf = true;
			return tf;
		}
		// もしくはどちらも5~9の間か
		else if ((0 <= exchangeNums.compareTo(five) && 0 >= exchangeNums.compareTo(nine)) &&
				(0 <= isExchangeNums.compareTo(five) && 0 >= isExchangeNums.compareTo(nine))) {
			tf = true;
			return tf;
		}
		// 例外(マイナスや10以上で判断できない値が存在)
		else if ((0 >= exchangeNums.compareTo(zero) && 0 <= exchangeNums.compareTo(10)) ||
				(0 >= isExchangeNums.compareTo(zero) && 0 <= isExchangeNums.compareTo(10))) {
			throw this.exceptionComponent.createNumer0nUncontinuableException(null, CLASS_NAME, METHOD_NAME, null,
					"ERR_OPTION_01_1", null, null, null);
		}
		// High同士、Low同士でない
		else {
			return tf;
		}

	}

	/**
	 * 数字がLowかHighかを判断するメソッド
	 * @param num 任意の数字
	 * @return
	 * @throws Numer0nUncontinuableException Numer0n実行不可能例外
	 */
	private String judgeLowHighNumber(String num) throws Numer0nUncontinuableException {
		final String METHOD_NAME = "judgeLowHighNumber";

		Integer zero = Anything.convertStringToInteger(Numer0nSelectNumberEnum.ZERO.getNum());
		Integer four = Anything.convertStringToInteger(Numer0nSelectNumberEnum.FOUR.getNum());
		Integer five = Anything.convertStringToInteger(Numer0nSelectNumberEnum.FIVE.getNum());
		Integer nine = Anything.convertStringToInteger(Numer0nSelectNumberEnum.NINE.getNum());
		Integer nums = Integer.parseInt(num);
		if (0 <= nums.compareTo(zero) && 0 >= nums.compareTo(four)) {
			return Const.LOW;
		} else if (0 <= nums.compareTo(five) && 0 >= nums.compareTo(nine)) {
			return Const.HIGH;
		}

		throw this.exceptionComponent.createNumer0nUncontinuableException(null, CLASS_NAME, METHOD_NAME, null,
				"ERR_OPTION_01_2", null, null, null);
	}

	/**
	 * 交換後の数字を返却する
	 * @return changedNumberList
	 */
	public ArrayList<String> getChangedNumberList() {
		return changedNumberList;
	}

	/**
	 * indexを返却する
	 * @return digitInd index
	 */
	public int getDigitInd() {
		return digitInd;
	}

	/**
	 * 交換した数値の情報を返却する
	 * @return lh LoworHigh
	 */
	public String getLh() {
		return lh;
	}

	/**
	 * 交換する桁の情報を返却する
	 * @return exNum
	 */
	public String getExNum() {
		return exNum;
	}

}
