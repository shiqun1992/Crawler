package daZhongDianPing.getFromDaZhong;

import java.util.ArrayList;

/**
 * Created by shiqun on 2015/11/27.
 */
public class GetAllByCategory {


    public static void main(String[] args) throws Exception {

        //手动设置；
        String daLeiUrl = "http://www.dianping.com/search/category/6/80";
        int categoryID = 1;


        GetStoreByCategory getStoreByCategory
                = new GetStoreByCategory();

        //需要手动设置，获取每一个打类下小类的名称；
        ArrayList<String> allCategoryName
                = new ArrayList<String>();

        allCategoryName.add("商圈");
        allCategoryName.add("房屋地产");


        //根据每一个大类找到子类，例如KTV等；
        //根据每一个小类，找到每个小类中每页的商家ID
        //找到的同时就插入到数据库
        getStoreByCategory.getIDByCategory(daLeiUrl,allCategoryName,categoryID);
    }
}


