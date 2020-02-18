package io.VideoClub.Model;

import java.time.LocalDateTime;

public interface IClient {
    String getID();  //is UNIQUE in SYSTEM
    String getName();
    void setName(String n);
    LocalDateTime getTime();
    void setTime(LocalDateTime t);
    String getPhone();
    void setPhone(String p);

}
