package application.component.consts;

/**
 * Numer0nゲームで使用する0〜9までの数字のEnum
 * @author shiraishitoshio
 *
 */
public enum Numer0nShuffleEnum {

	/** 1:シャッフルする */
	SHUFFLE_GO("1", "シャッフルする"),
	/** 2:シャッフルしない */
	SHUFFLE_REJECT("2", "シャッフルしない");

	/** オプションコード */
	private final String optionCd;
	/** オプションコードの略称 */
	private final String abb;

	/**
	 * コンストラクタ
	 * @param oprionCd オプションコード
	 * @param abb オプションコードの略称
	 */
	Numer0nShuffleEnum(final String optionCd, final String abb) {
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
