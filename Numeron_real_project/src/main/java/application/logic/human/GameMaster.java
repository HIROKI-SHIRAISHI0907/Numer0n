package application.logic.human;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import application.component.consts.Const;
import application.component.consts.DifficultyConst;
import lombok.Getter;
import lombok.Setter;

@Component
public class GameMaster {

	/**
	 * 名前
	 */
	@Setter
	@Getter
	private String name;

	/**
	 * 難易度
	 */
	@Setter
	@Getter
	private String difficulty;

	/**
	 * 桁
	 */
	@Setter
	@Getter
	private int digit;

	/**
	 * コールする数字
	 */
	@Getter
	public ArrayList<String> callNumber = new ArrayList<String>();

	/**
	 * 決めた数字リスト(プレーヤー)
	 */
	@Setter
	@Getter
	public ArrayList<String> correctPlayerNumberList = new ArrayList<String>();

	/**
	 * 決めた数字リスト(CPU)
	 */
	@Setter
	@Getter
	public ArrayList<String> correctCpuNumberList = new ArrayList<String>();
	Scanner sc = new Scanner(System.in);

	/**
	 * 先攻後攻,名前,難易度,桁を決定する
	 */
	public void decideGameMasterInfo() {
		// 名前
		decideName();
		// 難易度
		decideDifficulty();
		// 桁
		decideDigit();
		// ターン
		decideTurnName();
	}

	/**
	 * 先攻後攻の順番を決める
	 */
	private void decideTurnName() {
		String name;
		int ints = new Random().nextInt(2);
		if (ints == 0) {
			name = Const.CPU;
		} else {
			name = decideName();
		}
		this.setName(name);
	}

	/**
	 * あなたの名前を決める
	 */
	private String decideName() {
		// 名前を決めるメッセージ
		String name = null;
		return name;
	}

	/**
	 * 難易度を決定する
	 */
	private void decideDifficulty() {
		String diff;
		int diffs = new Random().nextInt(5);
		if (diffs == 0) {
			diff = DifficultyConst.EASY;
		} else if (diffs == 1) {
			diff = DifficultyConst.NORMAL;
		} else if (diffs == 2) {
			diff = DifficultyConst.HARD;
		} else if (diffs == 3) {
			diff = DifficultyConst.EXHAUSTED;
		} else {
			diff = DifficultyConst.INSANE;
		}
		this.setDifficulty(diff);
	}

	/**
	 * 桁を決定する
	 */
	private void decideDigit() {
		int dig;
		while (true) {
			dig = new Random().nextInt(5)+1;
			boolean tf = false;
			if (dig >= 3 && dig <= 5) {
				tf = true;
			}

			if (tf) {
				break;
			}
		}
		this.setDigit(dig);
	}

}
