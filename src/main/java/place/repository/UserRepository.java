package place.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import place.dto.entity.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, String> {

}
