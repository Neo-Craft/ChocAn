package org.chocan;

import org.chocan.dao.MemberDao;
import org.chocan.dao.ProviderDao;
import org.chocan.dao.ServiceDao;

public class Database {

    public static MemberDao MEMBERS;
    public static ProviderDao PROVIDERS;
    public static ServiceDao SERVICES;


    public static void load(){
        PROVIDERS = new ProviderDao();
        SERVICES = new ServiceDao();
        MEMBERS = new MemberDao();
    }

    public static void save(){
        System.out.println("Database save starts...");
        MEMBERS.save();
        PROVIDERS.save();
        SERVICES.save();
        System.out.println("End of database save");
    }

}
