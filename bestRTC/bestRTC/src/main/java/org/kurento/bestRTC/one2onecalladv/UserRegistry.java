package org.kurento.bestRTC.one2onecalladv;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.WebSocketSession;

public class UserRegistry {

  private ConcurrentHashMap<String, UserSession> usersByName = new ConcurrentHashMap<>();
  private ConcurrentHashMap<String, UserSession> usersBySessionId = new ConcurrentHashMap<>();

  public void register(UserSession user) {
    usersByName.put(user.getName(), user);
    usersBySessionId.put(user.getSession().getId(), user);
  }

  public UserSession getByName(String name) {
    return usersByName.get(name);
  }

  public UserSession getBySession(WebSocketSession session) {
    return usersBySessionId.get(session.getId());
  }

  public boolean exists(String name) {
    return usersByName.keySet().contains(name);
  }

  public UserSession removeBySession(WebSocketSession session) {
    final UserSession user = getBySession(session);
    if (user != null) {
      usersByName.remove(user.getName());
      usersBySessionId.remove(session.getId());
    }
    return user;
  }

}
