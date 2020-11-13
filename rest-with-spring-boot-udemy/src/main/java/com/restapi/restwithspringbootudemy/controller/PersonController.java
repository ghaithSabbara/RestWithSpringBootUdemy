package com.restapi.restwithspringbootudemy.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.restwithspringbootudemy.data.vo.PersonVO;
import com.restapi.restwithspringbootudemy.data.vo.PersonVO2;
import com.restapi.restwithspringbootudemy.services.PersonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
// @CrossOrigin(origins = { "http://localhost:8080" })
@Api(value = "Person Endpoint", tags = { "PersonEndpoint" })
@RequestMapping("/api/person")
public class PersonController {

	@Autowired
	PersonService personServcie;

	@Autowired
	PagedResourcesAssembler<PersonVO> assembler;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
	@RequestMapping(value = "/v1/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO findPersonVO(@PathVariable("id") Long id) {
		return personServcie.findById(id);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
	@GetMapping(value = "/v1/find", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO findPersonVO2(@RequestParam(value = "id", defaultValue = "2") Long id) {
		PersonVO personVO = personServcie.findById(id);
		personVO.add(linkTo(methodOn(PersonController.class).findPersonVO2(id)).withSelfRel());
		return personVO;
	}

	@GetMapping(value = "/v1", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {

		Direction directionSort = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;

		Pageable pageable = PageRequest.of(page, limit, Sort.by(directionSort, "firstName"));
		Page<PersonVO> personnes = personServcie.findAll(pageable);
		personnes.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findPersonVO(p.getKey())).withSelfRel()));
		PagedResources<?> resources = assembler.toResource(personnes);

		return new ResponseEntity<>(resources, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
	@ApiOperation(value = "Find a specific person by name")
	@GetMapping(value = "/findPersonByName/{firstName}", produces = { "application/json", "application/xml",
			"application/x-yaml" })
	public ResponseEntity<?> findPersonByName(@PathVariable("firstName") String firstName,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {

		Direction directionSort = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;

		Pageable pageable = PageRequest.of(page, limit, Sort.by(directionSort, "firstName"));

		Page<PersonVO> persons = personServcie.findPersonByName(firstName, pageable);
		persons.stream()
				.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findPersonVO(p.getKey())).withSelfRel()));

		PagedResources<?> resources = assembler.toResource(persons);

		return new ResponseEntity<>(resources, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "/v1", produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public PersonVO create(@RequestBody PersonVO personVO) {
		PersonVO vo = personServcie.create(personVO);
		vo.add(linkTo(methodOn(PersonController.class).findPersonVO(vo.getKey())).withSelfRel());
		return vo;
	}

	@PostMapping(value = "/v2", produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public PersonVO2 create2(@RequestBody PersonVO2 personVO) {
		return personServcie.create2(personVO);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping(value = "/v1", produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public PersonVO update(@RequestBody PersonVO personVO) {
		PersonVO vo = personServcie.update(personVO);
		vo.add(linkTo(methodOn(PersonController.class).findPersonVO(vo.getKey())).withSelfRel());
		return vo;
	}

	@DeleteMapping("/v1/{id}")
	public void delete(@PathVariable Long id) {
		personServcie.delete(id);
	}

}
