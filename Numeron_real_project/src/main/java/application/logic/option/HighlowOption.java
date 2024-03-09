package application.logic.option;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import application.component.consts.Const;
import application.component.consts.Numer0nSelectNumberEnum;
import application.component.error.CreateErrorExceptionComponent;
import application.logic.human.GameMaster;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * High&Lowアイテム使用クラス（小機能ID:OPTION004）<br>
 * 相手の全ての桁の数字が、それぞれ「HIGH (5-9)」「LOW (0-4)」のどちらかを知ることができる。
 * EASY,NORMAL,HARD,EXHAUSTED,INSANE... 「HIGH (5-9)」「LOW (0-4)」のどちらかを知ることができる。
 * @author shiraishitoshio
 */
@Service
@RequiredArgsConstructor
public class HighlowOption {

	/**
	 * 小機能ID
	 */
	private static final String S_FUNC = "OPTION003";

	/**
	 * クラス名
	 */
	private static final String CLASS_NAME = HighlowOption.class.getSimpleName();

	/**
	 * GameMaster
	 */
	private final GameMaster gameMaster;

	/**
	 * CreateErrorExceptionComponent
	 */
	private final CreateErrorExceptionComponent exceptionComponent;

	/**
	 * HighLow確認名を一時保管
	 */
	@Getter
	private String chkMember;

	/**
	 * HighLowの結果格納用
	 */
	private ArrayList<String> highLowList;

	//	/**
	//	 * 0〜4ならLOW、5~9ならHIGHを返す
	//	 * @param playerCPU playerorCPU名
	//	 * @param difficulty 難易度
	//	 * @param gameMaster GameMasterオブジェクト
	//	 */
	//	public HighlowOption(String playerCPU, GameMaster gameMaster) {
	//		// 名前を一時保管
	//		this.mem = playerCPU;
	//		this.gameMaster = gameMaster;
	//	}

	/**
	 * highLowクラス
	 * @return
	 */
	public void highLowLogic() {
		final String METHOD_NAME = "highLowLogic";

		// 名前設定
		this.chkMember = this.gameMaster.getName();

		ArrayList<String> numList;
		if (Const.CPU.equals(this.getChkMember())) {
			numList = this.gameMaster.getCorrectPlayerNumberList();
		} else {
			numList = this.gameMaster.getCorrectCpuNumberList();
		}

		ArrayList<String> hlList = new ArrayList<String>();
		for (int i = 0; i < numList.size(); i++) {
			String hl = null;
			Integer num = Integer.parseInt(numList.get(i));
			if (0 <= num.compareTo(Integer.parseInt(Numer0nSelectNumberEnum.ZERO.getNum())) && 0 >= num.compareTo(Integer.parseInt(Numer0nSelectNumberEnum.FOUR.getNum()))) {
				hl = Const.LOW;
			} else if (0 <= num.compareTo(Integer.parseInt(Numer0nSelectNumberEnum.FIVE.getNum()))
					&& 0 >= num.compareTo(Integer.parseInt(Numer0nSelectNumberEnum.NINE.getNum()))) {
				hl = Const.HIGH;
			} else {
				throw this.exceptionComponent.createNumer0nUncontinuableException(S_FUNC, CLASS_NAME, METHOD_NAME, null,
						"ERR_OPTION_04", null, null, null, numList.get(i));
			}
			hlList.add(hl);
		}
		this.highLowList = hlList;

		//		// メッセージ（HIGH内訳前）
		//		lp.setLogParam(Const.HIGH_LOW, 2,
		//					new ArrayList<String>(Arrays.asList(Const.GAMEMASTER, gameMaster.getAite())));
		//
		//		// メッセージ（HIGH内訳）
		//		lp.setLogParam(Const.HIGH_LOW, 3,
		//					new ArrayList<String>(Arrays.asList(Const.GAMEMASTER,
		//							Anything.concatListToComma(this.getHighLowList()))));
	}

	/**
	 * HighLowの結果を返却する
	 * @return highLowList HighLowの結果
	 */
	public ArrayList<String> getHighLowList() {
		return highLowList;
	}

}
