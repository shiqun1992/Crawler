package daZhongDianPing.getFromDaZhong;

import daZhongDianPing.tools.HttpConn;
import daZhongDianPing.dataBase.DBconnection;

import java.util.ArrayList;

/**
 * Created by shiqun on 2015/11/28.
 */
//获取每个小类中50页商家ID并存入数据库；
public class GetPageIDThread implements  Runnable{

    String xiaoLeiUrl;
    String CategoryName;
    int categoryID;

    public GetPageIDThread(String xiaoLeiUrl,String CategoryName,int categoryID)
    {
        this.CategoryName = CategoryName;
        this.xiaoLeiUrl =xiaoLeiUrl;
        this.categoryID =categoryID;
    }

    public void insertStore()
    {
        HttpConn hc = new HttpConn();

        //小类每个分类的每一个页的URL
        ArrayList<String> pagetUrl = new ArrayList<String>();
        pagetUrl.add("");
        for (int i = 2; i <= 50; i++)
            pagetUrl.add("p" + i);

        ArrayList<String> allIDByCategory = new ArrayList<String>();
        GetDetalUrl getIDByCategory = new GetDetalUrl();
        String URL = "";

        //第二页：http://www.dianping.com/search/category/6/80/p2
        for (int i = 0; i < pagetUrl.size(); i++) {
            URL = xiaoLeiUrl + pagetUrl.get(i);
            System.out.println(URL);

            //获取50页的html，获取其中商家ID；
            String content = "";
            try {
                if ((content = hc.httpConn(URL)) != null)
                    getIDByCategory.get(content, allIDByCategory);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        DBconnection db = DBconnection.getDbConnection();
        for(int i=0;i<allIDByCategory.size();i++) {
            String insertSql = "INSERT INTO shop (shopID,categoryID,secondCategory) values " +
                    "(" + allIDByCategory.get(i) + "," + categoryID + ",'" + CategoryName + "')";

            try {
                db.executeUpdate(insertSql);
            } catch (Exception e) {
                System.out.println("插入失败！！！！！！");
            }


        }
    }

    public void run()
    {
        this.insertStore();
    }

    public static void main(String[] args) throws Exception
    {
        String[] URL = new String[20];
        URL[0] = "http://www.dianping.com/search/category/6/80/g181r75";
        URL[1] = "http://www.dianping.com/search/category/6/80/g181r72";
        URL[2] = "http://www.dianping.com/search/category/6/80/g181r74";
        URL[3] = "http://www.dianping.com/search/category/6/80/g181r76";
        URL[4] = "http://www.dianping.com/search/category/6/80/g181r73";
        URL[5] = "http://www.dianping.com/search/category/6/80/g181r79";
        URL[6] = "http://www.dianping.com/search/category/6/80/g181r77";
        URL[7] = "http://www.dianping.com/search/category/6/80/g181c3599";
        URL[8] = "http://www.dianping.com/search/category/6/80/g181c417";
        URL[9] = "http://www.dianping.com/search/category/6/80/g181c416";
        URL[10] = "http://www.dianping.com/search/category/6/80/g181c419";

        URL[9] = "http://www.dianping.com/search/category/6/80/g181c418";
        URL[10] = "http://www.dianping.com/search/category/6/80/g181c420";

        for(int i=0;i<11;i++) {

            String xiaoLeiUrl = URL[i];
            String CategoryName = "医院";
            int categoryID = 1;
            new GetPageIDThread(xiaoLeiUrl, CategoryName, categoryID).insertStore();
        }
    }

}
