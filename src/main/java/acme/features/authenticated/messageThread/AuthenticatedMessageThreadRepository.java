
package acme.features.authenticated.messageThread;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messageThreads.MessageThread;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageThreadRepository extends AbstractRepository {

	@Query("select m from MessageThread m where m.id =?1")
	MessageThread findOneById(Integer id);

	@Query("select u from UserAccount u where u.id = ?1")
	UserAccount findPrincipal(int id);

	@Query("select m from MessageThread m join m.users use where use = ?1")
	Collection<MessageThread> findMyMessageThread(UserAccount user);

	@Query("select m.users from MessageThread m where m.id = ?1")
	Collection<UserAccount> usersOfThread(Integer threadId);
}
