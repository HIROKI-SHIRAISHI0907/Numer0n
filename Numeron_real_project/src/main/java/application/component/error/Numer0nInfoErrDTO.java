package application.component.error;



import application.component.anything.Anything;
import lombok.Data;

/**
 * 正常例外取得格納DTO
 * <p>
 * Numeron実行途中及び不可能例外発生した場合、当DTOにまとめて格納する
 * </p>
 * @author shiraishitoshio
 *
 */
@Data
public class Numer0nInfoErrDTO implements Cloneable {

	/**
	 * 小機能ID
	 */
	private String sFuncId;

	/**
	 * クラス名
	 */
	private String className;

	/**
	 * メソッド名
	 */
	private String methodName;

	/**
	 * 検索エラーコード（getMessageにて取得する検索コード）
	 */
	private String expErrorCd;

	/**
	 * 内部メッセージコード（10桁の内部コード）
	 */
	private String messageCd;

	/**
	 * ジョブID
	 */
	private String jobId;

	/**
	 * 例外
	 */
	private Throwable ErrorClass;

	/**
	 * 出力メッセージ
	 */
	private String outMessage;

	/**
	 * 詳細出力メッセージ（Throwable発生メッセージなど）
	 */
	private String outErrorDetailMessage;

	/**
	 * 小機能ID
	 */
	private static final String S_FUNC = "小機能ID";

	/**
	 * クラス名
	 */
	private static final String CLASS_NAME = "クラス名";

	/**
	 * メソッド名
	 */
	private static final String METHOD_NAME = "メソッド名";

	/**
	 * 検索エラーコード（getMessageにて取得する検索コード）
	 */
	private static final String EXP_ERROR_CD = "検索エラーコード";

	/**
	 * 内部エラーメッセージコード（10桁の内部コード）
	 */
	private static final String MESSAGE_CD = "内部エラーメッセージコード";

	/**
	 * ジョブID
	 */
	private static final String JOB_ID = "ジョブID";

	/**
	 * 出力エラーメッセージ
	 */
	private static final String OUT_ERROR_MESSAGE = "出力エラーメッセージ";

	/**
	 * 詳細出力メッセージ（Throwable発生メッセージなど）
	 */
	private static final String OUT_ERROR_DETAIL_MESSAGE = "詳細出力メッセージ";

	/**
	 * ログ出力用全文字連結
	 */
	public String getAll() {
		StringBuilder sb = new StringBuilder();
		sb.append(S_FUNC).append(Anything.COMMA);
		sb.append(CLASS_NAME).append(Anything.COMMA);
		sb.append(METHOD_NAME).append(Anything.COMMA);
		sb.append(EXP_ERROR_CD).append(Anything.COMMA);
		sb.append(MESSAGE_CD).append(Anything.COMMA);
		sb.append(JOB_ID).append(Anything.COMMA);
		sb.append(OUT_ERROR_MESSAGE).append(Anything.COMMA);
		sb.append(OUT_ERROR_DETAIL_MESSAGE);
		return sb.toString();
	}
}
