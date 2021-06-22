package com.paypal.reports.reportsextract.service.impl;

import com.paypal.infrastructure.util.DateUtil;
import com.paypal.reports.reportsextract.service.HMCFileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of {@link HMCFileService}
 */
@Slf4j
@Profile({ "!qa" })
@Service
public class HMCFileServiceImpl extends AbstractHmcFileService implements HMCFileService {

	private static final String DATE_FORMAT = "yyyy-MM-dd_HH-mm";

	private static final String CSV_EXTENSION = ".csv";

	private static final String SLASH = "/";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String saveCSVFile(final String path, final String prefixFileName, final List<String> lines,
			final String headers) {
		try {
			if (Objects.nonNull(lines)) {
				final String dateString = DateUtil.convertToString(LocalDateTime.now(), DATE_FORMAT);
				final String fileName = prefixFileName + "_" + dateString + CSV_EXTENSION;
				Path filePath = Paths.get(path + SLASH + fileName);
				return printCSVFile(filePath, headers, fileName, lines);
			}
		}
		catch (IOException ex) {
			log.error("There was an error trying to close file", ex);
		}
		return StringUtils.EMPTY;
	}

}
