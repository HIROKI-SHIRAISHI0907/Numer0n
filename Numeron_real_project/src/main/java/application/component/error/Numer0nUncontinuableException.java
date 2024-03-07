package application.component.error;

/**
 * Numeron実行不可能例外クラス
 * <p>
 * 通常で取得できる例外（NullPointerExceptionなど）以外は当例外クラスにて管理させる
 * </p>
 * @author shiraishitoshio
 *
 */
public class Numer0nUncontinuableException extends Numer0nException {

	/**
	 * コンストラクタ
	 * @param errDTO 正常例外取得格納DTO
	 * @param th スロー
	 */
	public Numer0nUncontinuableException(Numer0nInfoErrDTO errDTO, Throwable th) {
		super(errDTO, th);
	}

	/**
	 * コンストラクタ
	 * @param errDTO 正常例外取得格納DTO
	 */
	public Numer0nUncontinuableException(Numer0nInfoErrDTO errDTO) {
		super(errDTO);
	}

}
