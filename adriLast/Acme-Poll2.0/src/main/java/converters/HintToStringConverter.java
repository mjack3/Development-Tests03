package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Hint;

@Component
@Transactional
public class HintToStringConverter implements Converter<Hint, String>{

	@Override
	public String convert(final Hint poll) {
		String result;

		if (poll == null)
			result = null;
		else
			result = String.valueOf(poll.getId());

		return result;
	}
}
