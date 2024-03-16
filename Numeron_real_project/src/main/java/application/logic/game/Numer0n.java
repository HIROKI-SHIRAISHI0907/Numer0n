package application.logic.game;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import application.component.anything.Anything;
import application.component.consts.Const;
import application.component.consts.Numer0nDoubleEnum;
import application.component.consts.Numer0nOptionEnum;
import application.component.consts.OtherMatchFlagConst;
import application.logic.human.Computer;
import application.logic.human.GameMaster;
import application.logic.human.Player;
import application.logic.human.gameComponent.GameComponentMapUtil;
import application.logic.info.Numer0nInfo;
import application.logic.info.Numer0nNextAction;
import application.logic.judge.Eatbite;
import application.logic.option.CommonOptionImpl;
import lombok.RequiredArgsConstructor;

/**
 * Numer0n開始クラス
 * 1, 相手がナンバーをコールした時点でアイテムは使えない
 * 2, 1ターンに1つしかアイテムを使えない
 * 3, 攻撃系アイテム（DOUBLE,HIGHLOW,TARGET,SLASH）と防御系アイテム（SHUFFLE,CHANGE）があるが、相手が攻撃系アイテムを使用した時、次のターン防御系アイテムは使えない
 * @author shiraishitoshio
 *
 */
@Service
@RequiredArgsConstructor
public class Numer0n {

	/**
	 * GameMasterクラス
	 */
	private final GameMaster gameMaster;

	/**
	 * Playerクラス
	 */
	private final Player player;

	/**
	 * Computerクラス
	 */
	private final Computer computer;

	/**
	 * Infoクラス
	 */
	private final Numer0nInfo info;

	/**
	 * optionクラス
	 */
	private final CommonOptionImpl option;

	/**
	 * GameComponentMapUtil
	 */
	private final GameComponentMapUtil utilMap;

	/**
	 * Numer0nNextAction
	 */
	private final Numer0nNextAction action;

	/**
	 * Eatbiteクラス
	 */
	private final Eatbite eb;

	/**
	 * ゲームスタート
	 */
	public void startNumer0n() {
		// GameMasterの情報決定
		this.gameMaster.decideGameMasterInfo();

		// 使用オプション,正しい数字を決定
		this.player.decideHumanLogic();
		this.computer.decideHumanLogic();

		// ターン
		int turn = 1;
		while (true) {
			// 攻撃オプション使用
			String item = getNumer0nOffenseOption();

			// オプション使用時、optionクラスにアクセス
			if (!Numer0nOptionEnum.NOOPTION.getOprionName().equals(item)) {
				int optionResult = this.option.summarizeOption(item);

				// オプション（DOUBLE）で当てられた場合、試合終了
				if (optionResult == OtherMatchFlagConst.Numer0n_GAMEOVER) {
					break;
				} else {
					// DOUBLEコールで当てられなかった場合、（DOUBLE,0,2）相手が情報を得るので、候補を絞っておく。
					if (Numer0nOptionEnum.DOUBLE.getOprionName().equals(item)
							&& Const.CPU.equals(this.gameMaster.getName())) {
						this.action.nextActionLogic();
					}
				}
			}

			// DOUBLEコールの時はすでにコールしているため、ここではコールできない。
			Integer ebResult;
			ArrayList<String> callNumber = new ArrayList<String>();
			if (!Numer0nOptionEnum.DOUBLE.getOprionName().equals(item)) {
				// 数字をコール及び宣言した数字リストにセット
				if (Const.CPU.equals(this.gameMaster.getName())) {
					this.computer.doCallNumber();
					callNumber = this.computer.getCallNumber();
					ebResult = this.eb.judgeEatBite(
							callNumber, this.gameMaster.getCorrectPlayerNumberList());

					this.info.addCpuInfoList(Anything.concatStringToComma(
							Numer0nOptionEnum.NOOPTION.getOprionName(),
							Anything.concatListToComma(callNumber),
							this.eb.getEatBiteResult()));
				} else {
					this.player.doCallNumber();
					callNumber = this.player.getCallNumber();
					ebResult = this.eb.judgeEatBite(
							callNumber, this.gameMaster.getCorrectCpuNumberList());

					this.info.addPlayerInfoList(Anything.concatStringToComma(
							Numer0nOptionEnum.NOOPTION.getOprionName(),
							Anything.concatListToComma(callNumber),
							this.eb.getEatBiteResult()));
				}

				// nEAT0BITEの場合、勝ちなのでループから抜ける
				if (ebResult == Numer0nDoubleEnum.ALLEAT.getOprionCd()) {
					break;
				}
			}

			// 防御オプションを使用するか。ただしすでに攻撃オプションを使っていた場合、防御オプションは使えない
			if (Numer0nOptionEnum.NOOPTION.getOprionName().equals(item)) {
				item = getNumer0nDiffenseOption();
			} else {
				item = Numer0nOptionEnum.NOOPTION.getOprionName();
			}

			// 防御オプション使用時、optionクラスにアクセス
			if (Numer0nOptionEnum.SHUFFLE.getOprionName().equals(item) ||
					Numer0nOptionEnum.CHANGE.getOprionName().equals(item)) {
				this.option.summarizeOption(item);

				// SHUFFLE,CHANGEは特に情報が増えないため、情報リストにハイフンを追加
				if (Const.CPU.equals(this.gameMaster.getName())) {
					this.info.addCpuInfoList(Anything.concatStringToComma(
							item, Const.HYPHEN));
				} else {
					this.info.addPlayerInfoList(Anything.concatStringToComma(
							item, Const.HYPHEN));
				}

				// 候補を絞る
				this.action.nextActionLogic();
			}

			// 攻守交代
			if (!Const.CPU.equals(this.gameMaster.getName())) {
				this.gameMaster.setName(Const.CPU);
			} else {
				this.gameMaster.setName(this.gameMaster.getName());
			}

			// ターン数+1
			turn++;
		}

	}

	/**
	 * 攻撃用オプションを選択する
	 */
	private String getNumer0nOffenseOption() {
		String item;
		// 攻撃オプションを使用するか（CPUの場合、優先的にオプションを使用するフラグが立っていた際の選択もここで行う）
		item = Numer0nOptionEnum.NOOPTION.getOprionName();

		// CPUオプション選択
		if (this.utilMap.getOffenseMapSize() > 1 && Const.CPU.equals(this.gameMaster.getName())) {
			item = this.computer.useOffenseOption();
			// PLAYERオプション選択
		} else if (this.utilMap.getOffenseListSize() > 1) {
			item = this.player.useOffenseOption();
		}
		// すでにoptionを全て使用してしまっている場合、強制的にNOOPTIONメッセージ
		return item;
	}

	/**
	 * 防御用オプションを選択する
	 */
	private String getNumer0nDiffenseOption() {
		String item = Numer0nOptionEnum.NOOPTION.getOprionName();
		// CPUオプション選択
		if (this.utilMap.getDiffenseMapSize() > 0 && Const.CPU.equals(this.gameMaster.getName())) {
			item = this.computer.useDiffenseOption();
			// PLAYERオプション選択
		} else if (this.utilMap.getOffenseListSize() > 0) {
			item = this.player.useDiffenseOption();
		}
		// すでにoptionを全て使用してしまっている場合、強制的にNOOPTIONメッセージ
		return item;
	}

}
