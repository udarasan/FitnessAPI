package com.perspicaz.jim.modules.workOutPlan;

import com.perspicaz.jim.modules.userProfile.UserProfile;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WorkOutRepository extends PagingAndSortingRepository<WorkOutPlan, Long> {

}
