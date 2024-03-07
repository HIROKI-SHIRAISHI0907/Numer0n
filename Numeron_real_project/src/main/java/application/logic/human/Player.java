package application.logic.human;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.springframework.stereotype.Service;

import application.component.anything.Anything;
import application.component.consts.Const;
import application.logic.human.gameComponent.GameComponentUtil;
import application.logic.info.Numer0nInfo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Player extends Human {

	/**
	 * GameMaster
	 */
	private final GameMaster gameMaster;

	/**
	 * Numer0nInfo
	 */
	private final Numer0nInfo info;

	/**
	 * GameComponentUtil
	 */
	private final GameComponentUtil UtilMap;

	/**
	 * 候補となる数字のリスト
	 */
	@Getter
	private ArrayList<String> candidatePlayerNumberList = new ArrayList<String>();

	/**
	 * 候補ではない数字のリスト
	 */
	@Getter
	private ArrayList<String> notCandidatePlayerNumberList = new ArrayList<String>();

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
		ArrayList<String> callNumber = null;
		this.callNumber = callNumber;
		// 宣言ずみリストに格納
		info.addDeclaredPlayerNumberList(
				Anything.convertListToString(callNumber));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String useOffenseOption() {
		int op = 0;
		while (true) {
			boolean tf = false;
			op = new Random().nextInt(this.UtilMap.getOffenseListSize());
			if (op < 0 || op >= this.UtilMap.getOptionListSize()) {
				// 制御message(opListから選ぶ)
			} else {
				tf = true;
			}

			if (tf) {
				break;
			}
		}
		String item = this.UtilMap.getPlayerOffense(op);
		if (!Const.NO_OPTION.equals(item)) {
			this.UtilMap.removeOffenseOptionList(op);
		}
		return item;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String useDiffenseOption() {
		int op = 0;
		while (true) {
			boolean tf = false;
			op = new Random().nextInt(this.UtilMap.getDiffenseListSize());
			if (op <= 0 || op >= this.UtilMap.getOptionListSize()) {
				// 制御message(opListから選ぶ)
			} else {
				tf = true;
			}

			if (tf) {
				break;
			}
		}
		String item = this.UtilMap.getCpuOffense(op);
		if (!Const.NO_OPTION.equals(item)) {
			this.UtilMap.removeDiffenseOptionList(op);
		}
		return item;
	}

	/**
	 * 勝負する数字を決定する
	 */
	private void makeCorrectNumber() {
		// メッセージ
		ArrayList<String> correctList = new ArrayList<String>(Arrays.asList("3", "2", "5"));
		this.gameMaster.setCorrectPlayerNumberList(correctList);
	}

	/**
	 * オプションを選択する
	 */
	private void selectOption() {
		// 攻撃選択
		while (true) {
			int ind = new Random().nextInt(this.UtilMap.getOptionListSize());
			if ((!Const.CHANGE.equals(this.UtilMap.getOption(ind))
					&& !Const.SHUFFLE.equals(this.UtilMap.getOption(ind))
					&& (this.UtilMap.getOffenseListSize() == 0
							|| !this.UtilMap.containOffenseOptionList(this.UtilMap.getOption(ind))))) {
				this.UtilMap.addOffenseOptionList(this.UtilMap.getOption(ind));
			}

			if (this.UtilMap.getOffenseListSize() == 2) {
				break;
			}
		}
		this.UtilMap.addOffenseOptionList(Const.NO_OPTION);

		// 防御選択
		while (true) {
			int ind = new Random().nextInt(this.UtilMap.getOptionListSize());
			if ((Const.CHANGE.equals(this.UtilMap.getOption(ind))
					|| Const.SHUFFLE.equals(this.UtilMap.getOption(ind)))
					&& (this.UtilMap.getDiffenseListSize() == 0
							|| !this.UtilMap.containDiffenseOptionList(this.UtilMap.getOption(ind)))) {
				this.UtilMap.addDiffenseOptionList(this.UtilMap.getOption(ind));
			}

			if (this.UtilMap.getDiffenseListSize() == 1) {
				break;
			}
		}
		this.UtilMap.addDiffenseOptionList(Const.NO_OPTION);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addCandidateNumberList(String number) {
		this.candidatePlayerNumberList.add(number);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addNotCandidateNumberList(String number) {
		this.notCandidatePlayerNumberList.add(number);
	}

}
