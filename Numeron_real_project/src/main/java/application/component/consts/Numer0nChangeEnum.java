package application.component.consts;

/**
 * Numer0nゲームで使用する0〜9までの数字のEnum
 * @author shiraishitoshio
 *
 */
public enum Numer0nChangeEnum {

	/** 1:変更する */
	CHANGE_GO("1", "変更する"),
	/** 2:変更しない */
	CHANGE_REJECT("2", "変更しない"),
	/** 難易度:EXHAUSTED,INSANEの際に存在、非存在を教えないようにする定数 */
	DONT_TEACH_INDEX("3", "DONTTEACHINDEX"),
	/** 交換後のHIGH,LOW情報を教えないようにする定数 */
	NOT_CLEAR("4", "NOTCLEAR");

	/** オプションコード */
	private final String optionCd;
	/** オプションコードの略称 */
	private final String abb;

	/**
	 * コンストラクタ
	 * @param oprionCd オプションコード
	 * @param abb オプションコードの略称
	 */
	Numer0nChangeEnum(final String optionCd, final String abb) {
		this.optionCd = optionCd;
		this.abb = abb;
	}

	/**
	 * オプションコードを取得する
	 * @return
	 */
	public String getOprionCd() {
		return optionCd;
	}

	/**
	 * オプションコードの略称を取得する
	 * @return
	 */
	public String getAbb() {
		return abb;
	}

}
