package application.component.consts;

/**
 * Numer0nゲームで使用する0〜9までの数字のEnum
 * @author shiraishitoshio
 *
 */
public enum Numer0nDoubleEnum {

	/** 0:EATが桁数と一致(全数字がわかった) */
	ALLEAT(0, "EAT終了"),
	/** 1:nEAT0BITEでなく続ける */
	TEACH_NUMBER(1, "続行"),
	/** 2:ALLEAT以外 */
	NONE_ALLEAT(2, "ALLEAT以外");

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
