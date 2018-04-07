package ua.dp.mign.trees.twoThree;

////////////////////////////////////////////////////////////////
class DataItem {
    public long dData;          // one data item

    //--------------------------------------------------------------
    public DataItem(long dd)    // constructor
    {
        dData = dd;
    }

    //--------------------------------------------------------------
    public void displayItem()   // display item, format "/27"
    {
        System.out.print("/" + dData);
    }

    public long getData() {
        return dData;
    }
    //--------------------------------------------------------------
}  // end class DataItem
