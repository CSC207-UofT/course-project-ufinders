package Marketplace;

import Marketplace.Items.types.Item;

import java.util.ArrayList;
import java.util.Stack;

public class Searcher {
    private final Stack<Filter> filters;
    private Sorter sorter;

    /**
     * executes a search using the filters specified in filters and the sorter, if it has been given a value.
     *
     */
    public Searcher(){
        this.filters = new Stack<>();
        this.sorter = null;

    }

    public ArrayList<Item> execute() {
        ArrayList<Item> items = Database.GetLst();
        while (this.filters.size() > 0){
            Filter filter = this.filters.pop();
            items = filter.apply(items);
        }
        if (!(this.sorter == null)){
            this.sorter.sort(items);
        }
       return items;
    }

    /**
     * adds a new filter to filters
     * @param filter   the filter to be added
     */
    public void addFilter(Filter filter){
        this.filters.push(filter);
    }

    /**
     * changes this searcher's sorter
     * @param sorter the sorter to be used
     */
    public void addSorter(Sorter sorter){
        this.sorter = sorter;
    }

}