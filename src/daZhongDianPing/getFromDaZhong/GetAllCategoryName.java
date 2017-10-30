package daZhongDianPing.getFromDaZhong;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shiqun on 2015/11/27.
 */
public class GetAllCategoryName {


    public ArrayList<String> get(String buf) {

        ArrayList<String> allCategoryName = new ArrayList<String>();
        System.out.println(buf);

//        Pattern pattern = Pattern
//                .compile("<a href=\"/search/category/6/30/g(.*?)o3");

        //需要重新设置；
        Pattern pattern = Pattern
                .compile("<span>(.+?)</span></a>");
        Matcher matcher = pattern.matcher(buf);

        while (matcher.find()) {
            for (int i = 0; i < matcher.groupCount(); i++) {

                String temp = matcher.group(i);
                allCategoryName.add(temp);
                System.out.println(temp);
            }

        }

        return allCategoryName;
    }

    public static void main(String[] args) throws Exception
    {

        //new GetAllCategoryName().get()



    }

}
