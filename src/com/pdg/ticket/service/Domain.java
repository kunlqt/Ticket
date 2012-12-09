
package com.pdg.ticket.service;

import com.pdg.ticket.Global.Constants;

public class Domain {
    // Server for development
    private static final String SERVER_URL = "http://awtyticketsdev.alsocreativedev.com/";

    // Server
    //    public static final String SERVER_URL = "http://awtytickets.alsocreativedev.com/";

    // API
    //    public static final String SERVICES_URL = SERVER_URL + "awty_tickets/services/";

    // SERVICE API for developer
    public static final String SERVICES_URL = SERVER_URL + "services/";

    // Login
    public static final String LOGIN_URL = SERVICES_URL + "login?username=%s&password=%s";

    public static String getUrlLoadTicket(String idTicket) {
        return Domain.SERVICES_URL + Constants.RUN_TICKET + "?id=" + idTicket;
    }

}
