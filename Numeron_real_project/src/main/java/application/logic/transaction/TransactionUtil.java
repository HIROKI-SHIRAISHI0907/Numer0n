package application.logic.transaction;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

/**
 * トランザクション管理クラス
 * @author shiraishitoshio
 *
 */
public class TransactionUtil {

	/**
	 * コンストラクタ
	 */
	protected TransactionUtil() {}

	/**
	 * トランザクションを開始する。
	 * @param tran PlatformTransactionManager
	 * @param def TransactionDefinition
	 * @return TransactionStatus
	 */
	public static TransactionStatus startTransaction(
			PlatformTransactionManager tran, TransactionDefinition def) {

		TransactionStatus stat = null;
		if (tran != null) {
			stat = tran.getTransaction(def);
		}
		return stat;
	}

	/**
	 * トランザクションをコミットさせる。コネクションのコミットを行う。
	 * @param tran PlatformTransactionManager
	 * @param stat TransactionStatus
	 */
	public static void commitTransaction(
			PlatformTransactionManager tran, TransactionStatus stat) {

		if (tran != null && stat != null) {
			tran.commit(stat);
		}
	}

	/**
	 * トランザクションを終了する。
	 * @param tran PlatformTransactionManager
	 * @param stat TransactionStatus
	 */
	public static void endTransaction(
			PlatformTransactionManager tran, TransactionStatus stat) {

		if (tran != null && stat != null && !stat.isCompleted()) {
			tran.rollback(stat);
		}
	}

	/**
	 * トランザクションをロールバックする。
	 * @param tran PlatformTransactionManager
	 * @param stat TransactionStatus
	 */
	public static void rollbackTransaction(
			PlatformTransactionManager tran, TransactionStatus stat) {

		if (tran != null && stat != null && !stat.isCompleted()) {
			tran.rollback(stat);
		}
	}

}
