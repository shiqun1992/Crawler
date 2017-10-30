package daZhongDianPing.getFromDaZhong;

import daZhongDianPing.tools.HttpConn;
import daZhongDianPing.tools.CaptureHtml;

import java.util.ArrayList;

/**
 * Created by shiqun on 2015/11/26.
 */
public class GetURL {

    //找寻某个小分类下的50页所有的ID
    //小分类：http://www.dianping.com/search/category/6/30/g141o3
    public ArrayList<String> getStoreIdByCategory(String categoryUrl) throws Exception {

        HttpConn hc = new HttpConn();

        //小类每个分类的每一个页的URL
        ArrayList<String> pagetUrl = new ArrayList<String>();
        pagetUrl.add(""+"?aid=d216e1fa595cbcd7ce871909dde3e272f285bac29dbf6931656d1e1ecf969e1b52127a5129aca21b64a72bb1370e0f906063457aa9d9fd167cd2619e4343b158bded0b5240f97cf7524ff1be3f948428cc763ba81d1c7f16b86c4e32583b9d03d46c29b5c1d3b4614d14c5400cb02a089dde4978827018c3ba3cd1bac1fb4cf3aa93dd50aad4dfc9d714e99782cf11c9d2a8eeaa42d3566c8973f72cc1fb3503f22cb16870996509a8d4f10a52e4d8311e5f814ab33aabf7e4a1ec505fcf2114&tc=3");

        for (int i = 2; i <= 50; i++)
            pagetUrl.add("p" + i+"?aid=d216e1fa595cbcd7ce871909dde3e272f285bac29dbf6931656d1e1ecf969e1b52127a5129aca21b64a72bb1370e0f906063457aa9d9fd167cd2619e4343b158bded0b5240f97cf7524ff1be3f948428cc763ba81d1c7f16b86c4e32583b9d03d46c29b5c1d3b4614d14c5400cb02a089dde4978827018c3ba3cd1bac1fb4cf3aa93dd50aad4dfc9d714e99782cf11c9d2a8eeaa42d3566c8973f72cc1fb3503f22cb16870996509a8d4f10a52e4d8311e5f814ab33aabf7e4a1ec505fcf2114&tc=3");

        ArrayList<String> allIDByCategory = new ArrayList<String>();
        GetDetalUrl getIDByCategory = new GetDetalUrl();
        String URL = "";

        //第二页：http://www.dianping.com/search/category/6/80/p2
        for (int i = 0; i < pagetUrl.size(); i++) {
            URL = categoryUrl + pagetUrl.get(i);
            System.out.println(URL);

            //获取50页的html，获取其中商家ID；
            String content = "";
            if ((content = hc.httpConn(URL)) != null)
                getIDByCategory.get(content, allIDByCategory);
        }

        return allIDByCategory;
    }

    public static void main(String[] args) throws Exception {
        CaptureHtml ch = new CaptureHtml();

        ArrayList<String> allCategory = new ArrayList<String>();
        allCategory.add("http://www.dianping.com/search/category/6/80/g237");

        allCategory.add("http://www.dianping.com/search/category/6/80/g181");
        allCategory.add("http://www.dianping.com/search/category/6/80/g26466");
        allCategory.add("http://www.dianping.com/search/category/6/80/g26465");
        allCategory.add("http://www.dianping.com/search/category/6/80/g6823");
        allCategory.add("http://www.dianping.com/search/category/6/80/g979");
        allCategory.add("http://www.dianping.com/search/category/6/80/g3082");

        ArrayList<String> ID = null;
        for (int i = 0; i < allCategory.size(); i++) {
            ID = new GetURL().getStoreIdByCategory(allCategory.get(i));
            for (int j = 0; j < ID.size(); j++)
                ch.getInformation("http://www.dianping.com/shop/" + ID.get(j));
        }
    }
}
