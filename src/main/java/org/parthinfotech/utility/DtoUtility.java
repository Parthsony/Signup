package org.parthinfotech.utility;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoUtility {

	private static ModelMapper mapper;

	@Autowired
	synchronized void init(ModelMapper mapper) {
		DtoUtility.mapper = mapper;
	}

	private DtoUtility() {
	}

	public static Object dtoToEntity(Object dto, Class<?> entity) {
		return mapper.map(dto, entity);
	}

	public static Object entityToDto(Object entity, Class<?> dto) {
		return mapper.map(entity, dto);
	}

}
