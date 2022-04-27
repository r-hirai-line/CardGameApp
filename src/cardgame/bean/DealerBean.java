package cardgame.bean;

import java.util.ArrayList;
import java.util.List;

import cardgame.card.Card;

public class DealerBean {

	/**
	 * 持ち札
	 */
	private List<Card> hand = new ArrayList<>();

	/**
	 * 得点
	 */
	private int score = 0;

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(Card card) {
		this.hand.add(card);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
