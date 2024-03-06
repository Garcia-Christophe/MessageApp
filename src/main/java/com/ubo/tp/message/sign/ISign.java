package main.java.com.ubo.tp.message.sign;

import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.session.ISessionObserver;

public interface ISign {

	/**
	 * Ajoute un observateur à la session.
	 *
	 * @param observer
	 */
	void addObserver(ISignObserver observer);

	/**
	 * Retire un observateur à la session.
	 *
	 * @param observer
	 */
	void removeObserver(ISignObserver observer);

	/**
	 * @return l'utilisateur connecté.
	 */
	User getConnectedUser();

}
