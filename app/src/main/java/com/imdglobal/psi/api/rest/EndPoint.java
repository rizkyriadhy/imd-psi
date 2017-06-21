package com.imdglobal.psi.api.rest;

import com.imdglobal.psi.utils.ImdGlobalPSIConst;
import com.imdglobal.psi.utils.ImdGlobalPSISession;

/**
 * Created by rizkyriadhy on 19/06/17.
 */
public class EndPoint {
    private static final String PRODUCTION_DOMAIN = "https://api.data.gov.sg/";
    private static final String DEVELOPMENT_DOMAIN = "https://api.data.gov.sg/";
    private static final String STAGING_DOMAIN = "https://api.data.gov.sg/";
    private static final String VERSION = "v1/";
    private static final String ENVIRONMENT = "environment/";

    public final static String psi = "psi";

    public static String getDomain(){
        return (ImdGlobalPSISession.getSession(ImdGlobalPSISession.SessionName.ACTIVE_ENV).compareToIgnoreCase(ImdGlobalPSIConst.API_ENV_DEVELOPMENT)==0)?
                DEVELOPMENT_DOMAIN:
                ((ImdGlobalPSISession.getSession(ImdGlobalPSISession.SessionName.ACTIVE_ENV).compareToIgnoreCase(ImdGlobalPSIConst.API_ENV_STAGING)==0)?STAGING_DOMAIN:PRODUCTION_DOMAIN);
    }

    public static String getApiBaseUri(){
        return getDomain() + VERSION + ENVIRONMENT;
    }
}
