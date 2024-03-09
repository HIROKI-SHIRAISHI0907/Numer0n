package application.component.consts;

/**
 * Numer0nゲームで使用する桁(0〜4(10000~1の位))のEnum
 * @author shiraishitoshio
 *
 */
public enum Numer0nDigitEnum {

	/**
	 * 3桁:100の位
	 * 4桁:1000の位
	 * 5桁:10000の位
	 */
	ZEROD(0, "ZERO", "ZERO"),
	/**
	 * 3桁:10の位
	 * 4桁:100の位
	 * 5桁:1000の位
	 */
	ONED(1, "ONE", "ONE"),
	/**
	 * 3桁:1の位
	 * 4桁:10の位
	 * 5桁:100の位
	 */
	TWOD(2, "TWO", "TWO"),
	/**
	 * 4桁:1の位
	 * 5桁:10の位
	 */
	THREED(3, "THREE", "THREE"),
	/** 5桁:1の位  */
	FOURD(4, "FOUR", "FOUR");

	/** 数字の桁 */
	private final Integer digit;
	/** 数字の名前 */
	private final String name;
	/** 数字の名前の略称 */
	private final String abb;

	/**
	 * コンストラクタ
	 * @param num 数字
	 * @param name 数字の名前
	 * @param abb 数字の名前の略称
	 */
	Numer0nDigitEnum(final Integer digit, final String name, final String abb) {
		this.digit = digit;
		this.name = name;
		this.abb = abb;
	}

	/**
	 * 数字の桁を取得する
	 * @return
	 */
	public Integer getDigit() {
		return digit;
	}

	/**
	 * 数字の名前を取得する
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 数字の名前の略称を取得する
	 * @return
	 */
	public String getAbb() {
		return abb;
	}

}
