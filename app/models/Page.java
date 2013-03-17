package models;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: reyoung
 * Date: 3/15/13
 * Time: 1:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class Page<T> {
    public List<T> Data;
    public int     Index;
    public int     Length;
    public long    Count;

    public Page(List<T> data, int index, int length, long count) {
        Data = data;
        Index = index;
        Length = length;
        Count = count;
    }

    public boolean hasPrev(){
        return Index>1;
    }
    public boolean hasNext(){
        return Index < Count/Length;
    }
    public int prev(){
        return Index -1;
    }
    public int next(){
        return Index+1;
    }
}
