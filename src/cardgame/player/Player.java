package cardgame.player;

import java.util.List;

import cardgame.cards.Tramp;

/**
 * ゲームをする参加者を扱う抽象クラス
 *
 * @author kijima
 *
 */
public abstract class Player {

	/**
	 * プレイヤーID
	 */
	public String playerId;

	/**
	 * 名前
	 */
	public String name;

	/**
	 * CPUかどうか
	 */
	public boolean isCPU = false;

	/**
	 * 自分のターンかどうか
	 */
	public boolean isMyTurn = false;

	/**
	 *
	 */
	public boolean isGameEnd = false;

	/**
	 * プレイヤーの作成
	 */
	public abstract void build();

	/**
	 * カードの確認
	 */
	public abstract void showMycard(List<Tramp> cardList);
}
