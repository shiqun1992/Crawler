package daZhongDianPing.dataBase;

import daZhongDianPing.beans.Store;

/**
 * Created by shiqun on 2015/11/29.
 */
public class InsertStore {

    public int insert(Store store)
    {
        DBconnection db = DBconnection.getDbConnection();
        String InsertStr = "insert into shopdetal(shopID,menu,shopName,province,city,region,address,tel,anotherName,shopHours,introduce,shopTag,price,delicious,environment,service,POI,shopClass) " +
                "VALUES ("+store.getShopID()+",'"
                +store.getMenu()+"','"
                +store.getShopName()+"','"
                +store.getProvince()+"','"
                +store.getCity()+"','"
                +store.getRegion()+"','"
                +store.getAddress()+"','"
                +store.getTel()+"','"
                +store.getAnotherName()+"','"
                +store.getShopHours()+"','"
                +store.getIntroduce()+"','"
                +store.getShopTag()+"','"
                +store.getPrice()+"','"
                +store.getDelicious()+"','"
                +store.getEnvironment()+"','"
                +store.getService()+"','"
                +store.getPOI()+"','"
                +store.getShopClass()+"')";
        try {
            db.executeUpdate(InsertStr);
        }catch(Exception e) {
            System.out.println(e.toString());
        }

        return 1;
    }

}
