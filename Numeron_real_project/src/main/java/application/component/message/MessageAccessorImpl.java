package application.component.message;

import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

import application.component.error.Numer0nInfoErrDTO;
import application.component.error.Numer0nUncontinuableException;

/**
 * メッセージ取得クラス（機能ID:NR001）
 * <p>
 *メッセージIDに紐づくメッセージを引数を投入して取得する
 * </p>
 * @author shiraishitoshio
 *
 */
@Component
public class MessageAccessorImpl extends ApplicationObjectSupport implements MessageAccessorIF {

	/**
	 * 小機能ID
	 */
	private static final String S_FUNC = "NR001";

	/**
	 * 内部メッセージコード
	 */
	private static final String MESSAGE_CODE = "9000010001";

	/**
	 * クラス名
	 */
	private static final String CLASS_NAME = MessageAccessorImpl.class.getSimpleName();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMessage(String code, Object[] args) {
		final String METHOD_NAME = "getMessage";

		String message = "";
		try {
			// 検索コードがnull
			if (code == null) {
				return "";
			}
			// メッセージ取得
			message = getMessageSourceAccessor().getMessage(code, args);
		} catch (IllegalStateException | NoSuchMessageException e) {
			Numer0nInfoErrDTO errDTO = new Numer0nInfoErrDTO();
			errDTO.setSFuncId(S_FUNC);
			errDTO.setClassName(CLASS_NAME);
			errDTO.setMethodName(METHOD_NAME);
			errDTO.setExpErrorCd(code);
			errDTO.setMessageCd(MESSAGE_CODE);
			errDTO.setOutErrorDetailMessage(e.getStackTrace()[0].toString());
			throw new Numer0nUncontinuableException(errDTO);
		}
		return message;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMessage(String code, String... args) {
		return this.getMessage(code, (Object[]) args);
	}
}
