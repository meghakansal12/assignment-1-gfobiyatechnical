/*
 *   @Author: Rakesh Yadav
 */

import java.io.Serializable;

@SuppressWarnings("serial")
public class ContactsList implements Serializable {
    private Person head;
    private int contactLintLength;

    //initialize contactsList
    public ContactsList() {
        head = null;
        contactLintLength = 0;
    }

    // method to add firstName,lastName,contactNumber,anotherContactNumber,emailAddress and anotherEmailAddress in String form
    public void addInContactsLast(String firstName, String lastName, String contactNumber, String anotherContactNumber, String emailAddress) {
        Person current = head;
        Person previous = null;
        Person newNode = new Person();

        // set the data
        newNode.setFirstName(firstName);
        newNode.setLastName(lastName);
        newNode.setContactNumber(contactNumber);
        newNode.setAnotherContactNumber(anotherContactNumber);
        newNode.setE_mailAddress(emailAddress);

        if (isEmpty()) {
            head = newNode;
            contactLintLength++;
        } else {

            // compare First Name of entries for priority by first letter in First Name
            for (int i = 0; i < contactLintLength; i++) {
                String[] ourNames1 = current.getFirstName().split(" ");
                String[] ourNames2 = newNode.getFirstName().split(" ");

                int result = ourNames1[ourNames1.length - 1].compareToIgnoreCase(ourNames2[ourNames2.length - 1]);

                // if First Name entered goes before what's stored in the head, new entry becomes the head
                if (result > 0) {
                    if (previous == null) {
                        newNode.setNext(current);
                        head = newNode;
                        contactLintLength++;
                        break;
                    }
                    /**
                     *   this section cycles through the "body" of the nodes if
                     *   it's not the tail
                     **/
                    previous.setNext(newNode);
                    newNode.setNext(current);
                    contactLintLength++;
                    break;
                } else
                /**
                 *  if the entry replaces node at the tail,
                 *  this entry becomes the new tail
                 **/ {
                    if (current.getNext() == null) {
                        current.setNext(newNode);
                        newNode.setNext(null);
                        contactLintLength++;
                        break;
                    }
                    previous = current;
                    current = current.getNext();
                }
            }
        }
    }

    /*
     *  method to use if the linked list is empty
     */
    public boolean isEmpty() {
        return (contactLintLength == 0);
    }

    /*
     *  method to printContactsList
     */
    public void printContactsList() {
        Person tempNode = head;
        if (head == null) {
            System.out.println("***************************************\n|    OPPss!!  The list is empty!      |\n***************************************\n");
        } else {
            System.out.println("-------- * -------- * -------- * --------");
            for (int i = 0; i < contactLintLength; i++) {
                System.out.print("Index = " + (i + 1) + " ");
                System.out.println(tempNode);
                tempNode = tempNode.getNext();
            }
            System.out.println("-------- * -------- * -------- * --------");
        }
        System.out.println();
    }

    /*
     *  method used to search through the names in the string
     */
    public void searchByFirstName(String firstName) {
        Person current = head;
        boolean empty = true;

        if (isEmpty()) {
            System.out.println("***************************************\n|    OPPss!!  The list is empty!      |\n***************************************\n");
        } else {
            for (int i = 0; i < contactLintLength; i++) {
                if (current.getFirstName().toLowerCase().contains(firstName.toLowerCase())) {
                    System.out.print("Index = " + (i + 1) + " ");
                    System.out.println(current + "\n");
                    empty = false;
                }
                // go to the next node
                current = current.getNext();
            }
            /*
             * if condition for contact doesn't found
             */
            if (empty) {
                System.out.println("NO RESULTS FOUND!");
            }
        }

    }

    /*
     *   method to delete contact by using index concept
     */
    public void deleteFromContactsList(int index) {
        Person current = head;
        Person previous = null;
        boolean empty = true;

        if (isEmpty()) {
            System.out.println("***************************************\n|    OPPss!!  The list is empty!      |\n***************************************\n");
        } else {
            for (int i = 0; i < contactLintLength; i++) {
                if ((index - 1) == 1) {
                    if (previous == null) {
                        head = head.getNext();
                        contactLintLength--;
                        empty = false;
                        break;
                    } else if (current.getNext() == null) {
                        previous.setNext(null);
                        contactLintLength--;
                        empty = false;
                        break;
                    } else {
                        previous.setNext(current.getNext());
                        contactLintLength--;
                        empty = false;
                        break;
                    }
                } else {
                    previous = current;
                    current = current.getNext();
                }
            }
            if (empty) {
                System.out.println("Contact Not Found");
            } else {
                //notification of deletion
                System.out.println("contact deleted from list!");
            }
        }
    }
}