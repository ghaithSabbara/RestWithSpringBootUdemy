package com.restapi.restwithspringbootudemy.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.restwithspringbootudemy.data.vo.BookVO;
import com.restapi.restwithspringbootudemy.services.BookService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/book")
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping(value = "/v1/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public BookVO findBookVO(@PathVariable("id") Long id) {
		return bookService.findById(id);
	}

	@ApiOperation(value = "Find All people recorded")
	@GetMapping(value = "/v1", produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<BookVO> findAll() {
		List<BookVO> personnes = bookService.findAll();
		personnes.forEach(p -> p.add(linkTo(methodOn(BookController.class).findBookVO(p.getKey())).withSelfRel()));
		return personnes;
	}

	@PostMapping(value = "/v1", produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public BookVO create(@RequestBody BookVO bookVO) {
		BookVO vo = bookService.create(bookVO);
		vo.add(linkTo(methodOn(BookController.class).findBookVO(vo.getKey())).withSelfRel());
		return vo;
	}

	@PutMapping(value = "/v1", produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public BookVO update(@RequestBody BookVO bookVO) {
		BookVO vo = bookService.update(bookVO);
		vo.add(linkTo(methodOn(BookController.class).findBookVO(vo.getKey())).withSelfRel());
		return vo;

	}

	@DeleteMapping("/v1/{id}")
	public void delete(@PathVariable Long id) {
		bookService.delete(id);
	}

}
