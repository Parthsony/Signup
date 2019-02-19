package org.parthinfotech.utility;

import org.modelmapper.ModelMapper;
import org.parthinfotech.dto.SignupDto;

public class DtoUtility {

	private DtoUtility() {
	}

	public static Object convertModelToDto(Object model, Object dto, ModelMapper mapper) {
		return mapper.map(model, dto.getClass());
	}

	public static Object convertDtoToModel(SignupDto newUserDto, Class<?> modelClass, ModelMapper mapper) {
		return mapper.map(newUserDto, modelClass);
	}

}
