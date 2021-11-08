package Marketplace;

import java.util.ArrayList;
import java.util.Stack;

public class Searcher {
    private Stack<Filter> filters;
    private Sorter sorter;

    public void execute() {
        ArrayList<Item> items = Database.GetLst();
        while (filters.size() > 0){
            Filter filter = filters.pop();
            items = filter.apply(items);
        }
        sorter.sort(items);
        //NEED TO FIGURE OUT HOW TO PRESENT INSTEAD
    }

    public void addFilter(Filter filter){
        filters.push(filter);
    }

    public void addSorter(Sorter sorter){
        this.sorter = sorter;
    }

}