package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Bill;

@Component
@Transactional
public class BillToStringConverter implements Converter<Bill,String>{

	@Override
	public String convert(final Bill poll) {
		String result;

		if (poll == null)
			result = null;
		else
			result = String.valueOf(poll.getId());

		return result;
	}
}
