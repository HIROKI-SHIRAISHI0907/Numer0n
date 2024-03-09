package application.logic.info;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import application.component.anything.Anything;
import application.component.consts.Const;
import application.component.consts.Numer0nChangeEnum;
import application.component.consts.Numer0nSelectNumberEnum;
import application.component.consts.Numer0nTargetEnum;
import application.logic.human.Computer;
import application.logic.human.GameMaster;
import application.logic.human.Player;
import lombok.RequiredArgsConstructor;

/**
 * 数々の情報をもとに、どの数値を候補として残すか思考するクラス
 * <p>
 * #1(オプションを使用して数字をコールしEatBite情報を得られた後)に、必要不要の数字を整理する
 * 整理する情報は、直近で得られた#1の情報を元に行う
 * </p>
 * @author shiraishitoshio
 *
 */
@Service
@RequiredArgsConstructor
public class Numer0nSpecifyNumberImpl implements Numer0nSpecifyNumber {

	/**
	 * ランダムNo.（10）
	 */
	private static final int RANDOM_NUMBER_TEN = 10;

	/**
	 * Numer0n続行
	 */
	private static final Integer Numer0n_CONTINUE = 0;

	/**
	 * Playerクラス
	 */
	private final Player player;

	/**
	 * computerクラス
	 */
	private final Computer computer;

	/**
	 * GameMasterクラス
	 */
	private final GameMaster gameMaster;

	/**
	 * 条件に合う数値のリスト
	 */
	private ArrayList<String> conditionNumberList = new ArrayList<String>();

	/**
	 * 数字を作成する用MAP
	 */
	private Map<String, String> conditionMap = new HashMap<String, String>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void arrangeCandidateNumberLogic(String info) {
		// 得られた情報を元に整理する数字を一時格納する
		String item = Anything.splitComma(info).get(0);
		ArrayList<String> arrageNumberList = setArrangeNumberList();

		// 3桁
		if (this.gameMaster.getDigit() == 3) {
			StringBuilder chkNumber = new StringBuilder();
			for (int i = 0; i < RANDOM_NUMBER_TEN; i++) {
				for (int j = 0; j < RANDOM_NUMBER_TEN; j++) {
					for (int k = 0; k < RANDOM_NUMBER_TEN; k++) {

						// 全ての数字が異なっていない場合スキップ（難易度INSANEは除外）
						if ((i == j || j == k || k == i)
								&& !Const.INSANE.equals(this.gameMaster.getDifficulty())) {
							continue;
						}

						String istr = Anything.convertIntegerToString(i);
						String jstr = Anything.convertIntegerToString(j);
						String kstr = Anything.convertIntegerToString(k);
						chkNumber.append(istr).append(jstr).append(kstr);

						// 条件に合う数値を作成するフラグの場合
						if (Const.MAKE_NUMBER_FLAG.equals(item)) {
							int makeNumberResult = notConditionMakeNumber(info, chkNumber.toString(),
									gameMaster);
							if (makeNumberResult == Const.MATCH) {
								this.conditionNumberList.add(chkNumber.toString());
							}
							// オプション使用なし用（DOUBLEの場合、サイズがEATBITE情報が格納済みのもののみ）
						} else if (Const.NO_OPTION.equals(item)) {
							setNoneOptionAddCandidateMethod(info, chkNumber.toString(), Const.LAST_INFO_FLAG);
							// オプション使用あり用（DOUBLEのサイズが3も含む）
						} else {
							setOptionAddCandidateMethod(info, chkNumber.toString(), Const.LAST_INFO_FLAG);
						}
						// 確認数字を初期化
						chkNumber.delete(0, chkNumber.length());
					}
				}
			}
			// 4桁
		} else if (this.gameMaster.getDigit() == 4) {
			StringBuilder chkNumber = new StringBuilder();
			for (int i = 0; i < RANDOM_NUMBER_TEN; i++) {
				for (int j = 0; j < RANDOM_NUMBER_TEN; j++) {
					for (int k = 0; k < RANDOM_NUMBER_TEN; k++) {
						for (int l = 0; l < RANDOM_NUMBER_TEN; l++) {

							// 全ての数字が異なっていない場合スキップ（難易度INSANEは除外）
							if ((i == j || j == k || k == l || l == i || i == k || j == l)
									&& !Const.INSANE.equals(this.gameMaster.getDifficulty())) {
								continue;
							}

							String istr = Anything.convertIntegerToString(i);
							String jstr = Anything.convertIntegerToString(j);
							String kstr = Anything.convertIntegerToString(k);
							String lstr = Anything.convertIntegerToString(l);
							chkNumber.append(istr).append(jstr).append(kstr).append(lstr);

							// 条件に合う数値を作成するフラグの場合
							if (Const.MAKE_NUMBER_FLAG.equals(item)) {
								int makeNumberResult = notConditionMakeNumber(info, chkNumber.toString(),
										gameMaster);
								if (makeNumberResult == Const.MATCH) {
									this.conditionNumberList.add(chkNumber.toString());
								}
								// オプション使用なし用（DOUBLEの場合、サイズがEATBITE情報が格納済みのもののみ）
							} else if (Const.NO_OPTION.equals(item)) {
								setNoneOptionAddCandidateMethod(info, chkNumber.toString(), Const.LAST_INFO_FLAG);
								// オプション使用あり用（DOUBLEのサイズが3も含む）
							} else {
								setOptionAddCandidateMethod(info, chkNumber.toString(), Const.LAST_INFO_FLAG);
							}

							// 確認数字を初期化
							chkNumber.delete(0, chkNumber.length());
						}
					}
				}
			}
			// 5桁
		} else if (this.gameMaster.getDigit() == 5) {
			StringBuilder chkNumber = new StringBuilder();
			for (int i = 0; i < RANDOM_NUMBER_TEN; i++) {
				for (int j = 0; j < RANDOM_NUMBER_TEN; j++) {
					for (int k = 0; k < RANDOM_NUMBER_TEN; k++) {
						for (int l = 0; l < RANDOM_NUMBER_TEN; l++) {
							for (int m = 0; m < RANDOM_NUMBER_TEN; m++) {
								// 全ての数字が異なっていない場合スキップ（難易度INSANEは除外）
								if ((i == j || j == k || k == l || l == m || m == i
										|| i == k || j == l || i == k || j == m || i == l)
										&& !Const.INSANE.equals(this.gameMaster.getDifficulty())) {
									continue;
								}

								String istr = Anything.convertIntegerToString(i);
								String jstr = Anything.convertIntegerToString(j);
								String kstr = Anything.convertIntegerToString(k);
								String lstr = Anything.convertIntegerToString(l);
								String mstr = Anything.convertIntegerToString(m);
								chkNumber.append(istr).append(jstr).append(kstr).append(lstr).append(mstr);

								// 条件に合う数値を作成するフラグの場合
								if (Const.MAKE_NUMBER_FLAG.equals(item)) {
									int makeNumberResult = notConditionMakeNumber(info, chkNumber.toString(),
											gameMaster);
									if (makeNumberResult == Const.MATCH) {
										this.conditionNumberList.add(chkNumber.toString());
									}
									// オプション使用なし用（DOUBLEの場合、サイズがEATBITE情報が格納済みのもののみ）
								} else if (Const.NO_OPTION.equals(item)) {
									setNoneOptionAddCandidateMethod(info, chkNumber.toString(),
											Const.LAST_INFO_FLAG);
									// オプション使用あり用（DOUBLEのサイズが3も含む）
								} else {
									setOptionAddCandidateMethod(info, chkNumber.toString(),
											Const.LAST_INFO_FLAG);
								}

								// 確認数字を初期化
								chkNumber.delete(0, chkNumber.length());
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 得られた情報を元に整理する数字を格納する。
	 * <p>
	 * オプションによって候補の数字を一時格納する
	 * </p>
	 */
	private ArrayList<String> setArrangeNumberList() {
		ArrayList<String> arrageNumberList = new ArrayList<String>();

		// 候補の数リストがどちらも空の場合、整理はスキップ
		if (this.player.getCandidatePlayerNumberList().isEmpty() &&
				this.computer.getCandidateCpuNumberList().isEmpty()) {
			return arrageNumberList;
		}

		// OptionがDOUBLE
		if (Const.CPU.equals(this.gameMaster.getName())) {
			for (String can : this.player.getCandidatePlayerNumberList()) {
				arrageNumberList.add(can);
			}
		} else {
			for (String can : this.computer.getCandidateCpuNumberList()) {
				arrageNumberList.add(can);
			}
		}
		return arrageNumberList;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int setNoneOptionAddCandidateMethod(String info, String chkNumber, String flag) {
		ArrayList<String> infoList = Anything.splitComma(info);
		int eatFlg = 0;
		int biteFlg = 0;
		boolean confNumber = false;

		// 得られた情報を一旦分割し、eatbiteの情報を得た元の数値リストだけ取得して、リスト化
		List<String> getInfoList = Anything.convertNumberToArrayList(
				infoList.subList(1, 2).get(0));

		for (int dig = 0; dig < this.gameMaster.getDigit(); dig++) {
			// 同じ桁同士が同じ数字か
			if (getInfoList.get(dig).equals(chkNumber.substring(dig, dig + 1))) {
				eatFlg += 1;
			}
			// 数字が含まれているか
			if (!getInfoList.get(dig).equals(chkNumber.substring(dig, dig + 1)) &&
					getInfoList.contains(chkNumber.substring(dig, dig + 1))) {
				biteFlg += 1;
			}
		}

		// xEATyBITEのx,yに合致していたらtrue
		if (Anything.convertIntegerToString(eatFlg).equals(infoList.get(infoList.size() - 1)
				.substring(Const.EAT_START_STRING, Const.EAT_START_STRING + 1))
				&& Anything.convertIntegerToString(biteFlg).equals(infoList.get(infoList.size() - 1)
						.substring(Const.BITE_START_STRING, Const.BITE_START_STRING + 1))) {
			confNumber = true;
		}

		// 条件に合致していたらMATCH
		if (Const.EVER_INFO_FLAG.equals(flag)) {
			if (confNumber) {
				return Const.MATCH;
			} else {
				return Const.NOT_MATCH;
			}
		}

		// 該当するxEATyBITEである場合候補リストにセット
		if (confNumber) {
			if (Const.CPU.equals(this.gameMaster.getName())) {
				this.computer.addCandidateNumberList(chkNumber);
				// 元々候補になかった数字だが、該当するxEATyBITEである場合削除
				if (this.computer.getNotCandidateCpuNumberList().contains(chkNumber)) {
					this.computer.getNotCandidateCpuNumberList().remove(chkNumber);
				}
			} else {
				this.player.addCandidateNumberList(chkNumber);
				// 元々候補になかった数字だが、該当するxEATyBITEである場合削除
				if (this.player.getNotCandidatePlayerNumberList().contains(chkNumber)) {
					this.player.getNotCandidatePlayerNumberList().remove(chkNumber);
				}
			}
		} else {
			if (Const.CPU.equals(this.gameMaster.getName())) {
				this.computer.addNotCandidateNumberList(chkNumber);
				// 元々候補にあった数字だが、該当するxEATyBITEでない場合削除
				if (this.computer.getCandidateCpuNumberList().contains(chkNumber)) {
					this.computer.getCandidateCpuNumberList().remove(chkNumber);
				}
			} else {
				this.player.addNotCandidateNumberList(chkNumber);
				// 元々候補にあった数字だが、該当するxEATyBITEでない場合削除
				if (this.player.getCandidatePlayerNumberList().contains(chkNumber)) {
					this.player.getCandidatePlayerNumberList().remove(chkNumber);
				}
			}
		}

		// 処理を続ける
		return Numer0n_CONTINUE;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int setOptionAddCandidateMethod(String info, String chkNumber, String flag) {
		ArrayList<String> infoList = Anything.splitComma(info);
		boolean everFlag = true;
		switch (infoList.get(0)) {
		case Const.DOUBLE:
			// DOUBLE（[DOUBLE,0,1]（桁、その数字））の形式か
			if (!infoList.get(2).contains(Const.EAT)) {
				// infoにある情報と同じであるか
				int dig = Anything.convertStringToInteger(infoList.get(1));
				if (chkNumber.substring(dig, dig + 1).equals(infoList.get(2))) {
					if (Const.LAST_INFO_FLAG.equals(flag)) {
						if (Const.CPU.equals(this.gameMaster.getName())) {
							this.computer.addCandidateNumberList(chkNumber);
							if (this.computer.getNotCandidateCpuNumberList().contains(chkNumber)) {
								this.computer.getNotCandidateCpuNumberList().remove(chkNumber);
							}
						} else {
							this.player.addCandidateNumberList(chkNumber);
							if (this.player.getNotCandidatePlayerNumberList().contains(chkNumber)) {
								this.player.getNotCandidatePlayerNumberList().remove(chkNumber);
							}
						}
					}
				} else {
					if (Const.EVER_INFO_FLAG.equals(flag)) {
						everFlag = false;
					} else {
						if (Const.CPU.equals(this.gameMaster.getName())) {
							this.computer.addNotCandidateNumberList(chkNumber);
							if (this.computer.getCandidateCpuNumberList().contains(chkNumber)) {
								this.computer.getCandidateCpuNumberList().remove(chkNumber);
							}
						} else {
							this.player.addNotCandidateNumberList(chkNumber);
							if (this.player.getCandidatePlayerNumberList().contains(chkNumber)) {
								this.player.getCandidatePlayerNumberList().remove(chkNumber);
							}
						}
					}
				}
				// DOUBLE（[DOUBLE,5489,1EAT2BITE）の形式か
				// NOOPTIONと同じ処理を行う。
			} else {
				// EVER_INFO_FLAGかつ得られているDOUBLE情報にMATCHしていない場合、everFlagをfalse（対象外）
				int noneResult = setNoneOptionAddCandidateMethod(info, chkNumber, flag);
				if (Const.EVER_INFO_FLAG.equals(flag) &&
						noneResult == Const.NOT_MATCH) {
					everFlag = false;
				}
			}
			break;
		// CHANGE（[CHANGE,DONTTEACHINDEX,HIGH]（桁、HIGHかLOWか））の形式か
		case Const.CHANGE:
			// [CHANGE,--]の場合は、特に判断できないため、スキップ
			if (infoList.size() == 2) {
				return Const.MATCH;
			}

			String judgeChangeTfResult = judgeChangeTF(
					Anything.convertNumberToArrayList(chkNumber),
					infoList.get(1),
					infoList.get(2));
			// 一致しなかった場合、除去対象
			if (Const.NOT_CONSISTENT.equals(judgeChangeTfResult)) {
				if (Const.EVER_INFO_FLAG.equals(flag)) {
					everFlag = false;
				} else {
					if (Const.CPU.equals(this.gameMaster.getName())) {
						this.computer.addNotCandidateNumberList(chkNumber);
						if (this.computer.getCandidateCpuNumberList().contains(chkNumber)) {
							this.computer.getCandidateCpuNumberList().remove(chkNumber);
						}
					} else {
						this.player.addNotCandidateNumberList(chkNumber);
						if (this.player.getCandidatePlayerNumberList().contains(chkNumber)) {
							this.player.getCandidatePlayerNumberList().remove(chkNumber);
						}
					}
				}
				// 一致した場合、候補対象
			} else if (Const.CONSISTENT.equals(judgeChangeTfResult)) {
				if (Const.LAST_INFO_FLAG.equals(flag)) {
					if (Const.CPU.equals(this.gameMaster.getName())) {
						this.computer.addCandidateNumberList(chkNumber);
						if (this.computer.getNotCandidateCpuNumberList().contains(chkNumber)) {
							this.computer.getNotCandidateCpuNumberList().remove(chkNumber);
						}
					} else {
						this.player.addCandidateNumberList(chkNumber);
						if (this.player.getNotCandidatePlayerNumberList().contains(chkNumber)) {
							this.player.getNotCandidatePlayerNumberList().remove(chkNumber);
						}
					}
				}
				// 不明だった場合、
			} else if (Numer0nChangeEnum.NOT_CLEAR.getAbb().equals(judgeChangeTfResult)) {
				// 桁がわかっている場合も含め
				// 候補にもなり、そうでないものにもなり得るため、両方入れておく
				if (Const.CPU.equals(this.gameMaster.getName())) {
					this.computer.addCandidateNumberList(chkNumber);
					this.computer.addNotCandidateNumberList(chkNumber);
				} else {
					this.player.addCandidateNumberList(chkNumber);
					this.player.addNotCandidateNumberList(chkNumber);
				}
			}
			break;
		// HIGH&LOW（[HIGH&LOW,LOW,LOW,LOW,LOW,HIGH]（左からHIGHかLOWか））の形式か
		case Const.HIGH_LOW:
			if (Const.EVER_INFO_FLAG.equals(flag)) {
				if (!judgeHighLowTF(
						Anything.convertNumberToArrayList(chkNumber), infoList)) {
					everFlag = false;
				}
				// コール用数字として候補リストに格納
			} else {
				if (judgeHighLowTF(
						Anything.convertNumberToArrayList(chkNumber), infoList)) {
					if (Const.CPU.equals(this.gameMaster.getName())) {
						this.computer.addCandidateNumberList(chkNumber);
						if (this.computer.getNotCandidateCpuNumberList().contains(chkNumber)) {
							this.computer.getNotCandidateCpuNumberList().remove(chkNumber);
						}
					} else {
						this.player.addCandidateNumberList(chkNumber);
						if (this.player.getNotCandidatePlayerNumberList().contains(chkNumber)) {
							this.player.getNotCandidatePlayerNumberList().remove(chkNumber);
						}
					}
				} else {
					if (Const.CPU.equals(this.gameMaster.getName())) {
						this.computer.addNotCandidateNumberList(chkNumber);
						if (this.computer.getCandidateCpuNumberList().contains(chkNumber)) {
							this.computer.getCandidateCpuNumberList().remove(chkNumber);
						}
					} else {
						this.player.addNotCandidateNumberList(chkNumber);
						if (this.player.getCandidatePlayerNumberList().contains(chkNumber)) {
							this.player.getCandidatePlayerNumberList().remove(chkNumber);
						}
					}
				}
			}
			break;
		// SLASH（[SLASH,3]（スラッシュナンバー））の形式か
		case Const.SLASH:
			if (Const.EVER_INFO_FLAG.equals(flag)) {
				ArrayList<String> numList = Anything.convertNumberToArrayList(chkNumber);
				if (Integer.parseInt(Collections.max(numList)) - Integer.parseInt(Collections.min(numList))
						!= Integer.parseInt(infoList.get(1))) {
					everFlag = false;
				}
				// コール用数字として候補リストに格納
			} else {
				// [SLASH,--]はスキップ
				if (Const.HYPHEN.equals(infoList.get(1))) {
					return Const.MATCH;
				}

				if (Integer.parseInt(Collections.max(Anything.convertNumberToArrayList(chkNumber)))
						- Integer.parseInt(
								Collections.min(Anything.convertNumberToArrayList(chkNumber))) == Integer
										.parseInt(infoList.get(1))) {
					if (Const.CPU.equals(this.gameMaster.getName())) {
						this.computer.addCandidateNumberList(chkNumber);
						if (this.computer.getNotCandidateCpuNumberList().contains(chkNumber)) {
							this.computer.getNotCandidateCpuNumberList().remove(chkNumber);
						}
					} else {
						this.player.addCandidateNumberList(chkNumber);
						if (this.player.getNotCandidatePlayerNumberList().contains(chkNumber)) {
							this.player.getNotCandidatePlayerNumberList().remove(chkNumber);
						}
					}
				} else {
					if (Const.CPU.equals(this.gameMaster.getName())) {
						this.computer.addNotCandidateNumberList(chkNumber);
						if (this.computer.getCandidateCpuNumberList().contains(chkNumber)) {
							this.computer.getCandidateCpuNumberList().remove(chkNumber);
						}
					} else {
						this.player.addNotCandidateNumberList(chkNumber);
						if (this.player.getCandidatePlayerNumberList().contains(chkNumber)) {
							this.player.getCandidatePlayerNumberList().remove(chkNumber);
						}
					}
				}
			}
			break;
		// [TARGET,2,EXISTLISTOFNUMBER,4]（オプション、桁、存在するか？、宣言した数字）の形式か
		case Const.TARGET:
			if (Numer0nTargetEnum.NONE_EXIST_LIST_OF_NUMBER.getAbb().equals(infoList.get(2))) {
				if (Const.EVER_INFO_FLAG.equals(flag)) {
					// 宣言した数字が数値リストに存在した場合、対象外（存在しないことがわかっているため）
					// everFlag = false→NOT_MATCH（満たしていないため削除対象）
					ArrayList<String> numList = Anything.convertNumberToArrayList(chkNumber);
					if (Numer0nTargetEnum.DONT_TEACH_INDEX.getAbb().equals(infoList.get(1))) {
						if (numList.contains(infoList.get(3))) {
							everFlag = false;
						}
					} else {
						if (numList.get(Anything.convertStringToInteger(infoList.get(1)))
								.equals(infoList.get(3))) {
							everFlag = false;
						}
					}
				} else {
					// 宣言した数字が数値リストに存在しないか
					ArrayList<String> numList = Anything.convertNumberToArrayList(chkNumber);
					if (Numer0nTargetEnum.DONT_TEACH_INDEX.getAbb().equals(infoList.get(1))) {
						if (numList.contains(infoList.get(3))) {
							if (Const.CPU.equals(this.gameMaster.getName())) {
								this.computer.addNotCandidateNumberList(chkNumber);
								if (this.computer.getCandidateCpuNumberList().contains(chkNumber)) {
									this.computer.getCandidateCpuNumberList().remove(chkNumber);
								}
							} else {
								this.player.addNotCandidateNumberList(chkNumber);
								if (this.player.getCandidatePlayerNumberList().contains(chkNumber)) {
									this.player.getCandidatePlayerNumberList().remove(chkNumber);
								}
							}
						} else {
							if (Const.CPU.equals(gameMaster.getName())) {
								this.computer.addCandidateNumberList(chkNumber);
								if (this.computer.getNotCandidateCpuNumberList().contains(chkNumber)) {
									this.computer.getNotCandidateCpuNumberList().remove(chkNumber);
								}
							} else {
								this.player.addCandidateNumberList(chkNumber);
								if (this.player.getNotCandidatePlayerNumberList().contains(chkNumber)) {
									this.player.getNotCandidatePlayerNumberList().remove(chkNumber);
								}
							}
						}
						// 存在する桁がわかっているか
					} else {
						// 得た桁にアクセスし、一致するか
						if (numList.get(Anything.convertStringToInteger(infoList.get(1)))
								.equals(infoList.get(3))) {
							if (Const.CPU.equals(this.gameMaster.getName())) {
								this.computer.addNotCandidateNumberList(chkNumber);
								if (this.computer.getCandidateCpuNumberList().contains(chkNumber)) {
									this.computer.getCandidateCpuNumberList().remove(chkNumber);
								}
							} else {
								this.player.addNotCandidateNumberList(chkNumber);
								if (this.player.getCandidatePlayerNumberList().contains(chkNumber)) {
									this.player.getCandidatePlayerNumberList().remove(chkNumber);
								}
							}
						} else {
							if (Const.CPU.equals(this.gameMaster.getName())) {
								this.computer.addCandidateNumberList(chkNumber);
								if (this.computer.getNotCandidateCpuNumberList().contains(chkNumber)) {
									this.computer.getNotCandidateCpuNumberList().remove(chkNumber);
								}
							} else {
								this.player.addCandidateNumberList(chkNumber);
								if (this.player.getNotCandidatePlayerNumberList().contains(chkNumber)) {
									this.player.getNotCandidatePlayerNumberList().remove(chkNumber);
								}
							}
						}
					}
				}
				// コール用数字として候補リストに格納
			} else {
				if (Const.EVER_INFO_FLAG.equals(flag)) {
					// 宣言した数字が数値リストに存在しない場合、対象外（存在することがわかっているため）
					// everFlag = false→NOT_MATCH（満たしていないため削除対象）
					ArrayList<String> numList = Anything.convertNumberToArrayList(chkNumber);
					// 存在する桁がわかっているか（[TARGET, 0orDONTTEACHINDEX, EXISTLISTOFNUMBER, 4]）
					if (Numer0nTargetEnum.DONT_TEACH_INDEX.getAbb().equals(infoList.get(1))) {
						if (!numList.contains(infoList.get(3))) {
							everFlag = false;
						}
					} else {
						if (!numList.get(Anything.convertStringToInteger(infoList.get(1)))
								.equals(infoList.get(3))) {
							everFlag = false;
						}
					}
				} else {
					// 宣言した数字が数値リストに存在するか
					ArrayList<String> numList = Anything.convertNumberToArrayList(chkNumber);
					// 存在する桁がわかっているか（[TARGET, 0orDONTTEACHINDEX, EXISTLISTOFNUMBER, 4]）
					if (Numer0nTargetEnum.DONT_TEACH_INDEX.getAbb().equals(infoList.get(1))) {
						if (numList.contains(infoList.get(3))) {
							if (Const.CPU.equals(this.gameMaster.getName())) {
								this.computer.addCandidateNumberList(chkNumber);
								if (this.computer.getNotCandidateCpuNumberList().contains(chkNumber)) {
									this.computer.getNotCandidateCpuNumberList().remove(chkNumber);
								}
							} else {
								this.player.addCandidateNumberList(chkNumber);
								if (this.player.getNotCandidatePlayerNumberList().contains(chkNumber)) {
									this.player.getNotCandidatePlayerNumberList().remove(chkNumber);
								}
							}
						} else {
							if (Const.CPU.equals(this.gameMaster.getName())) {
								this.computer.addNotCandidateNumberList(chkNumber);
								if (this.computer.getCandidateCpuNumberList().contains(chkNumber)) {
									this.computer.getCandidateCpuNumberList().remove(chkNumber);
								}
							} else {
								this.player.addNotCandidateNumberList(chkNumber);
								if (this.player.getCandidatePlayerNumberList().contains(chkNumber)) {
									this.player.getCandidatePlayerNumberList().remove(chkNumber);
								}
							}
						}
					} else {
						// 得た桁にアクセスし、一致するか
						if (numList.get(Anything.convertStringToInteger(infoList.get(1)))
								.equals(infoList.get(3))) {
							if (Const.CPU.equals(this.gameMaster.getName())) {
								this.computer.addCandidateNumberList(chkNumber);
								if (this.computer.getNotCandidateCpuNumberList().contains(chkNumber)) {
									this.computer.getNotCandidateCpuNumberList().remove(chkNumber);
								}
							} else {
								this.player.addCandidateNumberList(chkNumber);
								if (this.player.getNotCandidatePlayerNumberList().contains(chkNumber)) {
									this.player.getNotCandidatePlayerNumberList().remove(chkNumber);
								}
							}
						} else {
							if (Const.CPU.equals(this.gameMaster.getName())) {
								this.computer.addNotCandidateNumberList(chkNumber);
								if (this.computer.getCandidateCpuNumberList().contains(chkNumber)) {
									this.computer.getCandidateCpuNumberList().remove(chkNumber);
								}
							} else {
								this.player.addNotCandidateNumberList(chkNumber);
								if (this.player.getCandidatePlayerNumberList().contains(chkNumber)) {
									this.player.getCandidatePlayerNumberList().remove(chkNumber);
								}
							}
						}
					}
				}
			}
			break;
		}

		if (everFlag) {
			return Const.MATCH;
		} else {
			return Const.NOT_MATCH;
		}
	}

	/**
	 * conditionにて与えられた条件に合うリストを作成する。（AiSpecifyOptionAndNextActionクラスにて作成された条件限定のメソッド）<br>
	 * ex) [2,5,MAKE_OTHER_THESE_NUMBER]のようなリストがconditionに入り、2,5を含まない数字であればMATCH扱い。
	 * @param condition 条件
	 * @param num 数値
	 * @param gameMaster ゲームマスタークラス
	 */
	private int notConditionMakeNumber(String condition, String num, GameMaster gameMaster) {
		// 任意の数字をリスト化
		ArrayList<String> numList = Anything.convertNumberToArrayList(num);
		// conditionの情報をリスト化
		ArrayList<String> conditionList = Anything.splitComma(condition);

		// 数字を作成する条件マップを生成（作成されていなければここで作成）
		if (conditionMap.size() == 0) {
			for (int n = 0; n < conditionList.size() - 1; n++) {
				conditionMap.put(conditionList.get(conditionList.size() - 1), conditionList.get(n));
			}
		}

		for (String numStr : numList) {
			// 条件に合わない数値MAP
			if (conditionMap.containsKey(Const.MAKE_OTHER_THESE_NUMBER)) {
				if (conditionMap.containsValue(numStr)) {
					// 当てはまった数字がある時点で条件に合ってしまっているためNOT_MATCH
					return Const.NOT_MATCH;
				}
			}
		}

		// 当てはまる数字がない場合、MATCH
		return Const.MATCH;

	}

	/**
	 * HighorLowのリストから渡した数値リストがHigh（5-9）,Low（0-4）に該当するかorしないかのbooleanを返す
	 * @param numList 数値リスト
	 * @param itemList High,Lowリスト（ex) HIGH&LOW,HIGH,LOW,LOW）
	 * @return tr <code>true:</code>（highlowリストに一致）,<code>false:</code>（一致しない）
	 */
	private boolean judgeHighLowTF(ArrayList<String> numList, ArrayList<String> itemList) {
		boolean tf = true;

		Integer zero = Anything.convertStringToInteger(Numer0nSelectNumberEnum.ZERO.getNum());
		Integer four = Anything.convertStringToInteger(Numer0nSelectNumberEnum.FOUR.getNum());
		Integer five = Anything.convertStringToInteger(Numer0nSelectNumberEnum.FIVE.getNum());
		Integer nine = Anything.convertStringToInteger(Numer0nSelectNumberEnum.NINE.getNum());

		for (int i = 0; i < numList.size(); i++) {
			Integer num = Anything.convertStringToInteger(numList.get(i));
			if (Const.HIGH.equals(itemList.get(i + 1))) {
				if (0 >= zero.compareTo(num) &&
						0 <= four.compareTo(num)) {
					tf = false;
				}
			} else {
				if (0 >= five.compareTo(num) &&
						0 <= nine.compareTo(num)) {
					tf = false;
				}
			}
			// 1つでもfalseだった場合、その時点で当てはまらないのでbreak
			if (!tf) {
				break;
			}
		}
		return tf;
	}

	/**
	 * Changeのリストから渡した数値リストがHigh（5-9）,Low（0-4）に該当するかorしないかのStringを返す
	 * @param numList 任意の数字リスト
	 * @param digit 桁の情報 0~4orDONT_TEACH_INDEXのいずれか
	 * @param changeHighLowInfo HIGHorLOWorNOT_CLEARのいずれか
	 * @return
	 */
	private String judgeChangeTF(ArrayList<String> numList, String digit, String changeHighLowInfo) {
		//EXHAUSTED,INSANE用（交換された数字がHIGHかLOWかわからない場合）
		if (Numer0nChangeEnum.NOT_CLEAR.getAbb().equals(changeHighLowInfo)) {
			return Numer0nChangeEnum.NOT_CLEAR.getAbb();
		}

		Integer zero = Anything.convertStringToInteger(Numer0nSelectNumberEnum.ZERO.getNum());
		Integer four = Anything.convertStringToInteger(Numer0nSelectNumberEnum.FOUR.getNum());
		Integer five = Anything.convertStringToInteger(Numer0nSelectNumberEnum.FIVE.getNum());
		Integer nine = Anything.convertStringToInteger(Numer0nSelectNumberEnum.NINE.getNum());

		// 数字を調べどこかの桁にHIGHorLOWが混じっていたらOK
		boolean tf = false;
		if (Numer0nChangeEnum.DONT_TEACH_INDEX.getAbb().equals(digit)) {
			Iterator<String> ite = numList.iterator();
			while (ite.hasNext()) {
				Integer iteInt = Anything.convertStringToInteger(ite.next());
				if (Const.LOW.equals(changeHighLowInfo)) {
					if (0 >= zero.compareTo(iteInt) &&
							0 <= four.compareTo(iteInt)) {
						tf = true;
					}
				} else {
					if (0 >= five.compareTo(iteInt) &&
							0 <= nine.compareTo(iteInt)) {
						tf = true;
					}
				}
			}
			// 桁がわかっている場合、その桁がHIGHorLOWに一致していたらOK
		} else {
			String re = numList.get(Anything.convertStringToInteger(digit));
			Integer reInt = Anything.convertStringToInteger(re);
			if (Const.LOW.equals(changeHighLowInfo) &&
					0 >= zero.compareTo(reInt) &&
					0 <= four.compareTo(reInt)) {
				tf = true;
			} else if (Const.HIGH.equals(changeHighLowInfo) &&
					0 >= five.compareTo(reInt) &&
					0 <= nine.compareTo(reInt)) {
				tf = true;
			}
		}

		if (tf) {
			return Const.CONSISTENT;
		} else {
			return Const.NOT_CONSISTENT;
		}

	}

	/**
	 * 条件に合う数値リストを返却する
	 * @return conditionNumberList 条件に合う数値リスト
	 */
	public ArrayList<String> getConditionNumberList() {
		return conditionNumberList;
	}

}
