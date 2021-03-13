package com.fitnessfirst.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fitnessfirst.model.City;

public interface CityRepository extends JpaRepository<City, Integer> {

}
