package application.log;


import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import application.component.error.Numer0nInfoErrDTO;
import application.component.error.Numer0nUncontinuableException;

/**
 * <p>
 * ログ出力共通処理（機能ID:NR002）
 * </p>
 * @author shiraishitoshio
 *
 */
@Service
public class CommonLogger {

	/**
	 * 改行コード
	 */
	private static final String CRLF = "¥r¥n";

	/**
	 * ロガー
	 */
	private static Logger logger;

	/**
	 * プライベートなコンストラクタ
	 */
	private CommonLogger() {
		// プライベートなコンストラクタ
	}

	/**
	 * ログ出力処理（正常）
	 */
	public static void writeInfo(Numer0nInfoErrDTO infoDTO) {
		try {
			if (infoDTO == null) {
				return;
			}
			// 出力メッセージ格納し直し
			if (infoDTO.getOutMessage() == null) {
				infoDTO.setOutMessage("");
			}
			String outMessage = replaceCRLF(infoDTO.getOutMessage());
			StringBuilder sb = new StringBuilder(infoDTO.getAll());
			sb.append(",");
			sb.append(outMessage);
			String msg = sb.toString();
			logger.info(msg);
		} catch (Exception e) {
			sendErrLog(e);
		}
	}

	/**
	 * ログ出力処理（デバッグ・途中処理）
	 */
	public static void writeDebug(Numer0nInfoErrDTO infoDTO) {
		try {
			if (infoDTO == null) {
				return;
			}
			// 出力メッセージ格納し直し
			if (infoDTO.getOutMessage() == null) {
				infoDTO.setOutMessage("");
			}
			String outMessage = replaceCRLF(infoDTO.getOutMessage());
			StringBuilder sb = new StringBuilder(infoDTO.getAll());
			sb.append(",");
			sb.append(outMessage);
			String msg = sb.toString();
			logger.debug(msg);
		} catch (Exception e) {
			sendErrLog(e);
		}
	}

	/**
	 * ログ出力処理（警告）
	 */
	public static void writeWarn(Numer0nInfoErrDTO errDTO) {
		try {
			if (errDTO == null) {
				return;
			}
			// 出力メッセージ、詳細メッセージ格納し直し
			if (errDTO.getOutMessage() == null) {
				errDTO.setOutMessage("");
			}
			if (errDTO.getOutErrorDetailMessage() == null) {
				errDTO.setOutErrorDetailMessage("");
			}
			String outErrorMessage = replaceCRLF(errDTO.getOutMessage());
			String outErrorDetailMessage = replaceCRLF(errDTO.getOutErrorDetailMessage());
			StringBuilder sb = new StringBuilder(errDTO.getAll());
			sb.append(",");
			sb.append(outErrorMessage);
			if (!errDTO.getOutErrorDetailMessage().isEmpty() || errDTO.getOutErrorDetailMessage() != null) {
				sb.append(",");
				sb.append(outErrorDetailMessage);
			}
			String msg = sb.toString();
			logger.warn(msg);
		} catch (Exception e) {
			sendErrLog(e);
		}
	}

	/**
	 * ログ出力処理（エラー）
	 */
	public static void writeError(Numer0nInfoErrDTO errDTO) {
		try {
			if (errDTO == null) {
				return;
			}
			// 出力メッセージ、詳細メッセージ格納し直し
			if (errDTO.getOutMessage() == null) {
				errDTO.setOutMessage("");
			}
			if (errDTO.getOutErrorDetailMessage() == null) {
				errDTO.setOutErrorDetailMessage("");
			}
			String outErrorMessage = replaceCRLF(errDTO.getOutMessage());
			String outErrorDetailMessage = replaceCRLF(errDTO.getOutErrorDetailMessage());
			StringBuilder sb = new StringBuilder(errDTO.getAll());
			sb.append(",");
			sb.append(outErrorMessage);
			if (!errDTO.getOutErrorDetailMessage().isEmpty() || errDTO.getOutErrorDetailMessage() != null) {
				sb.append(",");
				sb.append(outErrorDetailMessage);
			}
			String msg = sb.toString();
			logger.error(msg);
		} catch (Exception e) {
			sendErrLog(e);
		}
	}

	/**
	 * 改行コードを元に分裂する
	 * @param message メッセージ
	 * @return
	 */
	private static String replaceCRLF(String message) {
		return null;
	}

	/**
	 * 例外ログ
	 */
	private static void sendErrLog(Throwable e) {
		Numer0nInfoErrDTO errDTO = new Numer0nInfoErrDTO();
		errDTO.setOutErrorDetailMessage(e.getMessage());
		throw new Numer0nUncontinuableException(errDTO);
	}
}
