package application.logic.option;

import org.springframework.stereotype.Service;

import application.component.anything.Anything;
import application.component.consts.Const;
import application.component.consts.Numer0nChangeEnum;
import application.component.consts.Numer0nDoubleEnum;
import application.component.consts.Numer0nOptionEnum;
import application.component.consts.Numer0nTargetEnum;
import application.logic.human.GameMaster;
import application.logic.info.Numer0nInfo;
import lombok.RequiredArgsConstructor;

/**
 * オプションをまとめるクラス
 * @author shiraishitoshio
 *
 */
@Service
@RequiredArgsConstructor
public class CommonOptionImpl implements CommonOption {

	/**
	 * Numer0n続行
	 */
	private static final Integer Numer0n_CONTINUE = 0;

	/**
	 * Numer0n終了
	 */
	private static final Integer Numer0n_GAMEOVER = 1;

	/**
	 * GameMaster
	 */
	private final GameMaster gameMaster;

	/**
	 * Numer0nInfo
	 */
	private final Numer0nInfo info;

	/**
	 * ChangeOption
	 */
	private final ChangeOption changeOption;

	/**
	 * DoubleOption
	 */
	private final DoubleOption doubleOption;

	/**
	 * HighlowOption
	 */
	private final HighlowOption highlowOption;

	/**
	 * ShuffleOption
	 */
	private final ShuffleOption shuffleOption;

	/**
	 * SlashOption
	 */
	private final SlashOption slashOption;

	/**
	 * TargetOption
	 */
	private final TargetOption targetOption;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int summarizeOption(String item) {

		String teachStr;
		boolean gameOver = false;
		Integer doubleResult;
		if (Numer0nOptionEnum.DOUBLE.getOprionName().equals(item)) {
			// 2連続コール以内に当てられたか当てられなかったかを返却
			doubleResult = this.doubleOption.doubleLogic();
			// 当てられた場合ゲーム終了
			if (doubleResult == Numer0nDoubleEnum.ALLEAT.getOprionCd()) {
				gameOver = true;
			}
		} else if (Numer0nOptionEnum.HIGHLOW.getOprionName().equals(item)) {
			this.highlowOption.highLowLogic();
			// Highlowクラス
			if (Const.CPU.equals(this.gameMaster.getName())) {
				this.info.addCpuInfoList(Anything.concatStringToComma(
						item, Anything.concatListToComma(this.highlowOption.getHighLowList())));
			} else {
				this.info.addPlayerInfoList(Anything.concatStringToComma(
						item, Anything.concatListToComma(this.highlowOption.getHighLowList())));
			}
		} else if (Numer0nOptionEnum.SLASH.getOprionName().equals(item)) {
			this.slashOption.slashLogic();
			if (Const.CPU.equals(this.gameMaster.getName())) {
				this.info.addCpuInfoList(Anything.concatStringToComma(
						item, this.slashOption.getSlashNum()));
			} else {
				this.info.addPlayerInfoList(Anything.concatStringToComma(
						item, this.slashOption.getSlashNum()));
			}
		} else if (Numer0nOptionEnum.TARGET.getOprionName().equals(item)) {
			String targetResult = this.targetOption.targetLogic();
			// -1が返却された場合、D'ont teach indexを連結
			teachStr = (-1 == this.targetOption.getExistsInd())
					? Numer0nTargetEnum.DONT_TEACH_INDEX.getAbb()
					: Anything.convertIntegerToString(this.targetOption.getExistsInd());
			if (Const.CPU.equals(this.gameMaster.getName())) {
				this.info.addCpuInfoList(Anything.concatStringToComma(
						item, teachStr, targetResult, this.targetOption.getExNum()));
			} else {
				this.info.addPlayerInfoList(Anything.concatStringToComma(
						item, teachStr, targetResult, this.targetOption.getExNum()));
			}
		} else if (Numer0nOptionEnum.CHANGE.getOprionName().equals(item)) {
			changeOption.changeLogic();
			// -1が返却された場合、D'ont teach indexを連結
			teachStr = (-1 == this.changeOption.getDigitInd())
					? Numer0nChangeEnum.DONT_TEACH_INDEX.getAbb()
					: Anything.convertIntegerToString(this.changeOption.getDigitInd());
			if (Const.CPU.equals(this.gameMaster.getName())) {
				this.info.addPlayerInfoList(Anything.concatStringToComma(
						item, teachStr, this.changeOption.getLh()));
			} else {
				this.info.addCpuInfoList(Anything.concatStringToComma(
						item, teachStr, this.changeOption.getLh()));
			}
		} else {
			this.shuffleOption.shuffleLogic();
		}

		// DOUBLEを除く、オプションを使用したタイミングでは数字はコールしていないので、
		// 使用した数字リストにハイフンを追加
		if (!Numer0nOptionEnum.DOUBLE.getOprionName().equals(item)) {
			if (Const.CPU.equals(this.gameMaster.getName())) {
				this.info.addDeclaredCpuNumberList(Const.HYPHEN);
			} else {
				this.info.addDeclaredPlayerNumberList(Const.HYPHEN);
			}
		}

		// 当てられた場合試合終了（DOUBLEの場合に限る）
		if (gameOver) {
			return Numer0n_GAMEOVER;
		}
		return Numer0n_CONTINUE;
	}
}
