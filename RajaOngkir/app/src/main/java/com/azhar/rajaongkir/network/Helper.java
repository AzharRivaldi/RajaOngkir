package com.azhar.rajaongkir.network;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Azhar Rivaldi on 25-12-2020
 */

public class Helper {
    public static boolean tiki = false;
    public static boolean jne = false;
    public static boolean pos = false;

    public static String formatRupiah(int price) {
        Locale localeID = new Locale("in", "ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(localeID);
        return format.format(price);
    }
}
