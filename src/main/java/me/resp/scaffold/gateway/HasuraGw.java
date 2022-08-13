package me.resp.scaffold.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import me.resp.scaffold.ScaffoldProperties;
import me.resp.scaffold.gateway.filters.SecretModifyFilter;

@Configuration
public class HasuraGw {
  @Bean
  public RouteLocator routes(RouteLocatorBuilder builder, ScaffoldProperties appProperties,
      SecretModifyFilter secretModifyFilter) {
    return builder.routes().route("hasura", r -> r.path("/v1/graphql").filters(f ->
    // f.modifyRequestBody(
    // String.class,
    // String.class,
    // (exchange, s) -> {
    // return Mono.just(s);
    // })
    f.filter(secretModifyFilter)).uri(appProperties.calHasuraBase())
    // r.host("*")
    // .filters(
    // f ->
    // f.prefixPath("/v1/graphql")
    // .modifyRequestBody(
    // String.class,
    // String.class,
    // MediaType.APPLICATION_JSON_VALUE,
    // (exchange, s) -> {
    // return Mono.just(s);
    // }))
    // .uri(appProperties.getHasura_base_uri())

    ).build();
  }
}
