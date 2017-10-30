package daZhongDianPing.getFromDaZhong;

import daZhongDianPing.tools.HttpConn;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by shiqun on 2015/11/27.
 */
public class GetAllCategory {

    public ArrayList<String> getAllCategory(String buf) {

        ArrayList<String> allCategory = new ArrayList<String>();
        System.out.println(buf);

        Pattern pattern = Pattern
                .compile("<a href=\"/search/category/6/45/g([0-9]{3,})");


//        //需要重新设置；
//        //Pattern pattern = Pattern
//                //.compile("href=\"/suzhou/hotel/g([0-9]*)");
//        Matcher matcher = pattern.matcher(buf);
//        while (matcher.find()) {
//            for (int i = 0; i < matcher.groupCount(); i++) {
//                String temp = this
//                        .getCategory(matcher.group(i));
//                allCategory.add(temp);
//                System.out.println(matcher.group(i));
//                System.out.println(temp);
//            }
//
//        }


       allCategory.add("g2884");
       allCategory.add("g836");

       // allCategory.add("g3022");
       // allCategory.add("g3024");
       // allCategory.add("g6714");
        //allCategory.add("g172");
        //allCategory.add("g6693");
        //allCategory.add("g25842");
       // allCategory.add("g173");
        //allCategory.add("g174");







        return allCategory;
    }

    public String getCategory(String str)
    {
        StringBuffer temp = new StringBuffer();
        int i=0;
        for(i=0;i<str.length();i++)
        {
            if(str.charAt(i)=='6')
                break;
        }

        for(int j=i;i<str.length();i++)
        {
            if(str.charAt(i)=='g')
                break;
        }

        for(int j=i;j<str.length();j++) {
            if (str.charAt(j) != '"')
                temp.append(str.charAt(j));
            else
                break;
        }

        return temp.toString();
    }

    public static void main(String[] args) throws Exception
    {

        ArrayList<String> allURL = new ArrayList<String>();




        String URL = "http://www.dianping.com/search/category/6/45";

        HttpConn hc = new HttpConn();
        String buf = hc.httpConn(URL);

        new GetAllCategory().getAllCategory(buf);

    }



}
