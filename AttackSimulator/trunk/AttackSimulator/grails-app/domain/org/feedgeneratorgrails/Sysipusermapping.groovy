package org.feedgeneratorgrails

class Sysipusermapping {
    
    Integer id;
    Integer secuserid;
    Integer userid;
    String ipaddress1;
    String ipaddress2;
    String ipaddress3;
    
    static constraints = {
        id blank: false
        secuserid blank: false
        userid blank: false
        ipaddress1 blank: false
        ipaddress2 blank: false
        ipaddress3 blank: false
    }
}
