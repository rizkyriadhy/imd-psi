package com.imdglobal.psi.api;

import com.imdglobal.psi.api.database.LocalData;
import com.google.gson.reflect.TypeToken;
import com.imdglobal.psi.api.entities.PsiByDate;

/**
 * Created by rizkyriadhy on 19/06/17.
 */
public class ImdGlobalPSILocalData {

    /**
     * method to save psi by date
     *
     * @param psiByDate
     * @return
     */
    public static boolean savePsiDate(PsiByDate psiByDate) {
        return LocalData.saveLocalData(LocalData.Type.PSI_DATE, psiByDate);
    }

    /**
     * method to save psi by date and times
     *
     * @param psiByDateTime
     * @return
     */
    public static boolean savePsiDateTime(PsiByDate psiByDateTime) {
        return LocalData.saveLocalData(LocalData.Type.PSI_DATETIME, psiByDateTime);
    }

    /**
     * method to get data psi by date
     *
     * @return
     */
    public static PsiByDate getPsiDate() {
        return LocalData.getLocalData(LocalData.Type.PSI_DATE, new TypeToken<PsiByDate>() {
        }.getType());
    }

    /**
     * method to get data psi by date and times
     *
     * @return
     */
    public static PsiByDate getPsiDateTime() {
        return LocalData.getLocalData(LocalData.Type.PSI_DATETIME, new TypeToken<PsiByDate>() {
        }.getType());
    }

    /**
     * method to clear all cache
     *
     */
    public static void clearAllCache() {
        LocalData.clearAllCache();
    }

    /**
     * method to clear some cache
     *
     * @param type
     */
    public static void clearCache(String type) {
        LocalData.clearCache(type);
    }
}

