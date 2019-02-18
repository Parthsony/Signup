package org.parthinfotech;

import org.parthinfotech.model.Signup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignupRepository extends JpaRepository<Signup, Long> {

	boolean findByEmailContainingIgnoreCase(String email);

}
