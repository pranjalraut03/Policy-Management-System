package com.policymanagament.policyplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.policymanagament.policyplan.entities.MyUser;
 
@Repository
public interface MyUserRepository extends JpaRepository<MyUser, String>
{
 
}