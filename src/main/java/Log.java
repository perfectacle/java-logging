import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
    private static final Logger logger = LoggerFactory.getLogger(Log.class);

    public static void main(String[] args) {
        logger.warn("위험해!!");
        logger.info("넌 저장 안됨 ㅋㅋ");
    }
}
