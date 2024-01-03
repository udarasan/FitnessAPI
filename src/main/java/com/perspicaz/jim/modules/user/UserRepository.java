package com.perspicaz.jim.modules.user;

import com.perspicaz.jim.modules.trainer.Trainer;
import com.perspicaz.jim.modules.userProfile.UserProfile;
import com.perspicaz.jim.modules.workOutPlan.WorkOutPlan;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByProfile(UserProfile profile);

    List<User> findAllByTrainer(Trainer trainer);


}
