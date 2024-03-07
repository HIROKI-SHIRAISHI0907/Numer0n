package application.component.error;

/**
 * Numeron共通例外クラス
 * <p>
 * Numeron共通例外クラス
 * </p>
 * @author shiraishitoshio
 *
 */
public class Numer0nException extends RuntimeException {

	/**
	 * 正常例外取得格納DTOフィールド
	 */
	private Numer0nInfoErrDTO errDTO;

	/**
	 * コンストラクタ
	 * @param errDTO 正常例外取得格納DTO
	 * @param th スロー
	 */
	public Numer0nException(Numer0nInfoErrDTO errDTO, Throwable th) {
		super(th);
		this.errDTO = errDTO;
	}

	/**
	 * コンストラクタ
	 * @param errDTO 正常例外取得格納DTO
	 */
	public Numer0nException(Numer0nInfoErrDTO errDTO) {
		super();
		this.errDTO = errDTO;
	}

	/**
	 * 正常例外取得格納DTOを取得する
	 */
	public Numer0nInfoErrDTO getNumer0nErrDTO() {
		return this.errDTO;
	}

}
