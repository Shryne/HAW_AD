package adt;

import adt.interfaces.AdtList;

/**
 * This implementation uses references from one entry to
 * another. I used the null object pattern in this case
 * and I find it highly usable here.
 */
public class AdtListSingleConc implements AdtList {
    // ##################################################
    // variables
    // ##################################################
    private static final int START_POS = 1;

    private Entry head = new EmptyEntry(START_POS);
    private int length;

    // ##################################################
    // methods
    // ##################################################
    private AdtListSingleConc() {}

    public static AdtList valueOf() {
        return new AdtListSingleConc();
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public void insert(int pos, int elem) {
        if (pos == START_POS) {
            head = new FilledEntry(elem, head.pos(head.pos() + 1), pos);
            length++;
        } else {
            if (head.insert(pos, elem)) {
                length++;
            }
        }
    }

    @Override
    public void delete(int pos) {
        if (pos == START_POS) {
            if (!(head instanceof EmptyEntry)) {
                length--;
                head = head.next();
                head.pos(head.pos() - 1);
            }
        } else {
            if (head.delete(pos)) {
                length--;
            }
        }


    }

    @Override
    public int find(int elem) {
        return head.find(elem);
    }

    @Override
    public int retrieve(int pos) {
        return head.retrieve(pos);
    }

    @Override
    public void concat(AdtList list) {
        int otherLength = list.length();

        for (int i = START_POS; i <= otherLength; i++) {
            insert(START_POS + length, list.retrieve(i));
        }
    }



    // ##################################################
    // bonus
    // ##################################################
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof AdtList))
            return false;

        AdtList obj = (AdtList) o;
        if (length != obj.length())
            return false;

        for (int i = 1; i <= length(); i++) {
            if (retrieve(i) != obj.retrieve(i)) {
                System.out.println("HEY" + i + " - " + retrieve(i) + " - " + obj.retrieve(i));
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 7;
        for (Entry e = head; !(e instanceof EmptyEntry); e = e.next()) {
            result = result * Integer.hashCode(e.elem());
        }
        return result;
    }

    /*
     * AdtList with the elements: 1, 2, 3, 4 ->
     * AdtList[1, 2, 3, 4]
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getClass().getSimpleName()
                + '[' + head + ']' + ": " + length;
    }
    // ##################################################
    // private helper
    // ##################################################
    private interface Entry {
        boolean insert(int pos, int elem);
        int retrieve(int pos);
        int find(int elem);
        boolean delete(int pos);

        int elem();
        Entry next();
        int pos();
        Entry pos(int newPos);
    }

    private class FilledEntry implements Entry {
        private final int elem;
        private int pos;
        private Entry next;

        public FilledEntry(int data, Entry next, int pos) {
            this.elem = data;
            this.next = next;
            this.pos = pos;
        }

        @Override
        public boolean insert(int pos, int elem) {
            if (next.pos() == pos) {
                next = new FilledEntry(elem, next.pos(next.pos() + 1), pos);
                return true;
            } else {
                return next.insert(pos, elem);
            }
        }

        @Override
        public int retrieve(int pos) {
            return this.pos == pos ? elem : next.retrieve(pos);
        }

        @Override
        public int find(int elem) {
            return this.elem == elem ? pos : next.find(elem);
        }

        @Override
        public boolean delete(int pos) {
            if (next.pos() == pos) {
                if (!(next instanceof EmptyEntry)) {
                    next = next.next();
                    next.pos(next.pos() - 1);

                    return true;
                }
                return false;
            } else {
                return next.delete(pos);
            }
        }

        @Override
        public int elem() {
            return elem;
        }

        @Override
        public Entry next() {
            return next;
        }

        @Override
        public int pos() {
            return pos;
        }

        @Override
        public Entry pos(int pos) {
            this.pos = pos;
            next.pos(pos + 1);
            return this;
        }

        @Override
        public String toString() {
            return elem + ": " + pos + (next instanceof EmptyEntry ? next : "," + next).toString();
        }
    }

    private class EmptyEntry implements Entry {
        private int pos;

        public EmptyEntry(int pos) {
            this.pos = pos;
        }

        @Override
        public boolean insert(int pos, int elem) {
            return false;
        }

        @Override
        public int retrieve(int pos) {
            return 0;
        }

        @Override
        public int find(int elem) {
            return 0;
        }

        @Override
        public boolean delete(int pos) {
            return false;
        }

        @Override
        public int elem() {
            return 0;
        }

        @Override
        public Entry next() {
            return new EmptyEntry(pos + 1);
        }

        @Override
        public int pos() {
            return pos;
        }

        @Override
        public Entry pos(int pos) {
            this.pos = pos;
            return this;
        }

        @Override
        public String toString() {
            return "";
        }
    }
}
