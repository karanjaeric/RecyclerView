package com.thepensionapp.recyclerview;

/**
 * Created by ekaranja on 3/31/18.
 */

public class ListItem {

    private String head;
    private String desc;

    public ListItem(String head, String desc) {
        this.head = head;
        this.desc = desc;
    }


    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
