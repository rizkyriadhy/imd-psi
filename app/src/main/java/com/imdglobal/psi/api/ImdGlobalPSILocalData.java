package com.imdglobal.psi.api;

import com.imdglobal.psi.api.database.LocalData;
import com.google.gson.reflect.TypeToken;
import com.imdglobal.psi.api.entities.PsiByDate;

/**
 * Created by rizkyriadhy on 19/06/17.
 */
public class ImdGlobalPSILocalData {

    public static boolean savePsiDate(PsiByDate psiByDate) {
        return LocalData.saveLocalData(LocalData.Type.PSI_DATE, psiByDate);
    }

    public static boolean savePsiDateTime(PsiByDate psiByDateTime) {
        return LocalData.saveLocalData(LocalData.Type.PSI_DATETIME, psiByDateTime);
    }

    public static PsiByDate getPsiDate() {
        return LocalData.getLocalData(LocalData.Type.PSI_DATE, new TypeToken<PsiByDate>() {
        }.getType());
    }

    public static PsiByDate getPsiDateTime() {
        return LocalData.getLocalData(LocalData.Type.PSI_DATETIME, new TypeToken<PsiByDate>() {
        }.getType());
    }

    public static void clearAllCache() {
        LocalData.clearAllCache();
    }

    public static void clearCache(String type) {
        LocalData.clearCache(type);
    }
}

