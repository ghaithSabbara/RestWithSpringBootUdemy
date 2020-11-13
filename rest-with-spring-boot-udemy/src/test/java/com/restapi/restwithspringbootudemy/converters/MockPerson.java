package com.restapi.restwithspringbootudemy.converters;

import java.util.ArrayList;
import java.util.List;

import com.restapi.restwithspringbootudemy.data.models.Person;
import com.restapi.restwithspringbootudemy.data.vo.PersonVO;

public class MockPerson {

	public Person mockEntity() {
		return mockEntity(0);
	}

	public PersonVO mockVO() {
		return mockVO(0);
	}

	public List<Person> mockEntityList() {
		List<Person> personnes = new ArrayList<Person>();
		for (int i = 0; i < 4; i++) {
			personnes.add(mockEntity(i));
		}
		return personnes;
	}

	public List<PersonVO> mockVOList() {
		List<PersonVO> personnes = new ArrayList<PersonVO>();
		for (int i = 0; i < 4; i++) {
			personnes.add(mockVO(i));
		}
		return personnes;
	}

	private Person mockEntity(Integer number) {
		Person person = new Person();
		person.setId(number.longValue());
		person.setAddress("Address Test : " + number);
		person.setFirstName("Ghaith : " + number);
		person.setLastName("Sabbara : " + number);
		person.setGender(((number % 2) == 0) ? "Male" : "Femme");
		return person;
	}

	private PersonVO mockVO(Integer number) {
		PersonVO person = new PersonVO();
		person.setKey(number.longValue());
		person.setAddress("Address Test : " + number);
		person.setFirstName("Ghaith : " + number);
		person.setLastName("Sabbara : " + number);
		person.setGender(((number % 2) == 0) ? "Male" : "Femme");
		return person;
	}
}
