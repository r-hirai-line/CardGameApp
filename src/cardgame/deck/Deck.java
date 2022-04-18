package cardgame.deck;

import java.util.List;

import cardgame.card.Card;

/**
 * Deckの抽象クラス
 *
 * ※各ゲームの山札クラスはこのクラスを継承すること
 *
 * @author kijima
 *
 */
public abstract class Deck {

	/**
	 * 山札
	 */
	public List<Card> deck;

	/**
	 * 山札作成
	 *
	 * @return
	 */
	public abstract void build();

	/**
	 * スートを作成
	 *
	 * @param suit
	 */
	public abstract void createSuit(String suit);

	/**
	 * ジョーカーを作成
	 *
	 * @param count：生成するジョーカーの枚数を入力して下さい。
	 */
	public abstract void createJocker(int count);

	/**
	 * 山札シャッフル
	 *
	 * @return
	 */
	public abstract void shuffle();

	/**
	 * デッキの先頭からカードを一枚取り出す
	 */
	public abstract Card pick();
}
