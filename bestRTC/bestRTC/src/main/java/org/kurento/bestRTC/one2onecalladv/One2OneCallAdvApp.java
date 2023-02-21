package org.kurento.bestRTC.one2onecalladv;

import org.kurento.client.KurentoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@SpringBootApplication
@EnableWebSocket
public class One2OneCallAdvApp implements WebSocketConfigurer {

  static final String DEFAULT_APP_SERVER_URL = "https://localhost:8443";

  @Bean
  public CallHandler callHandler() {
    return new CallHandler();
  }

  @Bean
  public UserRegistry registry() {
    return new UserRegistry();
  }

  @Bean
  public KurentoClient kurentoClient() {
    return KurentoClient.create();
  }

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(callHandler(), "/call");
  }

  public static void main(String[] args) throws Exception {
    SpringApplication.run(One2OneCallAdvApp.class, args);
  }
}
