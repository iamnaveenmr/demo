package com.nalashaa.junitTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nalashaa.junitTest.entity.Skill;

public interface ISkillRepository extends JpaRepository<Skill, Long>{

}
