package application.component.error;

public interface CreateErrorExceptionComponent {

	/**
	 * <p>
	 * Numer0n実行不可能例外作成
	 * </p>
	 * {@link Numer0nUncontinuableException}クラスを呼び出してスローする
	 * @param sFuncId 小機能ID
	 * @param className クラス名
	 * @param methodName メソッド名
	 * @param expErrorCd 検索エラーコード
	 * @param messageCd メッセージコード
	 * @param jobId ジョブID
	 * @param outMessage 出力メッセージ
	 * @param e スロー
	 * @param fillChar 備考情報等
	 * @return Numer0nUncontinuableException Numer0n実行不可能例外
	 */
	public Numer0nUncontinuableException createNumer0nUncontinuableException(String sFuncId, String className, String methodName, String expErrorCd, String messageCd, String jobId, String errMessage, Throwable e, String... fillChar);

	/**
	 * <p>
	 * Numer0n実行不可能例外作成用DTO
	 * </p>
	 * {@link Numer0nInfoErrDTO}クラスを呼び出してスローする
	 * @param sFuncId 小機能ID
	 * @param className クラス名
	 * @param methodName メソッド名
	 * @param expErrorCd 検索エラーコード
	 * @param messageCd メッセージコード
	 * @param jobId ジョブID
	 * @param outMessage 出力メッセージ
	 * @param e スロー
	 * @param fillChar 備考情報等
	 * @return Numer0nUncontinuableException Numer0n実行不可能例外
	 */
	public Numer0nInfoErrDTO createNumer0nInfoErrDTO(String sFuncId, String className, String methodName, String expErrorCd, String messageCd, String jobId, String outMessage, String outDetailMessage, Throwable e, String[] fillChar);

	/**
	 * <p>
	 * Numer0n実行不可能例外作成(小機能ID,埋め字変更)
	 * </p>
	 * {@link Numer0nUncontinuableException}クラスを呼び出してスローする
	 * @param sFuncId 小機能ID
	 * @param dto Numer0n実行不可能例外作成用DTO
	 * @oaram fillChar 埋め字
	 * @return Numer0nUncontinuableException Numer0n実行不可能例外
	 */
	public Numer0nUncontinuableException createOnlyFuncIdAndFillCharOtherDTONumer0nUncontinuableException(String sFuncId, Numer0nInfoErrDTO dto, String... fillChar);

}
