package com.restapi.restwithspringbootudemy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.restwithspringbootudemy.converter.DozerConverter;
import com.restapi.restwithspringbootudemy.converters.custom.PersonConverter;
import com.restapi.restwithspringbootudemy.data.models.Book;
import com.restapi.restwithspringbootudemy.data.vo.BookVO;
import com.restapi.restwithspringbootudemy.exception.RessourceNotFoundException;
import com.restapi.restwithspringbootudemy.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository repository;

	@Autowired
	PersonConverter converter;

	public BookVO create(BookVO vo) {
		Book entity = DozerConverter.parseObject(vo, Book.class);
		return DozerConverter.parseObject(repository.save(entity), BookVO.class);
	}

	public BookVO update(BookVO vo) {
		Book entity = findBook(vo.getKey());
		entity.setId(vo.getKey());
		entity.setAuthor(vo.getAuthor());
		entity.setLaunchDate(vo.getLaunchDate());
		entity.setPrice(vo.getPrice());
		entity.setTitle(vo.getTitle());
		return DozerConverter.parseObject(repository.save(entity), BookVO.class);
	}

	public void delete(Long id) {
		Book book = findBook(id);
		repository.delete(book);
	}

	public BookVO findById(Long id) {
		return DozerConverter.parseObject(findBook(id), BookVO.class);
	}

	public List<BookVO> findAll() {
		return DozerConverter.parseListObjects(repository.findAll(), BookVO.class);
	}

	private Book findBook(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new RessourceNotFoundException("No Records found for this ID"));
	}
}
