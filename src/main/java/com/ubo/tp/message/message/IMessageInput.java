package main.java.com.ubo.tp.message.message;

public interface IMessageInput {

	/**
	 * Ajoute un observateur à la session.
	 *
	 * @param observer
	 */
	void addObserver(IMessageInputObserver observer);

	/**
	 * Retire un observateur à la session.
	 *
	 * @param observer
	 */
	void removeObserver(IMessageInputObserver observer);

}
