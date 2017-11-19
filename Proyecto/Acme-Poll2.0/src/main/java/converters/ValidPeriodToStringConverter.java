package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.ValidPeriod;

@Component
@Transactional
public class ValidPeriodToStringConverter implements Converter<ValidPeriod, String>{

	@Override
	public String convert(final ValidPeriod poll) {
		String result;

		if (poll == null)
			result = null;
		else
			result = String.valueOf(poll.getId());

		return result;
	}
}
