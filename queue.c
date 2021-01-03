/*
 * Developed by R. E. Bryant, 2017
 * Extended to store strings, 2018
 */

/*
 * This program implements a queue supporting both FIFO and LIFO
 * operations.
 *
 * It uses a singly-linked list to represent the set of queue elements
 */

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include "harness.h"
#include "queue.h"

/*
  Create empty queue.
  Return NULL if could not allocate space.
*/
queue_t *q_new()
{
    queue_t *q =  malloc(sizeof(queue_t));
    if(q == NULL)
    {
        return NULL;
    }
    q->head = NULL;
    q->tail = NULL;
    q->numNodes = 0;
    return q;
}

/* Free all storage used by queue */
void q_free(queue_t *q)
{
    //list_ele_t *curr;
    if(q==NULL) return;

    list_ele_t *tempH = q->head;

    while(tempH != NULL)  // traverse entire linked list
    {
        list_ele_t* next = tempH -> next;
        //curr = tempH->next;     // point ahead to next curr
        free(tempH->value);
        free(tempH);            // free current node
        tempH = next;           // move the freeing pointer to the next
    }
    free(q);        // free the queue

    /* How about freeing the list elements and the strings? */
    /* Free queue structure */
}

/*
  Attempt to insert element at head of queue.
  Return true if successful.
  Return false if q is NULL or could not allocate space.
  Argument s points to the string to be stored.
  The function must explicitly allocate space and copy the string into it.
 */
bool q_insert_head(queue_t *q, char *s)
{
    list_ele_t *newh;
    /* What should you do if the q is NULL? */
    if(q == NULL)  // if the previous linked list does not exist, make a new one
    {
        return false;
    }

    newh = malloc(sizeof(list_ele_t));
    if(newh == NULL)
    {
        return false;
    }

    newh->value = malloc(strlen(s)+1);
    if(newh->value == NULL)
    {
        free(newh);
        return false;
    }

    strcpy(newh->value, s);

    if(q->head == NULL)  //  if this is the first Node
    {
        q->head = q->tail = newh;
        newh->next = NULL;
        q->numNodes++;
        return true;
    }
    else  // every other case
    {
        newh->next = q->head;
        q->head = newh;
        q->numNodes++;
        return true;
    }
    /* Don't forget to allocate space for the string and copy it */
    /* What if either call to malloc returns NULL? */
}


/*
  Attempt to insert element at tail of queue.
  Return true if successful.
  Return false if q is NULL or could not allocate space.
  Argument s points to the string to be stored.
  The function must explicitly allocate space and copy the string into it.
 */
bool q_insert_tail(queue_t *q, char *s)
{
    list_ele_t *tempTail;
    /* What should you do if the q is NULL? */
    if(q == NULL)  // if the previous linked list does not exist, make a new one
    {
        return false;
    }

    tempTail = malloc(sizeof(list_ele_t));
    if(tempTail == NULL)
    {
        return false;
    }

    tempTail->value = malloc(strlen(s)+1);
    if(tempTail->value == NULL)
    {
        free(tempTail);
        return false;
    }

    strcpy(tempTail->value, s);

    if(q->head == NULL)  //  if this is the first Node
    {
        tempTail->next = NULL;

        q->tail->next = q->tail;
        q->head = tempTail;
        q->tail = tempTail;
        q->numNodes++;
        return true;
    }
    else  // every other case
    {
        tempTail->next = NULL;
        q->tail->next = tempTail;
        q->tail = tempTail;
        q->numNodes++;
        return true;
    }
}

/*
  Attempt to remove element from head of queue.
  Return true if successful.
  Return false if queue is NULL or empty.
  If sp is non-NULL and an element is removed, copy the removed string to *sp
  (up to a maximum of bufsize-1 characters, plus a null terminator.)
  The space used by the list element and the string should be freed.
*/
bool q_remove_head(queue_t *q, char *sp, size_t bufsize)
{
    /* You need to fix up this code. */
    list_ele_t *tHead= q->head;
    if(q == NULL)   // NULL list returns false
    {
        return false;
    }
    if(q->head == NULL)  // if the list is already empty, return false
    {
        return false;
    }
    if(sp == NULL)
    {
        return false;
    }
    if(sp != NULL && tHead -> value != NULL)
    {
        strncpy(sp, tHead->value, bufsize-1);
        sp[bufsize-1] = '\0';
    }
    if(tHead->value != NULL)
    {
        free(tHead->value);
    }
    q -> head = tHead -> next;
    free(tHead);
    q->numNodes--;
    return true;

}

/*
  Return number of elements in queue.
  Return 0 if q is NULL or empty
 */
int q_size(queue_t *q)
{
    if(q == NULL)
    {
        return 0;
    }
    return q->numNodes;
}

/*
  Reverse elements in queue
  No effect if q is NULL or empty
  This function should not allocate or free any list elements
  (e.g., by calling q_insert_head, q_insert_tail, or q_remove_head).
  It should rearrange the existing ones.
 */
void q_reverse(queue_t *q)
{
    /* You need to write the code for this function */
    if(q == NULL)
    {
        return;
    }
    if(q->numNodes == 0)
    {
        return;
    }

    list_ele_t *oldHead = q->head;
    //list_ele_t *oldTail = q->tail;

    list_ele_t *curr = q->head;
    list_ele_t *curr_next = NULL;
    list_ele_t *prev = NULL;

    while(curr != NULL)
    {
        curr_next = curr->next;
        curr->next = prev;
        prev = curr;
        curr = curr_next;
    }
    q->head = prev;
    q->tail = oldHead;
}
