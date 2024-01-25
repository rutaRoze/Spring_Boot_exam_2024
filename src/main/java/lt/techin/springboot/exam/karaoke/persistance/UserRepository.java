package lt.techin.springboot.exam.karaoke.persistance;

import lt.techin.springboot.exam.karaoke.persistance.modal.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserRecord, String> {
}
