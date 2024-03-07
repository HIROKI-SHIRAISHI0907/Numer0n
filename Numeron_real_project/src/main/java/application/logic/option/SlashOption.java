package application.logic.option;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.stereotype.Service;

import application.component.consts.Const;
import application.component.error.CreateErrorExceptionComponent;
import application.logic.human.GameMaster;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Slashアイテム使用クラス（小機能ID:OPTION005）<br>
 * 相手が使っているナンバーの最大数から最小数を引いた「スラッシュナンバー」を訊くことができる。<br>
 * 例として「634」ならば、最大数「6」-最小数「3」=スラッシュナンバー「3」となる。<br>
 * もしスラッシュナンバーが「9」だった場合、最大数「9」・最小数「0」での可能性しかないため、0と9の使用が確定する。<br>
 * また、「2」だった場合は必然的に「012」「123」のような連続した3種類の数字で構成されることがわかる。<br>
 * EASY,NORMAL,HARD,EXHAUSTED,INSANE... 最大数から最小数を引いた「スラッシュナンバー」を訊くことができる。
 * @author shiraishitoshio
 */
@Service
@RequiredArgsConstructor
public class SlashOption {

	/**
	 * 小機能ID
	 */
	private static final String S_FUNC = "OPTION005";

	/**
	 * クラス名
	 */
	private static final String CLASS_NAME = SlashOption.class.getSimpleName();

	/**
	 * GameMaster
	 */
	private final GameMaster gameMaster;

	/**
	 * CreateErrorExceptionComponent
	 */
	private final CreateErrorExceptionComponent exceptionComponent;

	/**
	 * Slash確認名を一時保管
	 */
	@Getter
	private String chkMember;

	/**
	 * スラッシュナンバー
	 */
	private String slashNum;

	//	/**
	//	 * リスト内の最大値から最小値の数値を引く
	//	 * @param playerCPU プレーヤー名（プレーヤーorCPU）
	//	 * @param gameMaster GameMasterオブジェクト
	//	 */
	//	public SlashOption(String playerCPU, GameMaster gameMaster) {
	//		this.mem = playerCPU;
	//		this.gameMaster = gameMaster;
	//	}

	/**
	 * リスト内の最大値から最小値の数値を引く
	 */
	public void slashLogic() {
		final String METHOD_NAME = "slashLogic";

		// 名前設定
		this.chkMember = this.gameMaster.getName();

		ArrayList<String> numList;
		if (Const.CPU.equals(this.getChkMember())) {
			numList = this.gameMaster.getCorrectPlayerNumberList();
		} else {
			numList = this.gameMaster.getCorrectCpuNumberList();
		}

		try {
			Integer ints = Integer.valueOf(Integer.parseInt(Collections.max(numList)) -
					Integer.parseInt(Collections.min(numList)));
			this.slashNum = ints.toString();
		} catch (NumberFormatException e) {
			throw this.exceptionComponent.createNumer0nUncontinuableException(S_FUNC, CLASS_NAME, METHOD_NAME, null,
					"ERR_OPTION_05", null, null, e);
		}

		//		// メッセージ（slashNo.前）
		//		lp.setLogParam(Const.SLASH, 2,
		//				new ArrayList<String>(Arrays.asList(Const.GAMEMASTER, gameMaster.getAite())));
		//
		//		// メッセージ（slashNo.）
		//		lp.setLogParam(Const.SLASH, 3,
		//				new ArrayList<String>(Arrays.asList(Const.GAMEMASTER,
		//						Anything.concatStringToComma(this.getSlashNum()))));
	}

	/**
	 * スラッシュナンバーを返却する
	 * @return slashNum スラッシュナンバー
	 */
	public String getSlashNum() {
		return slashNum;
	}

}
