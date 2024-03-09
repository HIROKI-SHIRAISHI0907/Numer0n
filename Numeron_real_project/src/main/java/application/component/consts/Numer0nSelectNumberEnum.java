package application.component.consts;

/**
 * Numer0nゲームで使用する0〜9までの数字のEnum
 * @author shiraishitoshio
 *
 */
public enum Numer0nSelectNumberEnum {

	/** 数字:0 */
	ZERO("0", "ZERO", "ZERO"),
	/** 数字:1 */
	ONE("1", "ONE", "ONE"),
	/** 数字:2 */
	TWO("2", "TWO", "TWO"),
	/** 数字:3 */
	THREE("3", "THREE", "THREE"),
	/** 数字:4 */
	FOUR("4", "FOUR", "FOUR"),
	/** 数字:5 */
	FIVE("5", "FIVE", "FIVE"),
	/** 数字:6 */
	SIX("6", "SIX", "SIX"),
	/** 数字:7 */
	SEVEN("7", "SEVEN", "SEVEN"),
	/** 数字:8 */
	EIGHT("8", "EIGHT", "EIGHT"),
	/** 数字:9 */
	NINE("9", "NINE", "NINE");

	/** 数字 */
	private final String num;
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
	Numer0nSelectNumberEnum(final String num, final String name, final String abb) {
		this.num = num;
		this.name = name;
		this.abb = abb;
	}

	/**
	 * 数字を取得する
	 * @return
	 */
	public String getNum() {
		return num;
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
