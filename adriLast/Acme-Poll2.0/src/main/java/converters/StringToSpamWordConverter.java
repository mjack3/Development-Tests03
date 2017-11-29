package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import domain.SpamWord;
import repositories.SpamWordRepository;

public class StringToSpamWordConverter implements Converter<String, SpamWord> {

	@Autowired
	SpamWordRepository pollRepository;


	@Override
	public SpamWord convert(final String text) {
		SpamWord res;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				res = null;
			else {
				id = Integer.valueOf(text);
				res = this.pollRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return res;
	}
}
