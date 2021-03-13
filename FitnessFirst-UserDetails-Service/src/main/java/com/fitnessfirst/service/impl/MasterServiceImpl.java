package com.fitnessfirst.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.fitnessfirst.dao.CityRepository;
import com.fitnessfirst.model.City;
import com.fitnessfirst.service.MasterService;

import lombok.Getter;
import lombok.Setter;
@Service
public class MasterServiceImpl implements MasterService{
  @Autowired
  @Getter
  @Setter
  private CityRepository cityRepository;
	@Override
	public List<City> fetchCity() {
		// TODO Auto-generated method stub
		List<City> cities = this.cityRepository.findAll();
		System.out.println("inside fetchcity"+cities.size());
		return cities;
	}

}
