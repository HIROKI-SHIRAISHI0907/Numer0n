package application.component.consts;

public class Const {

	// CPU
	public static final String CPU = "CPU";
	// プレーヤー
	public static final String PLAYER = "Player";

	/**
	 * EAT（宣言した数字が桁と使用している数字が正しいならこちら）
	 */
	public static final String EAT = "EAT";

	/**
	 * BITE（宣言した数字が使用している数字のみ正しいならこちら）
	 */
	public static final String BITE = "BITE";

	// Question
	public static final String QUESTION_THREE = "???";
	// GameMaster
	public static final String GAMEMASTER = "GameMaster";
	// Computer
	public static final String COMPUTER = "Computer";
	// バリデーション（バリデーションメッセージ集約用）
	public static final String VALIDATION = "VALIDATION";

	// 5~9の数字
	public static final String HIGH = "HIGH";
	// 0~4の数字
	public static final String LOW = "LOW";
	// 不明
	public static final String NOT_CLEAR = "NOTCLEAR";
	// 一致
	public static final String CONSISTENT = "0";
	// 不一致
	public static final String NOT_CONSISTENT = "1";
	// 合致している
	public static final int MATCH = 0;
	// 合致していない
	public static final int NOT_MATCH = 1;
	// 処理を続ける
	public static final int CONTINUE = 2;
	/**
	 * 交換する
	 */
	public static final String CHANGE_GO = "1";

	/**
	 * 交換しない
	 */
	public static final String CHANGE_REJECT = "2";

	/**
	 * シャッフルする
	 */
	public static final String SHUFFLE_GO = "1";

	/**
	 * シャッフルしない
	 */
	public static final String SHUFFLE_REJECT = "2";

	// アイテム
	public static final String DOUBLE = "DOUBLE";
	public static final String HIGH_LOW = "HIGH&LOW";
	public static final String TARGET = "TARGET";
	public static final String SLASH = "SLASH";
	public static final String SHUFFLE = "SHUFFLE";
	public static final String CHANGE = "CHANGE";
	// NO_OPTION定数（オプションを使用しない）
	public static final String NO_OPTION = "NOOPTION";

	// 空文字
	public static final String EMPTY = "";

	// 難易度
	public static final String EASY = "EASY";
	public static final String NORMAL = "NORMAL";
	public static final String HARD = "HARD";
	public static final String EXHAUSTED = "EXHAUSTED";
	public static final String INSANE = "INSANE";

	// 対象外の桁やフラグ
	public static final int YUUSEN_MINUS = -1;
	// 最優先
	public static final int SAI_YUUSEN_FLAG = 1;
	// 以下優先度2以下
	public static final int YUUSEN_FLAG_2 = 2;
	public static final int YUUSEN_FLAG_3 = 3;
	public static final int YUUSEN_FLAG_4 = 4;
	public static final int YUUSEN_FLAG_5 = 5;
	public static final int YUUSEN_FLAG_6 = 6;

	// EATの文字
	public static final int EAT_START_STRING = 0;
	// BITEの文字
	public static final int BITE_START_STRING = 4;
	// 思い浮かべている数字が5個以下
	public static final int Numer0n_PINCH_NUMBER = 5;

	// ハイフン
	public static final String HYPHEN = "--";

	// 直前の情報用フラグ
	public static final String LAST_INFO_FLAG = "0";
	// 今までの情報用フラグ
	public static final String EVER_INFO_FLAG = "1";
	// 数字作成用フラグ
	public static final String MAKE_NUMBER_FLAG = "2";
	// この数字以外で
	public static final String MAKE_OTHER_THESE_NUMBER = "MAKEOTHERTHISNUMBER";

	// indexを教えない定数
	public static final String DONT_TEACH_INDEX = "DONTTEACHINDEX";
}
