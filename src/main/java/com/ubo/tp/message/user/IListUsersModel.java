package main.java.com.ubo.tp.message.user;

public interface IListUsersModel {

	/**
	 * Ajoute un observateur à la session.
	 *
	 * @param observer
	 */
	void addObserver(IListUsersModelObserver observer);

	/**
	 * Retire un observateur à la session.
	 *
	 * @param observer
	 */
	void removeObserver(IListUsersModelObserver observer);

}
