package log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Log {
    private Logger logger;

    public Log() {
        logger = LoggerFactory.getLogger(Log.class);

        logger.warn("위험해!!");
        logger.info("넌 저장 안됨 ㅋㅋ");
    }

    public Logger getLogger() {
        return logger;
    }
}
