package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Question;

@Component
@Transactional
public class QuestionToStringConverter implements Converter<Question, String>{

	@Override
	public String convert(final Question poll) {
		String result;

		if (poll == null)
			result = null;
		else
			result = String.valueOf(poll.getId());

		return result;
	}
}
