package com.zhangwuji.api.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @program: railway
 * @description: 组织发送数据
 * @author: lijiwen
 * @create: 2019-09-23 15:58
 **/
public class DataUtil {

    /**
     * @return java.lang.String
     * @author: Lijiwen
     * Description:组装查询条件
     * @param: * @param
     * @createDate 2019-12-28 15:43
     **/
    public static String generatDeviceId(String factory, String cityId, String line, String status) {
        //"2105030125FFFF"
        //16进制表示。第一个字节代表省及直辖市编号， 21
        // 举例：0x23代表广东省；第二个字节代表所属城市，如0x06代表广州市； 05
        // 第三个字节代表地铁线路编号，0x01代表1号线； 03
        // 第四个字节代表轨道交通车辆编号，如0x02代表该线路第2号列车；01
        // 第五个字节表示给车辆门编号，如0x26，表示第2节车厢第6号门。此号码为唯一号码，出厂时候配备， 25
        // 对应轨道交通车辆对应的城市、线路、车辆编号等信息。0x23 0x06 0x01 0x02 0x26 0xFF 0xFF （不足14位、用“F”补齐，预留），
        // 表示为广东省广州市1号线第2号列车第2节车厢6号门。
        StringBuilder sb = new StringBuilder();
        //省
        sb.append("21");
        //市
        if (StringUtils.isNotEmpty(cityId)) {
            int city = (Integer.parseInt(cityId, 16) & 0xFF);
            if (city < 10) {
                sb.append(0).append(city);
            } else {
                sb.append(city);
            }
        } else {
            sb.append("00");
        }
        //线路
        if (StringUtils.isNotEmpty(line)) {
            int lines = (Integer.parseInt(line, 16) & 0xFF);
            if (lines < 10) {
                sb.append(0).append(lines);
            } else {
                sb.append(lines);
            }
        } else {
            sb.append("00");
        }

        sb.append("0125FFFF");
        return sb.toString();
    }

    public static byte[] creatDate(String feedback) {
        byte[] bytes = new byte[feedback.length() / 2];
        for (int i = 0; i < feedback.length() / 2; i++) {
            String subStr = feedback.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }
        return bytes;
    }

    /***
     * @author: Lijiwen
     * Description:组织
     * @param feedback
     * @return int[]
     * @createDate
     **/
    public static int[] creatDateInt(String feedback) {
        int[] ints = new int[feedback.length() / 2];
        for (int i = 0; i < feedback.length() / 2; i++) {
            String subStr = feedback.substring(i * 2, i * 2 + 2);
            ints[i] = Integer.parseInt(subStr, 16);
        }
        return ints;
    }

    public static Integer getDataSize(String sizeStrinng) {
        int hight = Integer.parseInt(sizeStrinng.substring(2, 4), 16);
        int low = Integer.parseInt(sizeStrinng.substring(0, 2), 16);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(hight);
        stringBuilder.append(low);
        return Integer.valueOf(stringBuilder.toString());
    }


}
