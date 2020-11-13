package com.restapi.restwithspringbootudemy.converters;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.restapi.restwithspringbootudemy.converter.DozerConverter;
import com.restapi.restwithspringbootudemy.data.vo.PersonVO;

public class DozerConverterTest {

	MockPerson inputObject;

	@Before
	public void setUp() {
		inputObject = new MockPerson();
	}

	@Test
	public void parseEntityToVOTest() {
		PersonVO output = DozerConverter.parseObject(inputObject.mockEntity(), PersonVO.class);
		Assert.assertEquals(Long.valueOf(0L), output.getKey());
		Assert.assertEquals("Address Test : 0", output.getAddress());
		Assert.assertEquals("Ghaith : 0", output.getFirstName());
		Assert.assertEquals("Sabbara : 0", output.getLastName());
		Assert.assertEquals("Male", output.getGender());
	}

	@Test
	public void parseListEntityToListVOTest() {
		List<PersonVO> output = DozerConverter.parseListObjects(inputObject.mockEntityList(), PersonVO.class);

		Assert.assertEquals(Long.valueOf(0L), output.get(0).getKey());
		Assert.assertEquals("Address Test : 0", output.get(0).getAddress());
		Assert.assertEquals("Ghaith : 0", output.get(0).getFirstName());
		Assert.assertEquals("Sabbara : 0", output.get(0).getLastName());
		Assert.assertEquals("Male", output.get(0).getGender());

		Assert.assertEquals(Long.valueOf(3L), output.get(3).getKey());
		Assert.assertEquals("Address Test : 3", output.get(3).getAddress());
		Assert.assertEquals("Ghaith : 3", output.get(3).getFirstName());
		Assert.assertEquals("Sabbara : 3", output.get(3).getLastName());
		Assert.assertEquals("Femme", output.get(3).getGender());
	}

}
