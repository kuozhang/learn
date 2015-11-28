
package learn.dsaa.misc;

import learn.dsaa.list.SingleList;
import learn.dsaa.list.SingleListNode;

/**
 * @author Kuo Zhang
 */
public class BeautyOfProgramming
{

    // Chapter 3.4, 从无头单链表中删除节点
    // the previous node cann't be known, so there is no way to delete the current node directly
    // we can delete the next node, and put its value in the current node
    public <T> void C3Dot4( SingleListNode<T> node )
    {
        node.data = node.next.data;
        node.next = node.next.next;
    }

    // Chapter 3.6, check if two lists are crossed
    // method 4: check if the tail nodes are the same
    public <T> boolean C3Dot6( SingleList<T> list1, SingleList<T> list2 )
    {
        SingleListNode<T> tail1 = list1.getTail();
        SingleListNode<T> tail2 = list1.getTail();

        return tail1 == tail2;
    }

}
