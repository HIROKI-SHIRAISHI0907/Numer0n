package application.component.message;

/**
 * メッセージ取得IF
 * <p>
 * spring frameworkの提供するメッセージ取得用クラスのインターフェース
 * </p>
 * @author shiraishitoshio
 *
 */
public interface MessageAccessorIF {

	/**
	 *	メッセージ取得を行う
	 * @param code 検索コード
	 * @param args 引数（配列）（メッセージに格納する備考情報など）
	 * @return メッセージ
	 */
	public String getMessage(String code, Object[] args);

	/**
	 *	メッセージ取得を行う
	 * @param code 検索コード
	 * @param args 引数（可変）（メッセージに格納する備考情報など）
	 * @return メッセージ
	 */
	public String getMessage(String code, String... args);

}
