package cardgame.player;

import cardgame.exception.SystemErrorException;

/**
 * ゲームをする参加者を扱う抽象クラス
 *
 * @author kijima
 *
 */
public abstract class Player {

	/**
	 * プレイヤーの作成
	 */
	public abstract void build();

	/**
	 * プレイヤーの動作
	 * 
	 * @throws SystemErrorException
	 */
	public abstract void action() throws SystemErrorException;
}
