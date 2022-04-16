package cardgame;

import java.util.LinkedHashMap;
import java.util.Map;

public class Consts {

	/**
	 * 改行コード
	 */
	public static final String CRLF = System.getProperty("line.separator");

	// ブラックジャック関連のルールなどをEnumでもつ

	/**
	 * ブラックジャック：001
	 */
	public static final String BLACK_JACK = "001";

	/**
	 * 大富豪：002
	 */
	public static final String DAIFUGO = "002";

	/**
	 * スペード
	 */
	public static final String SPADE = "♠︎";

	/**
	 * ダイヤ
	 */
	public static final String DAIYA = "♦︎";

	/**
	 * クローバー
	 */
	public static final String CLOVER = "☘";

	/**
	 * ハート
	 */
	public static final String HEART = "❤︎";

	/**
	 * ジョーカー1枚
	 */
	public static final int ONE_JOCKER = 1;

	/**
	 * ジョーカー2枚
	 */
	public static final int TWO_JOCKER = 2;

	/**
	 * 持ち札：2枚
	 */
	public static final int BLACK_JACK_HAND = 2;

	/**
	 * 記号と数字をマッピング
	 */
	@SuppressWarnings("serial")
	public static final Map<Integer, String> numRankMap = new LinkedHashMap<Integer, String>() {
		{
			put(0, "Jocker");
			put(1, "A");
			put(2, "2");
			put(3, "3");
			put(4, "4");
			put(5, "5");
			put(6, "6");
			put(7, "7");
			put(8, "8");
			put(9, "9");
			put(10, "10");
			put(11, "J");
			put(12, "Q");
			put(13, "K");
		}
	};

}
