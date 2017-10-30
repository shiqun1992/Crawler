package daZhongDianPing.tools;


import daZhongDianPing.beans.Store;
import daZhongDianPing.tools.decrypt.Analysis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by JQ-Cao on 15/4/28.
 */
public class Dazhong {

    private Store store;

    public Store analysisDazhong(String ID,String buf) {

        store = new Store();
        store.setShopID(ID);

        Pattern pattern = Pattern
                .compile("<h1 class=\"shop-name\">(.+?)<a");
        Matcher matcher = pattern.matcher(buf);
        if (matcher.find())
            store.setShopName(matcher.group(1));

        pattern = Pattern.compile("province=(.+?);");
        matcher = pattern.matcher(buf);
        if (matcher.find())
            store.setProvince(matcher.group(1));

        pattern = Pattern.compile("city=(.+?);");
        matcher = pattern.matcher(buf);
        if (matcher.find())
            store.setCity(matcher.group(1));

        //有的没有区域信息；
        pattern = Pattern
                .compile("<div class=\"expand-info address\" itemprop=\"street-address\">(.+?)</div>");
        matcher = pattern.matcher(buf);
        if(matcher.find()) {
            pattern = Pattern
                    .compile("itemprop=\"locality region\">(.+?)</span>");
            matcher = pattern.matcher(buf);
            if (matcher.find())
                store.setRegion(matcher.group(1));
        }

        //有三种地址格式；
        //1、
        pattern = Pattern.compile("itemprop=\"street-address\" title=\"(.+?)\">");
        matcher = pattern.matcher(buf);
        if (matcher.find())
            store.setAddress(matcher.group(1));

        //2、
        pattern = Pattern.compile("<p class=\"shop-address\">地址：(.+?)(\\s*)<span>");
        matcher = pattern.matcher(buf);
        if (matcher.find())
            store.setAddress(matcher.group(1));

        //3、
        pattern = Pattern.compile("itemprop=\"street-address\" title=\"(.+?)\">");
        matcher = pattern.matcher(buf);
        if (matcher.find())
            store.setAddress(matcher.group(1));

        pattern = Pattern.compile("itemprop=\"tel\">(.+?)</span>");
        matcher = pattern.matcher(buf);
        String tel="";
        while (matcher.find())
            tel = tel + "   " + matcher.group(1);
        store.setTel(tel);


        pattern = Pattern.compile("<span class=\"info-name\">" +
                "别&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</span>\n" +
                "            <span class=\"item\">(.+?)</span>");
        matcher = pattern.matcher(buf);
        if (matcher.find())
            store.setAnotherName(matcher.group(1));

        pattern = Pattern.compile("class=\"info-name\">营业时间：</span>\n" +
                "                <span class=\"item\">(.+?)</span>");
        matcher = pattern.matcher(buf);
        if (matcher.find())
            store.setShopHours(matcher.group(1));

        pattern = Pattern.compile("餐厅简介：</span>(.+?)</p>");
        matcher = pattern.matcher(buf);
        if (matcher.find())
            store.setIntroduce(matcher.group(1));

        pattern = Pattern.compile("rel=\"tag\" target=\"_blank\">(.+?)</a>");
        matcher = pattern.matcher(buf);
        String shopTag="";
        while (matcher.find())
            shopTag = shopTag + "  " + matcher.group(1);
        store.setShopTag(shopTag);

        //价格有两种标签；
        pattern = Pattern.compile("class=\"item\">人均：(.+?)</span>");
        matcher = pattern.matcher(buf);
        if (matcher.find())
            store.setPrice(matcher.group(1));

        pattern = Pattern.compile("class=\"item\">均价：(.+?)</span>");
        matcher = pattern.matcher(buf);
        if (matcher.find())
            store.setDelicious(matcher.group(1));

        pattern = Pattern.compile("class=\"item\">费用：(.+?)</span>");
        matcher = pattern.matcher(buf);
        if (matcher.find())
            store.setDelicious(matcher.group(1));


        pattern = Pattern.compile("class=\"item\">口味：(.+?)</span>");
        matcher = pattern.matcher(buf);
        if (matcher.find())
            store.setDelicious(matcher.group(1));

        pattern = Pattern.compile("class=\"item\">环境：(.+?)</span>");
        matcher = pattern.matcher(buf);
        if (matcher.find())
            store.setEnvironment(matcher.group(1));

        pattern = Pattern.compile("class=\"item\">服务：(.+?)</span>");
        matcher = pattern.matcher(buf);
        if (matcher.find())
            store.setService(matcher.group(1));

        pattern = Pattern.compile("poi: \"(.+?)\"");
        matcher = pattern.matcher(buf);
        if (matcher.find()) {
            Analysis anal = new Analysis();
            //解密位置信息；
            double lngLat[] = anal.analysis(matcher.group(1).toString());
            store.setPOI("[" + lngLat[0] + " " + lngLat[1] + "]");
        }

        pattern = Pattern.compile("itemprop=\"url\">(.+?)</a>");
        matcher = pattern.matcher(buf);
        String shopClass="";
        while (matcher.find())
            shopClass = shopClass + "  "
                    + matcher.group(1).replaceAll("\\s{1,}", " ")
                    .replace("\n", "");
        store.setShopClass(shopClass);

        store.setMenu(this.getMenu(buf));

        return store;
    }

    public String getMenu(String buf) {
        StringBuffer information = new StringBuffer();

        Pattern pattern = Pattern.compile("class=\"recommend-item\">(.+?)</div>");
        Matcher matcher = pattern.matcher(buf);

        information.append("\n推荐菜：\n");
        while (matcher.find()) {
            pattern = Pattern.compile("target=\"_blank\">(.+?)</span>");
            Matcher matcher1 = pattern.matcher(matcher.group(1));
            while (matcher1.find()) {
                information.append("  "
                        + matcher1.group(1).replaceAll("[<>\"=/[a-zA-Z]]", "")
                        .replaceAll("\\s{1,}", " ") + "\n");
            }
        }

        pattern = Pattern.compile("class=\"menus-mode\" id=\"(.+?)menus-bottom");
        matcher = pattern.matcher(buf);
        while (matcher.find()) {
            information.append("\n"
                    + matcher.group(1).substring(0, matcher.group(1).indexOf("\"")) + "：\n");

            pattern = Pattern.compile("dish-name=\"(.+?)</em>");
            Matcher matcher1 = pattern.matcher(matcher.group(1));
            while (matcher1.find()) {
                information.append(delRepeat(matcher1.group(1)
                        .replaceAll("[<>\"=/[a-zA-Z]]", "")
                        .replaceAll("\\s{1,}", " ")
                        .replace("&;", "")
                        .replace("-", "")) + "\n");
            }
        }
        return information.toString();
    }

    public String delRepeat(String sub) {
        String result = "";
        String s[] = sub.split(" ");
        for (int i = 1; i < s.length; ++i)
            result = result + s[i] + " ";
        return result;
    }

}
