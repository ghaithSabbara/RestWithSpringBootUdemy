package com.restapi.restwithspringbootudemy.converters.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.restapi.restwithspringbootudemy.data.models.Person;
import com.restapi.restwithspringbootudemy.data.vo.PersonVO2;

@Service
public class PersonConverter {

	public PersonVO2 convertEntityToVO(Person person) {
		PersonVO2 vo = new PersonVO2();
		vo.setBirthday(new Date());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setAddress(person.getAddress());
		vo.setGender(person.getGender());
		vo.setId(person.getId());
		return vo;
	}

	public Person convertVOToEntity(PersonVO2 vo) {
		Person entity = new Person();
		entity.setFirstName(vo.getFirstName());
		entity.setLastName(vo.getLastName());
		entity.setAddress(vo.getAddress());
		entity.setGender(vo.getGender());
		entity.setId(vo.getId());
		return entity;
	}

}
