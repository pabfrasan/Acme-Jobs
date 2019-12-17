
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messageThreads.MessageThread;
import acme.entities.messages.Message;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select m from Message m where m.id =?1")
	Message findOneById(Integer id);

	@Query("select m.messages from MessageThread m where m.id = ?1")
	Collection<Message> findMessageOfThread(Integer id);

	@Query("select m.id from MessageThread m join m.messages ms where ms.id = ?1")
	Integer findMessageThreadByMessage(Integer messageId);

	@Query("select u.id from MessageThread m join m.users u where m.id = ?1")
	Collection<Integer> usersOfThread(Integer threadId);

	@Query("select u from UserAccount u where u.id = ?1")
	UserAccount findUserById(int id);

	@Query("select m from MessageThread m where m.id = ?1")
	MessageThread findMessageThreadById(Integer threadId);
}
