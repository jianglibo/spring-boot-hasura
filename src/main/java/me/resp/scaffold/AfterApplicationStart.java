package me.resp.scaffold;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AfterApplicationStart {

  @Autowired
  private Environment environment;

  @EventListener(ApplicationReadyEvent.class)
  public void after() {
    log.info("Application started in profile(s) {}",
        String.join(", ", environment.getActiveProfiles()));
  }
}
