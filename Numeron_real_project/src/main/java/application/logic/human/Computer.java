package application.logic.human;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import org.springframework.stereotype.Service;

import application.component.anything.Anything;
import application.component.consts.DifficultyConst;
import application.component.consts.Numer0nOptionEnum;
import application.component.consts.PriorityFlagConst;
import application.logic.human.gameComponent.GameComponentMapUtil;
import application.logic.info.Numer0nInfo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Computer extends Human {

	/**
	 * GameMaster
	 */
	private final GameMaster gameMaster;

	/**
	 * Numer0nInfo
	 */
	private final Numer0nInfo info;

	/**
	 * GameComponentMapUtil
	 */
	private final GameComponentMapUtil UtilMap;

	/**
	 * 候補となる数字のリスト
	 */
	@Getter
	private ArrayList<String> candidateCpuNumberList = new ArrayList<String>();

	/**
	 * 候補ではない数字のリスト
	 */
	@Getter
	private ArrayList<String> notCandidateCpuNumberList = new ArrayList<String>();

	/**
	 * 次に選択する数字のリスト
	 */
	@Getter
	public ArrayList<String> nextActionNumberList = new ArrayList<String>();

	/**
	 * コールする数字
	 */
	@Getter
	public ArrayList<String> callNumber = new ArrayList<String>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void decideHumanLogic() {
		// 数字を決定する
		makeCorrectNumber();
		// オプションを決定する
		selectOption();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doCallNumber() {
		String callNum;
		int ind;
		// 候補リストにあればそこから選択する
		if (!this.candidateCpuNumberList.isEmpty()) {
			ind = new Random().nextInt(this.candidateCpuNumberList.size());
			callNum = this.candidateCpuNumberList.get(ind);
			this.callNumber = Anything.convertNumberToArrayList(callNum);
			// 任意の数字をコールする
		} else {
			ArrayList<String> tmpList = new ArrayList<String>();
			while (true) {
				int num = new Random().nextInt(10);
				String numStr = Anything.convertIntegerToString(num);
				// INSANE以外は数字が被るのは不可（数字のチェックを行う）
				if (!DifficultyConst.INSANE.equals(this.gameMaster.getDifficulty())) {
					if (Objects.isNull(tmpList)
							|| !tmpList.contains(numStr)) {
						tmpList.add(numStr);
					}
				} else {
					tmpList.add(numStr);
				}
				if (tmpList.size() == this.gameMaster.getDigit()) {
					break;
				}
			}
			this.callNumber = tmpList;
		}
		// 宣言ずみリストに格納
		info.addDeclaredCpuNumberList(
				Anything.convertListToString(this.callNumber));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String useOffenseOption() {
		String item;
		// 最優先フラグがセットされているoptionを取得
		if (this.UtilMap.containsValueOffenseOptionMap(PriorityFlagConst.SAI_YUUSEN_FLAG)) {
			item = "NOOTPION";
			//			item = Anything.getKey(GameComponentUtil.CPU_SELECT_OFFENSE_OPTION_PRIORITY_MAP,
			//					PriorityFlagDifficultyConst.SAI_YUUSEN_FLAG);
			// 任意のoptionを選択
		} else {
			Integer itemInt = new Random().nextInt(this.UtilMap.getOffenseMapSize());
			item = this.UtilMap.getCpuOffense(itemInt);
			if (!Numer0nOptionEnum.NOOPTION.getOprionName().equals(item)) {
				this.UtilMap.removeOffenseOptionMap(item);
			}
		}
		return item;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String useDiffenseOption() {
		String item;
		// 最優先フラグがセットされているoptionを取得
		if (this.UtilMap.containsValueDiffenseOptionMap(PriorityFlagConst.SAI_YUUSEN_FLAG)) {
			item = "NOOTPION";
			//			item = Anything.getKey(GameComponentUtil.CPU_SELECT_OFFENSE_OPTION_PRIORITY_MAP,
			//					PriorityFlagDifficultyConst.SAI_YUUSEN_FLAG);
			// 任意のoptionを選択
		} else {
			Integer itemInt = new Random().nextInt(this.UtilMap.getDiffenseMapSize());
			item = this.UtilMap.getCpuDiffense(itemInt);
			if (!Numer0nOptionEnum.NOOPTION.getOprionName().equals(item)) {
				this.UtilMap.removeDiffenseOptionMap(item);
			}
		}
		return item;
	}

	/**
	 * 勝負する数字を決定する
	 */
	private void makeCorrectNumber() {
		ArrayList<String> tmpList = new ArrayList<String>();
		while (true) {
			int num = new Random().nextInt(10);
			String numStr = Anything.convertIntegerToString(num);
			// INSANE以外は数字が被ってはいけない
			if (!DifficultyConst.INSANE.equals(this.gameMaster.getDifficulty())) {
				if (Objects.isNull(tmpList)
						|| !tmpList.contains(numStr)) {
					tmpList.add(numStr);
				}
			} else {
				tmpList.add(numStr);
			}

			if (tmpList.size() == this.gameMaster.getDigit()) {
				break;
			}
		}

		this.gameMaster.setCorrectCpuNumberList(tmpList);
	}

	/**
	 * オプションを選択する
	 */
	private void selectOption() {
		// 攻撃選択
		while (true) {
			int ind = new Random().nextInt(this.UtilMap.getOptionListSize());
			if ((!Numer0nOptionEnum.CHANGE.getOprionName().equals(this.UtilMap.getOption(ind))
					&& !Numer0nOptionEnum.SHUFFLE.getOprionName().equals(this.UtilMap.getOption(ind)))
					&& (this.UtilMap.getOffenseMapSize() == 0
							|| !this.UtilMap.containsKeyOffenseOptionMap(this.UtilMap.getOption(ind)))) {
				this.UtilMap.addOffenseOptionMap(this.UtilMap.getOption(ind));
			}

			if (this.UtilMap.getOffenseMapSize() == 2) {
				break;
			}
		}
		this.UtilMap.addOffenseOptionMap(Numer0nOptionEnum.NOOPTION.getOprionName());

		// 防御選択
		while (true) {
			int ind = new Random().nextInt(this.UtilMap.getOptionListSize());
			if ((Numer0nOptionEnum.CHANGE.getOprionName().equals(this.UtilMap.getOption(ind))
					|| Numer0nOptionEnum.SHUFFLE.equals(this.UtilMap.getOption(ind)))
					&& (this.UtilMap.getDiffenseMapSize() == 0
							|| !this.UtilMap.containsKeyDiffenseOptionMap(this.UtilMap.getOption(ind)))) {
				this.UtilMap.addDiffenseOptionMap(this.UtilMap.getOption(ind));
			}

			if (this.UtilMap.getDiffenseMapSize() == 1) {
				break;
			}
		}
		this.UtilMap.addDiffenseOptionMap(Numer0nOptionEnum.NOOPTION.getOprionName());

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addCandidateNumberList(String number) {
		this.candidateCpuNumberList.add(number);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addNotCandidateNumberList(String number) {
		this.notCandidateCpuNumberList.add(number);
	}

}
