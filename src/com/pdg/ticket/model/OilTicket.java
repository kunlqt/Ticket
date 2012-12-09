
package com.pdg.ticket.model;

public class OilTicket {
    public class FIELDS {
        public static final String TICKET_ID = "ticket_id";

        public static final String NUMBER = "number";

        public static final String DATE = "date";

        public static final String CUSTOMER_ID = "customer_id";

        public static final String OPERATOR = "operator";

        public static final String LEASENAME = "leasename";

        public static final String STATE = "state";

        public static final String COUNTY = "country";

        public static final String LOCATION_14_1 = "location141";

        public static final String LOCATION_14_2 = "location142";

        public static final String LOCATION_UNIT_LTR = "locationunitltr";

        public static final String LOCATION_SECTION = "locationsection";

        public static final String LOCATION_TOWNSHIP_1 = "locationtowship1";

        public static final String LOCATION_TOWNSHIP_2 = "locationtowship2";

        public static final String LOCATION_TOWNSHIP_3 = "locationtowship3";

        public static final String LOCATION_TOWN_DEC = "locationtowdec";

        public static final String LOCATION_TOWN_DIR_NS = "locationtowdirns";

        public static final String LOCATION_RANGE_1 = "locationrange1";

        public static final String LOCATION_RANGE_2 = "locationrange2";

        public static final String LOCATION_RANGE_3 = "locationrange3";

        public static final String LOCATION_RANGE_DEC = "locationrandec";

        public static final String LOCATION_RANGE_DIR_NS = "locationrandirns";

        public static final String LOCATION_MERIDIAN_1 = "locationmeridian1";

        public static final String LOCATION_MERIDIAN_2 = "locationmeridian2";

        public static final String FLAC_NO = "flacno";

        public static final String DISTRICT_NO = "districtno";

        public static final String FEDERA_NO = "federano";

        public static final String UNIQUE_LEASE_NO = "unique_lease_no";

        public static final String TANK_TYPE = "tanktype";

        public static final String TANK_NO = "tankno";

        public static final String TANK_SIZE = "tanksize";

        public static final String _1ST_LEVEL_FEET = "1stlevelfeet";

        public static final String _1ST_LEVEL_INCHES = "1stlevelinches";

        public static final String _1ST_LEVEL_14IN = "1stlevel14in";

        public static final String _1ST_TEMP = "1sttemp";

        public static final String _1ST_BBLS = "1stbbls";

        public static final String _1ST_BSW_FEET = "1stswfeet";

        public static final String _1ST_BSW_INCHES = "1stswinches";

        public static final String _2ND_LEVEL_FEET = "2stlevelfeet";

        public static final String _2ND_LEVEL_INCHES = "2stlevelinches";

        public static final String _2ND_LEVEL_14IN = "2stlevel14in";

        public static final String _2ND_TEMP = "2sttemp";

        public static final String _2ND_BBLS = "2ndbbls";

        public static final String _2ND_BSW_FEET = "2stswfeet";

        public static final String _2ND_BSW_INCHES = "2stswinches";

        public static final String EST_BARRELS = "estbarrels";

        public static final String OBSERVED_GTY = "observedgty";

        public static final String OBSERVED_TEMP = "observedtemp";

        public static final String BSW = "bsw";

        public static final String TRUCKED_BY = "truckby";

        public static final String TRUCKED_TO = "truckto";

        public static final String TRUCK_NUMBER = "trucknumber";

        public static final String TRAILER_NUMBER = "trailernumber";

        public static final String TURNED_ON_DRIVER_SIGNATURE = "turned_on_driver_sig";

        public static final String TURNED_ON_WITNESS_SIGNATURE = "turned_on_witness_sig";

        public static final String TURNED_ON_TIME = "turned_on_time";

        public static final String TURNED_ON_OFF_SEAL = "turned_on_off_seal";

        public static final String SHUT_OFF_DRIVER_SIGNATURE = "shut_off_driver_sig";

        public static final String SHUT_OFF_WITNESS_SIGNATURE = "shut_off_witness_sig";

        public static final String SHUT_OFF_TIME = "shut_off_time";

        public static final String SHUT_OFF_ON_SEAL = "shut_off_on_seal";

        public static final String SHUT_OFF_DATE = "shut_off_date";

        public static final String NO_UNIT_TYPE_1 = "nounittype1";

        public static final String HM_1 = "hm1";

        public static final String PROPER_1 = "proper1";

        public static final String NET_BARRELS = "netbarrels1";

        public static final String TANK_REFUSAL = "tank_refuse";

        public static final String REASON_FOR_REJECTION = "reason_for_rejection";

        public static final String OIL_HEIGHT_FEET = "oilheight_feet";

        public static final String OIL_HEIGHT_INCHES = "oilheight_inch";

        public static final String CONNECTION_HEIGHT_FEET = "connectionheight_feet";

        public static final String CONNECTION_HEIGHT_INCHES = "connectionheight_inch";

        public static final String TRUCK = "truck";

        public static final String PIPELINE = "pipeline";

        public static final String OTHER = "other";

        public static final String TOTAL_HOURS = "totalhour";

        public static final String BILLABLE_HOURS = "billablehour";

        public static final String GROSS_BARRELS_2 = "gross2";

        public static final String NET_BARRELS_2 = "netbarrels2";

        public static final String TRUE_GVT = "truegvt2";

        public static final String COMMON_REMARK = "common_remark";

        public static final String REMARKS = "remarks";
    }

    public String id;

    public String number;

    public String date;

    public String customer;

    public String operator;

    public String leasename;

    public String state;

    public String county;

    public String location_14_1;

    public String location_14_2;

    public String location_unit_ltr;

    public String location_unit_section;

    public String location_township;

    public String location_township_dec;

    public String location_township_dir;

    public String location_range;

    public String location_range_dec;

    public String location_range_dir;

    public String location_meridian;

    public String lease_no;

    public String district_no;

    public String federal;

    public String unique_lease_no;

    public String tank_no;

    public String tank_size;

    public String _1st_level_feet;

    public String _1st_level_inches;

    public String _1st_level_14in;

    public String _1st_temp;

    public String _1st_bbls;

    public String _1st_bsw_feet;

    public String _1st_bsw_inchs;

    public String _2nd_level_feet;

    public String _2nd_level_inches;

    public String _2nd_level_14in;

    public String _2nd_temp;

    public String _2nd_bbls;

    public String _2nd_bsw_feet;

    public String _2nd_bsw_inches;

    public String est_barrels;

    public String observed_gty;

    public String observed_temp;

    public String bsw;

    public String trucked_by;

    public String trucked_to;

    public String truck_number;

    public String trailer_number;

    public String turned_on_driver_sig;

    public String turned_on_witness_sig;

    public String turned_on_time;

    public String turned_on_off_seal;

    public String shut_off_driver_sig;

    public String shut_off_witness_sig;

    public String shut_off_time;

    public String shut_off_on_seal;

    public String shut_off_date;

    public String no_unit_type;

    public String hm;

    public String proper_shipping_name;

    public String net_barrels1;

    public boolean tank_refusal;

    public String reason_for_rejection;

    public String oil_height_feet;

    public String oil_height_inches;

    public String conn_height_feet;

    public String conn_height_inches;

    public boolean truck;

    public boolean pipeline;

    public boolean other;

    public String total_hours;

    public String billable_hours;

    public String gross_barrels;

    public String net_barrels2;

    public String true_gvt;

    public String common_remark;

    public String remark;

}
