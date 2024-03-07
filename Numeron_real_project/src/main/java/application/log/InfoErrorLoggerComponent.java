package application.log;

/**
 * ログ出力管理インターフェース
 * <p>
 * ログ出力の管理を行う
 * </p>
 * @author shiraishitoshio
 *
 */
public interface InfoErrorLoggerComponent {

	/**
	 * <p>
	 * 当ロガークラス開始ログ
	 * </p>
	 * @param outMessage 出力メッセージ
	 */
	public void writeStartLog(String outMessage);

	/**
	 * <p>
	 * 当ロガークラス終了ログ
	 * </p>
	 * @param outMessage 出力メッセージ
	 */
	public void writeEndLog(String outMessage);

	/**
	 * <p>
	 * その他処理開始ログ
	 * </p>
	 * @param sFunc 小機能ID
	 * @param className クラス名
	 * @param methodName メソッド名
	 * @param messageCd メッセージコード
	 * @param outMessage 出力メッセージ
	 */
	public void writeStartOtherLog(String sFunc, String className, String methodName, String messageCd, String outMessage);

	/**
	 * <p>
	 * その他処理終了ログ
	 * </p>
	 * @param sFunc 小機能ID
	 * @param className クラス名
	 * @param methodName メソッド名
	 * @param messageCd メッセージコード
	 * @param outMessage 出力メッセージ
	 */
	public void writeEndOtherLog(String sFunc, String className, String methodName, String messageCd, String outMessage);

	/**
	 * <p>
	 * 正常・途中ログ
	 * </p>
	 * @param sFunc 小機能ID
	 * @param className クラス名
	 * @param methodName メソッド名
	 * @param messageCd メッセージコード
	 * @param outMessage 出力メッセージ
	 */
	public void writeInfoLog(String sFunc, String className, String methodName, String messageCd, String outMessage);

	/**
	 * <p>
	 * 警告ログ
	 * </p>
	 * @param sFunc 小機能ID
	 * @param className クラス名
	 * @param methodName メソッド名
	 * @param messageCd メッセージコード
	 * @param outMessage 出力メッセージ
	 */
	public void writeWarnLog(String sFunc, String className, String methodName, String messageCd, String outMessage, String outDetailMessage);

	/**
	 * <p>
	 * エラーログ
	 * </p>
	 * @param sFunc 小機能ID
	 * @param className クラス名
	 * @param methodName メソッド名
	 * @param messageCd メッセージコード
	 * @param outMessage 出力メッセージ
	 * @param outDetailMessage 出力詳細メッセージ
	 */
	public void writeErrorLog(String sFunc, String className, String methodName, String messageCd, String outMessage, String outDetailMessage);

	/**
	 * <p>
	 * デバッグログ
	 * </p>
	 * @param sFunc 小機能ID
	 * @param className クラス名
	 * @param methodName メソッド名
	 * @param messageCd メッセージコード
	 * @param outMessage 出力メッセージ
	 */
	public void writeDebugLog(String sFunc, String className, String methodName, String messageCd, String outMessage);
}
