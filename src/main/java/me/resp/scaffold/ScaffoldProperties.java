package me.resp.scaffold;

import java.nio.file.Path;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "app")
@Configuration
@Getter
@Setter
public class ScaffoldProperties {
  private String output_separator;
  private Path plugin_exec_folder;
  private String hasura_secret;
  private String hasura_callback_secret;
  private String hasura_end_point;
  private RedisProperties redis;
  private long maxExecutorTime;
  private String hasura_base_uri;
  private boolean runuser;
  private boolean pluginDefaultEnabled;
  private boolean authorDefaultEnabled;
  private String testValue;

  public String calHasuraBase() {
    // http://df-hasura-service/v1/graphql
    int i = hasura_end_point.indexOf("/", 9);
    return hasura_end_point.substring(0, i);
  }

  // @PostConstruct
  // void after() throws IOException {
  //   this.plugin_exec_folder = this.plugin_exec_folder.toAbsolutePath().normalize();
  //   if (!Files.exists(plugin_exec_folder)) {
  //     Files.createDirectories(plugin_exec_folder);
  //   }
  // }

  @Data
  public static class RedisProperties {
    private String host;
    private int port;
    private String password;
  }
  
}
