package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Chirp;
import repositories.ChirpRepository;

@Component
@Transactional
public class StringToChirpConverter implements Converter<String, Chirp>{

	@Autowired
	ChirpRepository pollerRepository;


	@Override
	public Chirp convert(final String text) {
		Chirp res;
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
