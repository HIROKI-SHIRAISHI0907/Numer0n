package application.logic.db.common;

import org.springframework.transaction.PlatformTransactionManager;

/**
 * データアクセス振り分けインターフェース
 * @author shiraishitoshio
 *
 */
public interface DAOUtil {

	/**
	 * トランザクションマネージャーの選択取得
	 */
	PlatformTransactionManager getTransactionManager();

	/**
	 * QueryDAOの取得
	 * @return QueryDAO
	 */
	QueryDAO getQueryDAO();

	/**
	 * UpdateDAOの取得
	 * @return UpdateDAO
	 */
	UpdateDAO getUpdateDAO();

}
