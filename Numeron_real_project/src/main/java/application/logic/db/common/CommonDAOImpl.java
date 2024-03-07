package application.logic.db.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import application.component.error.CreateErrorExceptionComponent;
import application.logic.db.dto.AbstractInputDTO;
import application.logic.db.dto.AbstractOutputDTO;
import application.logic.option.ShuffleOption;
import lombok.RequiredArgsConstructor;

@Repository("commonDAO")
@RequiredArgsConstructor
public class CommonDAOImpl implements CommonDAO {

	/**
	 * 小機能ID
	 */
	private static final String S_FUNC = "COMMONDAO";

	/**
	 * クラス名
	 */
	private static final String CLASS_NAME = ShuffleOption.class.getSimpleName();

	/**
	 * DAOUtil
	 */
	private DAOUtil DAOUtil;

	/**
	 * CreateErrorExceptionComponent
	 */
	private final CreateErrorExceptionComponent exceptionComponent;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends AbstractOutputDTO> List<E> selectForObjectList(String dbAccessId, AbstractInputDTO dto) {
		final String METHOD_NAME = "selectForObjectList";

		if (dbAccessId == null) {
			throw this.exceptionComponent.createNumer0nUncontinuableException(S_FUNC, CLASS_NAME, METHOD_NAME, null,
					"ERR_COMMON_01", null, null, null, "データベースアクセスID:" + null);
		}

		if (dto == null) {
			throw this.exceptionComponent.createNumer0nUncontinuableException(S_FUNC, CLASS_NAME, METHOD_NAME, null,
					"ERR_COMMON_02", null, null, null, "InputDTO:" + null);
		}

		List<E> result = this.DAOUtil.getQueryDAO().executeForObjectList(dbAccessId, dto);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int insert(String dbAccessId, AbstractInputDTO dto) {
		final String METHOD_NAME = "insert";

		if (dbAccessId == null) {
			throw this.exceptionComponent.createNumer0nUncontinuableException(S_FUNC, CLASS_NAME, METHOD_NAME, null,
					"ERR_COMMON_01", null, null, null, "データベースアクセスID:" + null);
		}

		if (dto == null) {
			throw this.exceptionComponent.createNumer0nUncontinuableException(S_FUNC, CLASS_NAME, METHOD_NAME, null,
					"ERR_COMMON_02", null, null, null, "InputDTO:" + null);
		}

		int cnt = this.DAOUtil.getUpdateDAO().execute(dbAccessId, dto);
		return cnt;
	}

}
