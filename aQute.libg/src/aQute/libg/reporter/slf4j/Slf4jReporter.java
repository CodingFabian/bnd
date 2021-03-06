package aQute.libg.reporter.slf4j;

import static aQute.libg.slf4j.GradleLogging.LIFECYCLE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aQute.lib.strings.Strings;
import aQute.libg.reporter.ReporterAdapter;
import aQute.service.reporter.Reporter;
public class Slf4jReporter extends ReporterAdapter {
	final Logger logger;

	public Slf4jReporter(Class< ? > loggingClass) {
		logger = LoggerFactory.getLogger(loggingClass);
	}

	public Slf4jReporter() {
		logger = LoggerFactory.getLogger("default");
	}

	public SetLocation error(String format, Object... args) {
		SetLocation location = super.error(format, args);
		if (logger.isErrorEnabled()) {
			logger.error("{}", Strings.format(format, args));
		}
		return location;
	}

	public SetLocation warning(String format, Object... args) {
		SetLocation location = super.warning(format, args);
		if (logger.isWarnEnabled()) {
			logger.warn("{}", Strings.format(format, args));
		}
		return location;
	}

	/**
	 * @deprecated Use SLF4J Logger.debug instead.
	 */
	@Deprecated
	public void trace(String format, Object... args) {
		super.trace(format, args);
		if (isTrace()) {
			if (logger.isInfoEnabled()) {
				logger.info("{}", Strings.format(format, args));
			}
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("{}", Strings.format(format, args));
			}
		}
	}

	/**
	 * @deprecated Use SLF4J
	 *             Logger.info(aQute.libg.slf4j.GradleLogging.LIFECYCLE)
	 *             instead.
	 */
	@Deprecated
	public void progress(float progress, String format, Object... args) {
		super.progress(progress, format, args);
		if (logger.isInfoEnabled(LIFECYCLE)) {
			logger.info(LIFECYCLE, "{}", Strings.format(format, args));
		}
	}

	public SetLocation exception(Throwable t, String format, Object... args) {
		SetLocation location = super.exception(t, format, args);
		if (logger.isErrorEnabled()) {
			logger.error("{}", Strings.format(format, args), t);
		}
		return location;
	}

	public static Reporter getAlternative(Class< ? > class1, Reporter reporter) {
		if (reporter == null)
			return new Slf4jReporter(class1);
		else
			return reporter;
	}

}
