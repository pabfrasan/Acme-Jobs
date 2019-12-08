
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messages.Message;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select m from Message m where m.id =?1")
	Message findOneById(Integer id);

	@Query("select m.messages from MessageThread m where m.id = ?1")
	Collection<Message> findMessageOfThread(Integer id);

	@Query("select m.id from MessageThread m where ?1 in m.messages")
	Integer findMessageThreadByMessage(Integer messageId);

	@Query("select u.id from UserAccount u where u in (select m.users from MessageThread m where m.id = ?1)")
	Collection<Integer> usersOfThread(Integer threadId);
}
