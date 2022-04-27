package cardgame.bean;

import java.util.ArrayList;
import java.util.List;

import cardgame.card.BlackJackCard;

public class BlackJackPlayerBean {

	/**
	 * プレイヤーID
	 */
	private String playerId = "";

	/**
	 * 名前
	 */
	private String name = "";

	/**
	 * 持ち札
	 */
	private List<BlackJackCard> hand = new ArrayList<>();

	/**
	 * 得点
	 */
	private int score = 0;

	/**
	 * CPUかどうか
	 */
	private boolean isCPU = false;

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BlackJackCard> getHand() {
		return hand;
	}

	public void setHand(BlackJackCard card) {
		this.hand.add(card);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isCPU() {
		return isCPU;
	}

	public void setCPU(boolean isCPU) {
		this.isCPU = isCPU;
	}
}
