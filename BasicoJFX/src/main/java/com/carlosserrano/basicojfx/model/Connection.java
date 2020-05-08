package com.carlosserrano.basicojfx.model;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CONNECTION")
@XmlAccessorType(XmlAccessType.FIELD)
public class Connection {

    //@XmlElement(name="host")
    private String host;
    private String db;
    private String user;
    private String password;

    public Connection(String host, String db, String user, String password) {
        this.host = host;
        this.db = db;
        this.user = user;
        this.password = password;
    }

    public Connection() {
        this("", "", "", "");
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Connection{" + "host=" + host + ", db=" + db + ", user=" + user + ", password=" + password + '}';
    }

    public void loadDataXML() {
        String file = "conf.xml";
        File f = new File(file);
        if (f.canRead()) {
            JAXBContext context;
            try {
                context = JAXBContext.newInstance(Connection.class);
                Unmarshaller um = context.createUnmarshaller();
                Connection miconextion = (Connection) um.unmarshal(f);
                this.host = miconextion.host;
                this.db = miconextion.db;
                this.user = miconextion.user;
                this.password = miconextion.password;
            } catch (JAXBException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            System.out.println("Archivo no válido");
        }

    }

}
