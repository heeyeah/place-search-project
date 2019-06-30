package place.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import place.entity.UserInfo;

public interface DataRepository extends JpaRepository<UserInfo, String> {

}
