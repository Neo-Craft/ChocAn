package org.chocan;

import org.chocan.dao.ManagerDao;
import org.chocan.dao.MemberDao;
import org.chocan.dao.ProviderDao;
import org.chocan.dao.ServiceDao;

public class Database {

    public static MemberDao MEMBERS;
    public static ProviderDao PROVIDERS;
    public static ServiceDao SERVICES;
    public static ManagerDao MANAGERS;

    public static void load(){
        PROVIDERS = new ProviderDao();
        SERVICES = new ServiceDao();
        MEMBERS = new MemberDao();
        MANAGERS = new ManagerDao();
    }

    public static void save(){
        System.out.println("Database save starts...");
        MEMBERS.save();
        PROVIDERS.save();
        SERVICES.save();
        MANAGERS.save();
        System.out.println("End of database save");
    }

}
