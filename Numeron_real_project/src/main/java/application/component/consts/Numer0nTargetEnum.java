package application.component.consts;

/**
 * Numer0nゲームで使用する0〜9までの数字のEnum
 * @author shiraishitoshio
 *
 */
public enum Numer0nTargetEnum {

	/** 1:変更する */
	EXIST_LIST_OF_NUMBER("1", "EXISTLISTOFNUMBER"),
	/** 2:変更しない */
	NONE_EXIST_LIST_OF_NUMBER("2", "NONEEXISTLISTOFNUMBER"),
	/** 難易度:EXHAUSTED,INSANEの際に存在、非存在を教えないようにする定数 */
	DONT_TEACH_INDEX("3", "DONTTEACHINDEX"),
	/** 指定後のHIGH,LOW情報を教えないようにする定数 */
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
	Numer0nTargetEnum(final String optionCd, final String abb) {
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
