package application;

import application.component.consts.Const;
import application.logic.human.Computer;
import application.logic.human.GameMaster;
import application.logic.human.Player;
import application.logic.info.Info;

public class GetInfoClassTest {

	private int turn;
	private GameMaster gameMaster;
	private Info info;
	private Player player;
	private Computer computer;

	public GetInfoClassTest(int turn, GameMaster gameMaster, Player player, Computer computer, Info info) {
		this.turn = turn;
		this.gameMaster = gameMaster;
		this.player = player;
		this.computer = computer;
		this.info = info;
	}

	public void display(String name, String item, int pattern) {
		System.out.println("******************************************");
		System.out.println("******************************************");
		System.out.println(turn+"ターン目,攻撃:"+gameMaster.getName());
		System.out.println("難易度:"+gameMaster.getDifficulty());
		if (pattern == 1) {
			System.out.println(gameMaster.getName()+"が最後の情報を元に数字を整理しました。");
		} else if (pattern == 2) {
			if (Const.CPU.equals(gameMaster.getName())) {
				System.out.println(gameMaster.getName()+"が"+info.getCpuInfoList().size()+"つの情報を元に数字を整理しました。");
			} else {
				System.out.println(gameMaster.getName()+"が"+info.getPlayerInfoList().size()+"つの情報を元に数字を整理しました。");
			}
		} else if (pattern == 3) {
			System.out.println(gameMaster.getName()+"がオプションを使用しました。");
		} else if (pattern == 4) {
			System.out.println(gameMaster.getName()+"がオプションを使用しませんでした。");
		} else if (pattern == 5){
			System.out.println(gameMaster.getName()+"が攻撃オプションをすでに使用していたため、NOOPTIONが選択されます。");
		} else {
			System.out.println(gameMaster.getName()+"が数字をコールしてEATBITE情報を取得しました。");
		}

		if (Const.NO_OPTION.equals(item)) {
			System.out.println("使用したアイテム:なし");
		} else {
			System.out.println("使用したアイテム:"+item);
		}
		if (Const.CPU.equals(name)) {
			System.out.println("使用できるアイテム一覧（攻撃）:"+computer.getOffenseOptionList());
			System.out.println("使用できるアイテム一覧（防御）:"+computer.getDiffenseOptionList());
			System.out.println("宣言した数字:"+info.getDeclaredCpuNumberList().get(info.getDeclaredCpuNumberList().size()-1));
			System.out.println("宣言した数字一覧:"+info.getDeclaredCpuNumberList());
			System.out.println("CPUが得られた情報:"+info.getCpuInfoList());
			System.out.println("CPUの数字の候補:"+computer.getCandidateCpuNumberList());
			System.out.println("PLAYERの正解No."+gameMaster.getCorrectPlayerNumberList());
		} else {
			System.out.println("使用できるアイテム一覧（攻撃）:"+player.getOffenseOptionList());
			System.out.println("使用できるアイテム一覧（防御）:"+player.getDiffenseOptionList());
			System.out.println("宣言した数字:"+info.getDeclaredPlayerNumberList().get(info.getDeclaredPlayerNumberList().size()-1));
			System.out.println("宣言した数字一覧:"+info.getDeclaredPlayerNumberList());
			System.out.println("プレーヤーが得られた情報"+info.getPlayerInfoList());
			System.out.println("プレーヤーの数字の候補:"+player.getCandidatePlayerNumberList());
			System.out.println("CPUの正解No."+gameMaster.getCorrectCpuNumberList());
		}
		System.out.println("******************************************");
		System.out.println("******************************************");

	}

}
