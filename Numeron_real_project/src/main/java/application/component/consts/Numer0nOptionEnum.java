package application.component.consts;

/**
 * Numer0nゲームで使用する0〜9までの数字のEnum
 * @author shiraishitoshio
 *
 */
public enum Numer0nOptionEnum {

	/** DOUBLE */
	DOUBLE("DOUBLE", "DOUBLE"),
	/** CHANGE */
	CHANGE("CHANGE", "CHANGE"),
	/** HIGH&LOW */
	HIGHLOW("HIGH&LOW", "HIGH&LOW"),
	/** SHUFFLE */
	SHUFFLE("SHUFFLE", "SHUFFLE"),
	/** SLASH */
	SLASH("SLASH", "SLASH"),
	/** TARGET */
	TARGET("TARGET", "TARGET"),
	/** NOOPTION */
	NOOPTION("NOOPTION", "NOOPTION");

	/** オプション名 */
	private final String oprionName;
	/** オプション名の略称 */
	private final String abb;

	/**
	 * コンストラクタ
	 * @param oprionName オプション名
	 * @param abb オプション名の略称
	 */
	Numer0nOptionEnum(final String oprionName, final String abb) {
		this.oprionName = oprionName;
		this.abb = abb;
	}

	/**
	 * オプション名を取得する
	 * @return
	 */
	public String getOprionName() {
		return oprionName;
	}

	/**
	 * オプション名の略称を取得する
	 * @return
	 */
	public String getAbb() {
		return abb;
	}

}
