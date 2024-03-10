package application.component.consts;

public class Const {

	// CPU残留
	public static final String CPU = "CPU";
	// プレーヤー残留
	public static final String PLAYER = "Player";

	/**
	 * EAT（宣言した数字が桁と使用している数字が正しいならこちら）残留
	 */
	public static final String EAT = "EAT";

	/**
	 * BITE（宣言した数字が使用している数字のみ正しいならこちら）残留
	 */
	public static final String BITE = "BITE";

	// Question残留
	public static final String QUESTION_THREE = "???";
	// GameMaster残留
	public static final String GAMEMASTER = "GameMaster";
	// Computer残留
	public static final String COMPUTER = "Computer";
	// バリデーション（バリデーションメッセージ集約用）残留
	public static final String VALIDATION = "VALIDATION";

	// 5~9の数字残留
	public static final String HIGH = "HIGH";
	// 0~4の数字残留
	public static final String LOW = "LOW";
	// 一致残留
	public static final String CONSISTENT = "0";
	// 不一致残留
	public static final String NOT_CONSISTENT = "1";
	// 合致している残留
	public static final int MATCH = 0;
	// 合致していない残留
	public static final int NOT_MATCH = 1;
	// ハイフン残留
	public static final String HYPHEN = "--";

	// EATの文字残留
	public static final int EAT_START_STRING = 0;
	// BITEの文字残留
	public static final int BITE_START_STRING = 4;
	// 思い浮かべている数字が5個以下残留
	public static final int Numer0n_PINCH_NUMBER = 5;

	// この数字以外で残留
	public static final String MAKE_OTHER_THESE_NUMBER = "MAKEOTHERTHISNUMBER";
}
