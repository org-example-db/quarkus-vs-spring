package eu.davidemartorana.performance.quarkus.sched;

import io.quarkus.scheduler.Scheduled;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ScheduledActions {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledActions.class);

    private static final int lengthFrame = 120;
    private static final int lengthText = lengthFrame - 4;
    private static final String FRAME = StringUtils.repeat('*', lengthFrame);

    private void printFancyText(final String text) {
        final String centeredText;

        if(text.length() > lengthText ) {
            centeredText = StringUtils.abbreviate(text,"...", lengthText);
        } else if ( text.length() == lengthText) {
            centeredText = text;
        } else {
            centeredText = StringUtils.center(text, lengthText);
        }

        LOGGER.info(FRAME);
        LOGGER.info("* {} *", centeredText);
        LOGGER.info(FRAME);
    }

    @Scheduled(cron = "{cron.scheduler.expr}")
    public void cronExpressionTrigger(){
        this.printFancyText("Firing from statically defined cron expression. Triggering method 'cronExpressionTrigger'.");
    }

    @Scheduled(delay = 5, delayUnit = TimeUnit.SECONDS, every = "PT15S")
    public void delayTrigger() {
        this.printFancyText("Firing from delay express in seconds. Triggering method 'delayTrigger'.");
    }

    @Scheduled(every = "30s")
    public void periodicTrigger(){
        this.printFancyText("Firing from statically defined period. Triggering method 'periodicTrigger'.");
    }

}
