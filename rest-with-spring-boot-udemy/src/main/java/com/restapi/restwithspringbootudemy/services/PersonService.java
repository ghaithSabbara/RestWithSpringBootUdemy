package com.restapi.restwithspringbootudemy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.restapi.restwithspringbootudemy.converter.DozerConverter;
import com.restapi.restwithspringbootudemy.converters.custom.PersonConverter;
import com.restapi.restwithspringbootudemy.data.models.Person;
import com.restapi.restwithspringbootudemy.data.vo.PersonVO;
import com.restapi.restwithspringbootudemy.data.vo.PersonVO2;
import com.restapi.restwithspringbootudemy.exception.RessourceNotFoundException;
import com.restapi.restwithspringbootudemy.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	PersonRepository repository;

	@Autowired
	PersonConverter converter;

	public PersonVO create(PersonVO personVO) {
		Person entity = DozerConverter.parseObject(personVO, Person.class);
		return DozerConverter.parseObject(repository.save(entity), PersonVO.class);
	}

	public PersonVO2 create2(PersonVO2 personVO2) {
		Person person = converter.convertVOToEntity(personVO2);
		return converter.convertEntityToVO(repository.save(person));
	}

	public PersonVO update(PersonVO personVO) {
		Person entity = findPerson(personVO.getKey());
		entity.setId(personVO.getKey());
		entity.setFirstName(personVO.getFirstName());
		entity.setLastName(personVO.getLastName());
		entity.setGender(personVO.getGender());
		entity.setAddress(personVO.getAddress());
		return DozerConverter.parseObject(repository.save(entity), PersonVO.class);
	}

	public void delete(Long id) {
		Person person = findPerson(id);
		repository.delete(person);
	}

	public PersonVO findById(Long id) {
		return DozerConverter.parseObject(findPerson(id), PersonVO.class);
	}

	public Page<PersonVO> findAll(Pageable pageable) {
		Page<Person> personnes = repository.findAll(pageable);
		return personnes.map(this::convertToPersonneVO);
	}

	public Page<PersonVO> findPersonByName(String firstName, Pageable pageable) {
		Page<Person> page = repository.findPersonByName(firstName, pageable);
		return page.map(this::convertToPersonneVO);
	}

	private PersonVO convertToPersonneVO(Person entity) {
		return DozerConverter.parseObject(entity, PersonVO.class);
	}

	private Person findPerson(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new RessourceNotFoundException("No Records found for this ID"));
	}
}
