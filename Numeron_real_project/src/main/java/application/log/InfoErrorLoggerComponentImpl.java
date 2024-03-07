package application.log;

import org.springframework.stereotype.Service;

import application.component.error.Numer0nInfoErrDTO;

/**
 * <p>
 * ログ出力ロジック処理（機能ID:NR003）
 * </p>
 * @author shiraishitoshio
 *
 */
@Service
public class InfoErrorLoggerComponentImpl implements InfoErrorLoggerComponent {

	/**
	 * 小機能ID
	 */
	private static final String S_FUNC = "NR002";

	/**
	 * 内部メッセージコード
	 */
	private static final String MESSAGE_CODE = "0000000000";

	/**
	 * クラス名
	 */
	private static final String CLASS_NAME = InfoErrorLoggerComponentImpl.class.getSimpleName();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeStartLog(String outMessage) {
		final String METHOD_NAME = "writeStartLog";

		Numer0nInfoErrDTO DTO = new Numer0nInfoErrDTO();
		DTO.setSFuncId(S_FUNC);
		DTO.setClassName(CLASS_NAME);
		DTO.setMethodName(METHOD_NAME);
		DTO.setMessageCd(MESSAGE_CODE);
		DTO.setOutMessage(outMessage);
		CommonLogger.writeInfo(DTO);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeEndLog(String outMessage) {
		final String METHOD_NAME = "writeEndLog";

		Numer0nInfoErrDTO DTO = new Numer0nInfoErrDTO();
		DTO.setSFuncId(S_FUNC);
		DTO.setClassName(CLASS_NAME);
		DTO.setMethodName(METHOD_NAME);
		DTO.setMessageCd(MESSAGE_CODE);
		DTO.setOutMessage(outMessage);
		CommonLogger.writeInfo(DTO);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeStartOtherLog(String sFunc, String className, String methodName, String messageCd, String outMessage) {
		Numer0nInfoErrDTO DTO = new Numer0nInfoErrDTO();
		DTO.setSFuncId(sFunc);
		DTO.setClassName(className);
		DTO.setMethodName(methodName);
		DTO.setMessageCd(messageCd);
		DTO.setOutMessage(outMessage);
		CommonLogger.writeInfo(DTO);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeEndOtherLog(String sFunc, String className, String methodName, String messageCd, String outMessage) {
		Numer0nInfoErrDTO DTO = new Numer0nInfoErrDTO();
		DTO.setSFuncId(sFunc);
		DTO.setClassName(className);
		DTO.setMethodName(methodName);
		DTO.setMessageCd(messageCd);
		DTO.setOutMessage(outMessage);
		CommonLogger.writeInfo(DTO);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeInfoLog(String sFunc, String className, String methodName, String messageCd, String outMessage) {
		Numer0nInfoErrDTO DTO = new Numer0nInfoErrDTO();
		DTO.setSFuncId(sFunc);
		DTO.setClassName(className);
		DTO.setMethodName(methodName);
		DTO.setMessageCd(messageCd);
		DTO.setOutMessage(outMessage);
		CommonLogger.writeInfo(DTO);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeWarnLog(String sFunc, String className, String methodName, String messageCd, String outMessage, String outDetailMessage) {
		Numer0nInfoErrDTO DTO = new Numer0nInfoErrDTO();
		DTO.setSFuncId(sFunc);
		DTO.setClassName(className);
		DTO.setMethodName(methodName);
		DTO.setMessageCd(messageCd);
		DTO.setOutMessage(outMessage);
		DTO.setOutErrorDetailMessage(outDetailMessage);
		CommonLogger.writeWarn(DTO);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeErrorLog(String sFunc, String className, String methodName, String messageCd, String outMessage, String outDetailMessage) {
		Numer0nInfoErrDTO DTO = new Numer0nInfoErrDTO();
		DTO.setSFuncId(sFunc);
		DTO.setClassName(className);
		DTO.setMethodName(methodName);
		DTO.setMessageCd(messageCd);
		DTO.setOutMessage(outMessage);
		DTO.setOutErrorDetailMessage(outDetailMessage);
		CommonLogger.writeWarn(DTO);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeDebugLog(String sFunc, String className, String methodName, String messageCd, String outMessage) {
		Numer0nInfoErrDTO DTO = new Numer0nInfoErrDTO();
		DTO.setSFuncId(sFunc);
		DTO.setClassName(className);
		DTO.setMethodName(methodName);
		DTO.setMessageCd(messageCd);
		DTO.setOutMessage(outMessage);
		CommonLogger.writeDebug(DTO);
	}
}
