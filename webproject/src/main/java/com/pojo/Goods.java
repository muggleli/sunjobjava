package com.pojo;

public class Goods {
    private int goodsid ;
    private String goodsname ;
    private String goodspic ;
    private int goodscount ;
    private double goodsprice;

    public int getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(int goodsid) {
        this.goodsid = goodsid;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getGoodspic() {
        return goodspic;
    }

    public void setGoodspic(String goodspic) {
        this.goodspic = goodspic;
    }

    public int getGoodscount() {
        return goodscount;
    }

    public void setGoodscount(int goodscount) {
        this.goodscount = goodscount;
    }

    public double getGoodsprice() {
        return goodsprice;
    }

    public void setGoodsprice(double goodsprice) {
        this.goodsprice = goodsprice;
    }
    @Override
    public int hashCode() {
        return goodsid;
    }

    //从判断地址   改为判断两个商品的编号
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Goods){// obj  是不是Goods类对象
            if(((Goods)obj).goodsid==this.goodsid){
                return true ;
            }
        }
        return false ;
    }
}
