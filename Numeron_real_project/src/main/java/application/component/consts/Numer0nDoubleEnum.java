package application.component.consts;

/**
 * Numer0nゲームで使用する0〜9までの数字のEnum
 * @author shiraishitoshio
 *
 */
public enum Numer0nDoubleEnum {

	/** 0:Numer0nを終了 */
	ALLEAT(0, "終了"),
	/** 1:Numer0nを続行 */
	TEACH_NUMBER(1, "続行");

	/** オプションコード */
	private final Integer optionCd;
	/** オプションコードの略称 */
	private final String abb;

	/**
	 * コンストラクタ
	 * @param oprionCd オプションコード
	 * @param abb オプションコードの略称
	 */
	Numer0nDoubleEnum(final Integer optionCd, final String abb) {
		this.optionCd = optionCd;
		this.abb = abb;
	}

	/**
	 * オプションコードを取得する
	 * @return
	 */
	public Integer getOprionCd() {
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
