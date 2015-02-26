
package learn.dsaa.skiplist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Kuo Zhang
 *
 * 跳跃链表
 */
public class SkipList
{

    List<SkipListItem> items = new LinkedList<SkipListItem>();

    int highestLevel;

    SkipListItem head;
    SkipListItem tail;

    class SkipListItem
    {

        int value;
        ArrayList<SkipListItem> links = new ArrayList<SkipListItem>( 5 ); // Set the highest level is 5;

        SkipListItem( int value, ArrayList<SkipListItem> links )
        {
            this.value = value;
            this.links = links;
        }
    }

    public SkipList()
    {
        head.value = Integer.MAX_VALUE;

    }
}
