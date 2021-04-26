package vebProektEshop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vebProektEshop.model.User;
import vebProektEshop.model.WishList;
import vebProektEshop.model.enumerations.WishListStatus;

import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
    Optional<WishList> findByUserAndStatus(User user, WishListStatus status);
}
