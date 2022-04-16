package cardgame.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cardgame.Consts;
import cardgame.card.BlackJackCard;
import cardgame.card.Card;

/**
 * BlackJack山札クラス
 *
 * @author kijima
 *
 */
public class BlackJackDeck extends Deck {

	/**
	 * 山札
	 */
	public List<Card> deck;

	/**
	 * コンストラクタ
	 */
	public BlackJackDeck() {

		// 山札生成
		this.deck = new ArrayList<>();

		// 山札作成
		build();
	}

	/**
	 * 山札作成
	 */
	public void build() {

		// ♠︎を作成
		createSuit(Consts.SPADE);

		// ❤︎を作成
		createSuit(Consts.HEART);

		// ☘を作成
		createSuit(Consts.CLOVER);

		// ♦︎を作成
		createSuit(Consts.DAIYA);

		// Jocker︎を作成
		createJocker(Consts.TWO_JOCKER);

		shuffle();
	}

	/**
	 * スートを作成
	 *
	 * @param suit
	 */
	public void createSuit(String suit) {
		for (int i = 1; i <= 13; i++) {
			BlackJackCard card = new BlackJackCard();
			card.num = i;
			card.suit = suit;
			card.rank = Consts.numRankMap.get(i);
			this.deck.add(card);
		}
	}

	/**
	 * ジョーカーを作成
	 *
	 * @param count：生成するジョーカーの枚数を入力して下さい。
	 */
	public void createJocker(int count) {
		for (int i = 0; i < count; i++) {
			BlackJackCard card = new BlackJackCard();
			card.num = 0;
			card.suit = "Jocker";
			card.rank = Consts.numRankMap.get(0);
			this.deck.add(card);
		}
	}

	/**
	 * 山札シャッフル
	 *
	 * @return
	 */
	public void shuffle() {
		Collections.shuffle(this.deck);
	}

	/**
	 * デッキの先頭からカードを一枚取り出す
	 */
	public Card pick() {

		// デッキの先頭からカードを一枚取り出す
		BlackJackCard card = (BlackJackCard) this.deck.get(0);

		// デッキからカードを一枚削除する
		this.deck.remove(0);

		return card;
	}

	/**
	 * getter
	 *
	 * @return
	 */
	public List<Card> getDeck() {
		return deck;
	}
}
