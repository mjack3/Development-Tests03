package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.ValidPeriod;
import repositories.ValidPeriodRepository;

@Component
@Transactional
public class StringToValidPeriodConverter implements Converter<String, ValidPeriod>{

	@Autowired
	ValidPeriodRepository pollerRepository;


	public ValidPeriod convert(final String text) {
		ValidPeriod res;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				res = null;
			else {
				id = Integer.valueOf(text);
				res = this.pollerRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return res;
	}
}
