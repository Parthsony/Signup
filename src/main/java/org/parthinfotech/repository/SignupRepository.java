package org.parthinfotech.repository;

import org.parthinfotech.model.Signup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface SignupRepository extends JpaRepository<Signup, Long> {

	@Nullable
	Signup findByEmailIgnoreCase(String email);

	Long countByEmailIgnoreCase(String email);
	
	Long countByRequestId(String requestId);

	@Nullable
	Signup findByRequestId(String requestId);
}
