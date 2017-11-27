package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Instance;

@Component
@Transactional
public class InstanceToStringConverter implements Converter<Instance, String>{

	@Override
	public String convert(final Instance poll) {
		String result;

		if (poll == null)
			result = null;
		else
			result = String.valueOf(poll.getId());

		return result;
	}
}
