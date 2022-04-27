package cardgame.deck;

import java.util.Collections;

import cardgame.Consts;
import cardgame.bean.GameMasterBean;
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
	 * GameMasterBean
	 */
	GameMasterBean gameMasterBean;

	/**
	 * コンストラクタ
	 *
	 * @param gameMasterBean
	 */
	public BlackJackDeck(GameMasterBean gameMasterBean) {
		this.gameMasterBean = gameMasterBean;
		// 山札作成
		build();
	}

	/**
	 * 山札作成
	 */
	public void build() {

		// GameMasterBeanにセットしたい

		// ♠︎を作成
		createSuit(Consts.SPADE);

		// ❤︎を作成
		createSuit(Consts.HEART);

		// ☘を作成
		createSuit(Consts.CLOVER);

		// ♦︎を作成
		createSuit(Consts.DAIYA);

		// Jocker︎を作成
		// createJocker(Consts.TWO_JOCKER);

		shuffle();
	}

	/**
	 * スートを作成
	 *
	 * @param suit
	 */
	public void createSuit(String suit) {
		for (int i = 1; i <= 13; i++) {
			Card card = new BlackJackCard();
			card.setNum(i);
			card.setSuit(suit);
			card.setRank(Consts.numRankMap.get(i));
			gameMasterBean.setDeck(card);
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
			card.setNum(0);
			card.setSuit("Jocker");
			card.setRank(Consts.numRankMap.get(0));
			gameMasterBean.setDeck(card);
		}
	}

	/**
	 * 山札シャッフル
	 *
	 * @return
	 */
	public void shuffle() {
		Collections.shuffle(gameMasterBean.getDeck());
	}

	/**
	 * デッキの先頭からカードを一枚取り出す（共通にすべき）
	 */
	public Card pick() {

		// デッキの先頭からカードを一枚取り出す
		BlackJackCard card = (BlackJackCard) gameMasterBean.getDeck().get(0);

		// 山札からカードを一枚削除する
		gameMasterBean.getDeck().remove(0);

		return card;
	}
}
