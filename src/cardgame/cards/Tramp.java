package cardgame.cards;

import java.util.List;

public abstract class Tramp implements Cards {

	/**
	 * スート（クラブ・スペード・ダイヤ・ハートの4つのマーク）
	 */
	public String suit;

	/**
	 * 数字
	 */
	public int num;

	/**
	 * ランク
	 */
	public String rank;

	/**
	 * 山札作成
	 *
	 * @return
	 */
	public abstract List<Tramp> build();
}
