package com.imdglobal.psi.api.rest.data;

import com.imdglobal.psi.api.entities.PsiByDate;

/**
 * Created by rizkyriadhy on 19/06/17.
 */
public class PsiByDates {

    public static class Request {
        public String date;

        public Request(String date) {
            this.date = date;
        }
    }

    public static class Response extends PsiByDate {
    }

}
