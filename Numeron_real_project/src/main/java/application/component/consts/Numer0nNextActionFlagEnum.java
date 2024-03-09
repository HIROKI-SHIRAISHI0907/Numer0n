package application.component.consts;

/**
 * Numer0nゲームで使用する0〜9までの数字のEnum
 * @author shiraishitoshio
 *
 */
public enum Numer0nNextActionFlagEnum {

	/** 0:info情報の最後の情報のみ使用 */
	LAST_INFO_FLAG("0", "最後の情報使用"),
	/** 1:info情報の全ての情報を使用 */
	EVER_INFO_FLAG("1", "全ての情報を使用"),
	/** 2:info情報から優先フラグ等を決定する際の別リスト作成用フラグ */
	MAKE_NUMBER_FLAG("2", "別リスト作成用");

	/** フラグコード */
	private final String flagCd;
	/** オプションコードの略称 */
	private final String abb;

	/**
	 * コンストラクタ
	 * @param oprionCd フラグコード
	 * @param abb フラグコードの略称
	 */
	Numer0nNextActionFlagEnum(final String flagCd, final String abb) {
		this.flagCd = flagCd;
		this.abb = abb;
	}

	/**
	 * フラグコードを取得する
	 * @return
	 */
	public String getFlagCd() {
		return flagCd;
	}

	/**
	 * フラグコードの略称を取得する
	 * @return
	 */
	public String getAbb() {
		return abb;
	}

}
