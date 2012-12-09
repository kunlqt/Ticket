
package com.pdg.ticket.utils;

import org.json.JSONException;
import org.json.JSONObject;

import com.pdg.ticket.Global.Constants;
import com.pdg.ticket.model.OilTicket;

public class JSONParser {
    public static void getTicketFromJson(String json) {
        JSONObject jObject;
        try {
            jObject = new JSONObject(json);
            JSONObject jTicketType = jObject.getJSONObject(Constants.TICKET_TYPE);
            OilTicket ticket = new OilTicket();
            ticket.number = jTicketType.getString(OilTicket.FIELDS.NUMBER);
            ticket.date = jTicketType.getString(OilTicket.FIELDS.DATE);
            int customer_id = jTicketType.getInt(OilTicket.FIELDS.CUSTOMER_ID);
            ticket.customer = jTicketType.getString(OilTicket.FIELDS.CUSTOMER_ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
