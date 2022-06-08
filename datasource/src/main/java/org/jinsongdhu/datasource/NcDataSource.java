package org.jinsongdhu.datasource;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NcDataSource {

    public static void main(String[] args) throws Exception{


        System.out.println(System.currentTimeMillis());

        Thread.sleep(1000);

        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        Date d = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(simpleDateFormat.format(d));
    }

}
