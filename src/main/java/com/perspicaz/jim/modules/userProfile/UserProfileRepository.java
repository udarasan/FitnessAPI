package com.perspicaz.jim.modules.userProfile;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface UserProfileRepository extends PagingAndSortingRepository<UserProfile, Long> {
    UserProfile findOneByEmailAndPassword(String email,String password);
}
