package application.logic.info;

import org.springframework.stereotype.Service;

import application.component.consts.DifficultyConst;
import application.component.error.Numer0nUncontinuableException;
import application.logic.human.GameMaster;
import lombok.RequiredArgsConstructor;

/**
 * オプションコール→数字コールの次の行動のCPUの選択をまとめるクラス
 * <p>
 * ・DBに登録する<br>
 * ・次行動のオプション選択時にMapに登録する<br>
 * など
 * </p>
 * @author shiraishitoshio
 *
 */
@Service
@RequiredArgsConstructor
public class Numer0nNextActionImpl implements Numer0nNextAction {

	/**
	 * GameMaster
	 */
	private final GameMaster gameMaster;

	/**
	 * Numer0nInfo
	 */
	private final Numer0nInfo info;

	/**
	 * Numer0nSpecifyNumber
	 */
	private final Numer0nSpecifyNumber specifyNumber;

	/**
	 * Numer0nSpecifyNumberMultipleConfirmation
	 */
	private final Numer0nSpecifyNumberMultipleConfirmation specifyNumberMultiple;

	/**
	 * Numer0nNextActionSetFlagOnMap
	 */
	private final Numer0nNextActionSetFlagOnMap flagMap;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void nextActionLogic() {

		try {
			switch (this.gameMaster.getDifficulty()) {
				case DifficultyConst.NORMAL:
					this.specifyNumber.arrangeCandidateNumberLogic(
							this.info.getCpuInfoList().get(0));
					break;
				case DifficultyConst.HARD:
					this.specifyNumberMultiple.arrangeCandidateNumberMultipleConfirmationLogic();
					break;
				case DifficultyConst.EXHAUSTED:
					this.specifyNumberMultiple.arrangeCandidateNumberMultipleConfirmationLogic();
					this.flagMap.setFlagLogic();
					break;
				case DifficultyConst.INSANE:
					this.specifyNumberMultiple.arrangeCandidateNumberMultipleConfirmationLogic();
					// AI入れる
					this.flagMap.setFlagLogic();
					break;
			}
		} catch (Numer0nUncontinuableException e) {

		}

	}

}
