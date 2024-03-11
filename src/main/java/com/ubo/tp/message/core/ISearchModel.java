package main.java.com.ubo.tp.message.core;

public interface ISearchModel {

	/**
	 * Ajoute un observateur à la session.
	 *
	 * @param observer
	 */
	void addObserver(ISearchModelObserver observer);

	/**
	 * Retire un observateur à la session.
	 *
	 * @param observer
	 */
	void removeObserver(ISearchModelObserver observer);

}
