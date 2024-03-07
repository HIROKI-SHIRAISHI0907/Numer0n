package application.logic.info;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import org.springframework.stereotype.Service;

import application.component.anything.Anything;
import application.component.consts.Const;
import application.logic.human.Computer;
import application.logic.human.GameMaster;
import application.logic.human.Player;
import lombok.RequiredArgsConstructor;

/**
 * 得られた全ての情報に対して候補の数字かそうでないか確認するクラス
 * @author shiraishitoshio
 */
@Service
@RequiredArgsConstructor
public class Numer0nSpecifyNumberMultipleConfirmationImpl implements Numer0nSpecifyNumberMultipleConfirmation {

	/**
	 * GameMaster
	 */
	private final GameMaster gameMaster;

	/**
	 * Computer
	 */
	private final Computer computer;

	/**
	 * Player
	 */
	private final Player player;

	/**
	 * Numer0nInfoImpl
	 */
	private final Numer0nInfo info;

	/**
	 * Numer0nSpecifyNumber
	 */
	private final Numer0nSpecifyNumber aiSpecifyNumber;

	//	/**
	//	 * 得られた情報に対して他の情報にもマッチするか確認するクラス
	//	 * @param gameMaster ゲームマスタークラス
	//	 * @param player プレーヤークラス
	//	 * @param computer コンピュータクラス（CandidateCpuNumberList取得用）
	//	 * @param info 情報クラス（今まで得られた情報取得用）
	//	 */
	//	public FulfillOtherInfo(GameMaster gameMaster, Player player, Computer computer, Info info) {
	//		if (Const.CPU.equals(gameMaster.getName())) {
	//			aiSpecifyNumber = new AiSpecifyNumber(gameMaster, null, computer, null);
	//			// 候補でなかったものも対象
	//			computer.getCandidateCpuNumberList().addAll(computer.getNotCandidateCpuNumberList());
	//			for (String can: computer.getCandidateCpuNumberList()) {
	//				this.candidateList.add(can);
	//			}
	//			this.everGetInfoList = info.getCpuInfoList();
	//		} else {
	//			aiSpecifyNumber = new AiSpecifyNumber(gameMaster, player, null, null);
	//			// 候補でなかったものも対象
	//			player.getCandidatePlayerNumberList().addAll(player.getNotCandidatePlayerNumberList());
	//			for (String can: player.getCandidatePlayerNumberList()) {
	//				this.candidateList.add(can);
	//			}
	//			this.everGetInfoList = info.getPlayerInfoList();
	//		}
	//	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void arrangeCandidateNumberMultipleConfirmationLogic() {
		// 候補及び候補でないリストを整理
		ArrayList<String> arrangeNumberMultipleList = setArrangeNumberMultipleList();
		// 情報リストを整理
		ArrayList<String> infoMultipleList = setArrangeNumberMultipleInfoList();

		// 除去リスト
		ArrayList<String> exceptionList = new ArrayList<String>();
		for (String candNum : arrangeNumberMultipleList) {
			// 今まで得られた情報用ループを遡る
			boolean exceptionFlag = false;
			for (String infoStr : infoMultipleList) {
				// 得られた情報を一旦リスト化する
				ArrayList<String> infoList = Anything.splitComma(infoStr);
				// 先頭に使用したオプションがセットされているためそれによって処理を分ける
				switch (infoList.get(0)) {
					// オプション使用なしの場合
					case Const.NO_OPTION:
						// setNoneOptionAddMethodに渡し、結果を取得
						int everNoneOpResult = this.aiSpecifyNumber
							.setNoneOptionAddCandidateMethod(
									infoStr,
									candNum,
									Const.EVER_INFO_FLAG);
						if (everNoneOpResult == Const.NOT_MATCH) {
							exceptionFlag = true;
						}
						break;
						// その他のオプションの場合
					default:
						int everOpResult = this.aiSpecifyNumber
							.setOptionAddCandidateMethod(
									infoStr,
									candNum,
									Const.EVER_INFO_FLAG);
						if (everOpResult == Const.NOT_MATCH) {
							exceptionFlag = true;
						}
						break;
				}

				// NOT_MATCHがでてしまった時点で候補ではないのでbreak
				// 1つでも取得した情報に合致しない数字であれば候補でない数字に移動対象
				if (exceptionFlag) {
					exceptionList.add(candNum);
					break;
				}
			}
		}

		// 重複がある場合削除
		exceptionList = new ArrayList<String>(new LinkedHashSet<>(exceptionList));

		// 除去リスト（候補でないリスト）に入ったものを削除
		arrangeNumberMultipleList.removeAll(exceptionList);
		// 重複がある場合削除
		arrangeNumberMultipleList = new ArrayList<String>(new LinkedHashSet<>(arrangeNumberMultipleList));

		// 候補、候補でないリストにセットし直し
		if (Const.CPU.equals(this.gameMaster.getName())) {
			for (String can: arrangeNumberMultipleList) {
				this.computer.addCandidateNumberList(can);
			}
			for (String can: exceptionList) {
				this.computer.addNotCandidateNumberList(can);
			}
		} else {
			for (String can: arrangeNumberMultipleList) {
				this.player.addCandidateNumberList(can);
			}
			for (String can: exceptionList) {
				this.player.addNotCandidateNumberList(can);
			}
		}

	}

	/**
	 * 当ロジックで使用する過去に整理した候補数字リスト、候補でない数字リストを一時変数に格納する
	 */
	private ArrayList<String> setArrangeNumberMultipleList() {
		ArrayList<String> arrangeNumberMultipleList = new ArrayList<String>();
		if (Const.CPU.equals(this.gameMaster.getName())) {
			// 候補でなかったものも対象
			this.computer.getCandidateCpuNumberList().addAll(this.computer.getNotCandidateCpuNumberList());
			for (String can : this.computer.getCandidateCpuNumberList()) {
				arrangeNumberMultipleList.add(can);
			}
		} else {
			// 候補でなかったものも対象
			this.player.getCandidatePlayerNumberList().addAll(this.player.getNotCandidatePlayerNumberList());
			for (String can : this.player.getCandidatePlayerNumberList()) {
				arrangeNumberMultipleList.add(can);
			}
		}
		return arrangeNumberMultipleList;
	}

	/**
	 * 当ロジックで使用するinfo情報を一時変数に格納する
	 */
	private ArrayList<String> setArrangeNumberMultipleInfoList() {
		ArrayList<String> infoMultipleList = new ArrayList<String>();
		if (Const.CPU.equals(this.gameMaster.getName())) {
			infoMultipleList = this.info.getCpuInfoList();
		} else {
			infoMultipleList = this.info.getPlayerInfoList();
		}
		return infoMultipleList;
	}

}
