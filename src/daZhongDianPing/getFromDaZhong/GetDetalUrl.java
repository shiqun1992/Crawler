package daZhongDianPing.getFromDaZhong;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shiqun on 2015/11/26.
 */
//获取每页中所有商家ID
public class GetDetalUrl {

    public void get(String buf,ArrayList<String> PageUrl) {

        //匹配某一页中每组商家ID；
        Pattern pattern = Pattern
                .compile("\"_blank\" href=\"/shop/([0-9]{7,10})\" rel=\"nofollow\"");



        Matcher matcher = pattern.matcher(buf);

        while (matcher.find())
        {
            for(int i=0;i<matcher.groupCount();i++) {
                //避免重复，获取商家ID之后判断是否重复；
                String number=this.getNumber(matcher.group(i));
                if(this.isExist(PageUrl,number)==1)
                {
                    PageUrl.add(number);
                    System.out.println(number);
                }

            }

        }

    }

    //提取商家ID；
    public String getNumber(String str)
    {
        StringBuffer temp = new StringBuffer();
        for(int i=0;i<str.length();i++)
        {
            if(str.charAt(i)<='9' && str.charAt(i)>='0')
                temp.append(str.charAt(i));
        }
        return temp.toString();
    }

    //判断之前商家是否存在；
    public int isExist(ArrayList<String> detalUrl,String str)
    {
        for(int i=0;i<detalUrl.size();i++)
        {
            if(str.equals(detalUrl.get(i)))
                return 0;
        }
        return 1;
    }

}
