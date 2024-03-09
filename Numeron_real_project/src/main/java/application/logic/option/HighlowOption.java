package application.logic.option;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import application.component.anything.Anything;
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
			Integer num = Anything.convertStringToInteger(numList.get(i));
//			System.out.println(numList.get(i));
//			System.out.println(num);
//			System.out.println(num < 0);
//			System.out.println(num >= 10);
//			System.out.println(!numList.get(i).matches("[+-]?\\d*(\\.\\d+)?"));

			if (num < 0 || num >= 10 || !numList.get(i).matches("[+-]?\\d*(\\.\\d+)?")) {
				throw this.exceptionComponent.createNumer0nUncontinuableException(S_FUNC, CLASS_NAME, METHOD_NAME, null,
						"ERR_OPTION_04", null, null, null, numList.get(i));
			}

			if (0 <= num.compareTo(Integer.parseInt(Numer0nSelectNumberEnum.ZERO.getNum())) &&
					0 >= num.compareTo(Integer.parseInt(Numer0nSelectNumberEnum.FOUR.getNum()))) {
				hl = Const.LOW;
			} else if (0 <= num.compareTo(Integer.parseInt(Numer0nSelectNumberEnum.FIVE.getNum()))
					&& 0 >= num.compareTo(Integer.parseInt(Numer0nSelectNumberEnum.NINE.getNum()))) {
				hl = Const.HIGH;
			}
			hlList.add(hl);
		}
		this.highLowList = hlList;
	}

	/**
	 * HighLowの結果を返却する
	 * @return highLowList HighLowの結果
	 */
	public ArrayList<String> getHighLowList() {
		return highLowList;
	}

}
