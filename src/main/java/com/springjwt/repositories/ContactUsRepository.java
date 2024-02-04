package com.springjwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springjwt.entities.ContactUs;

@Repository
public interface ContactUsRepository extends JpaRepository<ContactUs,Long> {

}
