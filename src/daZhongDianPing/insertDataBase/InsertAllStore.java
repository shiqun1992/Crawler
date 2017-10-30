package daZhongDianPing.insertDataBase;

import daZhongDianPing.beans.Store;
import daZhongDianPing.dataBase.DBconnection;
import daZhongDianPing.dataBase.InsertStore;
import daZhongDianPing.tools.Dazhong;
import daZhongDianPing.tools.HttpConn;

import java.sql.ResultSet;

/**
 * Created by shiqun on 2015/11/29.
 */
public class InsertAllStore {

    public static void main(String[] args) throws Exception
    {
        String IDSql = "SELECT shopID,categoryID,secondcategory FROM shop " +
                "where shopID not in(select shopID from shopdetal) " +
                "and shopID in (select shopID from shop where categoryID=1);";
        DBconnection db = DBconnection.getDbConnection();

        ResultSet rs = db.executeQuery(IDSql);

        HttpConn ht = new HttpConn();
        Dazhong getStore = new Dazhong();
        InsertStore insertStore = new InsertStore();
        Store store;
        while(rs.next())
        {

            String ID = rs.getString(1);
            System.out.println(ID);

            String URL = "http://www.dianping.com/shop/"+ID;
            String html = ht.httpConn(URL);

            store = getStore.analysisDazhong(ID,html);

            String menuHtml = ht.httpConn(URL+"/menu");
            String menu = getStore.getMenu(menuHtml);
            store.setMenu(menu);

            insertStore.insert(store);
        }
    }
}
