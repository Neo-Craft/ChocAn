package org.chocan;

import org.chocan.dao.MemberDao;
import org.chocan.dao.ProviderDao;
import org.chocan.dao.ServiceDao;

public class Database {

    public static MemberDao MEMBERS;
    public static ProviderDao PROVIDERS;
    public static ServiceDao SERVICES;


    public static void load(){
        MEMBERS = new MemberDao();
        PROVIDERS = new ProviderDao();
        SERVICES = new ServiceDao();
    }

}
