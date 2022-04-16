package cardgame.dealer;

import java.util.ArrayList;
import java.util.List;

import cardgame.card.Card;

public class Dealer {

	/**
	 * 持ち札
	 */
	public List<Card> hand;

	/**
	 * 得点
	 */
	public int score;

	public Dealer() {
		hand = new ArrayList<>();
		score = 0;
	}
}
