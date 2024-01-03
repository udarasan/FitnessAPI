package com.perspicaz.jim.modules.trainer;

import com.perspicaz.jim.modules.user.User;
import com.perspicaz.jim.modules.userProfile.UserProfile;
import com.perspicaz.jim.modules.workOutPlan.WorkOutPlan;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TrainerRepository extends PagingAndSortingRepository<Trainer, Long> {
    Trainer findByProfile(UserProfile profile);
}
