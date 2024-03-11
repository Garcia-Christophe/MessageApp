package main.java.com.ubo.tp.message.message;

public interface IListMessagesModel {

	/**
	 * Ajoute un observateur à la session.
	 *
	 * @param observer
	 */
	void addObserver(IListMessagesModelObserver observer);

	/**
	 * Retire un observateur à la session.
	 *
	 * @param observer
	 */
	void removeObserver(IListMessagesModelObserver observer);

}
