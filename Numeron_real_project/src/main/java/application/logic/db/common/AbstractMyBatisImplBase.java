package application.logic.db.common;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

/**
 * MyBatisの基底クラス
 * @author shiraishitoshio
 *
 */
@RequiredArgsConstructor
public abstract class AbstractMyBatisImplBase extends SqlSessionDaoSupport {

	/**
	 * ネームスペース
	 */
	private final String commonNameSpace;

	/**
	 * SQLIDを取得する
	 * @param sqlId SQLID
	 * @return ネームスペースが必要な場合はネームスペース付きのSQLID
	 */
	protected String getSqlId(String sqlId) {
		if (StringUtils.isEmpty(this.commonNameSpace)) {
			return sqlId;
		} else {
			StringBuilder sb = new StringBuilder(this.commonNameSpace);
			return sb.append(".").append(sqlId).toString();
		}
	}

}
