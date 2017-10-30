package daZhongDianPing.getFromDaZhong;

import daZhongDianPing.tools.HttpConn;

import java.util.ArrayList;

/**
 * Created by shiqun on 2015/11/26.
 */
public class GetStoreByCategory {

    public int getIDByCategory(String daLeiUrl,ArrayList<String> allCategoryName,int categoryID) throws Exception {

        //每一个分类一组存入数组；
        ArrayList<ArrayList<String>> allIDByCategory = new  ArrayList<ArrayList<String>>();

        //所有小类的URL；
        ArrayList<String> allCategoryUrl = new ArrayList<String>();

        //根据大类地址找到每一小类；
        //大类：http://www.dianping.com/search/category/6/30/o3
        //小类：http://www.dianping.com/search/category/6/30/g135o3
        HttpConn hc = new HttpConn();
        allCategoryUrl = new GetAllCategory().getAllCategory(hc.httpConn(daLeiUrl));

        //每一个线程去找一个小类50页商家ID存到数据库；
        GetPageIDThread getPageIDThread;
        for (int i = 0; i < allCategoryUrl.size(); i++) {

            //每一个小分类50页的商家插入；
            System.out.println("HttpConn:strURL"+daLeiUrl+"/"+allCategoryUrl.get(i));
            getPageIDThread = new GetPageIDThread(daLeiUrl+"/"+allCategoryUrl.get(i),allCategoryName.get(i),categoryID);

            new Thread(getPageIDThread,""+i).start();

        }

        return 1;
    }

}
