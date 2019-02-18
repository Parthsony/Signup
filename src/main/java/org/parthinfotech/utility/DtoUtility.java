package org.parthinfotech.utility;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DtoUtility {

	@Autowired
	private static ModelMapper modelMapper;

	private DtoUtility() {
	}

	public static Object convertModelToDto(Object model, Object dto) {
		return modelMapper.map(model, dto.getClass());
	}

	public static Object convertDtoToModel(Object dto, Object model) {
		return modelMapper.map(dto, model.getClass());
	}
}
