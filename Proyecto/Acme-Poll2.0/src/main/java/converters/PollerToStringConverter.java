
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Poller;

@Component
@Transactional
public class PollerToStringConverter implements Converter<Poller, String> {

	@Override
	public String convert(final Poller poller) {
		String result;

		if (poller == null)
			result = null;
		else
			result = String.valueOf(poller.getId());

		return result;
	}
}
