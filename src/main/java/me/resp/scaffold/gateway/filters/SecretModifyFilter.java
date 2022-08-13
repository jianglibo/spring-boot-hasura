package me.resp.scaffold.gateway.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import me.resp.scaffold.CommonHeaderNames;
import me.resp.scaffold.ScaffoldProperties;
import reactor.core.publisher.Mono;

@Component
public class SecretModifyFilter implements GatewayFilter {

  private final ScaffoldProperties appProperties;

  @Autowired
  public SecretModifyFilter(ScaffoldProperties appProperties) {
    this.appProperties = appProperties;
  }

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    ServerHttpRequest req = exchange.getRequest();
    boolean hasAdminSecret = req.getHeaders().containsKey(CommonHeaderNames.HASURA_ADMIN_SECRET);
    if (!hasAdminSecret) {
      String dcsSecret = req.getHeaders().getFirst(CommonHeaderNames.HASURA_DCS_SECRET);

      if (appProperties.getHasura_secret().equals(dcsSecret)) { // change to admin secret.
        req = req.mutate().headers(headers -> {
          headers.set(CommonHeaderNames.HASURA_ADMIN_SECRET, dcsSecret);
          headers.remove(CommonHeaderNames.HASURA_DCS_SECRET);
        }).build();
      }
    }
    return chain.filter(exchange.mutate().request(req).build());
  }
}
