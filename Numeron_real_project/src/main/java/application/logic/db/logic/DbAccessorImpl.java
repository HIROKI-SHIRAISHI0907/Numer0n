package application.logic.db.logic;

import java.util.List;

import application.component.error.CreateErrorExceptionComponent;
import application.component.error.Numer0nException;
import application.logic.db.common.CommonDAO;
import application.logic.db.dto.AbstractInputDTO;
import application.logic.db.dto.Numer0nSelectOutputDTO;
import lombok.RequiredArgsConstructor;

/**
 * Numer0nDBアクセス用整理クラス
 * <p>
 * 難易度:EXHAUSTED,INSANEで主に使用
 * </p>
 * @author shiraishitoshio
 *
 */
@RequiredArgsConstructor
public class DbAccessorImpl implements DbAccessor {

	/**
	 * 小機能ID
	 */
	private static final String S_FUNC = "DBACCESSOR";

	/**
	 * クラス名
	 */
	private static final String CLASS_NAME = DbAccessorImpl.class.getSimpleName();

	/**
	 * CommonDAO
	 */
	private final CommonDAO commonDAO;

	/**
	 * CreateErrorExceptionComponent
	 */
	private final CreateErrorExceptionComponent exceptionComponent;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer numer0nInsert(String dbAccessId, AbstractInputDTO dto) {
		final String METHOD_NAME = "numer0nInsert";

		int cnt;
		try {
			cnt = this.commonDAO.insert(dbAccessId, dto);
		} catch (Numer0nException e) {
			throw this.exceptionComponent.createNumer0nUncontinuableException(S_FUNC, CLASS_NAME, METHOD_NAME, null,
					"ERR_DBACCESSOR_01", null, null, null);
		}

		return cnt;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Numer0nSelectOutputDTO> numer0nSelectForObject(String dbAccessId, AbstractInputDTO dto) {
		final String METHOD_NAME = "numer0nSelectForObject";

		List<Numer0nSelectOutputDTO> resultList = null;
		try {
			resultList = this.commonDAO.selectForObjectList(dbAccessId, dto);
		} catch (Numer0nException e) {
			throw this.exceptionComponent.createNumer0nUncontinuableException(S_FUNC, CLASS_NAME, METHOD_NAME, null,
					"ERR_DBACCESSOR_02", null, null, null);
		}

		return resultList;
	}

}
