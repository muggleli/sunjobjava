package com.dao;

import com.pojo.Goods;
import com.util.DBUtil;

import java.util.List;

public class GoodsDAO {
    //查找总条数
    public int getCount() {
        String sql = "select count(*) from goods";
        int count = (int) DBUtil.uniqueQuery(sql);
        return count;
    }

    //分页查找数据
    public List<Goods> fenye(int page, int size) {
        String sql = "select goodsid , goodsname , goodspic , goodscount , goodsprice from goods limit ?, ?";
        List<Goods> list = DBUtil.query(sql, Goods.class, (page - 1) * size, size);
        return list;
    }


    public Goods findbyid(String goodsid) {
        String sql = "select goodsid , goodsname , goodspic , goodscount , goodsprice from goods where goodsid = ?";
        List<Goods> list = DBUtil.query(sql, Goods.class, goodsid);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
